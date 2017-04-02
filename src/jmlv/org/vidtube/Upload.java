package jmlv.org.vidtube;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import jmlv.org.jbuilder.JBuilder;
import jmlv.org.jdbconnection.JDBConnection;

/**
 * Servlet implementation class FileServlet
 */
@MultipartConfig
@WebServlet("/upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Part file = request.getPart("file");
		System.out.println(file);
		InputStream filecontent = file.getInputStream();
		OutputStream os = null;
		String url = null;
		try {
			HttpSession session=request.getSession(); 
			String path = "/Users/joselopez/Documents/workspace/Vidtube_server/WebContent/Media/"+session.getAttribute("username");
			System.out.println(path);
			url = path + "/" + this.getFileName(file);
			System.out.println(url);
			os = new FileOutputStream(url);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (filecontent != null) {
                filecontent.close();
            }
            if (os != null) {
                os.close();
            }
            java.util.Date parsed;
	        java.sql.Date sql = null;
            Calendar cal = Calendar.getInstance();
			String time = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			try {
				parsed = new SimpleDateFormat("yyyy-MM-dd").parse(time);
				sql = new java.sql.Date(parsed.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String category = request.getParameter("category");
            
            JDBConnection w = new JDBConnection(); 
			w.setConnection("localhost", "5432", "vidtube", "postgres", "masterkey");
			Object [] params = {name,0,0,sql,url,description,Integer.parseInt(category)};
			w.executeQueryX("INSERT INTO media (name_media,likes_media,views_media,created_at,url_media,description_media,category_media) "
					+ "VALUES(?,?,?,?,?,?,?) RETURNING id_media",params);
			PrintWriter out = response.getWriter();
			JBuilder json2 = new JBuilder();
			json2.add("p",w.getTable());
			System.out.println("post:"+name);
			String [] validate = json2.getJBuilder().split("\"");
			HttpSession session=request.getSession(); 
			String id_user = ""+session.getAttribute("id");
			w.setConnection("localhost", "5432", "vidtube", "postgres", "masterkey");
			System.out.println(json2.getJBuilder());
			Object [] x = {Integer.parseInt(id_user),Integer.parseInt(validate[5])};
			w.execute("INSERT INTO user_media (id_user,id_media) VALUES (?,?)",x);
			out.print("1");
			w.close();
        }
		
	}
	
	//Esta funcion permite obtener el nombre del archivo
	private String getFileName(Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}

}
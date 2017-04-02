package jmlv.org.vidtube;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jmlv.org.jbuilder.JBuilder;
import jmlv.org.jdbconnection.JDBConnection;

/**
 * Servlet implementation class Comments
 */
@WebServlet("/comments")
public class Comments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String n = request.getParameter("id");  
		JDBConnection w = new JDBConnection();
		 w.setConnection("localhost", "5432", "vidtube", "postgres", "masterkey");
		w.executeQueryX("SELECT comment.created_at, comment.content, app_user.username FROM comment INNER JOIN app_user ON (comment.id_user = app_user.id_user) WHERE comment.id_media=?",Integer.parseInt(n));
		JBuilder json2 = new JBuilder();
		JBuilder json = new JBuilder();
		json2.add("e",w.getTable());
		w.executeQueryX("SELECT COUNT(*) FROM comment WHERE id_media=?",Integer.parseInt(n));
		json.add("count1",w.getTable());
		PrintWriter out = response.getWriter();
		System.out.println(json2.getJBuilder());
		if(json2.getJBuilder().equals("{ \"e\":}")){
			json.add("response1","2");
			out.print(json.getJBuilder());
			//out.print("{\"r\":[{\"response\":\"2\",\"likes\":"+"}]}");
		}else{
			json2.add("count1","2");
			out.print(json2.getJBuilder());
			//out.print("{\"r\":[{\"response\":\"1\"}]}");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String n = request.getParameter("id");  
		String con = request.getParameter("data");  
	    PrintWriter out = response.getWriter();
	    JDBConnection w = new JDBConnection();
	    HttpSession session = request.getSession();
	    String s = ""+session.getAttribute("id");
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
	    Object [] x = {Integer.parseInt(n),Integer.parseInt(s),con,sql};
	    w.setConnection("localhost", "5432", "vidtube", "postgres", "masterkey");
		w.execute("insert into comment (id_media,id_user,content,created_at) values(?,?,?,?)",x);
		out.print("{\"r\":\"done\"}");
		
		
			
	    
	}

}

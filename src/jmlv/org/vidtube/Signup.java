package jmlv.org.vidtube;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmlv.org.jbuilder.JBuilder;
import jmlv.org.jdbconnection.JDBConnection;


/**
 * Servlet implementation class Signup
 */
@WebServlet("/signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JDBConnection w = new JDBConnection();
		PrintWriter out = response.getWriter();
		String x = request.getParameter("username");
		w.setConnection("localhost", "5432", "vidtube", "postgres", "masterkey");
		w.executeQueryX("select * from app_user where username=?",x);
		JBuilder json2 = new JBuilder();
		json2.add("p",w.getTable());
		if(json2.getJBuilder().equals("{ \"p\":}")){
			out.print("{\"p\":[{\"username\":\"not found\"}]}");
		}else{
		out.print(json2.getJBuilder());
		w.close();
		System.out.println("get:"+json2.getJBuilder());}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String birth = request.getParameter("birth");
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date parsed;
	        java.sql.Date sql = null;
			try {
				parsed = format.parse(birth);
				sql = new java.sql.Date(parsed.getTime());
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JDBConnection w = new JDBConnection();
			w.setConnection("localhost", "5432", "vidtube", "postgres", "masterkey");
			String name = request.getParameter("nombre");
			String lastname = request.getParameter("lastname");
			String password = request.getParameter("password");
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			Object [] params = {name,lastname,sql,email,username,password};
			w.execute("insert into app_user (name_user,last_name,birthday_user,email_user,username,password_user) values(?,?,?,?,?,?)",params);
	        out = response.getWriter();
	        JBuilder json2 = new JBuilder();
			json2.add("name",name);
			json2.add("lastname",lastname);
			json2.add("username",username);
			json2.add("password",password);
			json2.add("email",email);
			json2.add("birthday",birth);
			System.out.println("post:"+name);
			out.print(json2.getJBuilder());
			File file = new File("/Users/joselopez/Documents/workspace/Vidtube_server/WebContent/Media/"+username);
	        if (!file.exists()) {
	            if (file.mkdir()) {
	                System.out.println("Directory is created!");
	            } else {
	                System.out.println("Failed to create directory!");
	            }
	        }

			w.close();
	}

}

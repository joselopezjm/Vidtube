package jmlv.org.vidtube;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jmlv.org.jbuilder.JBuilder;
import jmlv.org.jdbconnection.JDBConnection;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("done");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if(session!=null){
			out.print("{\"p\":[{\"name\":\""+session.getAttribute("name")+"\",\"email\":\""+session.getAttribute("email")+"\",\"result\":\"1\"}]}");
		}
		if(session==null){
			out.print("{\"p\":[{\"name\":\""+"\",\"result\":\"2\"}]}");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("login");
		JDBConnection w = new JDBConnection();
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		w.setConnection("localhost", "5432", "vidtube", "postgres", "masterkey");
		w.executeQueryX("select * from app_user where username=?",username);
		JBuilder json2 = new JBuilder();
		json2.add("p",w.getTable());

		if(json2.getJBuilder().equals("{ \"p\":}")){
			out.print("{\"p\":[{\"name\":\""+"\",\"result\":\"0\"}]}");
		}else{
		String [] validate = json2.getJBuilder().split("\"");
		System.out.println(json2.getJBuilder());
		System.out.println(request.getServletPath());
		if(username.equals(validate[25]) && password.equals(validate[29])){
			out.print("{\"p\":[{\"name\":\""+validate[9]+" "+validate[13]+"\",\"email\":\""+validate[21]+"\",\"result\":\"1\"}]}");
			HttpSession session = request.getSession();  
	        session.setAttribute("name",validate[9]+" "+validate[13]);  
	        session.setAttribute("id",validate[5]);
	        session.setAttribute("username", username);
	        session.setAttribute("email", validate[21]);
			w.close();
		    System.out.println("{\"p\":[{\"name\":\""+validate[9]+" "+validate[13]+"\",\"result\":\"1\"}]}");
		    File file = new File("/Users/joselopez/Documents/workspace/Vidtube_server/WebContent/Media/"+username);
	        if (!file.exists()) {
	            if (file.mkdir()) {
	                System.out.println("Directory is created!");
	            } else {
	                System.out.println("Failed to create directory!");
	            }
	        }
		 }
		else{
			out.print("{\"p\":[{\"name\":\""+"\",\"result\":\"0\"}]}");
		}
	}
	}

}

package jmlv.org.vidtube;

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
 * Servlet implementation class Likes
 */
@WebServlet("/likes")
public class Likes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Likes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String n = request.getParameter("id");  
	    PrintWriter out = response.getWriter();
	    JDBConnection w = new JDBConnection();
	    HttpSession session = request.getSession();
	    String f = request.getParameter("func");
	    int func = Integer.parseInt(f);
	    String s = ""+session.getAttribute("id");
	    Object [] x = {Integer.parseInt(n),Integer.parseInt(s)};
	    w.setConnection("localhost", "5432", "vidtube", "postgres", "masterkey");
	    if(func==0){
			w.executeQueryX("select * from likes_media WHERE id_media=? AND id_user=?",x);
			JBuilder json2 = new JBuilder();
			json2.add("p",w.getTable());
			w.executeQueryX("SELECT COUNT(*) FROM likes_media WHERE id_media=?",Integer.parseInt(n));
			JBuilder json = new JBuilder();
			json.add("count",w.getTable());
			System.out.println(json2.getJBuilder());
			if(json2.getJBuilder().equals("{ \"p\":}")){
				json.add("response","2");
				out.print(json.getJBuilder());
				//out.print("{\"r\":[{\"response\":\"2\",\"likes\":"+"}]}");
			}else{
				json.add("response","1");
				out.print(json.getJBuilder());
				//out.print("{\"r\":[{\"response\":\"1\"}]}");
			}
	    }
	    if(func==1){
	    	w.setConnection("localhost", "5432", "vidtube", "postgres", "masterkey");
			w.execute("insert into likes_media (id_media,id_user) values(?,?)",x);
			out.print("{\"r\":\"done\"}");
	    }
	    if(func==2){
	    	w.setConnection("localhost", "5432", "vidtube", "postgres", "masterkey");
			w.execute("DELETE FROM likes_media WHERE id_media=? AND id_user=?",x);
			out.print("{\"r\":\"done\"}");
	    }
	    
		w.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

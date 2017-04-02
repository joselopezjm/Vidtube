package jmlv.org.vidtube;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmlv.org.jdbconnection.JDBConnection;

/**
 * Servlet implementation class Views
 */
@WebServlet("/views")
public class Views extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Views() {
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
		w.setConnection("localhost", "5432", "vidtube", "postgres", "masterkey");
		w.execute("UPDATE media SET views_media=views_media+1 WHERE id_media=?",Integer.parseInt(n));
		w.close();
		out.print("{\"r\":\"done\"}");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

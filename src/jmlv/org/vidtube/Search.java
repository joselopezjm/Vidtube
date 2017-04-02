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
 * Servlet implementation class Search
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession(false);    
		PrintWriter out = response.getWriter();
        if(session!=null){  
        String search = request.getParameter("search");
		System.out.println("search "+search);
		JDBConnection w = new JDBConnection();
		w.setConnection("localhost", "5432", "vidtube", "postgres", "masterkey");
		w.executeQueryX("SELECT media.id_media, media.likes_media, media.views_media, media.created_at, media.url_media, media.name_media, media.category_media, media.description_media, app_user.username FROM user_media INNER JOIN media ON (user_media.id_media = media.id_media) INNER JOIN app_user ON (user_media.id_user = app_user.id_user) WHERE name_media LIKE LOWER(?)"
				,"%"+search+"%");
        out = response.getWriter();
        JBuilder json2 = new JBuilder();
        JBuilder json = new JBuilder();
        JBuilder json3 = new JBuilder(); 
        json.add("videos", w.getTable());
        System.out.println(json.getJBuilder());
		json2.add("response","1");
		JBuilder[] x = {json, json2};
		json3.add("r", x);
	
		if(json.getJBuilder().equals("{ \"videos\":}")){
			JBuilder json4 = new JBuilder();
			JBuilder json5 = new JBuilder();
			JBuilder json6 = new JBuilder();
			json5.add("response","2");
			json6.add("videos","none");
			JBuilder[] y = {json6, json5};
			json4.add("r",y);
			out.print(json4.getJBuilder());
			//out.print("{\"r\":[{\"response\":\"2\",\"likes\":"+"}]}");
		}else{
			out.print(json3.getJBuilder());
			System.out.println(json3.getJBuilder());
			System.out.println(session.getAttribute("name"));
			w.close();
		}
		}else{
			System.out.println("else");
			out.print("{\"r\":[{\"videos\":[{\"likes_media\":\"0\"}]},{\"response\":\"0\"}]}");
			
		}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

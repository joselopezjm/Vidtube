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
 * Servlet implementation class Set_videos
 */
@WebServlet("/set_videos")
public class Set_videos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Set_videos() {
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
        	JDBConnection w = new JDBConnection();
    		w.setConnection("localhost", "5432", "vidtube", "postgres", "masterkey");
        String param = request.getParameter("type");
    	if(param.equals("1")){
    		w.executeQuery("SELECT media.id_media, media.likes_media, media.views_media, media.created_at, media.url_media, media.name_media, media.category_media, media.description_media, app_user.username FROM user_media INNER JOIN media ON (user_media.id_media = media.id_media) INNER JOIN app_user ON (user_media.id_user = app_user.id_user)");
        }
        if(param.equals("2")){
        	w.executeQuery("SELECT media.id_media, media.likes_media, media.views_media, media.created_at, media.url_media, media.name_media, media.category_media, media.description_media, app_user.username FROM user_media INNER JOIN media ON (user_media.id_media = media.id_media) INNER JOIN app_user ON (user_media.id_user = app_user.id_user) ORDER BY views_media DESC LIMIT 10");
        }
        if(param.equals("3")){
        	w.executeQuery("SELECT media.id_media, media.likes_media, media.views_media, media.created_at, media.url_media, media.name_media, media.category_media, media.description_media, app_user.username FROM user_media INNER JOIN media ON (user_media.id_media = media.id_media) INNER JOIN app_user ON (user_media.id_user = app_user.id_user) WHERE category_media='1'");
        }
        if(param.equals("4")){
        	w.executeQuery("SELECT media.id_media, media.likes_media, media.views_media, media.created_at, media.url_media, media.name_media, media.category_media, media.description_media, app_user.username FROM user_media INNER JOIN media ON (user_media.id_media = media.id_media) INNER JOIN app_user ON (user_media.id_user = app_user.id_user) WHERE category_media='2'");
        }
        if(param.equals("5")){
        	w.executeQuery("SELECT media.id_media, media.likes_media, media.views_media, media.created_at, media.url_media, media.name_media, media.category_media, media.description_media, app_user.username FROM user_media INNER JOIN media ON (user_media.id_media = media.id_media) INNER JOIN app_user ON (user_media.id_user = app_user.id_user) WHERE category_media='3'");
        }
        if(param.equals("6")){
        	w.executeQuery("SELECT media.id_media, media.likes_media, media.views_media, media.created_at, media.url_media, media.name_media, media.category_media, media.description_media, app_user.username FROM user_media INNER JOIN media ON (user_media.id_media = media.id_media) INNER JOIN app_user ON (user_media.id_user = app_user.id_user) WHERE category_media='4'");
        }
        if(param.equals("7")){
        	w.executeQuery("SELECT media.id_media, media.likes_media, media.views_media, media.created_at, media.url_media, media.name_media, media.category_media, media.description_media, app_user.username FROM user_media INNER JOIN media ON (user_media.id_media = media.id_media) INNER JOIN app_user ON (user_media.id_user = app_user.id_user) WHERE category_media='5'");
        }
		out = response.getWriter();
        JBuilder json2 = new JBuilder();
        JBuilder json = new JBuilder();
        JBuilder json3 = new JBuilder();
        json.add("videos", w.getTable());
		json2.add("response","1");
		JBuilder[] x = {json, json2};
		json3.add("r", x);
		out.print(json3.getJBuilder());
		System.out.println(session.getAttribute("name"));
		w.close();
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

package jmlv.org.vidtube;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import jmlv.org.jbuilder.JBuilder;
import jmlv.org.jdbconnection.JDBConnection;

/**
 * Servlet implementation class Video
 */
@WebServlet("/video")
public class Video extends HttpServlet {
	private static final long serialVersionUID = 1L;	 
	private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24;
	Videos video = new Videos();
	BufferedInputStream input = null;
	BufferedOutputStream output=null;
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Video() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (input != null) {
			input = null;
		}
		
		if (output != null) {
			output = null;
		}
		 
		    System.out.println("start while");
		String dir = request.getParameter("id");
		String [] videoname = dir.split("/");
		String url = "/";
		for(int i =0; i<(videoname.length-1); i++){
			url+=videoname[i]+"/";
		}
		
		System.out.println(url);
		System.out.println();
		String videoFilename = URLDecoder.decode(videoname[videoname.length-1], "UTF-8");
	    String videoPath = url;
		Path video = Paths.get(videoPath, videoFilename);
		System.out.println(url+videoFilename);
		int length = (int) Files.size(video);
	    long start = 0L;
	    long end = length - 1;
	 
	    String range = request.getHeader("Range");
	    String rangeValues = range.substring(range.indexOf("=") + 1, range.length());
	    String[] rangeSplit = rangeValues.split("-");
	    System.out.println(range);
	   // String range0 = rangeSplit[0]+"L";
	   // String range1 = rangeSplit[0]+"L";
	    start = rangeSplit[0].isEmpty() ? start : Long.valueOf(rangeSplit[0]);
	    start = start < 0 ? 0 : start;
	    System.out.println("start: "+end);
	    end = rangeSplit.length == 1 ? end : Long.valueOf(rangeSplit[1]);
	    end = end > length - 1 ? length - 1 : end;   
	    System.out.println("end: "+end);
	   
	    response.reset();
	    response.setBufferSize(2048);
	    response.setDateHeader("Last-Modified", Files.getLastModifiedTime(video).toMillis());
	    response.setHeader("Content-Disposition", String.format("inline;filename=\"%s\"", videoFilename));
	    response.setHeader("Accept-Ranges", "bytes"); 	
	    response.setHeader("Content-Range", String.format("bytes %s-%s/%s", start, end, length));
	    response.setDateHeader("Expires", System.currentTimeMillis() + EXPIRE_TIME);
	    response.setContentType(Files.probeContentType(video));
	    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
	 
	    input = new BufferedInputStream(new FileInputStream (video.toString()), 2048);
	    output = new BufferedOutputStream(response.getOutputStream(), 2048);
	    
	    byte [] buffer = new byte[2048];
	    int count;	
	   
	    while ((count = input.read(buffer)) > 0) {
	    	output.write(buffer, 0, count);
        }
	    input.close();
		output.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		video = new Videos(url);
		System.out.println(video.getUrl());
		PrintWriter out = response.getWriter();
		out.print("{\"p\":[{\"username\":\"not found\"}]}"); 

		
	}
	
	

}
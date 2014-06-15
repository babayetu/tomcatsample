package tomcatsample;


import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
public class servlet extends HttpServlet {
    private String s;
    public void doGet (HttpServletRequest req, HttpServletResponse res)
          throws ServletException, IOException
    {
          PrintWriter out = res.getWriter();
          String s="Hello, javamxj Blog!";
          out.println(s);
          out.close();
     }
}
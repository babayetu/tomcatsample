<%
   String username=(String)session.getAttribute("userName");
   if(username==null || username.equals("")){
      request.getRequestDispatcher("/html/login_betpal.html").forward(request,response);
     return;
   } else {
      //out.println("Welcome back, " + username);
      request.getRequestDispatcher("/html/table_betpal.html").forward(request,response);
   }
%>

<%
   String username=(String)session.getAttribute("userName");
   if(username==null || username.equals("")){
      request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
     return;
   } else {
      //out.println("Welcome back, " + username);
      request.getRequestDispatcher("/jsp/user_main.jsp").forward(request,response);
   }
%>

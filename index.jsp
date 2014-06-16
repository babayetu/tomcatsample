<%
   String username=(String)session.getAttribute("userName");
   if(username==null || username.equals("")){
      request.getRequestDispatcher("/tomcatsample/jsp/login.jsp").forward(request,response);
     return;
   } else {
      out.println("Welcome back, " + username);
   }
%>
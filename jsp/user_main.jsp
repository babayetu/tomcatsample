<%@ page contentType="text/html; charset=UTF-8" language="java" import="login.RoleEnum" errorPage="" %>
<%  
    String myRole = "watcher";
    String userName = "NoName";
    
    if(request.getCookies() !=null)
    {
        for(Cookie cookie :request.getCookies()){
           if(cookie.getName().equals("role")) {
               String role = cookie.getValue();
               if (role.equals(RoleEnum.ADMIN.getMd5String())) {
                  myRole=RoleEnum.ADMIN.getRole();
               } else if (role.equals(RoleEnum.GAMBLER.getMd5String())) {
                  myRole=RoleEnum.GAMBLER.getRole();
               }
           } else if(cookie.getName().equals("loginName")) {
              userName = cookie.getValue();
           }          
        }
    }
    
    
%>
<% if(myRole.equals(RoleEnum.ADMIN.getRole())) {
           out.println("<p> 欢迎管理员, " + userName + " &nbsp;&nbsp; </p>");
           out.println("<br><br><a href=add_rate.jsp>管理赔率</a>");
           out.println("<br><br><a href=add_match.jsp>输入场次比分</a>");
           
           out.println("<br><br><a href=order_list.jsp>投注情况</a>");           
           out.println("<br><br><a href=user_account.jsp>用户一览</a>");          
           out.println("<br><br><a href=make_order.jsp>下注</a>");
} else if(myRole.equals(RoleEnum.GAMBLER.getRole())) {
           out.println("<p> 欢迎参赛者, " + userName + " &nbsp;&nbsp;</p>");
           out.println("<br><br><a href=order_list.jsp>投注情况</a>"); 
           out.println("<br><br><a href=user_account.jsp>用户一览</a>");          
           out.println("<br><br><a href=make_order.jsp>下注</a>");
} else if(myRole.equals(RoleEnum.WATCHER.getRole())) {
           out.println("<p> 欢迎群众, " + userName + " &nbsp;&nbsp;</p>");
           out.println("<br><br><a href=order_list.jsp>投注情况</a>"); 
           out.println("<br><br><a href=user_account.jsp>用户一览</a>");
}
%>

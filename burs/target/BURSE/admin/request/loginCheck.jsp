<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" import="ir.ut.burs.*, java.util.*"%>


<% 
	ServiceProvider sp = ServiceProvider.getServiceProvider();

	// if ( !session.getAttribute("username").equals(sp.getUsername()) )
	// 				response.sendRedirect("index.jsp");

	String username=request.getParameter("username"); 
	String password=request.getParameter("password"); 
	if((username.equals(sp.getUsername()) && password.equals(sp.getPassword()))) { 
		session.setAttribute("username",username); 
		response.sendRedirect("home-admin.jsp"); 
	} 

	else 
		response.sendRedirect("loginError.jsp");
%>


<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" import="ir.ut.burs.*, java.util.*"%>
<jsp:include page="header-admin.jsp" />

<div class="row">
	<jsp:include page="menu-admin.jsp" /> 

	<div class="page">
				<div class="col-7 col-m-11 section">
					
				<%
				ServiceProvider sp = ServiceProvider.getServiceProvider();

				if ( !session.getAttribute("username").equals(sp.getUsername()) )
					response.sendRedirect("index.jsp");
				%>

					<form action="changeUserPass.jsp" method="post"> 
						<br><b>نام کاربری قبلی:</b><br><input type="text" name="past-username"><br> 
						<br><b>نام کاربری:</b><br><input type="text" name="username"><br> 
						<br><b>گذرواژه:</b> <br><input type="password" name="password"><br> 
						<br><input class = "button" type="submit" value="تایید">
							<input class = "button" type="reset" value="تصحیح"> 
					
					</form> 
				</div>
	</div>
</div>

<jsp:include page="footer-admin.jsp" />
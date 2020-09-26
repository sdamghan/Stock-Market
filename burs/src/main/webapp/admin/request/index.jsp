<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<jsp:include page="header-admin.jsp" />

	<center> 
		<h2>صفحه ورود</h2> 
		<form action="loginCheck.jsp" method="post"> 
			<br><b>نام کاربری:</b><br><input type="text" name="username"><br> 
			<br><b>گذرواژه:</b> <br><input type="password" name="password"><br> 
			<br><input class = "button" type="submit" value="ورود">
				<input class = "button" type="reset" value="تصحیح"> 
		</form> 
	</center>


<jsp:include page="footer-admin.jsp" />
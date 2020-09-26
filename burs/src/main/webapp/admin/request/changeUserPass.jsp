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

					if ( request.getParameter("past-username").equals(sp.getUsername()) ) {
						sp.setUsername( request.getParameter("username") );
						sp.setPassword( request.getParameter("password") );
					%>

						<p id="p02"> تغییر نام کاربری با موفقیت انجام شد. <br><br></p>
						<p id="p02"> نام کاربری: <%=sp.getUsername()%> <hr></p>
						<p id="p02"> گذرواژه: <%=sp.getPassword()%> <br></p>

					<%
					}

					else {
					%>
						<p id="p02"> نام کاربری قبلی صحیح نمی باشد. <br><br></p>
						<%}%>


				</div>
	</div>
</div>

<jsp:include page="footer-admin.jsp" />
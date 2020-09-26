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



						sp.backup();
						%>

						<p id="p02">ذخیره سازی داده های معاملات انجام شد.</p>
				</div>
	</div>
</div>

<jsp:include page="footer-admin.jsp" />
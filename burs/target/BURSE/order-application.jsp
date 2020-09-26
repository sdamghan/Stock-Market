<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" import="ir.ut.burs.*, java.util.*"%>
<jsp:include page="header.jsp" /> 

	<div class="row">

		<jsp:include page="menu.jsp" />

		<div class="page">
				<div class="col-7 col-m-11 section">

					<%
					ServiceProvider sp = ServiceProvider.getServiceProvider();
					%>

					<p id="p02"> <%= sp.ordering ( request.getParameter("mode"),
												   request.getParameter("id"),
												   request.getParameter("instrument"),
												   request.getParameter("price"),
												   request.getParameter("quantity"),
												   request.getParameter("type"))%> <hr></p>

					
				</div>
		</div>

	</div>		


<jsp:include page="footer.jsp" />
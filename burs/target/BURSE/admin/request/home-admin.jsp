<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" import="ir.ut.burs.*, java.util.*"%>
<jsp:include page="header-admin.jsp" />

<div class="row">

	<jsp:include page="menu-admin.jsp" /> 

	<div class="page">
			<div class="col-7 col-m-11 section">

				<%
				ServiceProvider sp = ServiceProvider.getServiceProvider();

				%> <p id="p02">درخواست افزایش اعتباری موجود نمی باشد.</p>



				<%
				List<DepositRequest> dreqs = sp.getDepositRequests();

				if ( !session.getAttribute("username").equals(sp.getUsername()) )
					response.sendRedirect("index.jsp");

				if ( dreqs.size() == 0 ) {
				%>
					<p id="p02">-----><%=sp.getSql()%></p>
				<%
				}
				else
					for ( DepositRequest dreq : dreqs ) {
				%>
				
					<table id="t01" >
				  <tr>
	    				<th class="col-3">شناسه : <%=dreq.getId()%></th>
	    				<th class="col-3">مقدار اعتبار : <%=dreq.getAmount()%></th>	
	  			  </tr>
	  			  </table>
	  			  <br>
	  			  <form method="POST" action="request-handle.jsp" >
		  			  <input class="button" name="operation" type="submit" value="تایید"</input>
					  <input class="button" name="operation" type="submit" value="رد"></input>

					  <input type="hidden" name="id" value=<%=dreq.getId()%>>
				  		<input type="hidden" name="amount" value=<%=dreq.getAmount()%>>
				  </form>

				  

				  <br><br><hr>
				  <%}%>
			</div>
	</div>		

</div>
<jsp:include page="footer-admin.jsp" />
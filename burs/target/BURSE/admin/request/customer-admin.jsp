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

				List<Customer> customers = sp.getCustomers();				
				for ( Customer customer : customers ) {
				%>
					<table id="t01" >
						<tr>
		    				<th class="col-m-2">شناسه </th>
		    				<th class="col-m-2">نام </th>
		    				<th class="col-m-2">نام خانوادگی </th>	
		  				</tr>
		  				<tr>
		    				<th class="col-m-2"><%=customer.id%></th>
		    				<th class="col-m-2"><%=customer.name%></th>
		    				<th class="col-m-2"><%=customer.family%></th>	
		  				</tr>
	  				</table>
	  				<br>
	  				<left> 
	  					<form target="_blank" action="../../customer-application.jsp" method="POST"> 
	  						<input  type="submit" value="مشاهده اطلاعات">  </input> 
	  						<input type="hidden" name="id" value="<%=customer.id%>">
	  						 <input type="hidden" name="type" value="detail">
	  					</form> 
	  				</left>
		  			<hr>

	  			<%}%>

				</div>
	</div>
</div>

<jsp:include page="footer-admin.jsp" />
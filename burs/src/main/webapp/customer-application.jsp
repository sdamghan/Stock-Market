<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" import="ir.ut.burs.*, java.util.*"%>
<jsp:include page="header.jsp" /> 

	<div class="row">

		<jsp:include page="menu.jsp" />

		<div class="page">
				<div class="col-7 col-m-11 section">

					<%
					ServiceProvider sp = ServiceProvider.getServiceProvider();
					String applicationType = request.getParameter("type");
					if ( applicationType.equals("add") ) {
					%>

					<p id="p02"> <%= sp.createNewUser( request.getParameter("id"),
													   request.getParameter("name"), 
													   request.getParameter("family") 
													  ) 
													  %> </p>
					<%
					}
					else if ( applicationType.equals("detail") ) {
					%>

					 
						<%
						try {
							session.setAttribute("username", sp.getUsername());
						 	Customer customer = sp.searchCustomer( Integer.parseInt(request.getParameter("id")) );
						 	if ( customer != null ) { 
						%>

							<p id="p02">شناسه:   <%=customer.id%><hr></p>
							<p id="p02">نام: <%=customer.name%><hr></p>
							<p id="p02">نام خانوادگی: <%=customer.family%><hr></p>
							<p id="p02">اعتبار: <%=customer.credit%><hr></p>
							<p id="p02">لیست موجودی سهام: [
							<%for ( int i=0; i<customer.myStocks.size(); i++ ) {%>
					    	 	<%=customer.myStocks.get(i).sign%> <%if(customer.myStocks.size()>=1 && i!= customer.myStocks.size()-1)%>,
					    	<%}%>
					    	]<hr></p>
					    	 
				    	 	<!-- doneRequests -->
				    	 	<div class="col-m-12">
								<h3><u><b>لیست درخواست های انجام شده:</b></u></h3><br>
							</div>
				    		<%
				    		for ( int i=0; i<customer.doneRequests.size(); i++ ) {
				    		%>
				    			<div class="col-m-3 col-n-4">
									<table id="t01" align="center">
									  	  <tr>
							    				<td class="col-m-12">شماره:</td>
							    				<td class="col-m-12"><%=i%></td>
							  			  </tr>  

									  	  <tr>
							    				<td class="col-m-12">شناسه:</td>
							    				<td class="col-m-12"><%=customer.doneRequests.get(i).customerID%></td>
							  			  </tr>
							  			  <tr>
							    				<td class="col-m-12">قیمت:</td>
							    				<td class="col-m-12"><%=customer.doneRequests.get(i).pricePerOne%></td>
							  			  </tr>
							  			  <tr>
							    				<td class="col-m-12">حجم:</td>
							    				<td class="col-m-12"><%=customer.doneRequests.get(i).volume%></td>
							  			  </tr>
									</table>
								</div>
				    		<%}%>

				    		<!-- runningRequests -->
				    	 	<div class="col-m-12">
								<hr><h3><u><b>لیست درخواست های در حال اجرا:</b></u></h3><br>
							</div> 
				    		<%
				    		for ( int i=0; i<customer.runningRequests.size(); i++ ) {
				    		%>
				    			<div class="col-m-3 col-n-4">
									<table id="t01" align="center">
									  	  <tr>
							    				<td class="col-m-12">شماره:</td>
							    				<td class="col-m-12"><%=i%></td>
							  			  </tr>  

									  	  <tr>
							    				<td class="col-m-12">شناسه:</td>
							    				<td class="col-m-12"><%=customer.runningRequests.get(i).customerID%></td>
							  			  </tr>
							  			  <tr>
							    				<td class="col-m-12">قیمت:</td>
							    				<td class="col-m-12"><%=customer.runningRequests.get(i).pricePerOne%></td>
							  			  </tr>
							  			  <tr>
							    				<td class="col-m-12">حجم:</td>
							    				<td class="col-m-12"><%=customer.runningRequests.get(i).volume%></td>
							  			  </tr>
									</table>
								</div>
				    		<%}%>

				    		<!-- denidRequests -->
				    		<div class="col-m-12">
								<hr><h3><u><b>لیست درخواست های رد شده:</b></u></h3><br>
							</div>
				    		<%
				    		for ( int i=0; i<customer.deniadRequests.size(); i++ ) {
				    		%>
				    			<div class="col-m-3 col-n-4">
									<table id="t01" align="center">
									  	  <tr>
							    				<td class="col-m-12">شماره:</td>
							    				<td class="col-m-12"><%=i%></td>
							  			  </tr>  

									  	  <tr>
							    				<td class="col-m-12">شناسه:</td>
							    				<td class="col-m-12"><%=customer.deniadRequests.get(i).customerID%></td>
							  			  </tr>
							  			  <tr>
							    				<td class="col-m-12">قیمت:</td>
							    				<td class="col-m-12"><%=customer.deniadRequests.get(i).pricePerOne%></td>
							  			  </tr>
							  			  <tr>
							    				<td class="col-m-12">حجم:</td>
							    				<td class="col-m-12"><%=customer.deniadRequests.get(i).volume%></td>
							  			  </tr>
									</table>
								</div>
				    		<%}%>



						<%}else%> <p id="p02"> شماره شناسه کاربری موجود نمی باشد. </p> 
					<%} catch( NumberFormatException e ) {%> <p id="p02"> شماره شناسه کاربری باید به صورت عددی وارد شود. </p> <%}%>
					

					<%
					}
					else if ( applicationType.equals("deposit") ) {
					%>

					<p id="p02"> <%= sp.addToDepositList( request.getParameter("id"),
													   request.getParameter("amount") 
													  ) 
													  %> </p>

					<%
					}
					else if ( applicationType.equals("withdraw") ) {
					%>

					<p id="p02"> <%= sp.withDrawUser( request.getParameter("id"),
													   request.getParameter("amount") 
													  ) 
													  %> </p>
					<%}%>
					
				</div>
		</div>		

	</div>
	
<jsp:include page="footer.jsp" />

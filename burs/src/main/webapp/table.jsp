<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" import="ir.ut.burs.*, java.util.*"%>
<jsp:include page="header.jsp" /> 

	<div class="row">

		<jsp:include page="menu.jsp" />

		<div class="page">

			<%
			ServiceProvider sp = ServiceProvider.getServiceProvider();
			List<Stock> stocks = sp.getStocks();
			for ( int i=0; i<stocks.size(); i++ ) {
			%>
					<div class="col-m-12">

						<div class="col-m-12">
							<%if(i!=0){%><hr><%}%>
							<h3><u><b><%=stocks.get(i).sign%></b></u></h3><br>
							<h4> <b>صف خرید‌ :<b> </h4><br>
						</div>

						<%
						List<Request> buyQueue = stocks.get(i).getList("BUY");
						for ( int j=0; j<buyQueue.size(); j++ ) {
						%>
						<div class="col-m-3 col-n-4">
							
							<table id="t01" align="center">
								  <tr>
					    				<td class="col-m-12">شماره:</td>
					    				<td class="col-m-12"><%=j%></td>
					  			  </tr>

							  	  <tr>
					    				<td class="col-m-12">شناسه:</td>
					    				<td class="col-m-12"><%=buyQueue.get(j).customerID%></td>
					  			  </tr>
					  			  <tr>
					    				<td class="col-m-12">قیمت:</td>
					    				<td class="col-m-12"><%=buyQueue.get(j).pricePerOne%></td>
					  			  </tr>
					  			  <tr>
					    				<td class="col-m-12">حجم:</td>
					    				<td class="col-m-12"><%=buyQueue.get(j).volume%></td>
					  			  </tr>
							</table>
						</div>
						<%}%>
					

						<div class="col-m-12">
							<br><h4> <b>صف فروش :<b> </h4><br>
						</div>

						<%
						List<Request> sellQueue = stocks.get(i).getList("SELL");
						for ( int j=0; j<sellQueue.size(); j++ ) {
						%>
						<div class="col-m-3 col-n-4">
							
							<table id="t01" align="center">
							  	  <tr>
					    				<td class="col-m-12">شماره:</td>
					    				<td class="col-m-12"><%=j%></td>
					  			  </tr>  

							  	  <tr>
					    				<td class="col-m-12">شناسه:</td>
					    				<td class="col-m-12"><%=sellQueue.get(j).customerID%></td>
					  			  </tr>
					  			  <tr>
					    				<td class="col-m-12">قیمت:</td>
					    				<td class="col-m-12"><%=sellQueue.get(j).pricePerOne%></td>
					  			  </tr>
					  			  <tr>
					    				<td class="col-m-12">حجم:</td>
					    				<td class="col-m-12"><%=sellQueue.get(j).volume%></td>
					  			  </tr>
							</table>
						</div>
						<%}%>
					</div>

			<%}%>
		</div>	

	</div>	

<jsp:include page="footer.jsp" />
<%@ page import="ir.ut.burs.*" %>
<%@ page import="java.util.*" %>



<%
ServiceProvider sp = ServiceProvider.getServiceProvider();
List<DepositRequest> dreqs = sp.getDepositRequests();
DepositRequest dreq = sp.findDepositRequest( request.getParameter("id"), request.getParameter("amount") );
if ( request.getParameter("operation").equals("تایید") ) {
%>
	<p id="p01"> <%sp.acceptDepositRequest(dreq);%> </p>
<%
}
else
{
%>
	<p id="p01"> <%sp.denayDepositRequest(dreq);%> </p>
<%
}
%>

<jsp:forward page="home-admin.jsp" />

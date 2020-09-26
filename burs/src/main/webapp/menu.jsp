<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>

<div tabindex="0" class=" col-1 col-m-1 onclick-menu">
	<img class="menu-img" src="img/menu.png">
	<ul class="col-12 col-m-12 onclick-menu-content">
			  <li><a href="index.jsp">صفحه اصلی</a></li>
			  <li><a href="customer.jsp">کاربری</a></li>
			  <li><a href="table.jsp">وضعیت بازار</a></li>
			  <li><a href="order.jsp">خرید و فروش سهام</a></li>
	</ul>
</div>


<div class="col-2 col-m-2 menu">
	<ul>

	      <li><a href="index.jsp">صفحه اصلی</a></li>
	      <li><a href="customer.jsp">کاربری</a></li>
	      <li><a href="table.jsp">وضعیت بازار</a></li>
	      <li><a href="order.jsp">خرید و فروش سهام</a></li>
	</ul>
</div>

<%session.setAttribute("username","");%>
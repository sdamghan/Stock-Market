<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" import="ir.ut.burs.*, java.util.*"%>
<jsp:include page="header.jsp" /> 

	<div class="row">

		<jsp:include page="menu.jsp" />

		<div class="page">
				<div class="col-7 col-m-11 section">
					
					<nav class="navbar navbar-inverse navbar-static-top" >
					<div class="container-fluid">
						<div class="navbar-header navbar-right">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
							  <span class="icon-bar"></span>
							  <span class="icon-bar"></span>
							  <span class="icon-bar"></span>
						  </button>
						  <a class="navbar-brand" href="#">بورس دانشگاه تهران</a>
						</div>
						<div>
						  <div class="collapse navbar-collapse navbar-right" id="myNavbar">
							<ul class="nav navbar-nav">
							  <li><a href="#section1">خرید</a></li>
							  <li><a href="#section2">فروش</a></li>
							<!--   <li><a href="#section3">Section 3</a></li>
							  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Section 4 <span class="caret"></span></a>
								<ul class="dropdown-menu">
								  <li><a href="#section41">Section 4-1</a></li>
								  <li><a href="#section42">Section 4-2</a></li>
								</ul>
							  </li> -->
							</ul>
						  </div>
						</div>
					  </div>
					</nav>	

					<div id="section1" class="container-fluid">
					  <h1 id="bootTitle">خرید سهام</h1>
					  <form method="POST" action="order-application.jsp">
					  <br>
					  	  <input type="hidden" name="mode" value="buy">
						  شماره شناسه: <br><input class="text-input" type="text" name="id"><hr>
						  نام سهام   : <br><input class="text-input" type="text" name="instrument"><hr>
						  قیمت هر سهم: <br><input class="text-input" type="text" name="price"><hr>
						  حجم سهم    : <br><input class="text-input" type="text" name="quantity"><hr>
						  نوع درخواست   : <br><select id="type" name="type">
						  							<option id="type" value="none" selected>----</option>
						  							<option id="type" value="GTC">GTC</option>
						  							<option id="type" value="IOC">IOC</option>
						  							<option id="type" value="MPO">MPO</option>
						  					  </select>
						  					<br><br>
						  <button class="button" type="submit" value="Submit">ارسال</button>
						  <button class="button" type="reset" value="reset">تصحیح</button>
					 </form>

					</div>
					<div id="section2" class="container-fluid">
					  <h1 id="bootTitle">فروش سهام</h1>
					  <form method="POST" action="order-application.jsp">
					  <br>
					  	  <input type="hidden" name="mode" value="sell">
						  شماره شناسه: <br><input class="text-input" class="text-input" type="text" name="id"><br><hr>
						  نام سهام   : <br><input class="text-input" type="text" name="instrument"><hr>
						  قیمت هر سهم: <br><input class="text-input" type="text" name="price"><hr>
						  حجم سهم    : <br><input class="text-input" class="text-input" type="text" name="quantity"><hr>
						  نوع درخواست   : <br><select id="type" name="type">
						  							<option id="type" value="none" selected>----</option>
						  							<option id="type" value="GTC">GTC</option>
						  							<option id="type" value="IOC">IOC</option>
						  							<option id="type" value="MPO">MPO</option>
						  					  </select>
						  					  <br><br>
						  <button class="button" type="submit" value="Submit">ارسال</button>
						  <button class="button" type="reset" value="reset">تصحیح</button>
					  </form>
					</div>
					<!-- <div id="section3" class="container-fluid">
					  <h1>Section 3</h1>
					  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
					  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
					</div>
					<div id="section41" class="container-fluid">
					  <h1>Section 4 Submenu 1</h1>
					  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
					  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
					</div>
					<div id="section42" class="container-fluid">
					  <h1>Section 4 Submenu 2</h1>
					  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
					  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
					</div> -->

					
				</div>
		</div>		

	</div>
	
<jsp:include page="footer.jsp" />

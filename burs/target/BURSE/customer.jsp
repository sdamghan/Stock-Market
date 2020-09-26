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
							  <li><a href="#section1">ثبت نام</a></li>
							  <li><a href="#section2">نمایش اطلاعات</a></li>
							  <!-- <li><a href="#section3">Section 3</a></li> -->
							  <li class="dropdown"><a class="dropdown-toggle " data-toggle="dropdown" href="#">حساب <span class="caret "></span></a>
								<ul class="dropdown-menu pull-right">
								  <li><a href="#section41">افزودن به حساب</a></li>
								  <li><a href="#section42">کسر موجودی</a></li>
								</ul>
							  </li>
							</ul>
						  </div>
						</div>
					  </div>
					</nav>	

					<div id="section1" class="container-fluid">
					  <h1 id="bootTitle">ثبت نام</h1>
					  <form method="POST" action="customer-application.jsp">
					  <br>
						  <b>شماره شناسه:<b> <br><input class="text-input" type="text" name="id"><hr>
						  <b>نام   :<b> <br><input class="text-input" type="text" name="name"><hr>
						  <b>نام خانوادگی:<b> <br><input class="text-input" type="text" name="family"><br> <br>
						  <input type="hidden" name="type" value="add">
						  <button class="button" type="submit" value="Submit">ارسال</button>
						  <button class="button" type="reset" value="reset">تصحیح</button>

					 </form>

					</div>
					<div id="section2" class="container-fluid">
					  <h1 id="bootTitle">نمایش اطلاعات</h1>
					  <form method="POST" action="customer-application.jsp">
					  <br>
						  <b>شماره شناسه:<b> <br><input class="text-input" class="text-input" type="text" name="id">
						  <input type="hidden" name="type" value="detail">
						  <br><br>
						  <button class="button" type="submit" value="Submit">ارسال</button>
						  <button class="button" type="reset" value="reset">تصحیح</button>
					  </form>
					</div>
					
					<div id="section41" class="container-fluid">
					  <h1 id="bootTitle">افزودن به حساب</h1>
					  <form method="POST" action="customer-application.jsp">
					  <br>
						  <b>شماره شناسه:<b> <br><input class="text-input" type="text" name="id"><hr>
						  <b>قیمت:<b> <br><input class="text-input" type="text" name="amount"><br> <br>
						  <input type="hidden" name="type" value="deposit">
						  <button class="button" type="submit" value="Submit">ارسال</button>
						  <button class="button" type="reset" value="reset">تصحیح</button>
					 </form>
					</div>

					<div id="section42" class="container-fluid">
					  <h1 id="bootTitle">کسر موجودی</h1>
					  <form method="POST" action="customer-application.jsp">
					  <br>
						  <b>شماره شناسه:<b> <br><input class="text-input" type="text" name="id"><hr>
						  <b>قیمت :<b> <br><input class="text-input" type="text" name="amount"><br><br>
						  <input type="hidden" name="type" value="withdraw">
						  <button class="button" type="submit" value="Submit">ارسال</button>
						  <button class="button" type="reset" value="reset">تصحیح</button>
					 </form>
					</div>

					
				</div>
		</div>		

	</div>
	
<jsp:include page="footer.jsp" />

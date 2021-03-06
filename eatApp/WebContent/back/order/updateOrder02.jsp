<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/back/css/style.css" />
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<script src="${pageContext.request.contextPath }/back/js/jquery.js"></script>
<script
	src="${pageContext.request.contextPath }/back/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script>
	(function($) {
		$(window).load(
				function() {

					$("a[rel='load-content']").click(
							function(e) {
								e.preventDefault();
								var url = $(this).attr("href");
								$.get(url, function(data) {
									$(".content .mCSB_container").append(data); //load new content inside .mCSB_container
									//scroll-to appended content 
									$(".content").mCustomScrollbar("scrollTo",
											"h2:last");
								});
							});

					$(".content").delegate(
							"a[href='top']",
							"click",
							function(e) {
								e.preventDefault();
								$(".content").mCustomScrollbar("scrollTo",
										$(this).attr("href"));
							});

				});
	})(jQuery);
</script>
</head>
<body>
	<script>
		function checked() {
			
			orderdate = $("*[name='orderdate']").val();
			waycode = $("*[name='waycode']").val();
			waynum = $("*[name='waynum']").val();
			orderStatus = $("*[name='orderStatus']").val();		
			address = $("*[name='address']").val();
			
			if (orderdate == "") {
				alert("更新时间不能为空！");
				return false;
			} else if (waycode == "") {
				alert("物流不能为空！");
				return false;
			} else if (waynum == "") {
				alert("运单号不能为空！");
				return false;
			} else if (orderStatus == "") {
				alert("订单状态不能为空！");
				return false;
			} 
			/**
			else if (address == "") {
				alert("地址不能为空！");
				return false;
			} 
			*/ 
			else {
				return true;
			}
		}
	</script>
	
	<form method="post" action="${pageContext.request.contextPath}/BackOrderServlet?method=update"  name="f" onSubmit="return checked()">
	<h2>
		<strong style="color: grey;">添加订单页面</strong>
	</h2>
	<ul class="ulColumn2">
		<li>
			<span class="item_name" style="width: 120px;">订单编号：</span> 
			<input type="text" name="orderId" class="textbox textbox_295"placeholder="请输入订单编号" value="${order.id}" readonly="readonly"/>
		</li>
		<li>
			<span class="item_name" style="width: 120px;">用户：</span> 
			<input type="text" name="userId" class="textbox textbox_295" placeholder="请输入用户" value="${order.userId}" readonly="readonly"/>
		</li>
		<li>
			<span class="item_name" style="width: 120px;">更新时间：</span> 
			<input type="text" name="orderdate" class="textbox textbox_295" placeholder="请输入更新时间" value="${order.orderdate}"/>
		</li>
		<li>
			<span class="item_name" style="width: 120px;">订单价格：</span> 
			<input type="text" name="totalPrice" class="textbox textbox_295" placeholder="请输入订单价格" value="${order.totalprice}" readonly="readonly"/>
		</li>
		<li>
			<span class="item_name" style="width: 120px;">物流：</span> 
			<input type="text" name="waycode" class="textbox textbox_295" placeholder="请输入物流" value="${order.waycode}" readonly="readonly"/>
		</li>
		<li>
			<span class="item_name" style="width: 120px;">运单号：</span> 
			<input type="text" name="waynum" class="textbox textbox_295" placeholder="请输入用运单号" value="${order.waynum}" readonly="readonly"/>
		</li>
		<li>
			<span class="item_name" style="width: 120px;">订单状态：</span> 
			<input type="text" name="orderStatus" class="textbox textbox_295" placeholder="请输入订单状态" value="${order.orderStatus}"/>
		</li>
		<li>
			<span class="item_name" style="width: 120px;">地址：</span> 
			<input type="text" name="address" class="textbox textbox_295" placeholder="请输入地址" value="${order.address}" readonly="readonly"/>
		</li>
			
		<li><span class="item_name" style="width: 120px;"></span> 
		<input type="submit" class="link_btn" value="更新" />
		<input type="reset" class="link_btn">
		</li>
	</ul>
	</form>
</body>
</html>
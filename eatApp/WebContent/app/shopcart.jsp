<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<title>购物车页面</title>

		<link href="${pageContext.request.contextPath}/app/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/app/basic/css/demo.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/app/css/cartstyle.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/app/css/optstyle.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="${pageContext.request.contextPath}/app/js/jquery.js"></script>

		
	</head>
	<script type="text/javascript">
		//修改数量
		function alterSum(g){
			var snackid = $(g).attr("lang");
			var taste= $(g).attr("name");
			var con = $("#textbox"+snackid).val();
			alert(con);
			var stockNum = $("#Stock"+snackid).val();
			//获得当前修改商品的单价
			var singleprice=$("#singleprice").attr("lang");
			if (con*1>stockNum*1) {
				alert("库存不足，请重新输入！")
				return;
			}else{
				   //NO1)创建AJAX异步对象
			       var ajax = createAJAX();
			       //NO2)准备发送请求
			       var method = "GET";
			       var url = "${pageContext.request.contextPath}/CartServlet?method=clickPutcart&snackId="+snackid+"&con="+con+"&taste="+taste+"&time=" + new Date().getTime();
			       ajax.open(method,url);
			       //NO3)真正发送请求体的数据到服务器，如果请求体中无数据的话，就用null表示
			       ajax.send(null);
			       //-------------------------------------------------------------等待
			       ajax.onreadystatechange = function(){
			       	
			           if(ajax.readyState == 4){
			          	
			               if(ajax.status == 200){
			                   //NO5)
			                   var tip = ajax.responseText;
			                   //获修改单项的金额显示
			                   var price= $("#sp"+snackid).html();
			                   var vv = Math.pow(10,1);
			                   price=price*1+tip*1;
			                   var m=Math.round(price*vv)/vv; 
			                   $("#sp"+snackid).html(m*1.0);
			                   $("#singlecheck"+snackid).attr("lang",m);
			                   var totalprice=$("#sumMoney").attr("lang");
			                   var totalcon=$("#sumCon").attr("lang");
			                   totalprice=totalprice*1.0+tip*1.0;
			                   totalcon=totalcon*1+tip/singleprice;
			                   var n=Math.round(totalprice*vv)/vv; 
			                   $("#sumMoney").attr("lang",n)
			                   $("#sumCon").attr("lang",totalcon);
			                   $("#singlecheck"+snackid).val(con);
			                   if($("#J_SelectAllCbx2").attr("checked")=="checked"||$("#singlecheck"+snackid).attr("checked")=="checked"){
			                	  freshPrice();  
			                   }
			                 }
			           
			           }
			       } 
			}
			
		}
		
	
		//创建AJAX异步对象
	    function createAJAX(){
	        var ajax = null;
	        try{
	            //如果IE5=IE12的话
	            ajax = new ActiveXObject("microsoft.xmlhttp");
	        }catch(e1){
	            try{
	                //如果是非IE的话
	                ajax = new XMLHttpRequest();
	            }catch(e2){
	                alert("你的浏览器中不支持异步对象，请换浏览器");
	            }
	        }
	        return ajax;
	    }
		
	   function selectAll() {
		if($("#J_SelectAllCbx2").attr("checked")=="checked"){
			//NO1)创建AJAX异步对象
		       var ajax = createAJAX();
		       //NO2)准备发送请求
		       var method = "GET";
		       var url = "${pageContext.request.contextPath}/CartServlet?all=1&method=buildcartAll"+"&time=" + new Date().getTime();
		       ajax.open(method,url);
		       //NO3)真正发送请求体的数据到服务器，如果请求体中无数据的话，就用null表示
		       ajax.send(null);
			$("input[name='snackBox']").attr("checked", true);//获取所有商品的checkbox
			 var sumMoney=$("#sumMoney").attr("lang");
			 var sumCon=$("#sumCon").attr("lang");
			$("#J_Total").html(sumMoney);
			$("#J_SelectedItemsCount").html(sumCon);
		}else{
			//NO1)创建AJAX异步对象
		       var ajax = createAJAX();
		       //NO2)准备发送请求
		       var method = "GET";
		       var url = "${pageContext.request.contextPath}/CartServlet?all=0&method=buildcartAll"+"&time=" + new Date().getTime();
		       ajax.open(method,url);
		       //NO3)真正发送请求体的数据到服务器，如果请求体中无数据的话，就用null表示
		       ajax.send(null);
			$("input[name='snackBox']").attr("checked", false);
			$("#J_Total").html("0");
			$("#J_SelectedItemsCount").html("0");
		}
	}
	   function add(w) {
		   var snackid=$(w).attr("lang");
		   var taste=$(w).attr("name");
		   var stockNum = $("#Stock"+snackid).val();
		   var con=$("#textbox"+snackid).val()*1+1;
		   if(con>stockNum*1){
			   alert("库存不足！")
			   $("#textbox"+snackid).val(con-2);
			   return;
		   }
			   //NO1)创建AJAX异步对象
		       var ajax = createAJAX();
		       //NO2)准备发送请求
		       var method = "GET";
		       var url = "${pageContext.request.contextPath}/CartServlet?method=clickPutcart&snackId="+snackid+"&con="+con+"&taste="+taste+"&time=" + new Date().getTime();
		       ajax.open(method,url);
		       //NO3)真正发送请求体的数据到服务器，如果请求体中无数据的话，就用null表示
		       ajax.send(null);
		       //-------------------------------------------------------------等待
		       ajax.onreadystatechange = function(){
		       	
		           if(ajax.readyState == 4){
		          	
		               if(ajax.status == 200){
		                   //NO5)
		                 
		            	   var tip = ajax.responseText;
		                   //获修改单项的金额显示
		                   var price= $("#sp"+snackid).html();
		                   var vv = Math.pow(10,1);
		                   price=price*1.0+tip*1.0;
		                   var m=Math.round(price*vv)/vv; 
		                   $("#sp"+snackid).html(m*1.0);
		                   $("#singlecheck"+snackid).attr("lang",m);
		                   //修改总计
		                   var totalprice=$("#sumMoney").attr("lang");
		                   var totalcon=$("#sumCon").attr("lang");
		                   totalprice=totalprice*1.0+tip*1.0;
		                   totalcon=totalcon*1+1;
		                   var n=Math.round(totalprice*vv)/vv; 
		                   $("#singlecheck"+snackid).val(con);
		                   $("#sumMoney").attr("lang",n);
		                   $("#sumCon").attr("lang",totalcon);
		                   if($("#J_SelectAllCbx2").attr("checked")=="checked"||$("#singlecheck"+snackid).attr("checked")=="checked"){
		                	   freshPrice();
		                   }
		                 }
		           
		           }
		       } 
		
	}
		function minus(s){
			var snackid=$(s).attr("lang");
			var taste=$(s).attr("name");
			var con=$("#textbox"+snackid).val()*1-1;
			if(con<1){
				$("#textbox"+snackid).val("2");
				alert("亲，真的不能再少了");
				return;
			}
			   //NO1)创建AJAX异步对象
		       var ajax = createAJAX();
		       //NO2)准备发送请求
		       var method = "GET";
		       var url = "${pageContext.request.contextPath}/CartServlet?method=clickPutcart&snackId="+snackid+"&con="+con+"&taste="+taste+"&time=" + new Date().getTime();
		       ajax.open(method,url);
		       //NO3)真正发送请求体的数据到服务器，如果请求体中无数据的话，就用null表示
		       ajax.send(null);
		       //-------------------------------------------------------------等待
		       ajax.onreadystatechange = function(){
		       	
		           if(ajax.readyState == 4){
		          	
		               if(ajax.status == 200){
		                   //NO5)
		                 
		                   var tip = ajax.responseText;
		                   //获修改单项的金额显示
		                   var price= $("#sp"+snackid).html();
		                   var vv = Math.pow(10,1);
		                   price=price*1+tip*1;
		                   var m=Math.round(price*vv)/vv; 
		                   $("#sp"+snackid).html(m);
		                   $("#singlecheck"+snackid).attr("lang",m);
		                   //修改总计
		                   var totalprice=$("#sumMoney").attr("lang");
		                   var totalcon=$("#sumCon").attr("lang");
		                   totalprice=totalprice*1.0+tip*1.0;
		                   totalcon=totalcon*1-1;
		                   var k=Math.round(totalprice*vv)/vv; 
		                   $("#singlecheck"+snackid).val(con);
		                   $("#sumMoney").attr("lang",k);
		                   $("#sumCon").attr("lang",totalcon);
		                   if($("#J_SelectAllCbx2").attr("checked")=="checked"||$("#singlecheck"+snackid).attr("checked")=="checked"){
		                	   freshPrice();
		                   }
		                 }
		           
		           }
		       } 
		}
		function clicksinglecheck(checkbox) {
		
			var flag=0;
			var snackid=$(checkbox).attr("id");
			var taste=$("#textbox"+snackid).attr("name");
			snackid=snackid.substring(11,snackid.length);
			if($(checkbox).attr("checked")!="checked"){//点击之后不是勾选状态
				//NO1)创建AJAX异步对象
			       var ajax = createAJAX();
			       //NO2)准备发送请求
			       var method = "GET";
			       var url = "${pageContext.request.contextPath}/CartServlet?method=buildcartMin&snackId="+snackid+"&taste="+taste+"&time=" + new Date().getTime();
			       ajax.open(method,url);
			       //NO3)真正发送请求体的数据到服务器，如果请求体中无数据的话，就用null表示
			       ajax.send(null);
				//取消全选
				$("#J_SelectAllCbx2").attr("checked",false);
			}else{//点击之后是勾选状态
				//NO1)创建AJAX异步对象
		       var ajax = createAJAX();
		       //NO2)准备发送请求
		       var method = "GET";
		       var url = "${pageContext.request.contextPath}/CartServlet?method=buildcartAdd&snackId="+snackid+"&taste="+taste+"&time=" + new Date().getTime();
		       ajax.open(method,url);
		       //NO3)真正发送请求体的数据到服务器，如果请求体中无数据的话，就用null表示
		       ajax.send(null);
				$("input[name='snackBox']").each(function(){
					if ($(this).attr("checked")=="checked") {
						flag=flag*1+1*1;
					}else{
						return false;
					}
				});
				if (flag==$("input[name='snackBox']").size()) {
					$("#J_SelectAllCbx2").attr("checked",true);
				}
			}				
			freshPrice(); 		
		}
           function freshPrice() {
        	   var temp=0;
        	   var tem=0;
        	   $("input[name='snackBox']").each(function(){
					if ($(this).attr("checked")=="checked") {
						temp=temp*1.0+$(this).attr("lang")*1.0;
						tem=tem*1+$(this).attr("value")*1;
					}
					
					 $("#J_Total").html(temp);
					 $("#J_SelectedItemsCount").html(tem);
					 
				});
		}
		</script>

	<body>

		<!--顶部导航条 -->
		<div class="am-container header">
			<ul class="message-l">
				<div class="topMessage">
						<c:if test="${UserName!=null}">
					       <div class="menu-hd">
					           <a class="h">亲爱的${NickName}</a>&nbsp;&nbsp;
					           <a class="h" href="${pageContext.request.contextPath}/UserServlet?method=logout">退出</a>
					       </div>
					    </c:if>
					      <c:if test="${UserName==null}">
						<div class="menu-hd">
							<a href="${pageContext.request.contextPath}/app/login.jsp" target="_top" class="h">亲，请登录</a>
							<a href="${pageContext.request.contextPath}/app/register.jsp" target="_top">免费注册</a>
						</div>
						</c:if>
				</div>
			</ul>
			<ul class="message-r">
				<div class="topMessage home">

					<div class="menu-hd"><a href="${pageContext.request.contextPath}/ShowServlet?method=index" target="_top" class="h">商城首页</a></div>

				</div>
				<div class="topMessage my-shangcheng">
					<c:if test="${UserName!=null }">
						<div class="menu-hd MyShangcheng"><a href="${pageContext.request.contextPath}/PersonServlet?method=go" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
					</c:if>
					<c:if test="${UserName==null }">
						<div class="menu-hd MyShangcheng"><a href="${pageContext.request.contextPath}/app/login.jsp" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
					</c:if>
				</div>
				<div class="topMessage mini-cart">
						<c:if test="${UserName!=null }">
					<div class="menu-hd">
						<a id="mc-menu-hd" target="_top" href="${pageContext.request.contextPath}/app/shopcart.jsp"><i
							class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong
							id="J_MiniCartNum" class="h">0</strong></a>
					</div>
					</c:if>
					<c:if test="${UserName==null }">
					<div class="menu-hd">
						<a id="mc-menu-hd" target="_top" href="${pageContext.request.contextPath}/app/login.jsp"><i
							class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong
							id="J_MiniCartNum" class="h">0</strong></a>
				  </div>
				  </c:if>
				  </div>
				<div class="topMessage favorite">
				<c:if test="${UserName!=null }">
					<div class="menu-hd">
						<a target="_top" href="${pageContext.request.contextPath}/CollectionServlet?method=showCollection"><i
							class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a>
					</div>
					</c:if>
					<c:if test="${UserName==null }">
					<div class="menu-hd">
						<a target="_top" href="${pageContext.request.contextPath}/app/login.jsp"><i
							class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a>
					</div>
					</c:if>
					</div>
				</ul>
			</div>

			<!--悬浮搜索框-->

			<div class="nav white">
				<div class="logo"><img src="${pageContext.request.contextPath}/app/images/logo.png" /></div>
				<div class="logoBig">
					<li><img src="${pageContext.request.contextPath}/app/images/logobig.png" /></li>
				</div>

				<div class="search-bar pr">
					<a name="index_none_header_sysc" href="#"></a>
					<form  action="${pageContext.request.contextPath}/ShowServlet?method=search" method="post" >
						<input id="searchInput" name="searchValue" type="text" placeholder="搜索" autocomplete="off">
						<input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="submit">
					</form>
				</div>
			</div>

			<div class="clear"></div>

			<!--购物车 -->
			<div class="concent">
				<div id="cartTable">
					<div class="cart-table-th">
						<div class="wp">
							<div class="th th-chk">
								<div id="J_SelectAll1" class="select-all J_SelectAll">

								</div>
							</div>
							<div class="th th-item">
								<div class="td-inner">商品信息</div>
							</div>
							<div class="th th-price">
								<div class="td-inner">单价</div>
							</div>
							<div class="th th-amount">
								<div class="td-inner">数量</div>
							</div>
							<div class="th th-sum">
								<div class="td-inner">金额</div>
							</div>
							<div class="th th-op">
								<div class="td-inner">操作</div>
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<c:forEach items="${cart}" var="obj" varStatus="vs">
					<tr class="item-list">
						<div class="bundle  bundle-last ">
							<div class="clear"></div>
							<div class="bundle-main">
								<ul class="item-content clearfix">
									<li class="td td-chk">
										<div class="cart-checkbox ">
										<c:if test="${obj.key.isCh==1} ">
										   <input class="check" id="singlecheck${obj.key.id}" checked="checked" name="snackBox" value="${obj.value}" type="checkbox" onclick="clicksinglecheck(this);" lang="${obj.key.price*obj.value}">
										</c:if>
										<c:if test="${obj.key.isCh==0}">
										<input class="check" id="singlecheck${obj.key.id}" name="snackBox" value="${obj.value}" type="checkbox" onclick="clicksinglecheck(this);" lang="${obj.key.price*obj.value}">
										</c:if>
											
											<label for="J_CheckBox_170037950254"></label>
										</div>
									</li>
									<li class="td td-item">
										<div class="item-pic">
											<a href="#" target="_blank" data-title="" class="J_MakePoint" data-point="tbcart.8.12">
												<img src="${pageContext.request.contextPath }${obj.key.image }" class="itempic J_ItemImg"></a>
										</div>
										<div class="item-info">
											<div class="item-basic-info">
												<a href="#" target="_blank" title="${obj.key.snackName }" class="item-title J_MakePoint" data-point="tbcart.8.11">${obj.key.snackName }</a>
											</div>
										</div>
									</li>
									<li class="td td-info">
										<div class="item-props item-props-can">
											<span class="sku-line">口味：${obj.key.taste}</span>
											<span tabindex="0" class="btn-edit-sku theme-login">修改</span>
											<i class="theme-login am-icon-sort-desc"></i>
										</div>
									</li>
									<li class="td td-price">
										<div class="item-price price-promo-promo">
											<div class="price-content">
												<div class="price-line">
													<em class="price-original">￥${obj.key.mktprice }</em>
												</div>
												<div class="price-line">
													<em class="J_Price price-now" tabindex="0" id="singleprice" lang="${obj.key.price }">￥${obj.key.price }</em>
												</div>
											</div>
										</div>
									</li>
									<li class="td td-amount">
										<div class="amount-wrapper ">
											<div class="item-amount ">
												<div class="sl">
													<input class="min am-btn" name="${obj.key.taste}" type="button" lang="${obj.key.id}" onclick="minus(this);" value="-" />
													<input class="text_box"  id="textbox${obj.key.id }" name="${obj.key.taste}" type="text" value="${obj.value }" lang="${obj.key.id}" onblur="alterSum(this);" style="width:30px;" />
													<input id="Stock${obj.key.id }" name="" type="hidden" value="${obj.key.stock}" />
													<input class="add am-btn"  name="${obj.key.taste}" type="button" lang="${obj.key.id }" onclick="add(this);" value="+" />
												</div>
											</div>
										</div>
									</li>
									<li class="td td-sum">
										<div class="td-inner">
											<em tabindex="0" id="snackprice" class="J_ItemSum number">￥<span id="sp${obj.key.id }">${obj.key.price*obj.value}</span> </em>
										</div>
										<c:set var="sumMoney" value="${sumMoney+obj.key.price*obj.value}" scope="request"></c:set>
										<c:set var="sumCon" value="${sumCon+obj.value}"  scope="request"></c:set>
									</li>
									<li class="td td-op">
										<div class="td-inner">
											<a title="移入收藏夹" class="btn-fav" href="#"> 移入收藏夹</a>
											<a href="${pageContext.request.contextPath}/CartServlet?method=removecart&snackId=${obj.key.id }&stockNum=${obj.value }&taste=${obj.key.taste}"  data-point-url="#"  class="delete" > 删除</a>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</tr>
					</c:forEach>
					<span id="sumMoney" style="display:inline" lang="${sumMoney }"></span>
					<span id="sumCon" style="display:inline" lang="${sumCon }"></span>
				</div>
				<div class="clear"></div>

				<div class="float-bar-wrapper">
					<div id="J_SelectAll2" class="select-all J_SelectAll">
						<div class="cart-checkbox">
							<input class="check-all check" id="J_SelectAllCbx2" name="select-all" value="true" type="checkbox" onclick="selectAll();">
							<label for="J_SelectAllCbx2"></label>
						</div>
						<span>全选</span>
					</div>
					<div class="operations">
						<a href="#" hidefocus="true" class="deleteAll">删除</a>
						<a href="#" hidefocus="true" class="J_BatchFav">移入收藏夹</a>
					</div>
					<div class="float-bar-right">
						<div class="amount-sum">
							<span class="txt">已选商品</span>
							<span id="J_SelectedItemsCount" value="">0</span><span class="txt">件</span>
							<div class="arrow-box">
								<span class="selected-items-arrow"></span>
								<span class="arrow"></span>
							</div>
						</div>
						<div class="price-sum">
							<span class="txt">合计:</span>
							<strong class="price">¥<em id="J_Total">0</em></strong>
						</div>
						<div class="btn-area">
							<a href="${pageContext.request.contextPath}/CartServlet?method=topay" onclick="topay()" id="J_Go" class="submit-btn submit-btn-disabled" aria-label="请注意如果没有选择宝贝，将无法结算">
								<span>结&nbsp;算</span></a>
						</div>
					</div>

				</div>

				<div class="footer">
					<div class="footer-hd">
						<p>
							<a href="#"></a>
							<b>|</b>

							<a href="${pageContext.request.contextPath}/ShowServlet?method=index">商城首页</a>

							<b>|</b>
							<a href="#">支付宝</a>
							<b>|</b>
							<a href="#">物流</a>
						</p>
					</div>
					<div class="footer-bd">
						<p>
							<a href="#">关于恒望</a>
							<a href="#">合作伙伴</a>
							<a href="#">联系我们</a>
							<a href="#">网站地图</a>
							<em>© 2015-2025 Hengwang.com 版权所有</em>
						</p>
					</div>
				</div>

			</div>

			<!--操作页面-->

			<div class="theme-popover-mask"></div>

			<div class="theme-popover">
				<div class="theme-span"></div>
				<div class="theme-poptit h-title">
					<a href="javascript:;" title="关闭" class="close">×</a>
				</div>
				<div class="theme-popbod dform">
					<form class="theme-signin" name="loginform" action="" method="post">

						<div class="theme-signin-left">

							<li class="theme-options">
								<div class="cart-title">颜色：</div>
								<ul>
									<li class="sku-line selected">12#川南玛瑙<i></i></li>
									<li class="sku-line">10#蜜橘色+17#樱花粉<i></i></li>
								</ul>
							</li>
							<li class="theme-options">
								<div class="cart-title">包装：</div>
								<ul>
									<li class="sku-line selected">包装：裸装<i></i></li>
									<li class="sku-line">两支手袋装（送彩带）<i></i></li>
								</ul>
							</li>
							<div class="theme-options">
								<div class="cart-title number">数量</div>
								<dd>
									<input class="min am-btn am-btn-default" name="" type="button" value="-" />
									<input class="text_box" name="" type="text" value="1" style="width:30px;" />
									<input class="add am-btn am-btn-default" name="" type="button" value="+" />
									<span  class="tb-hidden">库存<span class="stock">1000</span>件</span>
								</dd>

							</div>
							<div class="clear"></div>
							<div class="btn-op">
								<div class="btn am-btn am-btn-warning">确认</div>
								<div class="btn close am-btn am-btn-warning">取消</div>
							</div>

						</div>
						<div class="theme-signin-right">
							<div class="img-info">
								<img src="images/kouhong.jpg_80x80.jpg" />
							</div>
							<div class="text-info">
								<span class="J_Price price-now">¥39.00</span>
								<span id="Stock" class="tb-hidden">库存<span class="stock">1000</span>件</span>
							</div>
						</div>

					</form>
				</div>
			</div>
		<!--引导 -->
		<div class="navCir">
			<li><a href="${pageContext.request.contextPath}/app/welcome.jsp"><i class="am-icon-home "></i>首页</a></li>
			<li><a href="sort.jsp"><i class="am-icon-list"></i>分类</a></li>
			<li class="active"><a href="shopcart.jsp"><i class="am-icon-shopping-basket"></i>购物车</a></li>	
			<li><a href="person/index.jsp"><i class="am-icon-user"></i>我的</a></li>					
		</div>
	</body>

</html>
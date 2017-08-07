<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>无线点餐后台管理</title>
</head>
	<!-- rows="100px,*,19px"  按 行 切 三等分 -->
	<frameset rows="100px,*,19px" framespacing="0" border="0" frameborder="0">
		<!-- 按顺序     第一行切的填充内容 -->
		<frame src="${pageContext.request.contextPath }/sys/public/top.jsp" scrolling="no" noresize /> 
		<!-- cols="178px,*" 按 列 切 二等分 -->
		<frameset cols="178px,*">
			<!-- 按顺序   第一  列  切的填充内容 -->
			<frame noresize src="${pageContext.request.contextPath }/sys/public/left.jsp" scrolling="yes" /> 
			<!-- 按顺序   第二  列  切的填充内容  -->
			<frame noresize name="right" src="${pageContext.request.contextPath }/sys/public/right.jsp" scrolling="yes" /> 
		</frameset>
		<!-- 按顺序   第三  行  切的填充内容  -->
		<frame noresize name="status_bar" scrolling="no" src="${pageContext.request.contextPath }/sys/public/bottom.jsp" />
	</frameset>
	<noframes>
		<body>
			你的浏览器不支持框架布局，推荐你使用<a href="http://www.firefox.com.cn/download/" style="text-decoration: none;">火狐浏览器</a>,
			<a href="http://www.google.cn/intl/zh-CN/chrome/" style="text-decoration: none;">谷歌浏览器</a>
		</body>
	</noframes>
</html>
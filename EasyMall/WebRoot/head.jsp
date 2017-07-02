<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/head.css" />
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />

<div id="common_head">
	<div id="line1">
		<div id="content">
			<c:if test="${!empty sessionScope.username }" var="emptyResult">
				欢迎：${sessionScope.username }
				<a href="${pageContext.request.contextPath }/LogoutServlet">注销</a>
			</c:if>

			<c:if test="${!emptyResult }">
				<a href="${pageContext.request.contextPath }/login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
					href="regist.jsp">注册</a>
			</c:if>
			<%-- <%
		Object obj=session.getAttribute("username");
		if(obj!=null &&! "".equals((String)obj)){
			out.print("欢迎"+(String)obj);%>
			<a href ="${pageContext.request.contextPath }/LogoutServlet">注销</a>
			<% 
		}else{%>
			<a href="${pageContext.request.contextPath }/login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="regist.jsp">注册</a>
		<%} %>
		 --%>
		</div>
	</div>
	<div id="line2">
		<input type="text" name="" /> <input type="button" value="搜 索" /> <span
			id="goto"> <a id="goto_order" href="#">我的订单</a> <a
			id="goto_cart" href="#">我的购物车</a> </span> <img id="erwm"
			src="${pageContext.request.contextPath }/img/head/qr.jpg" />
	</div>
	<div id="line3">
		<div id="content">
			<ul>
				<li><a href="#">首页</a>
				</li>
				<li><a href="#">全部商品</a>
				</li>
				<li><a href="#">手机数码</a>
				</li>
				<li><a href="#">电脑平板</a>
				</li>
				<li><a href="#">家用电器</a>
				</li>
				<li><a href="#">汽车用品</a>
				</li>
				<li><a href="#">食品饮料</a>
				</li>
				<li><a href="#">图书杂志</a>
				</li>
				<li><a href="#">服装服饰</a>
				</li>
				<li><a href="#">理财产品</a>
				</li>
			</ul>
		</div>
	</div>
</div>
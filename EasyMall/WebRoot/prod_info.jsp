<%@page language="java" import="java.util.*,java.net.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="css/prodInfo.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${app }/js/jquery-1.4.2.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$(".add_cart_but").click(function(){
				window.location.href="${app}/CartAddServlet?id=${prod.id}";
			
			});
		});
	</script>
</head>
<body>
<%@include file="/head.jsp" %>
	<div id="warp">
		<div id="left">
			<div id="left_top">
				<img src="${app }/ProdImgServlet?imgurl=${prod.imgurl}"/>
			</div>
			<div id="left_bottom">
				<img id="lf_img" src="${app }/img/prodInfo/lf.jpg"/>
				<img id="mid_img" src="${app }/ProdImgServlet?imgurl=${prod.imgurl}" width="60px" height="60px"/>
				<img id="rt_img" src="${app }/img/prodInfo/rt.jpg"/>
			</div>
		</div>
		<div id="right">
			<div id="right_top">
				<span id="prod_name">${prod.name } <br/></span>
				<br>
				<span id="prod_desc">${prod.description }<br/></span>
			</div>
			<div id="right_middle">
				<span id="right_middle_span">
					EasyMall 价：
				<span class="price_red">￥${prod.price }
				<br/>
			    运   费：满 100 免运费<br />
			    服    务：由EasyMall负责发货，并提供售后服务<br />
			    库    存：${prod.pnum }
	            <a href="javascript:void(0)" id="delNum" onclick="">-</a>
	            <input type="text" id="buyNumInp" name="buyNum" value="1">
		        <a href="javascript:void(0)" id="addNum" onclick="">+</a>
			</div>
			<div id="right_bottom">
				<input type="hidden" name="pid" value=""/>
				<input class="add_cart_but" type="submit" value=""/>	
			</div>
		</div>
	</div>
	<%@include file="/foot.jsp" %>
</body>
</html>
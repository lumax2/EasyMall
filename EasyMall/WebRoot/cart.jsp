<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
	<head>
		<link href="${app }/css/cart.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="${app }/js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
		$(function(){
		
			$(".del").click(function(){
				if(confirm("您确认删除吗？")){
					//jquery选择器
					var id =$(this).parent().parent().find("input[class=buyNumInp]").attr("id");
					//alert(id);
					window.location.href="${app}/CartDeleteServlet?id="+id;				
				}
			});
			
			$(".delNum").click(function(){
				//1获取商品的id
				var id = $(this).next().attr("id");
				//2获取输入框中的数量
				var num = $(this).next().val();
				//3计算减一后的结果
				num-=1;
				if(num>0){
					//4跳转
					window.location.href="${app}/CartEditServlet?id="+id+"&buynum="+num;
				}else{
					if(confirm("您确认删除吗？")){
						window.location.href="${app}/CartDeleteServlet?id="+id;
					}
				}
				
			});
			
			$(".addNum").click(function(){
				//1获取商品的id
				var id = $(this).prev().attr("id");
				//2获取输入框中的数量
				var num = $(this).prev().val();
				//3计算减一后的结果
				num	=parseInt(num)+1;
				//4跳转
				window.location.href="${app}/CartEditServlet?id="+id+"&buynum="+num;
			});
			$(".buyNumInp1").blur(function(){
				
				var num = $(this).val();
					//获取商品id
					var id = $(this).attr("id");
					//获取隐藏域中的值
					var oldNum = $("#hid_"+id).val();
				//如果num!=oldNum才执行修改操作
				if(num !=oldNum){
					//判断是否为零，
					if(num==0  ){
						window.location.href="${app}/CartDeleteServlet?id="+id;
						//判断输入的数据是否合法
					}else if(/^[1-9][0-9]*$/.test(num)){
						window.location.href="${app}/CartEditServlet?id="+id+"&buynum="+num;
					}else{
						alert("请输入大于等于零的整数!");
						$(this).val(oldNum);
					}
				}
			});
		});
			
		</script>
	</head>	
	<body>
	<%@include file="/head.jsp" %>
		<div class="warp">
			<!-- 标题信息 -->
			<div id="title">
				<input name="allC" type="checkbox" value="" onclick=""/>
				<span id="title_checkall_text">全选</span>
				<span id="title_name">商品</span>
				<span id="title_price">单价（元）</span>
				<span id="title_buynum">数量</span>
				<span id="title_money">小计（元）</span>
				<span id="title_del">操作</span>
			</div>
			<!-- 购物信息 -->
		<c:set var ="money" value="0"/>
		<c:forEach items="${cart }" var="entry">
			<div id="prods">
				<input name="prodC" type="checkbox" value="" onclick=""/>
				<img src="${app }/ProdImgServlet?imgurl=${entry.key.imgurl }" width="90" height="90" />
				<span id="prods_name">${entry.key.name }</span>
				<span id="prods_price">${entry.key.price}</span>
				<span id="prods_buynum"> 
					<a href="javascript:void(0)" class="delNum" >-</a>
					<input class="buyNumInp1" id="${entry.key.id }" type="text" value="${entry.value }" />
					<a href="javascript:void(0)" class="addNum" >+</a>
					<%-- <input type="hidden" id="hid_${entry.key.id}"/> --%>
					<input type="hidden" id="hid_${entry.key.id }" value="${entry.value }"/>
				</span>
				<span id="prods_money">${entry.value*entry.key.price }</span>
				<c:set var ="money" value="${money+entry.value*entry.key.price }"/>
				<span id="prods_del">
					<a href="javascript:void(0)" class="del">删除</a>
				</span>
			</div>
		</c:forEach>
			<!-- 总计条 -->
			<div id="total">
				<div id="total_1">
					<input name="allC" type="checkbox" value=""/> 
					<span>全选</span>
					<a id="del_a" href="#">删除选中的商品</a>
					<span id="span_1">总价：￥${page.money }</span>
					<span id="span_2"></span>
				</div>
				<div id="total_2">	
					<a id="goto_order" href="${app }/order_add.jsp">去结算</a>
				</div>
			</div>
		</div>
		<%@include file="/foot.jsp" %>
	</body>
</html>
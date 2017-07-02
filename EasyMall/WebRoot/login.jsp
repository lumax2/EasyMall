<%@page language="java" import="java.util.*,java.net.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="css/login.css" />
<title>EasyMall欢迎您登陆</title>
<script src="${pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
<script type="text/javascript" >
	$(function(){
		var uname = "${cookie.remname.value}";
		//alert(uname);
		$("#username").val(deCode(uname));
	});

</script>
</head>
<body>
	<h1>欢迎登陆EasyMall</h1>
	<!--浏览器加载页面，  -->
	<%String username ="";
		//获取所有的cookie
		Cookie[] cks =request.getCookies();
		
		if(cks!=null){
			for(int i=0;i<cks.length;i++){
				if("remname".equals(cks[i].getName())){
					username = cks[i].getValue();
					break;
				}
			}
		}
	 %>
	<form action="/LoginServlet" method="POST">
		<table>

			<tr>
				<td class="tdx">用户名：</td>
				<td>
				<input type="text" name="username" value="<%=URLDecoder.decode(username,"utf-8")%> "/>
				 <span
					style="color:red">${requestScope.username_msg }</span>
				</td>
			</tr>
			<tr>
				<td class="tdx">密&nbsp;&nbsp; 码：</td>
				<td>
				<input type="password" name="password" /> <span
					style="color:red">${requestScope.password_msg }</span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="checkbox" name="remname"value="true" />记住用户名 
				<input type="checkbox" name="autologin"value="true" />30天内自动登陆
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center">
				<input type="submit"value="登 陆" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center">
				<span style="color:red">
				${msg}
				</span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>

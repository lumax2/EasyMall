<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>欢迎注册EasyMall</title>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="css/regist.css" />
<script type="text/javascript" src="js/jquery-1.4.2.js"> </script>
<script type="text/javascript">
$(function()
	{
		
		$("#vcode").click(function() {
			$(this).attr("src", "/VerifyCodeServlet?" + Math.random());
		});

		//ajax
		$("input[name=username]").blur(
				function() {
					var val = $(this).val();
					if (!formObj.checkNull("username", "用户名不能 为空js")) {
						setMsg("username", "用户名不能 为空js");
					} else {
						//方式1：load
						/* $("#username_msg").load("/CheckUserNameServlet",{username:val},function(result){
							$("#username_msg").text(result);
						}); */
						//方式2：ajax
						$.post("/CheckUserNameServlet", {"username": val},
							function(result) {
							//$("#username_msg").html(result);
							if ("true" == result) {
								$("#username_msg").html("用户名已经存在js");
							} else if ("false" == result) {
								$("#username_msg").html(
										"<font color ='green'>用户名可用JS</font>");
							} else if ("error" == result) {
								$("#username_msg").html("系统错误，请重试JS");
							}
						});
					}
				});
	});
	//js中的json对象
	var formObj = {
		checkForm : function() {
				//alert("111");
			var flag = true;
			
			
			flag = this.checkNull("username", "用户名不能为空!js");
			//alert("flag1:"+flag);
			flag = this.checkNull("password", "密码不能为空!js") && flag;
			flag = this.checkNull("password2", "确认密码不能为空!js") && flag;
			flag =	this.checkEmail("email", "邮箱格式不正确!js") && flag;
			flag = this.checkNull("nickname", "昵称不能为空!js") && flag;
			flag = this.checkNull("email", "邮箱不能为空!js") && flag;
			flag = this.checkNull("valistr", "验证码不能为空!js") && flag;
			//alert("flag1:"+flag);
			flag = this.checkPassword("password", "两次密码不一致!js") && flag;
			//alert("flag2:"+flag);
			

			return flag;
		},
		checkNull : function(name, msg) {
			var value = $("input[name=" + name + "]").val();
			if ($.trim(value) == "") {
				this.setMsg(name, msg);
				return false;
			}
			return true;
		},
		checkPassword : function(name, msg) {
			//alert("aaaaaa");
			var pwd = $("input[name=" + name + "]").val();
				//alert("bbbbb");
			var pwd2 = $("input[name=" + name + "2]").val();
				//alert("bbbb");
			if ($.trim(pwd) != "" && $.trim(pwd2) != ""){
				if (pwd != pwd2) {
					this.setMsg(name, msg);
					return false;
				}
			}
					//alert("dddddd");
			return true;
		},
		checkEmail : function(name, msg) {
			var value = $("input[name=" + name + "]").val();
			var reg = /^\w+@\w+(\.\w+)+$/;
			if (!reg.test(value)) {
				this.setMsg(name, msg);
				return false;
			}
				return true;
		},
		setMsg : function(name, msg) {
			$("#" + name + "_msg").text(msg);
		}
	};
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath }/RegistServlet" method="post" onsubmit="return formObj.checkForm()">
	<%//避免重复提交表单1
	String token =UUID.randomUUID().toString();//产生一个随机的字符串
	session.setAttribute("token", token);
	%>
	 <input type="hidden" name="token" value="<%=token%>"></input>
		<h1>欢迎注册EasyMall</h1>
		<table>
			<tr>
				<%-- <td><font style="color:red"><%=request.getAttribute("msg") == null ? "" : request
					.getAttribute("msg")%></font>
				</td> --%>
			</tr>
			<tr>
				<td class="tds">用户名：</td>
				<td><input type="text" name="username"
					value="<%=request.getAttribute("username") == null ? "" : request
					.getAttribute("username")%>" />

					<span style="color:red" id="username_msg">${sessionScope.un_msg}</span></td>
			</tr>
			<tr>
				<td class="tds">密码：</td>
				<td><input type="password" name="password"
					value="<%=request.getAttribute("password") == null ? "" : request
					.getAttribute("password")%>" />
					<span style="color:red" id="password_msg">
					${requestScope.pwd_msg }
					<%-- <%=request.getAttribute("pwd_msg") == null ? "" : request
					.getAttribute("pwd_msg")%> --%></span>
				</td>
			</tr>
			<tr>
				<td class="tds">确认密码：</td>
				<td><input type="password" name="password2"
					value="<%=request.getAttribute("password2") == null ? "" : request
					.getAttribute("password2")%>" />
					<span style="color:red" id="password2_msg"> 
					${requestScope.pwd2_msg }
				<%-- 	<%=request.getAttribute("pwd2_msg") == null ? "" : request
					.getAttribute("pwd2_msg")%> --%></span>
				</td>
			</tr>
			<tr>
				<td class="tds">昵称：</td>
				<td><input type="text" name="nickname"
					value="<%=request.getAttribute("nickname") == null ? "" : request
					.getAttribute("nickname")%>" />
					<span style="color:red" id="nickname_msg">
					${requestScope.nn_msg }
					<%-- <%=request.getAttribute("nn_msg") == null ? "" : request
					.getAttribute("nn_msg")%> --%></span></td>
			</tr>
			<tr>
				<td class="tds">邮箱：</td>
				<td><input type="text" name="email"
					value="<%=request.getAttribute("email") == null ? "" : request
					.getAttribute("email")%>" />
					<span style="color:red" id="email_msg"> 
					${requestScope.email_msg }
					<%-- <%=request.getAttribute("email_msg") == null ? "" : request
					.getAttribute("email_msg")%> --%></span>
				</td>
			</tr>
			<tr>
				<td class="tds">验证码：</td>
				<td><input type="text" name="valistr" /> 
				<img id ="vcode" src="/VerifyCodeServlet" width="" height="" alt="" /> 
				<span style="color:red" id="valistr_msg">
				${requestScope.valistr_msg}
				<%-- <%=request.getAttribute("valistr_msg") == null ? "" : request
					.getAttribute("valistr_msg")%> --%></span>
				</td>
			</tr>
			<tr>
				<td class="sub_td" colspan="2" class="tds"><input type="submit"
					value="注册用户" /></td>
			</tr>
		</table>
	</form>
</body>
</html>

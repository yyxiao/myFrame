<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>阅读平台</title>
    <%@ include file="/include/meta.jsp"%>
    <script type="text/javascript">
	function login(){
		var u=  $("#UserName").val();
		var p= $("#Password").val();
		if(!u||u==''){
			$.dialog.alert('用户名不能为空!');
			return false;
		}
		if(!p||p==''){
			$.dialog.alert('密码不能为空!');
			return false;
		}
		
		$.getJSON('login!login.do?loginName='+u, {"loginPwd":p}, function(data){
			if(data.success){
				window.location.href = "${ctx}/user/users!view.do";
			}
			else{
				$.dialog.alert(data.msg);
			}
		});
	}
    </script>
    </head>
<body>
<div class="warp">
	<div class="title"></div>
    <div class="loginWin">
    	<table width="100%" height="282">
        	<tr height="60px">
            	<td colspan="2" class="user" >&nbsp;</td>
           	</tr>
            <tr height="60px">
            	<td align="right" class="name" width="90">账户</td>
                <td valign="middle">
                <input type="text" id="UserName" name="UserName" value="" class="longinput" /></td>
            </tr>
            <tr height="60px">
            	<td align="right" class="name" >密码</td>
                <td valign="middle"><input type="password"  id="Password" name="Password" value="" class="longinput"/></td>
            </tr>
            <tr>
            	<td>&nbsp;</td>
                <td><input type="submit"  value="登录" id="login" onclick="login();" class="btn"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>

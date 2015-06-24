$(function() {
	/* 用户登录信息验证 */
	$("#userName").focus(function() {
		var username = $(this).val();
		if (username == '输入用户名') {
			$(this).val('');
		}
	});
	$("#userName").focusout(function() {
		var username = $(this).val();
		if (username == '') {
			$(this).val('输入用户名');
		}
	});
	$("#pwd").focus(function() {
		var username = $(this).val();
		if (username == '输入密码') {
			$(this).val('');
		}
	});
	$("#pwd").focusout(function() {
		var username = $(this).val();
		if (username == '') {
			$(this).val('输入密码');
		}
	});
});

function but() {
	var u = $("#userName").val();
	var p = $("#pwd").val();
	if (!u || u == '' || u == '输入用户名') {
		$.dialog.alert('用户名不能为空!');
		return false;
	}
	if (!p || p == '' || p == '输入密码') {
		$.dialog.alert('密码不能为空!');
		return false;
	}
	$("#form1").submit();
}


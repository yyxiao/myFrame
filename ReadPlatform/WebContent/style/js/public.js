//注销登录
function login_off(){
	var message='<span style="font-size:16px;">您确认要退出系统吗?</span>';
	$.dialog.confirm(message, function(){ 
		top.location.href = '/ReadPlatform/login!logOff.do';
	}, function(){ 
	    return;
	}); 
}
//book——（想读、在读、读过）
function bookOperate(userId,bookId,type){
	if(userId==null||userId==''){
		$.dialog.alert('用户未登录!');
		return false;
	}
	if(type=='00A'){
		document.getElementById("type").value = "00A";
	}else if(type=='00B'){
		document.getElementById("type").value = "00B";
	}else if(type=='00C'){
		document.getElementById("type").value = "00C";
	}
	$("#formA").submit();
}
//book——（评论）
function bookPinglun(plinfo){
	document.getElementById("typeB").value = "00E";
	$("#plinfoB").attr("value",plinfo);
	$("#formB").submit();
}
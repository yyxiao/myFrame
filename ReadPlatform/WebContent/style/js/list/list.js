	$(function(){
			 var listWidth;
		   	 var winWidth;
		   		$(".navbar").click(function(){		
		   			if($(".floatnav").css("right")=="0px")
		   			{					
		   				$(".floatnav").animate({right:"-240px"},500);
		   				$(".title_position").animate({width:"99%"},500);
		   				$("#tablelist").animate({width:"99%"},500);
		   				if(navigator.appVersion.match(/7./i)=="7."&&navigator.userAgent.indexOf("MSIE")>0){
		   					$("#tablelist").css("padding-top","24px");
							}
		   			}
		   			else
		   			{
		   				$(".floatnav").animate({right:"0px"},500);
		   				$(".title_position").width("auto");
		   				$("#tablelist").width("auto");
		   				if(navigator.appVersion.match(/7./i)=="7."&&navigator.userAgent.indexOf("MSIE")>0){
		   					$("#tablelist").css("padding-top","5px");
							}
		   			}
		   		});	
		   		//IE7下修改样式
		   		if(navigator.appVersion.match(/7./i)=="7."&&navigator.userAgent.indexOf("MSIE")>0){
		   		winWidth = $(window).height();
				$(".floatnav").height(winWidth);
		   		$(window).resize(function()
				{
						winWidth = $(window).height();
						$(".floatnav").height(winWidth);
			   	})		
		   		}
		  });
	
	$(document).ready(function(e) {
	
		var str;
	$(".edit").click(function(e) {
		$(".checkboxs:checked").each(function(){ 
			$(this).parent().parent().find(".input").css("display","");
			$(this).parent().parent().find(".td").css("display","none");
		}) 
    });
	$(".sumbit").click(function(e) {
        $(".input").css("display","none");
		$(".td").css("display","");	
    });

	$(".input").blur(function(e) {
		var temp = $(this).val();
        $(this).parent().find(".td").text(temp);
    });
	
	$("tr").hover(function(){
		if($(this).find("th").length==0)
		{
			$(this).css("background-color","#EEE");
		}
		},function(){
			$("tr").css("background-color","#fff");
			$("tr:odd").css("background-color","#ECEFF4");		
	});
	
	$(".check_all").click(function(e) {
        if($(".check_all").attr("checked")=="checked")
		{
			$(".checkboxs").attr("checked","true");
		}
		else
		{
			$(".checkboxs").removeAttr("checked");
		}
    });
	
	
	
	$(".tablelist tr").contextMenu('myMenu', {
        bindings: {
            'edit': function(t) {
               window.location.href="biaodan.html";
            },
            'delete': function(e) {
			  $("#" + e.id).fadeOut(500,function(){
					 $("#" + e.id).remove();						
				});		            
            }
		}           

    });
		
	$("#search_green").click(function(e) {
         $("#search_plane").slideToggle("fast");
    });	
	});
	 function select(obj,eventTypeb){
         $.ajax({
             type: "post",
             dataType: "text",
             url: "/mfis/event/bus-event-basic!getEventTypes.do",
             data:"eventTypea="+obj.value,
             success: function(data){
             	$('#'+eventTypeb).html("<option value=''>--请选择--</option>");
                $('#'+eventTypeb).append(data);
             }
         });
     }
	//列表批量操作
	function doBatch(url, message, target) {
	var checkCount = 0;
	$('.tablelist tbody input[type=checkbox]').each(function(i, o) {
		if($(o).attr('checked')) {
			checkCount++;
		}
	});
	if(checkCount == 0) {
		$.dialog.alert('请选中一条以上的记录进行操作');
		return;
	}
	if(message) {
		$.dialog.confirm(message, function(){
			var targetstr = target ? 'target="'+target+'"' : "";
			var doBatchForm = $('<form id="doBatchForm" name="doBatchForm" action="'+url+'" '+ targetstr +' method="post"></form>');
			$(document.body).append(doBatchForm);
			$('.tablelist tbody input[type=checkbox]').each(function() {
				if($(this).attr('checked')) {
					doBatchForm.append($('<input type="hidden" name="ids" value="'+$(this).val()+'" />'));
				}
			});
			doBatchForm.submit();
		});
	}
	else {
		var targetstr = target ? 'target="'+target+'"' : "";
		var doBatchForm = $('<form id="doBatchForm" name="doBatchForm" action="'+url+'" '+ targetstr +' method="post"></form>');
		$(document.body).append(doBatchForm);
		$('.tablelist tbody input[type=checkbox]').each(function() {
			if($(this).attr('checked')) {
				doBatchForm.append($('<input type="hidden" name="ids" value="'+$(this).val()+'" />'));
			}
		});
		doBatchForm.submit();
	}
}

	//列表单个操作
	function doSingle(url, message, target) {
	var checkCount = 0;
	$('.tablelist tbody input[type=checkbox]').each(function(i, o) {
		if($(o).attr('checked')) {
			checkCount++;
		}
	});
	if(checkCount != 1) {
		$.dialog.alert('请选中一条记录进行操作');
		return;
	}
	if(message) {
		$.dialog.confirm(message, function() {
			var targetstr = target ? 'target="'+target+'"' : "";
			var doSingleForm = $('<form id="doSingleForm" name="doSingleForm" action="'+url+'" '+ targetstr +' method="post"></form>');
			$(document.body).append(doSingleForm);
			$('.tablelist tbody input[type=checkbox]').each(function() {
				if($(this).attr('checked')) {
					doSingleForm.append($('<input type="hidden" name="id" value="'+$(this).val()+'" />'));
				}
			});
			doSingleForm.submit();
		});
	}
	else {
		var targetstr = target ? 'target="'+target+'"' : "";
		var doSingleForm = $('<form id="doSingleForm" name="doSingleForm" action="'+url+'" '+ targetstr +' method="post"></form>');
		$(document.body).append(doSingleForm);
		$('.tablelist tbody input[type=checkbox]').each(function() {
			if($(this).attr('checked')) {
				doSingleForm.append($('<input type="hidden" name="id" value="'+$(this).val()+'" />'));
			}
		});
		doSingleForm.submit();
	}
}
  

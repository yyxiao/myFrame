
/*
 * 唯一性校验方法，后台返回"1"或"0"
 * validateName: 该校验规则的Name，在校验规则中使用
 * url: Ajax请求url
 * extend: 向后台传递的附加字段，需要传给后台的其他输入元素的id数组
 * message: 控件值不唯一时，页面显示的信息
 */
$.extend({
	uniqueValidate: function(validateName, url, extend, message) {
		jQuery.validator.addMethod(validateName, function(value, element) {
			var data = new Object();
			$.each(extend, function(i, o) {
				data[o] = $('#' + o).val();
			});
			data[element.id] = value;
			var returnBoolean = false;
			$.ajax({
				url: url,
				data: data,
				cache: false,
				async: false,
				type: 'POST',
				dataType: 'text',
				timeout: 10000,
				error: function() {
					jQuery.validator.messages[validateName] = '<span style="color:#EA5200;font-weight: bold;">&nbsp;对不起，服务器响应超时，请联系管理员</span>';
				},
				success: function(result) {
					if(result == '1') {
						returnBoolean = true;
					}
					else {
						returnBoolean = false;
					}
				}
			});
			return returnBoolean;
		}, "<span style='color:#EA5200;font-weight: bold;'>&nbsp;"+message+"</span>");
	}
});

/*
 * jQuery validation 验证类型扩展
 */
// 邮政编码验证
jQuery.validator.addMethod("isZipCode", function(value, element) {
  var zip = /^[0-9]{6}$/;
  return this.optional(element) || (zip.test(value));
}, "请正确填写您的邮政编码!");

// 身份证号码验证
jQuery.validator.addMethod("isIdCardNo", function(sId, element) {
	var flag=true;
  var idCard = /^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/;
  var iSum=0 ;
	var info="" ;
	var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
	if(!/^\d{17}(\d|x)$/i.test(sId)) flag=false; 
	sId=sId.replace(/x$/i,"a"); 
	if(aCity[parseInt(sId.substr(0,2))]==null) flag=false; 
	var sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2)); 
	var d=new Date(sBirthday.replace(/-/g,"/")) ;
	if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))flag=false; 
	for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
	if(iSum%11!=1) flag=false; 
  return this.optional(element) || (flag);
}, "请输入正确的身份证号码!");

// 手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
  var length = value.length;
  return this.optional(element) || (length == 11 && /^0{0,1}(133|153|180|189)[0-9]{8}$/.test(value));
},"<span style='color:#EA5200;font-weight: bold;'>&nbsp;请正确填写你的手机号码！<span>");

// 电话号码验证
jQuery.validator.addMethod("isPhone", function(value, element) {
  var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
  return this.optional(element) || (tel.test(value));
}, "请正确填写您的电话号码!");

// 用户名字符验证
jQuery.validator.addMethod("userName", function(value, element) {
  return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "用户名只能包括中文字、英文字母、数字和下划线!");

// 联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("isTel", function(value,element) {
    var length = value.length;
    var mobile = /^1[3|4|5|8][0-9]\d{4,8}$/;
    var tel = /^\d{3,4}-?\d{7,9}$/;
    return this.optional(element) || (tel.test(value) || mobile.test(value));
}, "<span style='color:#EA5200;font-weight: bold;'>&nbsp;请正确填写您的联系电话!</span>");

// IP地址验证
jQuery.validator.addMethod("ip", function(value, element) {
  return this.optional(element) || /^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/.test(value);
}, "请填写正确的IP地址！");

// 含有中文的最大字符长度校验
jQuery.validator.addMethod("cnRangelength", function(value, element, param) {
	var length = value.length;
	for(var i = 0; i < value.length; i++) {
		if(value.charCodeAt(i) > 127) {
			length++;
		}
	}
	return this.optional(element) || ( length >= param[0] && length <= param[1] );
}, jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串(一个中文长度为2)"));

// 只允许输入中文、英文字符，数字和下划线
jQuery.validator.addMethod("stringCheck", function(value, element) {
	return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "<span style='color:#EA5200;font-weight: bold;'>&nbsp;只能包括中文字、英文字母、数字和下划线</span>");

// 手机号码验证
jQuery.validator.addMethod("mobile", function(value, element) {
    var length = value.length;
    var mobile =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "手机号码格式错误");

// 电话号码验证
jQuery.validator.addMethod("phone", function(value, element) {
    var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
    return this.optional(element) || (tel.test(value));
}, "电话号码格式错误");

// 邮政编码验证
jQuery.validator.addMethod("zipCode", function(value, element) {
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "邮政编码格式错误");

// QQ号码验证
jQuery.validator.addMethod("qq", function(value, element) {
    var tel = /^[1-9]\d{4,9}$/;
    return this.optional(element) || (tel.test(value));
}, "qq号码格式错误");

// 字母和数字的验证
jQuery.validator.addMethod("chrnum", function(value, element) {
    var chrnum = /^([a-zA-Z0-9]+)$/;
    return this.optional(element) || (chrnum.test(value));
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");

// 中文的验证
jQuery.validator.addMethod("chinese", function(value, element) {
    var chinese = /^[\u4e00-\u9fa5]+$/;
    return this.optional(element) || (chinese.test(value));
}, "只能输入中文");

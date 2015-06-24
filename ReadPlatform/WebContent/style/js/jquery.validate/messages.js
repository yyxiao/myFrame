/*
* Translated default messages for the jQuery validation plugin.
* Locale: CN
*/
jQuery.extend(jQuery.validator.messages, {
    required: "必填",
    remote: "请修正该项",
    email: "请输入正确的电子邮件",
    url: "请输入合法的网址",
    date: "请输入有效日期",
    dateISO: "请输入有效日期",
    number: "请输入合法的数字",
    digits: "只能输入整数",
    creditcard: "请输入合法的信用卡号",
    equalTo: "请再次输入相同的值",
    accept: "请输入拥有合法后缀名的字符串",
    maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
    minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
    rangelength: jQuery.validator.format("长度介于 {0} 和 {1} 之间"),
    range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
    max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
    min: jQuery.validator.format("请输入一个最小为 {0} 的值")
});
/*
* 校验初始化
*/
$(function () {
    // 校验提示tip准备
    $('input, select, textarea').poshytip({
        className: 'tip-yellow',
        timeOnScreen: 3000,
        showOn: 'none',
        alignTo: 'target',
        alignX: 'right',
        alignY: 'center',
        offsetX: 5,
        offsetY: 5
    });
    // 表单校验
    var validate = $("form").validate({
        success: function (label) { }, // 注意！这句不能删除
        errorPlacement: function (error, element) {
            if (error.text() == '') {
                element.poshytip('update', error.text());
                element.poshytip('hide');
            }
            else {
                element.poshytip('update', error.text());
                element.poshytip('show');
            }
        }
    });
});
/*
* 唯一性校验方法，后台返回"1"或"0"
* validateName:该校验规则的Name，在校验规则中使用
* url: Ajax请求url
* extend: 向后台传递的附加字段，需要传给后台的其他输入元素的id数组
* message: 控件值不唯一时，页面显示的信息
*/
$.extend({
    uniqueValidate: function (validateName, url, extend, message) {
        jQuery.validator.addMethod(validateName, function (value, element) {
            var data = new Object();
            $.each(extend, function (i, o) {
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
                error: function () {
                    jQuery.validator.messages[validateName] = '对不起，服务器响应超时，请联系管理员';
                },
                success: function (result) {
                    if (result == '1') {
                        returnBoolean = true;
                    }
                    else {
                        returnBoolean = false;
                    }
                }
            });
            return returnBoolean;
        }, message);
    }
});
/*
* jQuery validation 验证类型扩展
*/
// 邮政编码验证
jQuery.validator.addMethod("isZipCode", function (value, element) {
    var zip = /^[0-9]{6}$/;
    return this.optional(element) || (zip.test(value));
}, "请正确填写您的邮政编码!");
// 身份证号码验证
jQuery.validator.addMethod("isIdCardNo", function (value, element) {
    var idCard = /^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/;
    return this.optional(element) || (idCard.test(value));
}, "请输入正确的身份证号码!");
// 手机号码验证
jQuery.validator.addMethod("isMobile", function (value, element) {
    var length = value.length;
    return this.optional(element) || (length == 11 && /^(((18[0-9]{1})|(15[0-9]{1})|(13[0-9]{1}))+\d{8})$/.test(value));
}, "请正确填写您的手机号码!");
// 电话号码验证
jQuery.validator.addMethod("isPhone", function (value, element) {
    var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的电话号码!");
// 用户名字符验证
jQuery.validator.addMethod("userName", function (value, element) {
    return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "用户名只能包括中文字、英文字母、数字和下划线!");
// 联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("isTel", function (value, element) {
    var length = value.length;
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    var tel = /^\d{3,4}-?\d{7,9}$/;
    return this.optional(element) || (tel.test(value) || mobile.test(value));
}, "请正确填写您的联系电话!");
// IP地址验证
jQuery.validator.addMethod("ip", function (value, element) {
    return this.optional(element) || /^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/.test(value);
}, "请填写正确的IP地址！");
// 含有中文的最大字符长度校验
jQuery.validator.addMethod("cnRangelength", function (value, element, param) {
    var length = value.length;
    for (var i = 0; i < value.length; i++) {
        if (value.charCodeAt(i) > 127) {
            length++;
        }
    }
    return this.optional(element) || (length >= param[0] && length <= param[1]);
}, jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串(一个中文长度为2)"));
// 只允许输入英文字符，数字和下划线
jQuery.validator.addMethod("charNo", function (value, element) {
    var length = value.length;
    var your_tel = '/[/^\W+$/]/g';
    return this.optional(element) || (!your_tel.test(value));
}, "请输入英文字符、数字、下划线！");
// 只允许输入中文、英文字符，数字和下划线
jQuery.validator.addMethod("stringCheck", function (value, element) {
    return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "只能包括中文字、英文字母、数字和下划线");
// 手机号码验证
jQuery.validator.addMethod("mobile", function (value, element) {
    var length = value.length;
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "手机号码格式错误");
// 电话号码验证
jQuery.validator.addMethod("phone", function (value, element) {
    var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
    return this.optional(element) || (tel.test(value));
}, "电话号码格式错误");
// 邮政编码验证
jQuery.validator.addMethod("zipCode", function (value, element) {
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "邮政编码格式错误");
// QQ号码验证
jQuery.validator.addMethod("qq", function (value, element) {
    var tel = /^[1-9]\d{4,11}$/;
    return this.optional(element) || (tel.test(value));
}, "qq号码格式错误");
// QQ号码验证
jQuery.validator.addMethod("qqex", function (value, element) {
    var tel = /^[1-9]\d{4,11}$/;
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    var email = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/;
    return this.optional(element) || (tel.test(value) || mobile.test(value) || email.test(value));
}, "qq号码格式错误");
// 字母和数字的验证
jQuery.validator.addMethod("chrnum", function (value, element) {
    var chrnum = /^([a-zA-Z0-9]+)$/;
    return this.optional(element) || (chrnum.test(value));
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");
// 中文的验证
jQuery.validator.addMethod("chinese", function (value, element) {
    var chinese = /^[\u4e00-\u9fa5]+$/;
    return this.optional(element) || (chinese.test(value));
}, "只能输入中文");
// 整数验证
jQuery.validator.addMethod("isInt", function (value, element) {
    var isInt = /^[0-9]{1,5}$/;
    return this.optional(element) || (isInt.test(value));
}, "只能输入整数");
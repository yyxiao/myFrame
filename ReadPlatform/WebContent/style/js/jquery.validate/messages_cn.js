/*
* Translated default messages for the jQuery validation plugin.
* Locale: CN
*/
jQuery.extend(jQuery.validator.messages, {
    required: "<span style='color:#EA5200;font-weight: bold;'>&nbsp;必填字段</span>",
    remote: "<span style='color:#EA5200;font-weight: bold;'>&nbsp;请修正该字段</span>",
    email: "<span style='color:#EA5200;font-weight: bold;'>&nbsp;请输入正确格式的电子邮件</span>",
    url: "<span style='color:#EA5200;font-weight: bold;'>&nbsp;请输入合法的网址</span>",
    date: "<span style='color:#EA5200;font-weight: bold;'>&nbsp;请输入合法的日期</span>",
    dateISO: "<span style='color:#EA5200;font-weight: bold;'>&nbsp;请输入合法的日期 (2001-01-01)</span>",
    number: "<span style='color:#EA5200;font-weight: bold;'>&nbsp;请输入合法的数字</span>",
    digits: "<span style='color:#EA5200;font-weight: bold;'>&nbsp;只能输入整数</span>",
    creditcard: "<span style='color:#EA5200;font-weight: bold;'>&nbsp;请输入合法的信用卡号</span>",
    equalTo: "<span style='color:#EA5200;font-weight: bold;'>&nbsp;请再次输入相同的值</span>",
    accept: "<span style='color:#EA5200;font-weight: bold;'>&nbsp;请输入拥有合法后缀名的字符串</span>",
    maxlength: jQuery.validator.format("<span style='color:#EA5200;font-weight: bold;'>&nbsp;请输入一个长度最多是 {0} 的字符串</span>"),
    minlength: jQuery.validator.format("<span style='color:#EA5200;font-weight: bold;'>&nbsp;请输入一个长度最少是 {0} 的字符串</span>"),
    rangelength: jQuery.validator.format("<span style='color:#EA5200;font-weight: bold;'>&nbsp;请输入一个长度介于 {0} 和 {1} 之间的字符串</span>"),
    range: jQuery.validator.format("<span style='color:#EA5200;font-weight: bold;'>&nbsp;请输入一个介于 {0} 和 {1} 之间的值</span>"),
    max: jQuery.validator.format("<span style='color:#EA5200;font-weight: bold;'>&nbsp;请输入一个最大为 {0} 的值</span>"),
    min: jQuery.validator.format("<span style='color:#EA5200;font-weight: bold;'>&nbsp;请输入一个最小为 {0} 的值</span>")
});

//界面验证控制
var validOptions = {
    invalidHandler: function (e, validator) {
        var errors = validator.numberOfInvalids();
        if (errors) {
            var message = validator.errorList[0].message;
            $(validator.errorList[0].element).focus().select();
            $.showMessage("<span style='color:#EA5200;font-weight: bold;'>&nbsp;"+message+"<span>", 'error');
        }
    },
    onfocusout: function (element, event) {
        if (!this.checkable(element) && (element.name in this.submitted || !this.optional(element))) {
            this.element(element)
        }
    },
    showErrors: function () {
        for (var i = 0; this.errorList[i]; i++) {
            var error = this.errorList[i];
            this.settings.highlight && this.settings.highlight.call(this, error.element, this.settings.errorClass, this.settings.validClass);
            //this.showLabel(error.element, error.message);
        }
        if (this.errorList.length) {
            this.toShow = this.toShow.add(this.containers);
        }
        if (this.settings.unhighlight) {
            for (var i = 0, elements = this.validElements(); elements[i]; i++) {
                this.settings.unhighlight.call(this, elements[i], this.settings.errorClass, this.settings.validClass);
            }
        }
        this.toHide = this.toHide.not(this.toShow);
        this.hideErrors();
        this.addWrapper(this.toShow).show();
    }
};
$(document).ready(function () {
    $('.msginfo').click(function () {
        $('.tips').fadeOut();
    });
});
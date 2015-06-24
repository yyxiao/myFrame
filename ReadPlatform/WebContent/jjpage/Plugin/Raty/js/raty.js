;(function ($) {
    $.fn.Score = function (options) {

        var setting = {
            value: 0,
            onselect: function (index) { }
        };

        var target = this;

        $("<span>").html("评论:").appendTo(target);
        $("<ul/>").appendTo(target)
            .append("<li><a href'javascript:;'>1</a></li>")
            .append("<li><a href'javascript:;'>2</a></li>")
            .append("<li><a href'javascript:;'>3</a></li>")
            .append("<li><a href'javascript:;'>4</a></li>")
            .append("<li><a href'javascript:;'>5</a></li>");
        $("<span>").appendTo(target);
        $("<p>").appendTo(target);

        var aLi = target.find("li");
        var oUl = target.find("ul").first();
        var oSpan = target.find("span").last();
        var oP = target.find("p").first();
        var i = iScore = iStar = 0;

        var aMsg = [
                    "很不满意|差得太离谱",
                    "不满意|部分有破损",
                    "一般|质量一般",
                    "满意|质量不错",
                    "非常满意|质量非常好"
        ];

        $.extend(setting, options || {});

        fnPoint(setting.value);

        for (i = 1; i <= aLi.length; i++) {
            aLi[i - 1].index = i;

            aLi[i - 1].onmouseover = function () {
                fnPoint(this.index);
                oP.css({ "display": "block", left: oUl.offsetLeft + this.index * this.offsetWidth - 104 });

                oP.html("<em><b>" + this.index + "</b> 分 " + aMsg[this.index - 1].match(/(.+)\|/)[1] + "</em>" + aMsg[this.index - 1].match(/\|(.+)/)[1]);
            };

            aLi[i - 1].onmouseout = function () {
                fnPoint();
                oP.css("display","none");
            };

            aLi[i - 1].onclick = function () {
                iStar = this.index;
                oP.css("display", "none");
                oSpan.html("<strong>" + (this.index) + " 分</strong> (" + aMsg[this.index - 1].match(/\|(.+)/)[1] + ")");
                setting.onselect(this.index);
            }
        }

        function fnPoint(iArg) {
            iScore = iArg || iStar;
            for (i = 0; i < aLi.length; i++) aLi[i].className = i < iScore ? "on" : "";
        }

        return this;
    };

})(jQuery);
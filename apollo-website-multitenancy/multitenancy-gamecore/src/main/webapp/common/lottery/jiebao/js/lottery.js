$(function(){
    var a = Cookies.get("_theme"),
    e = Cookies.get("_themeColor"),
    g = Cookies.get("_themeHighlight"),
    c = Cookies.get("_themeHighlightColor");
	a = a?("#" +a):_theme;
	e = e?("#"+e):_themeColor;
	g = g?("#"+g):_themeHighlight;
	c = c?("#"+c):_themeHighlightColor;
	Core.changeColor(a, e, g, c);
    a = $(document).height();
    e = $("#slider_menu").height();
    (a-106) > e && $("#slider_menu").css("height", (a-114));
    $("body").on("click", ".game_select a", function() {
    	var m =$("#slider_menu"),winWidth=$(document.body).width(),a=(winWidth - 970)/2,pl=0,ml="auto";
		if(m.hasClass("hideSide")){
			if(a>211){
				m.css("paddingLeft",(a-211));
	    	}else{
	    		$(".main_content").animate({"marginLeft":211},"slow");
	    	}
			m.removeClass("hideSide").addClass("showSide");
		}else{
			m.removeClass("showSide").addClass("hideSide");
			m.css("paddingLeft",0);
			$(".main_content").animate({"marginLeft":a},"slow");
		}
        return false;
    });
    $("body").on("click", ".game_list li a", function() {
        var a = $(this),scsp=$("#saiCheShiPin"),lotCode=a.attr("lotcode");
        scsp.hide();
        if(lotCode=='BJSC'){
        	if(scsp.attr("status")){
        		if(scsp.attr("status")=="on"){
        			$("#saiCheShiPin").show();
        		}
        	}else{
        		$.get(base+"/agentSetting/saiCheSwfStatus.do",function(d){
        			scsp.attr("status",d);
                    if(d && d=='on'){
                    	scsp.show();
                    }
                },"html");
        	}
        }
        if (0 <= $("body #bet_greview").length) $("#BetNumberListContanior table tbody tr").length ? dialog({
            title: "\u7cfb\u7edf\u63d0\u793a",
            content: "\u6295\u6ce8\u9805\u4e2d\u6709\u60a8\u6240\u6dfb\u52a0\u7684\u6ce8\u55ae\u9805\u76ee\uff0c\u4f60\u786e\u5b9a\u53d6\u6d88\u6ce8\u5355\uff0c\u5207\u6362\u5f69\u985e\u5417\uff1f",
            okValue: "\u786e\u5b9a",
            ok: function() {
                $(".r5").addClass("hide");
                $("a#clearallitem").click();
                $(".game_list li").removeClass("current");
                a.parent().addClass("current");
                $("a#nowGP b").attr("gpid",
                    a.attr("gpid"));
                $("div#betmenu_data , #betplay_data").empty();
                $(".gp_name").attr("gpid", a.attr("gpid"));
                $(".gp_name").text(a.text());
                $("body a#nowGP").click();
                this.close().remove();
                Core.menu(!0);
                return !1
            },
            cancelValue: "\u53d6\u6d88",
            cancel: function() {}
        }).width(400).showModal() : ($("a#clearallitem").click(), $(".game_list li").removeClass("current"), a.parent().addClass("current"), $("a#nowGP b").attr("gpid", a.attr("gpid")), $("div#betmenu_data , #betplay_data").empty(), $(".gp_name").attr("gpid", a.attr("gpid")),
            $(".gp_name").text(a.text()), $("body a#nowGP").click(), Core.menu(!0));
        else return window.location.reload(), !1
    });
    $(window).resize(function() {
        $("#slider_menu").hasClass("showSide") && $(".game_select a").click();
        $("#slider_thememenu").css("width", 0).hide();
        $("a#theme").removeClass("shows hides").addClass("hides");
        $(".main_content").css({"marginLeft":"auto"});
    });
});
var Core ={
	changeColor:function (a, e, g, c) {//修改皮肤样式
	    $("#jsStyle").remove();
	    return $("head").append("<style id='jsStyle'> /* \u80cc\u666f\u8272 */ body { background: " + a + " !important; } /* \u6309\u9215\u9078\u4e2d */ #nav .current,                /* \u6700\u4e0a\u65b9\u529f\u80fd\u9078\u55ae */ #bettop_menu .current,        /* \u6b21\u9078\u55ae */ #Bm_general > li.current > a, /* \u5f69\u7a2e\u73a9\u6cd5 */ #Bm_optional > li.current > a,/* \u4efb\u9078\u73a9\u6cd5 */ #srcListType li.current > a,  /* \u8a02\u55ae\u7ba1\u7406 */ #userinfotop_menu li.current > a, /* \u5e33\u6236\u4e2d\u5fc3 */ .selected,                    /* \u9078\u53d6\u7403\u865f */ .row.r5 .current > a,          /* \u8ffd\u865f\u6a21\u5f0f */ .step > .current              /* \u6b65\u9a5f(\u9280\u884c\u5361) */ { background-color:  " + e +
	        " !important; } /* \u908a\u6846 */ .open_result_no > span, /* \u958b\u734e\u7403\u865f */ #gamebuy,               /* \u78ba\u5b9a\u6295\u6ce8 */ .vote_total,            /* \u78ba\u5b9a\u6295\u6ce8\u3001\u7e3d\u4e0b\u6ce8\u984d */ .vote_total > div       /* \u78ba\u5b9a\u6295\u6ce8\u3001\u7e3d\u4e0b\u6ce8\u984d\u5167\u7684\u5206\u9694\u7dda */ { border-color: " + g + " !important; } /* \u7403\u865f\u4f4d\u7f6e\u3001\u7279\u6b8a\u865f\u6a19\u8a8c */ .no_sum_title,          /* \u7279\u6b8a\u865f\u78bc */ .digits                 /* \u6578\u5b57\u55ae\u4f4d */ { background-color: " +
	        g + " !important; } /* \u6587\u5b57\u8272\u5f69 */ #gamebuy,               /* \u78ba\u5b9a\u6295\u6ce8\u6587\u5b57 */ .positionre > a,        /* \u6ce8\u55ae\u865f */ .arr_top > i            /* \u7e3d\u4e0b\u6ce8\u984d\u4e0a\u65b9\u5c0f\u7bad\u982d */ { color: " + g + " !important; } /* \u73a9\u6cd5\u8aaa\u660e */ .row_con_c1_btm .fa-stack i{ color: " + g + " !important; } .row_con_c1_btm a { border-color: " + g + " !important; color: " + g + " !important; } /* fontAwesome */ .fa-customColor > i { color: " + c + " !important; } .fa-customColor > .fa-circle { color: " +
	        g + " !important; } </style>")
	}
};
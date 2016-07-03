<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>${website_name }</title>
    <link href="${base}/common/lottery/jiebao/css/all_f.css?v=${caipiao_version}" rel="stylesheet" type="text/css">
    <!--[if lte IE 8]>
    <link href="${base}/common/lottery/jiebao/css/ie_hack.css?v=${caipiao_version}" rel="stylesheet" type="text/css" />
    <![endif]-->
    <script>
    	var base = '${base}';
        var caipiao_version = '${caipiao_version}';
        var lotCode = '${lotCode}';

        var _theme = '${_theme}';
        var _themeColor = '${_themeColor}';
        var _themeHighlight = '${_themeHighlight}';
        var _themeHighlightColor = '${_themeHighlightColor}';
    </script>
    <noscript>
        <div class="noScript">
            <div>
                <h2>请开启浏览器的Javascript功能</h2>
                <p>亲，没它我们玩不转啊！求您了，开启Javascript吧！
                    <br/> 不知道怎么开启Javascript？那就请
                    <a href="http://www.baidu.com/s?wd=%E5%A6%82%E4%BD%95%E6%89%93%E5%BC%80Javascript%E5%8A%9F%E8%83%BD" rel="nofollow" target="_blank">猛击这里</a>！
                </p>
            </div>
        </div>
    </noscript>
</head>

<body>
<!-- menu -->
<div class="hideSide" id="slider_menu">
    <div class="slider_con" id="lotsaleMenu">
    	<c:forEach begin="1" end="6" var="ii">
    	<c:if test="${lotMap.containsKey(ii) }">
    		<h2><c:choose><c:when test="${ii == 1}">时时彩</c:when><c:when test="${ii == 2}">低频彩</c:when><c:when test="${ii == 3}">快开</c:when><c:when test="${ii == 4}">快三</c:when><c:when test="${ii == 5}">11选5</c:when><c:when test="${ii == 6}">香港彩</c:when></c:choose></h2>
			<ul class="game_list">
    		<c:forEach var="gl" items="${lotMap.get(ii)}">
    		<li ${lotCode == gl.code ? 'class="current"': ''}><a href="javascript:;" lotcode="${gl.code}">${gl.name}</a></li>
    		</c:forEach></ul>
    	</c:if>
    	</c:forEach>
    </div>
</div>
<!-- Color menu -->
<div style="width: 0px;" id="slider_thememenu">
    <div style="width: 206px;">
        <h3>请选择配色</h3>
        <ul id="themeContent">
            <!-- 預設顏色 -->
            <li>
                <div class="color1" style="background: rgb(102, 101, 78) none repeat scroll 0% 0%; border-color: rgb(127, 126, 103);"></div>
                <div class="color2" style="background: rgb(171, 106, 29) none repeat scroll 0% 0%; border-color: rgb(196, 131, 54);"></div>
                <div class="color3" style="background: rgb(254, 255, 168) none repeat scroll 0% 0%; border-color: rgb(255, 255, 193);" txtcolor="#A3A39B"></div>
                <p>01</p>
            </li>
            <!-- 九號 -->
            <li>
                <div class="color1" style="background: rgb(60, 60, 60) none repeat scroll 0% 0%; border-color: rgb(85, 85, 85);"></div>
                <div class="color2" style="background: rgb(250, 150, 30) none repeat scroll 0% 0%; border-color: rgb(255, 175, 55);"></div>
                <div class="color3" style="background: rgb(250, 250, 0) none repeat scroll 0% 0%; border-color: rgb(255, 255, 25);" txtcolor="#202020"></div>
                <p>02</p>
            </li>
            <li>
                <div class="color1" style="background: rgb(88, 89, 91) none repeat scroll 0% 0%; border-color: rgb(113, 114, 116);"></div>
                <div class="color2" style="background: rgb(90, 201, 244) none repeat scroll 0% 0%; border-color: rgb(115, 226, 255);"></div>
                <div class="color3" style="background: rgb(48, 149, 216) none repeat scroll 0% 0%; border-color: rgb(73, 174, 241);" txtcolor="#FFFFFF"></div>
                <p>03</p>
            </li>
            <li>
                <div class="color1" style="background: rgb(197, 196, 160) none repeat scroll 0% 0%; border-color: rgb(222, 221, 185);"></div>
                <div class="color2" style="background: rgb(119, 80, 18) none repeat scroll 0% 0%; border-color: rgb(144, 105, 43);"></div>
                <div class="color3" style="background: rgb(238, 128, 40) none repeat scroll 0% 0%; border-color: rgb(255, 153, 65);" txtcolor="#FFFFFF"></div>
                <p>04</p>
            </li>
            <!-- 各廳顏色 -->
            <li>
                <!-- 星彩 -->
                <div class="color1" style="background: rgb(41, 46, 30) none repeat scroll 0% 0%; border-color: rgb(66, 71, 55);"></div>
                <div class="color2" style="background: rgb(150, 73, 203) none repeat scroll 0% 0%; border-color: rgb(175, 98, 228);"></div>
                <div class="color3" style="background: rgb(175, 187, 242) none repeat scroll 0% 0%; border-color: rgb(200, 212, 255);" txtcolor="#202020"></div>
                <p>05</p>
            </li>
            <li>
                <!-- 金巴黎 -->
                <div class="color1" style="background: rgb(234, 218, 162) none repeat scroll 0% 0%; border-color: rgb(255, 243, 187);"></div>
                <div class="color2" style="background: rgb(206, 108, 71) none repeat scroll 0% 0%; border-color: rgb(231, 133, 96);"></div>
                <div class="color3" style="background: rgb(255, 208, 70) none repeat scroll 0% 0%; border-color: rgb(255, 233, 95);" txtcolor="#202020"></div>
                <p>06</p>
            </li>
            <li>
                <!-- 創世紀 -->
                <div class="color1" style="background: rgb(43, 43, 43) none repeat scroll 0% 0%; border-color: rgb(68, 68, 68);"></div>
                <div class="color2" style="background: rgb(255, 20, 24) none repeat scroll 0% 0%; border-color: rgb(255, 45, 49);"></div>
                <div class="color3" style="background: rgb(243, 255, 20) none repeat scroll 0% 0%; border-color: rgb(255, 255, 45);" txtcolor="#202020"></div>
                <p>07</p>
            </li>
            <li>
                <!-- w彩票 -->
                <div class="color1" style="background: rgb(39, 39, 39) none repeat scroll 0% 0%; border-color: rgb(64, 64, 64);"></div>
                <div class="color2" style="background: rgb(126, 61, 118) none repeat scroll 0% 0%; border-color: rgb(151, 86, 143);"></div>
                <div class="color3" style="background: rgb(255, 255, 0) none repeat scroll 0% 0%; border-color: rgb(255, 255, 25);" txtcolor="#202020"></div>
                <p>08</p>
            </li>
            <li>
                <!-- 雲頂 -->
                <div class="color1" style="background: rgb(130, 0, 2) none repeat scroll 0% 0%; border-color: rgb(155, 25, 27);"></div>
                <div class="color2" style="background: rgb(250, 150, 30) none repeat scroll 0% 0%; border-color: rgb(255, 175, 55);"></div>
                <div class="color3" style="background: rgb(250, 250, 0) none repeat scroll 0% 0%; border-color: rgb(255, 255, 25);" txtcolor="#202020"></div>
                <p>09</p>
            </li>
            <li>
                <!-- 未定 -->
                <div class="color1" style="background: rgb(128, 209, 153) none repeat scroll 0% 0%; border-color: rgb(153, 234, 178);"></div>
                <div class="color2" style="background: rgb(35, 51, 41) none repeat scroll 0% 0%; border-color: rgb(60, 76, 66);"></div>
                <div class="color3" style="background: rgb(104, 254, 242) none repeat scroll 0% 0%; border-color: rgb(129, 255, 255);" txtcolor="#202020"></div>
                <p>10</p>
            </li>
            <li>
                <!-- 皇冠 -->
                <div class="color1" style="background: rgb(197, 196, 160) none repeat scroll 0% 0%; border-color: rgb(222, 221, 185);"></div>
                <div class="color2" style="background: rgb(119, 80, 18) none repeat scroll 0% 0%; border-color: rgb(144, 105, 43);"></div>
                <div class="color3" style="background: rgb(238, 128, 40) none repeat scroll 0% 0%; border-color: rgb(255, 153, 65);" txtcolor="#202020"></div>
                <p>11</p>
            </li>
            <li>
                <!-- 彩票在線 -->
                <div class="color1" style="background: rgb(255, 230, 111) none repeat scroll 0% 0%; border-color: rgb(255, 255, 136);"></div>
                <div class="color2" style="background: rgb(0, 0, 0) none repeat scroll 0% 0%; border-color: rgb(25, 25, 25);"></div>
                <div class="color3" style="background: rgb(255, 255, 111) none repeat scroll 0% 0%; border-color: rgb(255, 255, 136);" txtcolor="#202020"></div>
                <p>12</p>
            </li>
            <li>
                <!-- 未定 -->
                <div class="color1" style="background: rgb(63, 124, 171) none repeat scroll 0% 0%; border-color: rgb(88, 149, 196);"></div>
                <div class="color2" style="background: rgb(149, 176, 187) none repeat scroll 0% 0%; border-color: rgb(174, 201, 212);"></div>
                <div class="color3" style="background: rgb(189, 210, 239) none repeat scroll 0% 0%; border-color: rgb(214, 235, 255);" txtcolor="#202020"></div>
                <p>13</p>
            </li>
            <li>
                <!-- 未定 -->
                <div class="color1" style="background: rgb(90, 177, 187) none repeat scroll 0% 0%; border-color: rgb(115, 202, 212);"></div>
                <div class="color2" style="background: rgb(30, 21, 42) none repeat scroll 0% 0%; border-color: rgb(55, 46, 67);"></div>
                <div class="color3" style="background: rgb(255, 234, 99) none repeat scroll 0% 0%; border-color: rgb(255, 255, 124);" txtcolor="#202020"></div>
                <p>14</p>
            </li>
            <li>
                <!-- 未定 -->
                <div class="color1" style="background: rgb(170, 90, 125) none repeat scroll 0% 0%; border-color: rgb(195, 115, 150);"></div>
                <div class="color2" style="background: rgb(118, 0, 50) none repeat scroll 0% 0%; border-color: rgb(143, 25, 75);"></div>
                <div class="color3" style="background: rgb(234, 245, 106) none repeat scroll 0% 0%; border-color: rgb(255, 255, 131);" txtcolor="#202020"></div>
                <p>15</p>
            </li>
            <li>
                <!-- 未定 -->
                <div class="color1" style="background: rgb(255, 136, 181) none repeat scroll 0% 0%; border-color: rgb(255, 161, 206);"></div>
                <div class="color2" style="background: rgb(106, 61, 55) none repeat scroll 0% 0%; border-color: rgb(131, 86, 80);"></div>
                <div class="color3" style="background: rgb(57, 148, 221) none repeat scroll 0% 0%; border-color: rgb(82, 173, 246);" txtcolor="#202020"></div>
                <p>16</p>
            </li>
            <li>
                <!-- 未定 -->
                <div class="color1" style="background: rgb(255, 185, 143) none repeat scroll 0% 0%; border-color: rgb(255, 210, 168);"></div>
                <div class="color2" style="background: rgb(229, 89, 53) none repeat scroll 0% 0%; border-color: rgb(254, 114, 78);"></div>
                <div class="color3" style="background: rgb(254, 0, 0) none repeat scroll 0% 0%; border-color: rgb(255, 25, 25);" txtcolor="#202020"></div>
                <p>17</p>
            </li>
            <li>
                <!-- 港龍 -->
                <div class="color1" style="background: rgb(248, 237, 219) none repeat scroll 0% 0%; border-color: rgb(255, 255, 244);"></div>
                <div class="color2" style="background: rgb(233, 86, 104) none repeat scroll 0% 0%; border-color: rgb(255, 111, 129);"></div>
                <div class="color3" style="background: rgb(72, 68, 65) none repeat scroll 0% 0%; border-color: rgb(97, 93, 90);" txtcolor="#202020"></div>
                <p>18</p>
            </li>
        </ul>
    </div>
</div>
<!--  header -->
<div class="container bg_gray7">
    <div id="header" class="wrapper">
        <h1 id="logo"><a href="${base }"><img <c:choose><c:when test="${not empty lotteryPageLogoUrl }">src="${lotteryPageLogoUrl }"</c:when><c:otherwise>src="${base}/common/lottery/jiebao/images/default_logo.png"</c:otherwise></c:choose> height="70" width="170"></a></h1>
        <div id="cat_nav">
            <ul>
                <li><a href="${base }/lottery/index.do?lotCode=${lotCode}" class="current">彩 票<span class="line"></span></a></li>
                <c:if test="${empty duLiCaiPiaoOpened || !duLiCaiPiaoOpened }">
                 <c:if test="${dianZiYouYiOpened }"><li><a href="${base }/index_.do?param=zrsx">真人视讯<span class="line"></span></a></li></c:if>
                <c:if test="${zhenRenYuLeOpened }"><li><a href="${base }/index_.do?param=dzyy">电子游艺<span class="line"></span></a></li></c:if>
                </c:if>
            </ul>
        </div>
        <div id="nav">
            <ul>
                <li id="b1"><i></i><a <c:choose><c:when test="${not empty base && base!=''}">href="${base}"</c:when><c:otherwise>href="/"</c:otherwise></c:choose> class="btn">回首页</a></li>
                <li id="b2"><i></i><a href="javascript: void(0);" _layer="save" class="btn">在线存款</a></li>
                <li id="b3"><i></i><a href="javascript: void(0);" _layer="cash" class="btn">在线取款</a></li>
                <li id="b4"><i></i><a href="javascript: void(0);" _layer="info" class="btn">账户中心</a></li>
                <li id="b5"><i></i><a href="javascript:;" class="btn playRule">玩法介绍</a></li>
                <li id="b6"><i></i><a <c:choose><c:when test="${not empty customerServiceUrlLink }">href="${customerServiceUrlLink}"</c:when><c:otherwise>href="javascript: void(0);"</c:otherwise></c:choose> target="_blank" class="btn">客 服</a></li>
            </ul>
        </div>
        <div id="acc_info">
            <p>
                <span class="acc_id"><i></i><label>${user.account }</label></span> /
                <span class="money">帐户金额 <i id="balance">0.00</i></span> /
                <span>未读讯息<a href="javascript:;" id="unreadSent">( 0 )</a></span> /
                <a href="javascript:;" >系统公告</a>
                <a href="${base }/logout.do" class="btn_logout btn b2">安全退出</a>
                <a href="javascript:;" class="btn_logout btn b2 hides" id="theme">更换皮肤</a>
            </p>
        </div>
    </div>
</div>
<div class="main_content">
	<!-- 系统公告 -->
	<div class="row bg_black5" style="margin: 7px auto 0 auto;padding:5px 0px;overflow: auto;zoom: 1;">
		<div class="fa-stack c_white3 fa-customColor f_left" style="margin-left:10px;">
			<i class="fa fa-circle fa-stack-2x"></i>
			<i class="fa fa-bullhorn fa-stack-1x fa-inverse"></i>
		</div><div class="f_left"style="margin-top: 6px;">&nbsp;最新消息：</div>
		<marquee id="xitonggonggao_content" scrollamount="3" scrolldelay="150" direction="left" onmouseover="this.stop();" onmouseout="this.start();" style="cursor: pointer; width:800px;height:18px;margin:6px 0 0 14px;float:left;">
	        尊敬的会员：欢迎光临！
	    </marquee>
	</div>
	<!-- bettop_menu -->
	<div class="row r1 bg_black5" style="margin: 7px auto 0 auto;" id="bettop_menu">
	    <div class="game_select"><a href="javascript:;" class="btn b1">&nbsp;<i class="fa fa-bars"></i> 选 择 彩 种</a></div>
	    <div class="game_title">
	        <i class="fa fa-caret-left fa-lg"></i>
	        <a href="javascript:;" class="btn b0 current" id="nowGP">
	            <b class="btn gp_name">${curLot.name }</b>
	        </a>
	    </div>
	    <ul>
	        <li><a href="javascript:;" id="order_history" class="btn b0">订单管理</a></li>
	        <li><a href="javascript:;"id="sbet_paper" class="btn b0">投注报表</a></li>
	        <li><a href="javascript:;" id="result" class="btn b0">开奖结果</a></li>
	        <li><a href="javascript:;" id="bonus" class="btn b0">奖金对照</a></li>
	    </ul>
	    <div class="sub_logo"></div>
	</div>
	<div class="container" id="layoutright">
		<div class="wrapper main hideMain" id="bet_greview">
		    <div class="row r2">
		        <div class="r2_c1 bg_black3">
		            <div class="clock_ico" style="background-image: none;"><a href="javascript:;"><span id="soundNotification" class="fa-stack fs12" data-soundon="true"><i class="fa fa-volume-up fa-stack-1x"></i><i class="fa fa-circle-thin fa-stack-2x"></i></span></a></div>
		            <div class="divClock">
		                <div class="top"></div>
		                <div class="middle"></div>
		                <div class="circle">
		                    <div class="center">
		                        <div class="hour" style="transform: rotate(0.41665deg);">
		                            <div></div>
		                        </div>
		                        <div class="min" style="transform: rotate(4.9998deg);">
		                            <div></div>
		                        </div>
		                        <div class="sec" style="transform: rotate(299.988deg);">
		                            <div></div>
		                        </div>
		                    </div>
		                </div>
		            </div>
		            <div class="clock">
		            	<span class="clock_no">00:00:00</span>
		            	<span class="gp_name" style="float:left;" id="GameTypename" gpid="7">${curLot.name }</span>
		            	<span class="status">  开放下注  </span>
		            	<div class="clear" id="nowroundsShow" nowrounds="201605121032">第 ? 期</div></div>
		        </div>
		        <div class="r2_c2 bg_black3" id="larbar_data" style="position: relative;">
		            <div class="r2_c2_rollout" style=" position: absolute; height: 100%;width: 100%;">
		                <div class="open_result_no <c:choose><c:when test="${lotCode!='PCEGG' && lotCode!='LHC'}">balls_${curLot.balls }</c:when><c:otherwise>balls_10</c:otherwise></c:choose>">
		                <c:choose><c:when test="${lotCode=='PCEGG'}">
		                	<span class="bg_black3_content">?</span><span class="bg_black3_content">+</span><span class="bg_black3_content">?</span><span class="bg_black3_content">+</span><span class="bg_black3_content">?</span><span class="bg_black3_content">=</span><span class="bg_black3_content">?</span>
		               	</c:when><c:otherwise><c:forEach var="i" begin="1" end="${curLot.balls }"><span class="bg_black3_content">?</span></c:forEach></c:otherwise></c:choose>
			            </div>
			            <div class="open_result_txt"><span class="gp_name">${curLot.name }</span> 第 ? 期 开奖结果</div>
		            </div>
		        </div>
		        <div class="r2_c3 bg_black3" id="chlong_data">
		        	<h3>近期开奖号码</h3>
		        	<div><ul>
		        	<li style="width: 100%;line-height: 22px;padding:0px 5px 0px 4px;" class="bg_black3">
			        	<span style="width:57px;text-align:center; margin-left:1px;">553444:</span>
			        	<span style="width:11px;text-align:center; margin-left:1px;">6</span>
			        	<span style="width:11px;text-align:center; margin-left:1px;">9</span>
			        	<span style="width:11px;text-align:center; margin-left:1px;">9</span>
			        	<span style="width:11px;text-align:center; margin-left:1px;">1</span>
			        	<span style="width:11px;text-align:center; margin-left:1px;">1</span>
			        	<span style="width:11px;text-align:center; margin-left:1px;">1</span>
			        	<span style="width:11px;text-align:center; margin-left:1px;">1</span>
			        	<span style="width:11px;text-align:center; margin-left:1px;">1</span>
			        	<span style="width:11px;text-align:center; margin-left:1px;">1</span>
			        	<span style="width:11px;text-align:center; margin-left:1px;">1</span></li>
		        	</ul></div></div>
		    </div>
		    
		    <div class="row r3 bg_black3">
		        <div class="row_top bg_black3" id="betmenu_data">
		        	<ul id="Bm_general">
		        		<c:if test="${not empty playGroups }"><c:forEach items="${playGroups }" var="pg" varStatus="var">
		        		<li<c:if test="${var.first }"> class="current"</c:if>><a href="javascript:;" class="btn b0" p="group_${pg.lotType}_${pg.code}">${pg.name }</a></li>
		        		</c:forEach></c:if>
		        	</ul></div>
		        <div class="row_con" id="betplay_data"><div class="row_con_c1 bg_black3"> <div class="row_con_c1_con"> <table border="0" cellspacing="0" cellpadding="0" class="ch_radio"> <tbody> <tr><th>五星玩法</th> <td> <label class="pointer"><input name="r0" type="radio" class="pointer" value="5_20_111" p="5_20_111" gc_p="5_1_12345" checked="">五星复式</label> </td></tr> </tbody> </table> </div> <div class="row_con_c1_btm bg_black3"><span>从万、千、百、十、个位各选一个号码组成一注。<a href="javascript:;" style="border:none;" original-title="从万位、千位、百位、十位、个位选择一个5位数号码组成一注，所选号码与开奖号码全部相同，且顺序一致，即为中奖。"> <span class="fa-stack fa-lg"><i class="fa fa-question fa-stack-1x" style="margin-top: 3px;"></i><i class="fa fa-circle-thin fa-stack-2x"></i></span> &nbsp;</a><a href="javascript:;" original-title="投注方案：13456 开奖号码:13456，即中五星直选。">示 例</a></span> </div> </div> <div class="row_con_c2" _type="1" _row="5" _column="5"> <div class="no_frame"> <div class="radio_select"> <label class="pointer"> <input name="data_miss" type="radio" _d="miss" class="pointer" value="遗 漏" checked="checked">遗 漏</label>&nbsp; <label class="pointer"> <input name="data_miss" type="radio" _d="hoco" class="pointer" value="冷 热">冷 热</label> </div> <div class="num_btn"><span class="digits">万</span><span><a href="javascript:;" num="0" class="isNum">0</a><s class="hide c_red">1</s><i>1</i></span><span><a href="javascript:;" num="1" class="isNum">1</a><s class="hide c_blue">2</s><i>3</i></span><span><a href="javascript:;" num="2" class="isNum">2</a><s class="hide c_blue">0</s><i>30</i></span><span><a href="javascript:;" num="3" class="isNum">3</a><s class="hide c_blue">2</s><i>5</i></span><span><a href="javascript:;" num="4" class="isNum">4</a><s class="hide c_blue">6</s><i>2</i></span><span><a href="javascript:;" num="5" class="isNum">5</a><s class="hide c_blue">3</s><i>7</i></span><span><a href="javascript:;" num="6" class="isNum">6</a><s class="hide c_blue">3</s><i>0</i></span><span><a href="javascript:;" num="7" class="isNum">7</a><s class="hide c_blue">1</s><i>19</i></span><span><a href="javascript:;" num="8" class="isNum">8</a><s class="hide c_red">2</s><i>4</i></span><span><a href="javascript:;" num="9" class="isNum">9</a><s class="hide c_red">0</s><i>22</i></span><span class="quickSelectMargin"><a href="javascript:;" btnfun="bfn_a" class="quickSelect">全</a></span><span><a href="javascript:;" btnfun="bfn_b" class="quickSelect">大</a></span><span><a href="javascript:;" btnfun="bfn_s" class="quickSelect">小</a></span><span><a href="javascript:;" btnfun="bfn_o" class="quickSelect">单</a></span><span><a href="javascript:;" btnfun="bfn_e" class="quickSelect">双</a></span><span><a href="javascript:;" btnfun="bfn_c" class="quickSelect">清</a></span></div> <div class="num_btn"><span class="digits">千</span><span><a href="javascript:;" num="0" class="isNum">0</a><s class="hide">1</s><i>2</i></span><span><a href="javascript:;" num="1" class="isNum">1</a><s class="hide c_red">2</s><i>11</i></span><span><a href="javascript:;" num="2" class="isNum">2</a><s class="hide c_red">6</s><i>4</i></span><span><a href="javascript:;" num="3" class="isNum">3</a><s class="hide c_red">0</s><i>28</i></span><span><a href="javascript:;" num="4" class="isNum">4</a><s class="hide c_red">2</s><i>1</i></span><span><a href="javascript:;" num="5" class="isNum">5</a><s class="hide">4</s><i>3</i></span><span><a href="javascript:;" num="6" class="isNum">6</a><s class="hide c_red">3</s><i>7</i></span><span><a href="javascript:;" num="7" class="isNum">7</a><s class="hide">0</s><i>40</i></span><span><a href="javascript:;" num="8" class="isNum">8</a><s class="hide c_blue">0</s><i>23</i></span><span><a href="javascript:;" num="9" class="isNum">9</a><s class="hide c_red">2</s><i>0</i></span><span class="quickSelectMargin"><a href="javascript:;" btnfun="bfn_a" class="quickSelect">全</a></span><span><a href="javascript:;" btnfun="bfn_b" class="quickSelect">大</a></span><span><a href="javascript:;" btnfun="bfn_s" class="quickSelect">小</a></span><span><a href="javascript:;" btnfun="bfn_o" class="quickSelect">单</a></span><span><a href="javascript:;" btnfun="bfn_e" class="quickSelect">双</a></span><span><a href="javascript:;" btnfun="bfn_c" class="quickSelect">清</a></span></div> <div class="num_btn"><span class="digits">百</span><span><a href="javascript:;" num="0" class="isNum">0</a><s class="hide c_red">1</s><i>19</i></span><span><a href="javascript:;" num="1" class="isNum">1</a><s class="hide">1</s><i>16</i></span><span><a href="javascript:;" num="2" class="isNum">2</a><s class="hide c_blue">1</s><i>9</i></span><span><a href="javascript:;" num="3" class="isNum">3</a><s class="hide">2</s><i>5</i></span><span><a href="javascript:;" num="4" class="isNum">4</a><s class="hide">4</s><i>1</i></span><span><a href="javascript:;" num="5" class="isNum">5</a><s class="hide c_blue">4</s><i>3</i></span><span><a href="javascript:;" num="6" class="isNum">6</a><s class="hide c_blue">2</s><i>15</i></span><span><a href="javascript:;" num="7" class="isNum">7</a><s class="hide">2</s><i>2</i></span><span><a href="javascript:;" num="8" class="isNum">8</a><s class="hide">1</s><i>10</i></span><span><a href="javascript:;" num="9" class="isNum">9</a><s class="hide c_red">2</s><i>0</i></span><span class="quickSelectMargin"><a href="javascript:;" btnfun="bfn_a" class="quickSelect">全</a></span><span><a href="javascript:;" btnfun="bfn_b" class="quickSelect">大</a></span><span><a href="javascript:;" btnfun="bfn_s" class="quickSelect">小</a></span><span><a href="javascript:;" btnfun="bfn_o" class="quickSelect">单</a></span><span><a href="javascript:;" btnfun="bfn_e" class="quickSelect">双</a></span><span><a href="javascript:;" btnfun="bfn_c" class="quickSelect">清</a></span></div> <div class="num_btn"><span class="digits">十</span><span><a href="javascript:;" num="0" class="isNum">0</a><s class="hide c_red">4</s><i>3</i></span><span><a href="javascript:;" num="1" class="isNum">1</a><s class="hide">3</s><i>0</i></span><span><a href="javascript:;" num="2" class="isNum">2</a><s class="hide c_blue">3</s><i>1</i></span><span><a href="javascript:;" num="3" class="isNum">3</a><s class="hide c_blue">0</s><i>43</i></span><span><a href="javascript:;" num="4" class="isNum">4</a><s class="hide c_red">1</s><i>7</i></span><span><a href="javascript:;" num="5" class="isNum">5</a><s class="hide c_red">3</s><i>11</i></span><span><a href="javascript:;" num="6" class="isNum">6</a><s class="hide c_red">2</s><i>9</i></span><span><a href="javascript:;" num="7" class="isNum">7</a><s class="hide">2</s><i>10</i></span><span><a href="javascript:;" num="8" class="isNum">8</a><s class="hide c_blue">0</s><i>21</i></span><span><a href="javascript:;" num="9" class="isNum">9</a><s class="hide c_red">2</s><i>2</i></span><span class="quickSelectMargin"><a href="javascript:;" btnfun="bfn_a" class="quickSelect">全</a></span><span><a href="javascript:;" btnfun="bfn_b" class="quickSelect">大</a></span><span><a href="javascript:;" btnfun="bfn_s" class="quickSelect">小</a></span><span><a href="javascript:;" btnfun="bfn_o" class="quickSelect">单</a></span><span><a href="javascript:;" btnfun="bfn_e" class="quickSelect">双</a></span><span><a href="javascript:;" btnfun="bfn_c" class="quickSelect">清</a></span></div> <div class="num_btn"><span class="digits">个</span><span><a href="javascript:;" num="0" class="isNum">0</a><s class="hide c_blue">3</s><i>6</i></span><span><a href="javascript:;" num="1" class="isNum">1</a><s class="hide">3</s><i>0</i></span><span><a href="javascript:;" num="2" class="isNum">2</a><s class="hide">1</s><i>5</i></span><span><a href="javascript:;" num="3" class="isNum">3</a><s class="hide">1</s><i>17</i></span><span><a href="javascript:;" num="4" class="isNum">4</a><s class="hide c_blue">2</s><i>2</i></span><span><a href="javascript:;" num="5" class="isNum">5</a><s class="hide c_red">1</s><i>16</i></span><span><a href="javascript:;" num="6" class="isNum">6</a><s class="hide c_red">3</s><i>4</i></span><span><a href="javascript:;" num="7" class="isNum">7</a><s class="hide c_red">3</s><i>3</i></span><span><a href="javascript:;" num="8" class="isNum">8</a><s class="hide">0</s><i>23</i></span><span><a href="javascript:;" num="9" class="isNum">9</a><s class="hide c_blue">3</s><i>1</i></span><span class="quickSelectMargin"><a href="javascript:;" btnfun="bfn_a" class="quickSelect">全</a></span><span><a href="javascript:;" btnfun="bfn_b" class="quickSelect">大</a></span><span><a href="javascript:;" btnfun="bfn_s" class="quickSelect">小</a></span><span><a href="javascript:;" btnfun="bfn_o" class="quickSelect">单</a></span><span><a href="javascript:;" btnfun="bfn_e" class="quickSelect">双</a></span><span><a href="javascript:;" btnfun="bfn_c" class="quickSelect">清</a></span></div> </div> </div></div>
		        <div class="row_btm bg_black2">
		            <div class="no_ranger" style="width:30px;"><span class="fa-stack fa-lg c_white3 fa-customColor" style="margin: 7px 0 0 0px;"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-usd fa-stack-1x fa-inverse"></i></span>
		            </div>
		            <div class="no_value" style="width: 220px;">
		                <div>
		                    <input type="hidden" id="rebate" value="14.0%"><!-- 反水、返点 -->
		                    <label>奖金返点：</label><span><i id="gcrate">170000.00</i>元 / <span id="rebateShow">14.0%</span></span>
		                </div>
		            </div>
		            <div class="no_amount" style="width: 200px;">
		                <span class="fa-stack fa-lg c_white3 fa-customColor" style="margin: 5px 0 0 0px; float: left;"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-edit fa-stack-1x fa-inverse" style="margin: 7px 0px 0px 1px;"></i></span>&nbsp;<span>共选择&nbsp;<span id="Immediate">0</span>&nbsp;注
		                <br>共投注&nbsp;<span id="allTotamounts">0</span>&nbsp;元</span>
		            </div>
		            <div class="no_input" style="width: 260px;">
		                倍数：
		                <input name="buymultiple" type="text" value="1" id="buymultiple" size="8" onkeypress="digitOnly(event)" onkeyup="value=value.replace(/[^0-9]/g,'')" onchange="value=value.replace(/[^0-9]/g,'')">
		                <input name="amount" type="hidden" value="2" id="buyamount" size="8" onkeypress="digitOnly(event)" onkeyup="value=value.replace(/[^0-9]/g,'')" onchange="value=value.replace(/[^0-9]/g,'')">&nbsp;
		                <select name="selectdollarunit" id="selectdollarunit"><option value="1">元模式</option><option value="10">角模式</option><option value="100">分模式</option></select>
		            </div>
		            <div class="no_b2" style="width: 90px;"><a href="javascript:;" class="btn2" id="seleall"><span class="fa-stack fa-lg c_white3 fa-customColor"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-plus fa-stack-1x fa-inverse"></i></span> 添 加 </a></div>
		            <div class="no_b1"><a href="javascript:;" class="btn2" id="randomPlay"><span class="fa-stack fa-lg c_white3 fa-customColor"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-random fa-stack-1x fa-inverse"></i></span> 机 选 </a></div>
		        </div>
		    </div>
		    <div class="row r4">
		        <div class="r4_c1 bg_black3">
		            <div class="row_top bg_black3">
		                <table class="780" border="0" cellspacing="0" cellpadding="0">
		                    <colgroup><col width="105"><col width="95"><col width="200"><col width="80"><col width="170"><col width="65"><col width="65"></colgroup><thead>
		                    <tr>
		                        <td>投注项</td>
		                        <td>下注类型</td>
		                        <td>号 码</td>
		                        <td>总注数</td>
		                        <td>奖金/返点</td>
		                        <td>金 额</td>
		                        <td>删 除</td>
		                    </tr>
		                </thead>
		                </table>
		            </div>
		            <div class="row_con clearfix" id="BetNumberListContanior">
		                <table width="770" border="0" cellspacing="0" cellpadding="0">
		                    <colgroup><col width="105"><col width="95"><col width="200"><col width="80"><col width="170"><col width="65"><col width="65"></colgroup><tbody></tbody>
		                </table>
		            </div>
		            <div class="row_btm bg_black2">
		                <div class="btn_row">
		                    <ul>
		                        <li class="hide" id="runbets_T" style="display: list-item;"><a href="javascript:;" class="btn2" id="runbets"><span class="fa-stack c_white3 fa-customColor" style="float: left;"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-arrow-up fa-stack-1x fa-inverse"></i></span>&nbsp;我要追号</a></li>
		                        <li><a href="javascript:;" class="btn2" id="nowbetinfo"><span class="fa-stack c_white3 fa-customColor" style="float: left;"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-search fa-stack-1x fa-inverse"></i></span>&nbsp;下注情况</a></li>
		                        <li id="saiCheShiPin" class="scsp hide"><a href="javascript:;" id="saiCheShiPinBtn" class="btn2"><span class="fa-stack c_white3 fa-customColor" style="float: left;"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-video-camera fa-stack-1x fa-inverse"></i></span>&nbsp;赛车视频</a></li>
		                        <li class="right"><a href="javascript:;" class="btn2" id="clearallitem"><span class="fa-stack c_white3 fa-customColor" style="float: left;"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-times fa-stack-1x fa-inverse"></i></span>&nbsp;删除全部</a></li>
		                    </ul>
		                </div>
		            </div>
		        </div>
		        <div class="r4_c2 bg_black5">
		            <div class="vote"><a href="javascript:;" class="btn b3" id="gamebuy" jx-method="POST" jx-datatype="json" jx-template=""><span class="fa-stack c_white3 fa-customColor" style="font-size: 0.7em; left: -5px;"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-check fa-stack-1x fa-inverse"></i></span>确定投注</a></div>
		            <div class="arr_top"><i class="fa fa-caret-up fa-lg"></i></div>
		            <div class="vote_total">
		                <div><span class="fa-stack fa-lg c_white3 fa-customColor" style="float: left;"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-ticket fa-stack-1x fa-inverse"></i></span><span class="total">总下注数：</span><span class="total"><span id="s_allTotbets">0</span></span>
		                </div>
		                <div><span class="fa-stack fa-lg c_white3 fa-customColor" style="float: left;"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-usd fa-stack-1x fa-inverse"></i></span><span class="total">总下注金额：</span><span class="total"><span id="s_allTotamounts">0</span></span>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
</div>
</body>
</html>
<script type="text/javascript" src="${base}/common/js/jquery.min.js?v=${caipiao_version}"></script>
<script type="text/javascript" src="${base}/common/js/js.cookie.js?v=${caipiao_version}"></script>
<script type="text/javascript" src="${base}/common/lottery/jiebao/js/lottery.js?v=${caipiao_version}"></script>
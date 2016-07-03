<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>管理后台</title>
	<jsp:include page="/common/include/easyui.jsp"></jsp:include>
	<script type="text/javascript" src="${base}/admin/js/core.js"></script>
	<script type="text/javascript" src="${base}/common/js/md5.js"></script>
</head>
<body>

    <div id="loginWin">
    	<div style="margin-left:43px; margin-top:30px;">
	        <div style="margin-bottom:10px">
	            <input id="accountTxt" class="easyui-textbox" style="width:90%;height:40px;padding:12px" data-options="prompt:'Username',iconCls:'icon-man',iconWidth:38">
	        </div>
	        <div style="margin-bottom:20px">
	            <input id="pwdTxt" class="easyui-textbox" type="password" style="width:90%;height:40px;padding:12px" data-options="prompt:'Password',iconCls:'icon-lock',iconWidth:38">
	        </div>
	 
	        <div>
	            <a id="loginBtn" onclick="doLogin()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:90%;">
	                <span style="font-size:14px;">登陆</span>
	            </a>
	        </div>
        </div>
    </div>

</body>
</html>
<script language="javascript">
	
	var msgbox = null;
	
	function doLogin(){	
		var accountTxt = $("#accountTxt");
		var pwdTxt = $("#pwdTxt");
		var account = accountTxt.textbox('getValue').trim();
		var pwd = pwdTxt.textbox('getValue');
		if(account == ''){
			Msg.info("请输入账号");
			accountTxt.textbox('textbox').focus();
			return;
		}
		if(pwd == ''){
			Msg.info("请输入密码");
			pwdTxt.textbox('textbox').focus();
			return;
		}
		$("#loginBtn").linkbutton('disable');
		pwd = hex_md5("~!@#$wc_" + pwd);
		$.ajax({ 
			url: "${base}/admin/login.do",
			data:{
				account:account, 
				password:pwd
			},
			success: function(result){
				window.location.href = "${base}";
	   		},
	   		complete:function(){
	   			$("#loginBtn").linkbutton('enable');
	   		}
		});
	}
	
	$(function(){
		$('#loginWin').window({
		    width:600,
		    height:250,
		    title:"系统登陆",
		    collapsible:false,
		    minimizable:false,
		    closable:false,
		    maximizable:false,
		    minimizable:false,
		    draggable:false,
		    resizable:false,
		    modal:true
		});
	});
	
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/base.jsp"%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${_title}</title>
<%@ include file="/common/include/easyui.jsp"%>
<script type="text/javascript" src="${base}/admin/js/core.js"></script>
<style type="text/css">
	.part{
		margin-left : 20px;
		font-size:12px;
	}
	
	.module{
		margin-left : 45px;
	}
	
	.function{
		margin-left : 70px;
	}
	
	.container{
		width:100px;
		float:left;
	}
	label{
		cursor:pointer;
	}
	input[type="checkbox"]{
		ursor:pointer;
	}
</style>
</head>
<body style="padding:0px;margin:0px;">
	<div style="position:fixed;top:0px;height:40px;border:1px #c3c3c3 solid;width:100%;background-color:#DDDDDD;">
		<div style="margin-top:5px;margin-left:5px;font-size:12px;">
			<a style="margin-right:30px;" id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',disabled:true" onclick="savePermission(this)">保存</a>
			<label>租户:</label>
			<input  id="agentId" type="text"/>
			<span id="tip" style="color:red;margin-left:10px;display:none;">数据加载中....</span>
		</div>
	</div>
	<div id="content" style="margin-top:50px;">
		
	</div>
</body>
</html>
<script language="javascript">

var IDheader = "menu_node_"; 

function getCheckedStr(pm,id){
	if(pm[id] === true){
		return ' checked="checked" ';
	}
	return '';
}

function initView(data){
	var pm = {};
	for(var i=0;i<data.pm.length;i++){
		var d = data.pm[i];
		pm[d.menuId] = true;
	}
	var nodes = data.menus.nodes;
	$("#content").html("");
	var html = "";
	for(var i=0;i< nodes.length;i++){
		var n = nodes[i];
		html += '<div class="part">';
		html += '<label><input id="'+IDheader+n.curNode.id+'" menu_id="'+n.curNode.id+'" '+getCheckedStr(pm,n.curNode.id)+'  type="checkbox" parent="0" level="1" value="'+n.curNode.id+'" onclick="checkMenu(this)"/>'+n.curNode.name+'</label>';
	
		for(var j=0;n.nodes && j<n.nodes.length;j++){
			var ns = n.nodes[j];
			html += '<div class="module">';
			html += '<label><input type="checkbox" id="'+IDheader+ns.curNode.id+'" menu_id="'+ns.curNode.id+'" '+getCheckedStr(pm,ns.curNode.id)+'  level="2"  parent="'+ns.curNode.parentId+'" value="'+ns.curNode.id+'" onclick="checkMenu(this)"/>'+ns.curNode.name+'</label>';
			html += '<div class="function">';
		
			for(var z=0;ns.nodes && z<ns.nodes.length;z++){
				var nt = ns.nodes[z];		
				html += '<div class="container"><label><input type="checkbox" level="3" menu_id="'+nt.curNode.id+'" '+getCheckedStr(pm,nt.curNode.id)+' id="'+IDheader+nt.curNode.id+'" parent="'+nt.curNode.parentId+'" value="'+nt.curNode.id+'" onclick="checkMenu(this)"/>'+nt.curNode.name+'</label></div>';
			}
			html += '<div style="clear:both;"></div>';
			html += '</div>';
			html += '</div>';
		}
		html += '</div>';
	}
	$("#content").html(html);
}

function checkMenu(chk){
	var level = $(chk).attr("level");
	var parentId = $(chk).attr("parent");
	var id = chk.id;
	if(level != 3){
		var checkboxs = $(chk).parent().parent().find("input");
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked = chk.checked;
		}
/* 		if(chk.checked){
			checkboxs.attr("checked",true);
		}else{
			checkboxs.attr("checked",false);
		} */
	}
	
	if(level == 2 || level == 3){
		if(chk.checked){//选中父节点
			$("#"+IDheader + parentId)[0].checked = true;
		}else if(level == 2){
			$("#"+IDheader + parentId)[0].checked = isOneChecked(parentId);
		}
	}
}

function isOneChecked(parentId){
	var checkboxs = $("#content").find("input");
	for(var i=0;i<checkboxs.length;i++){
		var ch = checkboxs[i];
		if(parentId == ch.getAttribute("parent") && ch.checked){
			return true;
		}
	}
	return false;
}

$(function(){
	$("#agentId").combobox({
		valueField:'id',
		textField:'account',
		url:'${base}/admin/dictionary/account/type/ddgd.do',
		onChange:function(newValue,oldValue){
			$('#agentId').combobox('disable');
			$('#tip').css("display","");
			$('#saveBtn').linkbutton('disable');
			$.ajax({
				url : "${base}/admin/agentpermission/getPermission.do",
				data : {
					agentId : newValue
				},
				success : function(data) {
					initView(data);
				},
				complete:function(){
					$('#agentId').combobox('enable');
					$('#tip').css("display","none");
					$('#saveBtn').linkbutton('enable');
				}
			});
		}	
	});
})

function savePermission(btn){
	Msg.confirm("是否确认要保存？",function(r){
		if(!r){
			return;
		}
		var checkboxs = $("#content").find("input");
		var ids = "";
		for(var i=0;i<checkboxs.length;i++){
			var ch = checkboxs[i];
			if(ch.checked){
				if(ids != ""){
					ids += ",";
				}
				ids += ch.getAttribute("menu_id");
			}
		}
		$('#saveBtn').linkbutton('disable');
		$.ajax({
			url : "${base}/admin/agentpermission/savePermission.do",
			data : {
				agentId : $('#agentId').combobox('getValue'),
				ids:ids
			},
			success : function(data) {
				if(data.success){
					Msg.info("保存成功");
				}else{
					Msg.info(data.msg);
				}
			},
			complete:function(){
				$('#saveBtn').linkbutton('enable');
			}
		});
	})
}
	

</script>

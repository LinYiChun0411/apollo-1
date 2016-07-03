<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${_title}</title>
<meta charset="utf-8">
<style type="text/css">
.secCheckBox {
	margin-left: 50px;;
}
</style>
</head>
<body>
	<jsp:include page="/admin2/include/adminmenu.jsp"></jsp:include>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div id="toolbar">
					<div id="search" class="form-inline">
						<button id="save" class="btn btn-primary" onclick="save();" disabled="disabled">保存</button>
						<select id="agentId" class="form-control"></select>
					</div>
				</div>
			</div>
			<div class="panel-body" id="menus"></div>
		</div>
	</div>
	<script id="agentlst_tpl" type="text/html">
	<option value="0">请选择租户</option>
	{{each data as at}}
			<option value="{{at.id}}">{{at.account}}</option>
	{{/each}}
	</script>
	<script id="menus_tpl" type="text/html">
	{{each menus.nodes as fstNode}}
	<div class="checkbox">
		<label>
  			<input type="checkbox" id="{{IDheader+fstNode.curNode.id}}" parent="{{fstNode.curNode.parentId}}" value="{{fstNode.curNode.id}}" {{checkLst[fstNode.curNode.id]}}> {{fstNode.curNode.name}}
		</label>
		<br>
		<div class="secCheckBox">
			{{each fstNode.nodes as secNode}}
				<label checkbox-inline >
  					<input type="checkbox" id="{{IDheader+secNode.curNode.id}}" parent="{{secNode.curNode.parentId}}" value="{{secNode.curNode.id}}" {{checkLst[secNode.curNode.id]}}> {{secNode.curNode.name}}
				</label>
			{{/each}}
		</div>
	</div>
	{{/each}}
	</script>
	<script type="text/javascript">
		var IDheader = "menu_node_";

		$(function() {
			//初始租户下拉
			initAgentLst();
			//绑定下拉事件
			bindAgentLst();
		})

		function initAgentLst() {
			$.ajax({
				url : "${base}/admin/dictionary/account/type/ddgd.do",
				success : function(result) {
					var eachdata = {
						"data" : result
					};
					var html = template('agentlst_tpl', eachdata);
					$("#agentId").append(html);
				}
			});

		}

		function bindAgentLst() {
			$("#agentId").change(function() {
				$("#menus").html("");
				$("#save").attr("disabled", "disabled")
				var selval = $(this).children('option:selected').val();
				if (selval > 0) {
					loadMenuByAccountId(selval);
				}
			});
		}
		/**
		 *加载租户权限菜单
		 */
		function loadMenuByAccountId(accountId) {
			$.ajax({
				url : "${base}/admin/agentpermission/getPermission.do",
				data : {
					agentId : accountId
				},
				success : function(data) {

					var checklst = {};
					for (var i = 0; i < data.pm.length; i++) {
						var d = data.pm[i];
						checklst[d.menuId] = "checked=\"checked\"";

					}
					data.checkLst = checklst;
					data.IDheader = IDheader;

					var html = template('menus_tpl', data);
					$("#menus").html(html);
					$("#save").removeAttr("disabled");

					//绑定向上和向下的级联操作
					$(":checkbox").change(function() {
						checkMenu(this);
					});
				}
			});
		}

		function checkMenu(chk) {
			sonMenu(chk);
			parentMenu(chk);
		}

		function sonMenu(chk) {
			var val = $(chk).val();
			var curChecked = chk.checked;
			//点击当前checkbox下级的处理
			var others = $(":checkbox[parent='" + val + "']");
			if (others === null || others === undefined || others.length === 0) {
				return
			}
			var len = others.length;
			others.each(function() {
				this.checked = curChecked;
				//递归调用
				sonMenu(this);
			});
		}

		function parentMenu(chk) {
			var curChecked = chk.checked;
			var par = $(chk).attr("parent")
			var others = $(":checkbox[parent='" + par + "']:checked");//同级别其他元素
			if (others === null || others === undefined) {
				return
			}
			var len = others.length;
			//点击当前checkbox上级的处理
			if ((len == 0 && !curChecked) || (len == 1 && curChecked)) {
				var chkson = document.getElementById(IDheader + par);
				chkson.checked = curChecked;
				//递归调用
				parentMenu(chkson);
			}
		}

		function save() {
			var ids = [];
			$(":checkbox:checked").each(function() {
				ids.push($(this).val());
			});

			$.ajax({
				url : "${base}/admin/agentpermission/savePermission.do",
				data : {
					agentId : $('#agentId').val(),
					ids : ids.join(",")
				},
				success : function(result) {
					Msg.info("保存成功");
				}
			});
		}
	</script>
</body>
</html>
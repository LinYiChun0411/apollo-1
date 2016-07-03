<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="adminheadmenu"></div>
<div class="modal fade" id="logout" tabindex="-1" role="dialog" aria-labelledby="logoutLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="logoutLabel">提示</h4>
			</div>
			<div class="modal-body">确定退出登录吗？</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" onclick="doLogout();">确定</button>
			</div>
		</div>
	</div>
</div>
<jsp:include page="bootstrap.jsp"></jsp:include>

<script id="menu_tpl" type="text/html">
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
    			{{each menuNode.nodes as firstMenu i}}
      	 		 <li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
							<span class="{{firstMenu.curNode.icon}}" aria-hidden="true"></span> 
							{{firstMenu.curNode.name}}
						<span class="caret"></span>
						</a>
						{{if firstMenu.nodes}}
						<ul class="dropdown-menu">
							 {{each firstMenu.nodes as secondMenu j}}
								<li><a href="javascript:void(0);" onclick="gopage('{{secondMenu.curNode.url}}');">{{secondMenu.curNode.name}}</a></li>
							 {{/each}}
						</ul>
						{{/if}}
					</li>
    			{{/each}}
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" data-toggle="modal" data-target="#logout">退出</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>

</script>

<script id="nav_tpl" type="text/html">


<div style="height:52px;"> &nbsp;</div>
{{if data && data.length > 0}}
	<div class="container" style="margin-top:20px;">
		<ol class="breadcrumb">
			{{each data as name i}}
				{{if i!= data.length - 1}} 
					<li><a href="#">{{name}}</a></li>
				{{/if}}
				{{if i== data.length - 1}} 
					<li class="active">{{name}}</li>
				{{/if}}
			{{/each}}
		</ol>
	</div>
{{/if}}
</script>

<script type="text/javascript">
	$(function() {
		var url = "${pageUrl}";

		var menuMap = {};
		var urlMap = {};

		var toMenuMap = function(node) {
			var curNode = node.curNode;

			if (curNode) {
				menuMap[curNode.id] = curNode;
			}

			if (curNode && curNode.url) {
				var url = '${base}' + curNode.url;
				urlMap[url] = curNode;
			}

			if (!node.nodes) {
				return;
			}
			for (var i = 0; i < node.nodes.length; i++) {
				toMenuMap(node.nodes[i]);
			}
		}

		var initNav = function(data) {
			toMenuMap(data.menuNode);
			var arr = [];
			var node = urlMap[url];
			while (node) {
				arr.push(node.name);
				if (node.parentId) {
					node = menuMap[node.parentId];
				} else {
					break;
				}
			}
			var result = [];
			for (var i = arr.length - 1; i >= 0; i--) {
				result.push(arr[i]);
			}
			return result;
		};

		$.ajax({
			url : "${base}/admin/menu/menunav.do",
			success : function(data) {
				var menuArr = initNav(data);
				var html = template('menu_tpl', data);
				var menuHtml = template('nav_tpl', {
					data : menuArr
				});
				$("#adminheadmenu").html(html + menuHtml);
				$("#navbar > ul li").hover(function() {
					$(this).addClass('open');
				}, function() {
					$(this).removeClass('open');
				});
			}
		});
	});

	function gopage(url) {
		window.location.href = "${base}" + url;
	}

	function doLogout() {
		window.location.href = "${base}/admin/logout.do";
	}
</script>

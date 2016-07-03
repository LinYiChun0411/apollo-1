<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${_title}</title>
<meta charset="utf-8">
<style type="text/css">
td {
	vertical-align: middle !important;
}
</style>
</head>
<body>
	<jsp:include page="/agent/include/agentmenu.jsp"></jsp:include>
	<div class="container">
		<div id="toolbar">
			<div id="search" class="form-inline">
				<div class="input-group">
					<input type="text" class="form-control" id="searchtext" placeholder="输入用户名查询">
				</div>
				<button class="btn btn-primary" onclick="search();">查询</button>
				<button class="btn btn-primary" onclick="reset();">重置</button>
			</div>
		</div>
		<div id="memmnyope_tb"></div>
	</div>
	<script id="memmnyope_tpl" type="text/html">
		 <table class="table table-bordered" style="margin-top:10px;clear: both">
				<input type="hidden" id="accountId"/>
                <tbody>
					<tr>
						<td width="15%" class="text-right active">会员账号：</td>
                        <td width="85%" colspan="2"><span id="accountspan" class="text-primary"></span></td>
					</tr>
					<tr>
						<td width="15%" class="text-right active">账号余额：</td>
                        <td width="85%" colspan="2"><span id="moneyspan" class="text-danger"></span></td>
					</tr>  
					{{each data as third}}
                    	<tr>
							<td width="15%" class="text-right active">{{third.name}}余额：</td>
                        	<td width="15%"><span id="thirdMoney{{third.id}}" class="text-danger">0</span></td>
                        	<td width="70%">&nbsp;<a href="javascript:void(0)" onclick="refresh({{third.configId}});">刷新</a></td>
						</tr>
					{{/each}}
					<tr>
						<td width="15%" class="text-right active">操作类型：</td>
                        <td width="85%" colspan="2"><select id="type" class="form-control"><option value="1">人工存款</option><option value="2">人工取款</option><option value="99">其他</option></select></td>
					</tr>
					<tr>
						<td width="15%" class="text-right active">操作金额：</td>
                        <td width="85%" colspan="2"><input type="text" class="form-control" id="money" /></td>
					</tr>
					<tr>
						<td width="15%" class="text-right active">操作原因：</td>
                        <td width="85%" colspan="2"><textarea class="form-control" id="remark"></textarea></td>
					</tr>
					<tr>
						<td colspan="3" class="text-center"><button type="button" class="btn btn-primary" onclick="submit();">确认</button></td>
					</tr>
				</tbody>
		</table>
		
</script>
	<script type="text/javascript">
		$(function() {
			$.ajax({
				url : "${base}/agent/dictionary/third/list.do",
				success : function(data) {
					var eachdata = {
						"data" : data
					};
					var html = template('memmnyope_tpl', eachdata);
					$("#memmnyope_tb").html(html);
					$("#memmnyope_tb").addClass("hidden");
				}
			});
		});

		function submit() {
			$.ajax({
				url : "${base}/agent/finance/memmnyope/save.do",
				data : {
					id : $("#accountId").val(),
					type : $("#type").val(),
					money : $("#money").val(),
					remark : $("#remark").val(),
				},
				success : function(data) {
					$("#moneyspan").html(data);
					$("#money").val("");
					$("#type").val("");
					$("#remark").val("");
					Msg.info("操作成功！");
				}
			});
		}
		function search() {
			$.ajax({
				url : "${base}/agent/finance/memmnyope/memmny.do",
				data : {
					account : $("#searchtext").val()
				},
				success : function(data) {
					$("#accountId").val(data.accountId);
					$("#accountspan").html(data.account);
					$("#moneyspan").html(data.money);
					$("#memmnyope_tb").removeClass("hidden")
				}
			});
		}

		function reset() {
			$("#accountId").val("");
			$("#accountspan").html("");
			$("#moneyspan").html("");
			$("#searchtext").val("");
			$("#money").val("");
			$("#type").val("");
			$("#remark").val("");
			$("#memmnyope_tb").addClass("hidden")
		}
		
		function refresh(thirdid) {
			Msg.info("暂时没接入...");
		}
	</script>
</body>
</html>
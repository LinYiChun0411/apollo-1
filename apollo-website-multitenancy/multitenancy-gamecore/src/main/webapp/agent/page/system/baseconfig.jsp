<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${_title}</title>
<meta charset="utf-8">
</head>
<body>
	<jsp:include page="/agent/include/agentmenu.jsp"></jsp:include>
	<div class="container">
		<div id="config_table"></div>

	</div>
	<script id="config_tpl" type="text/html">
		 <table class="table table-bordered table-striped" style="clear: both">
                <tbody> 
					{{each data as conf}}
                    	<tr>
							<td width="35%" class="text-right">{{conf.name}}</td>
                        	<td width="65%"><a href="#" id="{{conf.configId}}" data-type="{{conf.type}}" 
											   data-value="{{conf.value}}" data-pk="{{conf.id}}" data-title="{{conf.title}}" 
											   data-source="{{conf.source}}" {{conf.expand}}></a></td>
						</tr>
					{{/each}}
				</tbody>
		</table>
		
</script>
	<script type="text/javascript">
		$(function() {
			$.ajax({
				url : "${base}/agent/system/baseconfig/list.do",
				success : function(data) {
					var html = template('config_tpl', data);
					$("#config_table").html(html);

					var options = {
						url : '${base}/agent/system/baseconfig/save.do',
						emptytext : '无配置',
						params : setParams,
						placement : 'right'
					}

					$("#config_table").find("a").each(function() {

						var type = $(this).attr("data-type");
						var source = $(this).attr("data-source");
						var val = $(this).attr("data-value");

						if (val !== undefined && val !== "") {
							$(this).text(val);
						}

						if (type === 'select') {
							options.source = source;
							var arr = eval(source);
							for (var i = 0; i < arr.length; i++) {
								var obj = arr[i];
								if (obj[val] !== undefined) {
									$(this).text(obj[val]);
									break;
								}
							}
						} else if (type === 'date') {
							options.template = 'yyyy-mm-dd';
							options.format = 'yyyy-mm-dd';
							options.viewformat = 'yyyy-mm-dd';
							options.language = 'zh-CN';
							options.datepicker = {
								todayBtn : 'linked',
								weekStart : 1,
								startView : 0,
								language : 'zh-CN',
								minViewMode : 0,
								autoclose : false
							}
						} else if (type === 'datetime') {
							options.template = 'yyyy-mm-dd hh:ii';
							options.format = 'yyyy-mm-dd hh:ii';
							options.viewformat = 'yyyy-mm-dd hh:ii';
							options.datetimepicker = {
								language : 'zh-CN',
								todayBtn : 'linked',
								weekStart : 1,
								minViewMode : 0,
								autoclose : false
							}
						} else if (type === 'combodate') {
							options.template = 'YYYY-MM-DD HH:mm:ss';
							options.format = 'YYYY-MM-DD HH:mm:ss';
							options.viewformat = 'YYYY-MM-DD HH:mm:ss';
							options.datetimepicker = {
								language : 'zh-CN',
								todayBtn : 'linked',
								weekStart : 1
							}
						} else if (type === 'textarea') {
							options.placement = 'bottom';
						} else if (type === 'checklist') {
							$(this).text("");
							options.source = source;
							var arr = eval(source);
							var checks = val.split(",");
							for (var i = 0; i < checks.length; i++) {
								var ck = checks[i];
								for (var j = 0; j < arr.length; j++) {
									var obj = arr[j];
									if (obj[ck] !== undefined) {
										$(this).append(obj[ck]);
										$(this).append("<br>");
										break;
									}
								}
							}
						}
						$(this).editable(options);
					});
				}
			});
		});

		function setParams(params) {
			if ($(this).attr("data-type") === 'checklist') {
				params.value = params.value.join(",");
			}
			params.configId = $(this).attr("id");
			return params;
		}
	</script>
</body>
</html>
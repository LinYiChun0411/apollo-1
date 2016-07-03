<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>实体类代码生成器</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript" src="${base}/common/js/jquery.min.js"></script>
	<style type="text/css">
		.req_label{
			color:red;
		}
		.txt{
			width:350px;
			height:28px;
		}
	</style>
  </head>
  
  <body>
  		<form id="myForm">
  			数据库表名: &nbsp;&nbsp;<input class="txt" name="tableName"/> <font class="req_label">(*)</font> <br/><br/>
  			
  			父类完整类名：
  			<input class="txt"  name="parentClass" disabled value="org.jay.frame.jdbc.model.BaseModel"/> 
  			<input type="checkbox" name="checkbox" onclick="setStatus(this)"/><br/><br/>
  			<button style="height:30px;width:120px;" type="button" onclick="createModel(this)">生成代码</button> <br/><br/><br/>
  			 代码： &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;<br/>
  			<textarea name="result" rows="30" cols="120"></textarea><br/><br/>
  			 &nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
  			
  		</form>
   		
  </body>
</html>

<script language="javascript">
	
	function setStatus(chk){
		var from = $("#myForm")[0];
		if(from["checkbox"].checked){
			from["parentClass"].removeAttribute("disabled");
		}else{
			from["parentClass"].setAttribute("disabled","");
		}
	}

	function createModel(btn){
		var form = $("#myForm")[0];
		var data = {};
		var tableName = form["tableName"].value.trim();
		if(tableName == ""){
			alert("请输入表名");
			return;
		}
		data["tableName"] = tableName;
		if(form["checkbox"].checked){
			var parentClass = form["parentClass"].value.trim();
			if(parentClass != ""){
				data["className"] = parentClass;
			}
		}
		$(btn).attr("disabled","");
		$.ajax({
			method:'post',
			data : data,
			url :"<%=path%>/admin/createModel.do",
			success:function(data){
				if(data.success){
					form["result"].value = data["code"];
				}else{
					alert(data.msg);
				}
				$(btn).removeAttr("disabled");
			}
		});
	}
</script>


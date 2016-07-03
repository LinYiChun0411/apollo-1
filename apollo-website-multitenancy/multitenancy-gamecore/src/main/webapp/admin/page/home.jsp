<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${_title}</title>
	<jsp:include page="/common/include/easyui.jsp"></jsp:include>
	<script type="text/javascript" src="${base}/admin/js/core.js"></script>
	<style type="text/css">
		
		.menu-ul{
			list-style:none;
			padding-left:5px;
		}
		
		.menu-ul li{
			display:block;
		}
		
		.icon{
			display:inline-block;
			height:16px;
			width:16px;
		}
		.menu-item{
			height:30px;
			font-size:12px;
			margin-right:5px;
		}
		.menu-header{
		    padding: 5px;
		    position: relative;
		}
		.menu-item-selected{
			border:1px solid rgb(153,187,232);
			background-color:rgb(224,236,255);
			color:rgb(65,106,183);
			font-weight:700;
			color:rgb(65,106,183);
		}
	</style>
</head>
<body class="easyui-layout" >
     <div data-options="region:'north'" style="height:100px;overflow:hidden;">
     	<div style="width:50px;float:right;margin-top:70px;">
     	
     		<button type="button" onclick="doLogout();">退出</button>
     	
     	</div>
     </div>
     <div data-options="region:'west',split:true" title="菜单栏" style="width:200px;overflow-x:hidden;">
     <!-- 菜单栏 -->
     	<div class="easyui-accordion" data-options="border:false" style="height:100%;width:100%;">
     		<c:forEach items="${menu}" var="m" >
		        <div title="${m.curNode.name}" style="overflow:auto;padding:0px;" data-options="iconCls:'icon-help'">
	        		<ul class="menu-ul">
			        	<c:forEach items="${m.nodes}" var="ms" >
			        		<li>
	        					<div class="menu-item"  onclick="openTab(${ms.curNode.id},'${ms.curNode.url}','${ms.curNode.name}')">
			        				<div  class="menu-header" >
									<div class="panel-title panel-with-icon">&nbsp;${ms.curNode.name}</div>
									<div class="panel-icon icon-help"></div>
			        				</div>
			        			</div>
			         		</li>		
			         	</c:forEach>
		         	</ul>
		        </div>
	 		</c:forEach>
			 
	    </div>
	    <!-- 菜单栏  end-->
     </div>
     <div data-options="region:'center'" style="overflow-x:hidden;">
		 <div id="tabs" class="easyui-tabs" data-options="border:false" style="height:100%;width:100%;">
	        <div title="About" style="padding:10px">
	   			
	        </div>
	    </div>
     </div>
    		
</body>
</html>

<script language="javascript">
	
	$(function(){
		$(".menu-item").mouseenter(function(){
			$(this).addClass("menu-item-selected");
		});
		
		$(".menu-item").mouseleave(function(){
			$(this).removeClass("menu-item-selected");
		});
	});
	
	function openTab(key,url,title){
		
	   var mainTabs = $("#tabs");
	   if(mainTabs.tabs('exists',title)){
		    mainTabs.tabs('select',title);
	        return;
	    }
		
		mainTabs.tabs('add',{
		    title:title,
		    fit:true,
		    content:createFrame('${base}'+url),
		    closable:true,
		    id:key
		});
	}
	
	function createFrame(url,id){
		var str = id ? 'id="'+id+'"' : "";
    	var s = '<iframe '+str+' scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:99%;"></iframe>';
   		return s;
	}	
	
	function doLogout(){
		Msg.confirm("是否确认要退出系统？",function(r){
			if(r){
				window.location.href = "${station}/logout.do";
			}
		});
	}
	
	function openPage(url,tabId){
		alert(url);
	}
	
</script>
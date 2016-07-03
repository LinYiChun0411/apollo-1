<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String base = request.getContextPath();
%>
<script>
var _parent = window;
while(_parent != window.parent){
	_parent = window.parent;
}
_parent.location.href = "${base}";
</script>
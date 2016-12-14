<%@ page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    isELIgnored="false" %>
<%@taglib 
	prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
		
		<link rel="stylesheet" href="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" >
		
		<c:url var="useroriginationURL" value='/userorigination'></c:url>
		
	<script type="text/javascript">
		$(document).ready(function() {
			$("#datatable").DataTable();
		});
	</script>
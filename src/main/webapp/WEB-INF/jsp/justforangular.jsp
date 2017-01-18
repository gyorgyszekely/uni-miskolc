<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="js/angularmodule.js"></script>
<title>Angular just for fun</title>
</head>
<body>

	<div data-ng-app="myApp" data-ng-controller="fruitCtrl">
		<input type="text" data-ng-model="name"> <br /> {{name}} <br />
		<button type="button" data-ng-click="letsDoThis()">Let's do this!</button>
		<br />
		<ul>
			<li data-ng-repeat="element in arr">{{ element }}</li>
		</ul>
		<span data-ng-show="tooManyElements()">Too many elements</span>
	</div>

</body>
</html>
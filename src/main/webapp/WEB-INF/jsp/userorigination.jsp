<%@include file="header.jsp"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<style>
		.errorblock {
			color: #000;
			background-color: #ffEEEE;
			border: 3px solid #ff0000;
			padding: 8px;
			margin: 16px;
		}
		</style>
		
		<title>Become registered user</title>
	</head>
	
	<body>
		
		<form:form modelAttribute="userRequest" action="${useroriginationURL}" method="POST" >
			<form:errors path="*" cssClass="errorblock" element="div" />
			
			<label>User name:</label>
			<form:input path="userName"></form:input> <br />
			
			<label>Credit:</label>
			<form:input path="creditBalance"></form:input> <br />
		
			<label>Education level:</label><br />
		
			<form:select path="qualification" items="${qualification}" multiple="false">
			</form:select><br />
		
			<label>Favorite colors:</label> <br />
			<form:checkboxes path="favouriteColor" items="${colors}" /> <br />
			
			<label>Gender:</label> <br />
			<form:radiobuttons path="gender" items="${genders}"/>

			<input type="submit">
		</form:form>
	</body>
</html>
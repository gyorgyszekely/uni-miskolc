<%@include file="header.jsp"%>

		<title>Become registered user</title>
	</head>
	
	<body>
		<c:if test="${hasError}">
   			<p>Has error!<p>
		</c:if> <br />
		
		<form action="${useroriginationURL}" method="POST" >
			<label>User name:</label>
			<input type="text" name="userName"></input> <br />
			
			<label>Credit:</label>
			<input type="text" name="creditBalance"></input> <br />
		
			<label>Education level:</label><br />
			
			<select name="qualification">
				<c:forEach items="${qualification}" var="qa">
    				<option value="${qa}">${qa}</option>
				</c:forEach>
			</select>
			<br />
		
			<label>Favorite colors:</label> <br />
			
			<c:forEach items="${colors}" var="col">
    			<label><input type="checkbox" name="colors" value="${col}" /><c:out value="${col}"/></label><br />
			</c:forEach>
			<br />
			
			<label>Gender:</label> <br />
			
			<c:forEach items="${genders}" var="gend">
    			<label><input type="radio" name="gender" value="${gend}" /><c:out value="${gend}"/></label><br />
			</c:forEach>

			<input type="submit">
		</form>
		
	</body>
</html>
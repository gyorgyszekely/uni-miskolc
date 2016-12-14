<%@include file="header.jsp"%>


		<title>User Statii</title>
	</head>
	
	<body>
		<a href="${useroriginationURL}">Add new user</a> <br />
		
		<h2>Registered users: </h2>
		<table id="datatable">
			<thead>
				<tr>
					<th>USERNAME</th>
					<th>CREDITBALANCE</th>
					<th>QUALIFICATION</th>
					<th>FAVOURITECOLOR</th>
					<th>GENDER</th>
					<th>DELETE</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${users}">
					<tr>
						<td>${user.userName}</td>
						<td>${user.creditBalance}</td>
						<td>${user.qualification}</td>
						<td>
							<c:forEach items="${user.favouriteColor}" var="color">
								${color} <br/>
							</c:forEach>
						</td>
						<td>${user.gender}</td>
						<td><a href="/spring-demo/search?<c:out value="${user.userId}"/>">DELETE</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
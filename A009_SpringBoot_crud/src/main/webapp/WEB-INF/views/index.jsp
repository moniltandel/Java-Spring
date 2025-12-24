<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="addStudent" modelAttribute="student" method="post">
		<form:hidden path="id"/>
		
		<form:label path="name">Name</form:label>
		<form:input path="name" />

		<form:label path="email">Email</form:label>
		<form:input path="email" />

		<form:label path="phone">Phone</form:label>
		<form:input path="phone" />

		<input type="submit">
	</form:form>

	<br>
	<br>

	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Email</th>
			<th>Phone</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		
		<c:forEach var="i" items="${students}">
		<tr>
			<td>${i.getId()}</td>
			<td>${i.getName() }</td>
			<td>${i.getEmail() }</td>
			<td>${i.getPhone() }</td>
			<td><a href="edit?eid=${i.getId()}">Edit</a></td>
			<td><a href="delete?did=${i.getId()}">Delete</a></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
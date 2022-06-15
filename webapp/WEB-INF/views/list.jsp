<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>전화번호부</h1>
	<h2>리스트</h2>
	
	<p>입력한 정보 내용입니다.</p>
	
	<c:forEach items="${requestScope.phoneList}" var="phoneVo">
		<table border="1">
			<tr>
				<td>이름(name)</td>
				<td>${phoneVo.name}</td>
			</tr>
			<tr>
				<td>핸드폰(hp)</td>
				<td>${phoneVo.hp}</td>
			</tr>
			<tr>
				<td>회사(company)</td>
				<td>${phoneVo.company}</td>
			</tr>
			<tr>
				<td><a href="modifyForm?no=${phoneVo.personId}">[수정]</a></td>
				<td><a href="delete/${phoneVo.personId}">[삭제]</a></td>
			</tr>
		</table>
		
		<br>
		
	</c:forEach>
	
	<br>
	
	<a href="/phonebook4/writeForm">추가 번호 등록</a>
	
	<br><br>

</body>
</html>

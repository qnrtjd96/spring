<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
	<h1>글목록</h1>
	<div>
		<c:if test="${logStatus=='Y'}">
			<a href="/myapp/boardWrite">글쓰기</a>
		</c:if>
	</div>
	<ul>
		<c:forEach var="vo" items="${list}">
			<li>${vo.no }</li>
			<li><a href="/myapp/boardView?no=${vo.no}">${vo.subject}</a></li>
			<li>${vo.userid }</li>
			<li>${vo.hit }</li>
			<li style="border-bottom: 1p solid #ddd">${vo.writedate}</li>
		</c:forEach>
	</ul>
</div>

</body>
</html>
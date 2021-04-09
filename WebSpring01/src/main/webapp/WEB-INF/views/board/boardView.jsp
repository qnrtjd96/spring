<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	function delCheck(recordNo){
		if(confirm("정말 삭제할까요?")){
			location.href="/myapp/boardDelete?no="+recordNo;
		}
	}
	
</script>
<div class="container">
	<h1>글내용보기</h1>
	<ul>
		<li>번호 : ${vo.no}</li>
		<li>작성자 : ${vo.userid}</li>
		<li>등록일 : ${vo.writedate}, 조회수 : ${vo.hit }</li>
		<li>제목 : ${vo.subject }</li>
		<li>${vo.content }</li>
		
	</ul>
	<div>
		<c:if test="${logId==vo.userid }">
			<a href="/myapp/boardEdit?no=${vo.no}">수정</a>
			<a href="javascript:delCheck(${vo.no})">삭제</a>
		</c:if>
	</div>
	<hr/>
	<ul>
		<c:forEach var="vo2" items="${list}">
			<li>${vo2.userid} : ${vo2.content}</li>
		</c:forEach>
	</ul>
	<div class="container">
		<form method="post" action="/myapp/replyWriteOk">
		<input type="hidden" name="${vo.no}">
			댓글달기: <input type="text" name="content">
		<input type="submit" value="댓글등록"/>
		</form>
	</div>
</div>
</body>
</html>
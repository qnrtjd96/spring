<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
<script>
	function del(){
		if(confirm("삭제하시겠습니까?")){
			location.href="/jdbc/boardDel?no=${vo.no}"
		}
	}
</script>
	<h1>글내용보기</h1>
	<ul>
		<li>번호 : ${vo.no}</li>
		<li>작성자 : ${vo.userid}</li>
		<li>작성일 : ${vo.writedate}</li>
		<li>조회수 : ${vo.hit}</li>
		<li>글내용<br/>${vo.content}</li>
	</ul>
	<div>
		<c:if test="${logStatus=='Y' && logId==vo.userid}">
			<a href="/jdbc/boardEdit?no=${vo.no}">수정</a>
			<a href="javascript:del()">삭제</a>
		</c:if>
	</div>
</div>
</body>
</html>
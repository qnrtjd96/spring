<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
	<h1>자료실 글내용보기</h1>
	<ul>
		<li>번호 : ${vo.no }</li>
		<li>작성자 : ${vo.userid }</li>
		<li>등록일 : ${vo.writedate }, 조회수 : ${vo.hit }, 다운횟수 :  ${vo.downCount }</li>
		<li>첨부파일 : <a href="/jdbc/upload/${vo.filename1}" download>${vo.filename1}</a>
			<c:if test="${vo.filename2 !=''}">
				<a href="/jdbc/upload/${vo.filename2}" download>${vo.filename2}</a>
			</c:if>
		<li>제목 : ${vo.title }</li>
		<li>${vo.content }</li>
	</ul>
	<c:if test="${logId==vo.userid}">
		<a href="/jdbc/dataEdit?no=${vo.no }">수정</a>
		삭제
	</c:if>
</div>
</body>
</html>
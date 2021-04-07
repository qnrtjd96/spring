<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
<h1>글수정</h1>
	<form method="post" action="/myapp/boardEditOk">
		<input type="hidden" name="no" value="${vo.no}"/>
		제목: <input type="text" name="subject" value="${vo.subject }"><br/>
		<textarea name="content">value="${vo.content }"</textarea>
		<Script>
			CKEDITOR.replace("content");
		</Script>
		<input type="submit" value="글수정"/>
	</form>
</div>
</body>
</html>
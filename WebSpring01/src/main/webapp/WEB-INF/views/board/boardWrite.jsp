<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
	<form method="post" action="/myapp/boardWriteOk">
		제목: <input type="text" name="subject"><br/>
		<textarea name="content"></textarea>
		<Script>
			CKEDITOR.replace("content");
		</Script>
		<input type="submit" value="글등록"/>
	</form>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
<style>
	ul,li{margin0; padding:0; list-style-type:none;}
	.claseList li{float:left; width: 10%; height:40%; line-height:40px; border-bottom:1px solid #ddd;}
	.wordcut{white-space:nowrap; overflow:hidden; text-overflow:ellipsis;}
	.claseList li:nth-child(5n+2){width:60%}
</style>
<Script>
	function boardDel(){
		if(confirm("삭제하시겠습니까?")){
			location.href="claseDel?no=${dto.no}";
		}
	}
</Script>
</head>
<body>
	<div class="container">
		<h1>답변형게시판 글내용보기</h1>
		<ul>
			<li>번호 : ${dto.no}</li>
			<li>제목 : ${dto.subject}</li>
			<li>작성자 : ${dto.userid}</li>
			<li>글내용 : ${dto.content}</li>
		</ul>
		<div>
			<a href="claseEdit?no=${dto.no}">수정</a>
			<a href="javascript:boardDel()">삭제</a>
			<a href="claseWriteForm?no=${dto.no}">답글</a>
		</div>
		<!-- 내가한거 -->
		<div>
			<c:if test="${dto.pre_no !=null}">
				<a href="claseView?no=${dto.pre_no}">이전글</a> 제목 : ${dto.pre_title} <br/>
			</c:if>
			<c:if test="${dto.pre_no ==null}">
				<a>이전글</a> 제목 : ${dto.pre_title} <br/>
			</c:if>
			<c:if test="${dto.next_no!=null}">
				<a href="claseView?no=${dto.next_no}">다음글</a> 제목 : ${dto.next_title}
			</c:if>
			<c:if test="${dto.next_no==null}">
				<a>다음글</a> 제목 : ${dto.next_title}
			</c:if>
		</div> 
		<!-- 선생님이한거 -->
		<%-- 이전글 : 
		<c:if test="${vo.prevNo==0}">
			${vo.prevSubject}
		</c:if>
		<c:if test=${vo.prevNo>0}">
			<a href="claseView?no=${vo.prevNo}">${vo.prevSubject}</a>
		</c:if><Br/>
		<c:if test="${vo.nextNo==0}">
			${vo.prevSubject}
		</c:if>
		<c:if test=${vo.nextNo>0}">
			<a href="claseView?no=${vo.nextNo}">${vo.nextSubject}</a>
		</c:if> --%>
		
	</div>
</body>
</html>
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
	   ul, li{margin:0; padding:0; list-style:none;}
	   #boardList li{width:8%; height:40%; line-height:40px; border-bottom:1px solid gray; float:left;}
	   #boardList li:nth-child(6n+3){width:60%;}
	   .wordcut{white-space:nowrap; overflow:hidden; text-overflow:ellipsis;}
	   #searchbox{text-align: right;}
</style>
<script>
	$(function(){
		$("input:checkbox[id='kselected']").click(function(){
			var checked = $("input:checkbox[id='dselected']").prop("checked") == true
			console.log(checked);
			if(checked==false){
				$("input:checkbox[id='dselected']").prop("checked", true);
			}else{
				$("input:checkbox[id='dselected']").prop("checked", false);
			}
		});
	}); 
	var Deletecheck = new Array();
	
	function DeleteCheck(){
		if(confirm("삭제할까요?")){
			$("input:checkbox[id='dselected']:checked").each(function(){
				Deletecheck.push(this.value);
			});
			$('#hiddenValue').val(Deletecheck);
			console.log(Deletecheck);
			for(i=0; i<Deletecheck.length; i++){
				console.log(Deletecheck[i])
				location.href="boardDel?no="+ Deletecheck[i];
			}
		}
	}
</script>
</head>
<body>
	<div class="container">
		<h1>Oracle noXML 리스트</h1>
		<c:if test="${logStatus =='Y'}">
			<a href="write">글쓰기</a>
		</c:if>
		<br/>
		<c:if test="${logStatus =='Y'}">
			<input type="button" value="삭제하기" id="clickCheckbox" onclick="DeleteCheck()"/>
		</c:if>
		<div id="searchbox">
			<form method="get" action="search" id="SearchForm">
				<select name="searchKey" id="searchKey">
					<option value="subject" selected>제목</option>
					<option value="userid">작성자</option>
					<option value="content">글내용</option>
				</select>
				<input type="text" placeholder="검색할 내용을 입력하세요" id="searchWord" name="searchWord">
				<input type="submit" value="검색하기" id="SearchButton">
			</form>
		</div>
		<ul id="boardList">
			<li><input type="checkbox" name="kselected" id="kselected" value="kselected"/>전체선택</li>
			<li>번호</li>
			<li>제목</li>
			<li>작성자</li>
			<li>등록일</li>
			<li>조회수</li>
			<c:forEach var="vo" items="${list}">
				<li><input type="checkbox" name="dselected" id="dselected" value="${vo.no}"/></li>
				<li>${vo.no }</li>
				<li class="wordcut"><a href="view?no=${vo.no }">${vo.subject }</a></li>
				<li>${vo.userid }</li>
				<li>${vo.writedate }</li>
				<li>${vo.hit }</li>
			</c:forEach>
		</ul>
		<input type="hidden" name="hiddenValue" id="hiddenValue" value=""/>
	</div>
</body>
</html>
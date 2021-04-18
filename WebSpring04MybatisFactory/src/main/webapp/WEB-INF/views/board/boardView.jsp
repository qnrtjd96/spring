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
<Script>
	function boardDel(){
		if(confirm("삭제하시겠습니까?")){
			location.href="boardDel?no=${vo.no}"
		}
	}
	
	//ajax이용한 댓글처리
	$(function(){
			//댓글목록 선택
			function replyList(){
				var url = "replyList";
				var params = "no=${vo.no}";
				
				$.ajax({
					url:url,
					data:params,
					success:function(result){
						var $result = $(result); //vo, vo, vo, vo
						
						var tag="<ul>";
						$result.each(function(idx, obj){
							tag += "<li style='border-bottom:1px solid #ddd'><div>"+ obj.userid + "(" + obj.replydate + ") ";
							if(obj.userid =="${logId}"){ //"goguma777"
								tag += "<input type='button' value='수정'/>";
								tag += "<input type='button' value='삭제' title="+obj.num+">";
							}
							tag += "<br/>" + obj.content + "</div>";
 							if(obj.userid=="${logId}"){
								//수정폼
								tag += "<div style='display:none'>"
								tag += "<form method='post' id='Update'>"
								tag += "<textarea name='content' style='width:500px;height:80px;'>"+obj.content+"</textarea>"
								tag += "<input type='submit' value='수정하기'/>"
								tag += "<input type='hidden' name='num' value='"+obj.num+"'/>"
								tag += "</form></div>"
							} 
							
							tag +="</li>";
						});
						tag += "</ul>"
						$("#replyList").html(tag);
						
					},error:function(){
						console.log("댓글 가져오기 에러..");
					}
				});
			}
			//댓글쓰기
			$("#replyWriteBtn").click(function(){
				if($("#content").val()!=''){
					var url = "replyWriteOk";
					var params = $("#replyWriteForm").serialize();//데이터		no=181&content="작성중"
					
					$.ajax({
						url:url,
						data: params,
						beforeSend : function(xmlHttpRequest){
							xmlHttpRequest.setRequestHeader("AJAX", "true");
						},
						success:function(result){
							console.log(result);
							replyList();
							$("#content").val('');
							console.log("성공");
						},error:function(e){
							if(e.status==400){
								location.href="<%=request.getContextPath()%>/loginForm"
							}
						}
					})
				}else{
					alert("댓글을 입력후 등록버튼을 눌러주세요");
				}
		  });
			
		//글수정
		$(document).on('submit','#Update', function(){
				var url = "replyEditOk";
				var params = $(this).serialize();//content=090&num=242;
				
				$.ajax({
					url:url,
					data : params,
					type : "POST",
					success:function(result){
						replyList();
					}, error:function(){
						console.log('댓글수정에러');
					}
				});
		});
		
		//댓글삭제
		$(document).on('click','#replyList input[value=삭제]',function(){
			if(confirm('삭제하시겠습니까?')){
				var url = "replyDel";
				var params = "num="+$(this).attr("title");
				
				$.ajax({
					url :url,
					data  : params,
					success:function(result){
						replyList();
					},error:function(){
						console.log("댓글삭제 실패");
					}
				});
			}
		});
		//수정버튼 선택시 수정폼 보여주기
		$(document).on('click', '#replyList input[value=수정]', function(){
			$("#replyList>ul>li>div:nth-child(1)").css("display","block");
			$("#replyList>ul>li>div:nth-child(2)").css("display","none");
			
			$(this).parent().css("display", "none");
			$(this).parent().next().css("display", "block");
		});
		replyList();
	});
</Script>
</head>
<body>
	<div class="container">
		<h1>글내용보기</h1>
		번호 : ${vo.no }<br/>
		작성자 : ${vo.userid }<br/>
		작성일 : ${vo.writedate }, 조회수 : ${vo.hit }<br/>
		제목: ${vo.subject }<br/>
		글내용 : ${vo.content }<br/>
		<c:if test="${logId==vo.userid}">
			<a href="boardEdit?no=${vo.no}">수정</a>
			<a href="javascript:boardDel()">삭제</a>
		</c:if>
		<div>
			<%-- <c:if test="${logId != null}"><!-- 로그인이 된 경우만 보여줄껏  --> --%>
				<form method="post" id="replyWriteForm">
					<input type="hidden" name="no" value="${vo.no}"/>
					<textarea name="content" id="content" style="width:500px; height:100px;"></textarea>
					<input type="button" value="댓글등록" id="replyWriteBtn"/>
				</form>
			<%-- </c:if> --%>
		</div>
		<!-- 댓글리스트 -->
		<div id="replyList">
			
		</div>
	</div>
	
</body>
</html>
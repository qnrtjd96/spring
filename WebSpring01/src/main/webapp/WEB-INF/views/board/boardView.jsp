<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	function delCheck(recordNo){
		if(confirm("정말 삭제할까요?")){
			location.href="/myapp/boardDelete?no="+recordNo;
		}
	}
	
	//ajax이용한 댓글처리
	$(function(){
			//댓글목록 선택
			function replyList(){
				var url = "/myapp/boardView";
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
								tag += "<input type='button' value='삭제' title="+obj.num+"/>";
							}
							tag += "<br/>" + obj.content + "</div>";
 							if(obj.userid=="${logId}"){
								//수정폼
								tag += "<div style='display:none'>"
								tag += "<form method='post'>"
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
					var url = "/myapp/replyWriteOk";
					var params = $("#replyWriteForm").serialize();//데이터		no=181&content="작성중"
					
					$.ajax({
						url:url,
						data: params,
						success:function(result){
							console.log('댓글등록성공---> ' + result);
							
						},error:function(e){
							console.log(e.responseText);
						}
					})
				}else{
					alert("댓글을 입력후 등록버튼을 눌러주세요");
				}
		  });
			
		//글수정
		$(document).on('sumit','#replyList form', function(){
				var url = "/myapp/replyEditOk";
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
		replyList(); //글 내용보기가 실행되면 댓글이 ajax로 실행됨
		
		//댓글삭제
		$(document).on('click','#replyList input[value=삭제]',function(){
			if(confirm('삭제하시겠습니까?')){
				var url = "/myapp/replyDel";
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
	});
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
	<!-- 댓글폼 -->
	<div>
		<c:if test="${logStatus=='Y'}"><!-- 로그인이 된 경우만 보여줄껏  -->
			<form method="post" id="replyWriteForm">
				<input type="hidden" name="no" value="${vo.no}"/>
				<textarea name="content" id="content" style="width:500px; height:100px;"></textarea>
				<input type="button" value="댓글등록" id="replyWriteBtn"/>
			</form>
		</c:if>
	</div>
	<!-- 댓글리스트 -->
	<div id="replyList">
		
	</div>
</div>
</body>
</html>
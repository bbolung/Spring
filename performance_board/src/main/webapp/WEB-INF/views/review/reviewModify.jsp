<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp" %>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/js/bootstrap.min.js"></script>

<body>
   	<div class="contents">
	   	<nav aria-label="breadcrumb">
		     <ul class="sopt_list breadcrumb">
		         <li><span class="material-symbols-outlined">home</span></li>
		         <li><span class="separator"></span>공연/행사 관람평</li>
		     </ul>
	    </nav>
       	<form role="form" action="/review/reviewModify" method="post">
        	<div class="reivewDetail-header">
        	
        		<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
                <input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
        	
	            <div class="reivewDetail-img">
	                <div class="img-wrap">
	                    <img class="imgUrl" src="${performance.img}" alt="poster">
	                </div>
	            </div>
	            <table class="info-table">
	            	<input type="hidden" name = "bno" value='<c:out value="${performance.bno}" />'>
	            	<input type="hidden" name = "img" value='<c:out value="${performance.img}" />'>
	            	
	                <tr>
	                    <th>공연/행사명</th>
	                    <td colspan="3">
	                        <input class="form-control" name="title" 
	                        	value="<c:out value="${performance.title}" />">
	                    </td>
	                </tr>
	                <tr>
	                    <th>작성자</th>
	                    <td>
	                        <input class="form-control" name="writer" 
	                        	value="<c:out value="${performance.writer}" />" readonly>
	                    </td>
	                    <th>조회수</th>
	                    <td>
	                        <input class="form-control" name="viewCount" 
	                        	value="<c:out value="${performance.readCount}" />" readonly>
	                        
	                    </td>
	                </tr>
	                <tr>
	                    <th>작성일</th>
	                    <td>
	                        <input class="form-control" name="regDate" 
	                        	value="<fmt:formatDate pattern="yyyy-MM-dd" value="${performance.regDate}"/>" readonly>
	                    </td>
	                    <th>수정일</th>
	                    <td>
	                        <input class="form-control" name="updateDate" 
	                       		 value="<fmt:formatDate pattern="yyyy-MM-dd" value="${performance.updateDate}"/>" readonly>
	                    </td>
	                </tr>
	                <tr>
	                    <th colspan="4">내용</th>
	                </tr>
	                <tr>
	                    <td colspan="4">
	                        <textarea class="form-control" name="content" rows="10">
	                        <c:out value="${performance.content}" /></textarea>
	                    </td>
	                </tr>
	            </table>
        	</div>
        	<!-- end reivewDetail-header -->
	        <div class="button-wrap">
		        <div class="button">
		            <button type="submit" data-oper="modify" class="btn btn-info">Modify</button>
		            <button type="submit" data-oper="remove" class="btn btn-danger">Remove</button>
		            <button type="submit" data-oper="list" class="btn btn-info">List</button>
		        </div>
	        </div>
	        <!-- end button-wrap -->
         </form>
    </div>
	<!-- end contents -->
	    
    <!-- .row 댓글 처리 -->
    <div class="reply-section">
	    <div class="container">
	        <div class="row justify-content-center">
	            <div class="col-lg-12">
	               <div class="panel panel-default">
	                   <div class="panel-heading">
	                       <i class="fa fa-comments fa-fw"></i>reply
	                       <button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">댓글 등록</button>
	                   </div>
	                <!-- end reply-heading -->
	                <div class="panel-body">
	                    <ul class="chat">
	                        <li class="left clearfix clearfix" data-rno="1">
	                            <div class="header">
	                                <strong class="primary-dont">user00</strong>
	                                <small class="pull-right text-muted">2025-05-19</small>
	                            </div>
	                            <p>Success!</p>
	                        </li>
	                    </ul>
	                </div>
	                <!-- end panel-body -->
	                <div class="panel-footer">
	                </div>
	                </div>
	            </div>
	            <!-- end panel-->
	        </div>
	        <!-- end col-lg-12-->
	    </div>
	    <!-- end row-->
    </div>

<!-- Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
    
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal title</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      
      <!-- Modal body -->
      <div class="modal-body">
	   	<div class="form-group">
	   		<label>Reply</label>
	   		<input class="form-control" name="reply" value="New Reply!!!">
	   	</div>
	   	<div class="form-group">
	   		<label>Replyer</label>
	   		<input class="form-control" name="replyer" value="Replyer">
	   	</div>
	   	<div class="form-group">
	   		<label>Reply Date</label>
	   		<input class="form-control" name="replyDate" value="">
	   	</div>
      </div>
      
      <!-- Modal footer -->
      <div class="modal-footer">
     	<!-- data-dismiss="modal" : 창을 닫아주는 역할 -->
        <button id="modalRegisterBtn" type="button" class="btn btn-primary">Register</button>
        <button id="modalModBtn" type="button" class="btn btn-info">Modify</button>
        <button id="modalRemoveBtn" type="button" class="btn btn-danger">Remove</button>
        <button id="modalCloseBtn" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
      
    </div>
  </div>
</div>
<!-- end Modal -->
    
<script type="text/javascript" src="../resources/js/reply.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		
		let bnoValue = '<c:out value = "${performance.bno}" />';
		let replyUL = $(".chat");
		
		showList(1);
		
		function showList(page){
			replyService.getList(
				{bno: bnoValue, page: page || 1},
				
				function(replyCnt, list){
					
					//등록 시 마지막 페이지로 이동
					if(page == -1){
						pageNum = Math.ceil(replyCnt/10.0);
						showList(pageNum);
						return;
					}
					
					let str = "";
					
					if(list == null || list.length == 0){
						replyUL.html("");
						return;
					}
	
					for(let i=0; i<list.length; i++){
						str += "<li class='left clearfix clearfix' data-rno=" + list[i].rno + ">"
						str += "<div class='header'>"
						str += "<strong class='primary-dont'>" + list[i].replyer + "</strong>"
						str += "<small class='pull-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small>"
						str += "</div>"
						str += "<p>" + list[i].reply + "</p>"
						str += "</li>"
					}
					
					replyUL.html(str);
					
					showReplyPage(replyCnt);
				}
			)
			
		}; //end showList
		
		let modal = $(".modal");
		let modalInputReply = modal.find("input[name='reply']");
		let modalInputReplyer = modal.find("input[name='replyer']");
		let modalInputReplyDate = modal.find("input[name='replyDate']");
		
		let modalRegisterBtn = $("#modalRegisterBtn")
		let modalModBtn = $("#modalModBtn");
		let modalRemoveBtn = $("#modalRemoveBtn");
		
		//댓글 등록 화면
		$("#addReplyBtn").on("click", function(e){
			modal.find("input").val("");
			modalInputReplyDate.closest("div").hide();
			modal.find("button[id != 'modalCloseBtn']").hide();
			
			modalRegisterBtn.show();
			
			modal.modal("show");
		});
		
		//댓글 저장
		modalRegisterBtn.on("click", function(e){
			
			let reply = {
					reply: modalInputReply.val(),
					replyer: modalInputReplyer.val(),
					bno: bnoValue
			}
			
			replyService.add(reply, function(result){
				alert(result);
				modal.find("input").val("");
				modal.modal("hide");
				
				showList(-1);
			});
		});
		
		//댓글 클릭 이벤트 위임
		replyUL.on("click", "li", function(e){
			let rno = $(this).data('rno');
			
			replyService.get(rno,
				function(reply){
				modalInputReply.val(reply.reply);
				modalInputReplyer.val(reply.replyer);
				modalInputReplyDate.val(replyService.displayTime(reply.replyDate))
									.attr("readonly", "readonly");
				modal.data("rno", reply.rno);
				
				modal.find("button").hide();
				modal.find("#modalCloseBtn").show();
				modalModBtn.show();
				modalRemoveBtn.show();
				
				modal.modal("show");
				}
			);
		});
		
		//댓글 수정 이벤트 처리
		modalModBtn.on("click", function(e){
			let reply = {
					rno: modal.data('rno'),
					reply: modalInputReply.val()
			};
			
			replyService.update(reply, function(result){
				alert(result);
				modal.modal("hide");
				showList(pageNum);
			});
		});
		
		//댓글 삭제 이벤트 처리
		modalRemoveBtn.on("click", function(e){
			let rno = modal.data('rno');
			
			replyService.remove(rno, function(result){
				alert(result);
				modal.modal("hide");
				showList(pageNum);
			});
		});
		
		//페이징 처리
		let pageNum = 1;
		let replyPageFooter = $(".panel-footer");
		
		function showReplyPage(replyCnt){
			
			let endNum = Math.ceil(pageNum / 10.0) * 10;
			let startNum = endNum - 9;
			
			let prev = startNum != 1;
			let next = false;
			
			if(endNum * 10 >= replyCnt) {
				endNum = Math.ceil(replyCnt/10.0);
			}
			
			if(endNum * 10 < replyCnt){
				next = true;
			}
			
			let str = "<ul class='pagination pull-right'>";
			
			if(prev){
				str += "<li class='page-item'>"
				str += "<a class='page-link' href='" + (startNum-1) + "'>Previous</a></li>";
			}
			
			for(let i=startNum; i<=endNum; i++){
				let active = pageNum == i ? "active" : "";
				str += "<li class'page-item " + active +"'><a class='page-link' href='"+ i +"'>" + i + "</a></li>";
			}
			
			if(next){
				str += "<li class='page-item'>"
				str += "<a class='page-link' href='"+ (endNum+1) +"'>Next</a></li>";
			}
			
			str += "</ul>";
			
			console.log(str);
			
			replyPageFooter.html(str);
		
		}; //end showReplyPage
		
		replyPageFooter.on("click", "li a", function(e){
			e.preventDefault();
			
			let targetPageNum = $(this).attr("href");
			
			pageNum = targetPageNum;
			
			showList(pageNum);
		
		}); //end replyPageFooter
		
	});
</script>

<%@ include file="../includes/footer.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
		let formObj = $("form");
		
		$("button").on("click", function(e){
			e.preventDefault();		
			
			let operation = $(this).data("oper");	
			
			if(operation === 'remove'){
				formObj.attr("action", "/review/reviewRemove")
			}else if(operation === 'list'){
				formObj.attr("action", "/review/reviewList").attr("method", "get");
				
				let pageNumTag = $("input[name='pageNum']").clone();
				let amountTag = $("input[name='amount']").clone();
				
				formObj.empty();
				formObj.append(pageNumTag);
				formObj.append(amountTag);
			}
			formObj.submit();
		});
	});
</script>
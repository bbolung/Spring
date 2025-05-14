<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp" %>
 
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Tables</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board Register
            </div>
            <!-- end panel-heading -->
            <div class="panel-body">
                	<div class="form-group">
                		<lable>Bno</lable>
                		<input class="form-control" name="bno" 
                			value="<c:out value='${board.title}' />" readonly="readonly">
                	</div>
                
                	<div class="form-group">
                		<label>Title</label>
                		<input class="form-control" name="title" 
                			value="<c:out value='${board.title}' />" readonly="readonly">
                	</div>
                	
                	<div class="form-group">
	                	<label>Text area</label>
	                	<textarea rows="3" class="form-control" name = "content" readonly="readonly">
	                		<c:out value="${board.content}" />
	                	</textarea>
                	</div>
                	
                	<div class="form-group">
                		<label>Writer</label>
                		<input class="form-control" name = "writer" 
                			value="<c:out value='${board.writer}' />" readonly="readonly">
                	</div>
                	
                	<button data-oper="modify" class="btn btn-info">Modify</button>
                	<button data-oper="list" class="btn btn-default">List</button>
                	
                	<form id="openForm" action="/board/modify" method="get">
                		<input type="hidden" id="bno" name="bno" value="<c:out value='${board.bno}'/>">
                		<input type="hidden" name="pageNum" value="${cri.pageNum}">
                		<input type="hidden" name="amount" value="${cri.amount}">
                	</form>
            </div>
            <!-- end panel-body -->
        </div>
        <!-- end panel -->
    </div>
    <!-- end col-lg-12 -->
</div>
<!-- end row -->

<!-- /.row  댓글 처리-->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-comments fa-fw"></i>Reply
                <button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">댓글 등록</button>
            </div>
            <!-- end panel-heading -->
            <div class="panel-body">
                	<ul class="chat">
                		<li class="left clearfix" data-rno='12'>
	                		<div>
	                			<div class="header">
	                				<strong class="primary-dont">user00</strong>
	                				<small class="pull-right text-muted">2025-05-14</small>
	                			</div>
	                			<p>Good Job!</p>
	                		</div>
                		</li>
                	</ul>
            </div>
            <!-- end panel-body -->
        </div>
        <!-- end panel -->
    </div>
    <!-- end col-lg-12 -->
</div>
<!-- end row -->

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
	   		<lable>Reply</lable>
	   		<input class="form-control" name="reply" value="New Reply!!!">
	   	</div>
	   	<div class="form-group">
	   		<lable>Replyer</lable>
	   		<input class="form-control" name="replyer" value="Replyer">
	   	</div>
	   	<div class="form-group">
	   		<lable>Reply Date</lable>
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

<script type = "text/javascript" src="/resources/js/reply.js"></script>

<script type="text/javascript">
	
	$(document).ready(function(){
		let bnoValue = '<c:out value="${board.bno}"/>';
		
		let replyUL = $(".chat");
		
		showList(1);
		
		function showList(page){
			replyService.getList(
					{bno: bnoValue, page: page || 1},
					
					function(list){
						let str="";
						
						if(list == null || list.length == 0){
							replyUL.html("");
							return;
						}
						
						for(let i=0; i<list.length; i++){
							str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>"
							str += "<div>"
							str += "<div class='header'>"
							str += "<strong class='primary-dont'>"+list[i].replyer+"</strong>"
	                		str += "<small class='pull-right text-muted'>"+ replyService.displayTime(list[i].replyDate) +"</small>"
	                		str += "</div>"		
	                		str += "<p>"+list[i].reply+"</p>"
	                		str += "</div>"
	                		str += "</li>"
						}
						
						replyUL.html(str);
					}
			)
		}; //end showList()
		
		let modal = $(".modal");
		let modalInputReply = modal.find("input[name='reply']");
		let modalInputReplyer = modal.find("input[name='replyer']");
		let modalInputReplyDate = modal.find("input[name='replyDate']");
		
		let modalRegisterBtn = $("#modalRegisterBtn");
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
		
		//댓글 처리(DB 저장)
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
				
				showList(1);
			});
		});
		
		//댓글 클릭 이벤트 처리
		replyUL.on("click", "li", function(e){
			let rno = $(this).data('rno');
			
			//console.log(rno);
			
			//input에 rno로 해당 댓글 데이터 가져와서 보여줌
			replyService.get(rno,
				function(reply){
					modalInputReply.val(reply.reply);
					modalInputReplyer.val(reply.replyer);
					modalInputReplyDate.val(replyService.displayTime(reply.replyDate))
					.attr("readonly", "readonly");
					modal.data("rno", reply.rno);
					
					modal.find("button[id = 'modalRegisterBtn']").hide();
					
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
				showList(1);
			});
		});
		
		//댓글 삭제 이벤트 처리
		modalRemoveBtn.on("click", function(e){
			let rno =  modal.data('rno');
			
			replyService.remove(rno, function(result){
				alert(result);
				modal.modal("hide");
				showList(1);
			});
		});
		
	});
	
	
	/*
	테스트 코드
	
	let bnoValue = '<c:out value="${board.bno}"/>';
	
	replyService.add(
		{reply:"JS Test", replyer: "tester", bno:1},
		
		function(result){
			alert("Result: " + result);
		}
	);
	*/
	
	/*
	replyService.getList({bno:bnoValue, page:1},
		
		function(list){
			for(let i=0; i<list.length; i++){
				console.log(list[i]);
			}
		}
	);
	*/
	
	/*
	replyService.remove(41, 
			function(count){
				if(count == 'success'){
					alert("삭제 성공");
				}
			},
			function(err){
				alert("ERROR...."+err);
			}
	})
	*/
	
	/*
	replyService.get(20, function(result){
		alert(result);
		}
	);
	*/
	
	/*
	replyService.update(
		{rno: 21, reply: "방금 댓글 내용 수정"},
		function(result){
			alert(result);		//성공할 경우 알림창 : success
		}
	);
	*/
	
</script>

<script type="text/javascript">
	$(document).ready(function(){
		let operForm = $("#openForm");
		
		$("button[data-oper='modify']").on("click", function(e){
			operForm.attr("action", "/board/modify").submit();
		});
		
		$("button[data-oper='list']").on("click", function(e){
			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list").submit();
		});
	});
</script>

<%@ include file="../includes/footer.jsp" %>
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

<script type = "text/javascript" src="/resources/js/reply.js"></script>

<script type="text/javascript">
	let bnoValue = '<c:out value="${board.bno}"/>';
	
	/*
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
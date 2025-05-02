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
            	<form role="form" action="/board/modify" method="post">
                	<div class="form-group">
                		<lable>Bno</lable>
                		<input class="form-control" name="bno" 
                			value="<c:out value='${board.bno}' />" readonly="readonly">
                	</div>
                
                	<div class="form-group">
                		<label>Title</label>
                		<input class="form-control" name="title" 
                			value="<c:out value='${board.title}' />">
                	</div>
                	
                	<div class="form-group">
	                	<label>Text area</label>
	                	<textarea rows="3" class="form-control" name = "content">
	                		<c:out value="${board.content}" />
	                	</textarea>
                	</div>
                	
                	<div class="form-group">
                		<label>Writer</label>
                		<input class="form-control" name = "writer" 
                			value="<c:out value='${board.writer}' />" readonly="readonly">
                	</div>
                	
                	<button type="submit" data-oper="modify" class="btn btn-default">Modify</button>
                	<button type="submit" data-oper="remove" class="btn btn-danger">Remove</button>
                	<button type="submit" data-oper="list" class="btn btn-info">List</button>
                </form>
            </div>
            <!-- end panel-body -->
        </div>
        <!-- end panel -->
    </div>
    <!-- end col-lg-12 -->
</div>
<!-- end row -->

<%@ include file="../includes/footer.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
		let formObj = $("form");
		
		$("button").on("click", function(e){
			e.preventDefault();			//이벤트의 기본 동작을 막는 역할
			
			let operation = $(this).data("oper");	//data-oper의 값 가져옴
			
			if(operation === 'remove'){
				formObj.attr("action", "/board/remove")
			}else if(operation === 'list'){
				formObj.attr("action", "/board/list").attr("method", "get");
				formObj.empty();		//목록 페이지 이동할 때 입력한 데이터 필요X -> 전달되는 값 비우기
				return ;
			}
			formObj.submit();
		});
	});
</script>
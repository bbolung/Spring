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
        처리가 완료되었습니다.
      </div>
      
      <!-- Modal footer -->
      <div class="modal-footer">
     	<!-- data-dismiss="modal" : 창을 닫아주는 역할 -->
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save Changes</button>
      </div>
      
    </div>
  </div>
</div>
<!-- end Modal -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board List Page
                <button id="regBtn" type="button" class="btn btn-xs pull-right">Register New Board</button>
            </div>
            <!-- end panel-heading -->
            <div class="panel-body">
                <table width="100%" class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>#번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>수정일</th>
                        </tr>
                    </thead>
                    
                    <c:forEach var="board" items="${list}">
                    	<tr>
                    		<td><c:out value="${board.bno}" /></td>
                    		
                    		<%--el 표기법 : <td><a href='/board/get?bno=${board.bno}'>${board.title}</a></td> --%>
                    		
                    		<td><a href='/board/get?bno=<c:out value="${board.bno}" />'>
                    		<c:out value="${board.title}" /></a></td>
                    		
                    		<td><c:out value="${board.writer}" /></td>
                    		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}" /></td>
                    		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}" /></td>
                    	</tr>
                    </c:forEach>
                </table>
                
                <!-- 페이징 처리 -->
                <div class="container">
                	<div class="pull-right">
					  <ul class="pagination">
					  	
					  	<c:if test="${pageMaker.prev}">
					    	<li class="page-item"><a class="page-link" href="#">Previous</a></li>
					    </c:if>
					    
					    <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
						    <li class="page-item"><a class="page-link" href="#">${num}</a></li>
					    </c:forEach>
					    
					    <c:if test="${pageMaker.next}">
					    	<li class="page-item"><a class="page-link" href="#">Next</a></li>
					    </c:if>
					  </ul>
					 </div>
				</div>
                <!-- end 페이징 처리 -->
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
		let result = '<c:out value="${result}"/>';
		
		checkModal(result);		//result 매개변수로 함수 호출
		
		//브라우저의 현재 히스토리 항목을 새로운 상태로 대체
		//페이지를 새로고침 하거나 뒤로 가기 했을 때 모달창이 다시 표시되지 않도록 함
		history.replaceStat({}, null, null);
		
		function checkModal(result){
			if(result =='' || history.state) return;		//새로고침한 경우 result 값이 X
			
			if(parseInt(result) > 0){
				//modal-body에 처리가 완료되었습니다. 대신 html 출력
				$(".modal-body").html("게시글 " + result + "번이 등록되었습니다.");	
			}else {
				$(".modal-body").html("게시글 " + result);
			}
			
			$("#myModal").modal("show");	//modal창 보여줌
		} //end checkModal
		
		$("#regBtn").on("click", function(){	//id="regBtn" 버튼 클릭시 함수 동작
			self.location = "/board/register";
		});
	});
</script>
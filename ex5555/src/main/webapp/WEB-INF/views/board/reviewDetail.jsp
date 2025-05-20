<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp" %>

<link rel="stylesheet" href="../resources/css/review/reviewDetail.css">
<link rel="stylesheet" href="../resources/css/review/bootstrap.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
     integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />

<body>
   	<div class="contents">
	   	<nav aria-label="breadcrumb">
		     <ul class="sopt_list breadcrumb">
		         <li><span class="material-symbols-outlined">home</span></li>
		         <li><span class="separator"></span>공연/행사 관람평</li>
		     </ul>
	    </nav>
        <div class="reivewDetail-header">
            <div class="reivewDetail-img">
                <div class="img-wrap">
                    <img class="imgUrl" src="https://www.noblesse.com/shop/data/m/editor_new/2020/09/09/3cbef4f7922ef6b305.jpg" alt="poster">
                </div>
            </div>
            <table class="info-table">
                <tr>
                    <th>공연/행사명</th>
                    <td colspan="3">
                        <input class="form-control" name="title" value="해당 공연/행사명" readonly>
                    </td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>
                        <input class="form-control" name="writer" value="testWriter" readonly>
                    </td>
                    <th>조회수</th>
                    <td>
                        <input class="form-control" name="viewCount" value="10" readonly>
                    </td>
                </tr>
                <tr>
                    <th>작성일</th>
                    <td>
                        <input class="form-control" name="regDate" value="2025-05-19" readonly>
                    </td>
                    <th>수정일</th>
                    <td>
                        <input class="form-control" name="updateDate" value="2025-05-19" readonly>
                    </td>
                </tr>
                <tr>
                    <th colspan="4">내용</th>
                </tr>
                <tr>
                    <td colspan="4">
                        <textarea class="form-control" name="content" rows="10" readonly>사용자가 입력한 내용</textarea>
                    </td>
                </tr>
            </table>
        </div>
        <div class="button-wrap">
        <div class="button">
            <button type="submit" data-oper="modify" class="btn btn-info">Modify</button>
            <button type="submit" data-oper="remove" class="btn btn-danger">Remove</button>
            <button type="submit" data-oper="list" class="btn btn-default">List</button>
        </div>
        </div>
        <!-- end button -->
    </div>
    <!-- end reivewDetail-header -->
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
<%@ include file="../includes/footer.jsp" %>
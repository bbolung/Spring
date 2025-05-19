<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp" %>

<div class="panel-body">
        <div class="form-group">
            <img src="" alt="poster" width=350px, height=400px>
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
                        <textarea class="form-control" name="content" rows="15" readonly>사용자가 입력한 내용</textarea>
                    </td>
                </tr>
            </table>
        </div>
        <div class="button">
            <button type="submit" data-oper="modify" class="btn btn-info">Modify</button>
            <button type="submit" data-oper="remove" class="btn btn-danger">Remove</button>
            <button type="submit" data-oper="list" class="btn btn-default">List</button>
        </div>
        <!-- end button -->
    </div>
    <!-- end panel-body -->
    
    <!-- .row 댓글 처리 -->
    <div class="row">
        <div class="col-lg-12">
            <div class="reply">
                <div class="reply-heading">
                    <i class="fa fa-comments fa-fw"></i>reply
                </div>
                <!-- end reply-heading -->
                <div class="reply-body">
                    <ul class="chat">
                        <li class="left clearfix" data-rno="1">
                            <div class="header">
                                <strong class="primary-dont">user00</strong>
                                <small class="pull-right text-muted">2025-05-19</small>
                            </div>
                            <p>Success!</p>
                        </li>
                    </ul>
                </div>
                <!-- end reply-body -->
                <div class="reply-footer">
                </div>
            </div>
            <!-- end reply-->
        </div>
        <!-- end col-lg-12-->
    </div>
    <!-- end row-->

<%@ include file="../includes/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../includes/header.jsp" %>  
  
<!-- 공연/행사 목록 테이블 -->
<div class="krds-table-wrap" style="max-width: 1200px; margin: 60px auto 0 auto;">
	<table class="tbl col data">
		<colgroup>
            <col style="width: 5%;">
            <col style="width: 10%;">
            <col style="width: 10%;">
            <col>
            <col style="width: 20%;">
            <col style="width: 20%;">
		</colgroup>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">이미지</th>
				<th scope="col">분류</th>
				<th scope="col">공연/행사명</th>
				<th scope="col">장소</th>
				<th scope="col">기간</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="event" items="${eventList}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>
						<img src="${event.imageUrl}" alt="포스터" width="80"
						     onerror="this.onerror=null; this.src='/resources/images/noimage.png';">
					</td>
					<td>${event.category}</td>
					<td>
						<a href="/event/detail?no=${event.id}">${event.title}</a>
					</td>
					<td>${event.location}</td>
					<td>${event.startDate} ~ ${event.endDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<!-- 페이징 영역 -->
<nav class="krds-pagination-wrap" aria-label="페이지 네비게이션" style="margin-top: 40px; text-align: center;">
	<ul class="krds-pagination" style="display: inline-flex; list-style: none; padding: 0; gap: 6px;">
		
		<!-- 이전 버튼 -->
		<c:if test="${pageMaker.prev}">
			<li>
				<a href="?pageNum=${pageMaker.startPage - 1}&amount=${pageMaker.cri.amount}&keyword=${param.keyword}&category=${param.category}" 
				   class="btn line small" aria-label="이전">&laquo;</a>
			</li>
		</c:if>

		<!-- 페이지 번호 -->
		<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<li>
				<a href="?pageNum=${num}&amount=${pageMaker.cri.amount}&keyword=${param.keyword}&category=${param.category}"
				   class="btn line small <c:if test='${pageMaker.cri.pageNum == num}'>filled primary</c:if>">
					${num}
				</a>
			</li>
		</c:forEach>

		<!-- 다음 버튼 -->
		<c:if test="${pageMaker.next}">
			<li>
				<a href="?pageNum=${pageMaker.endPage + 1}&amount=${pageMaker.cri.amount}&keyword=${param.keyword}&category=${param.category}" 
				   class="btn line small" aria-label="다음">&raquo;</a>
			</li>
		</c:if>

	</ul>
</nav>
        
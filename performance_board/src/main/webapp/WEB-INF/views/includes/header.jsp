<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
    
	<!-- css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common/reviewDetail.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
	     integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
	    
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common/list.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common/common.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/component/component.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/component/output.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/plugin/swiper-bundle.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/token/krds_tokens.css">

	<script src="${pageContext.request.contextPath}/resources/js/component/ui-script.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/plugin/swiper-bundle.min.js"></script>
</head>
<style>
    .krds-tab-area.layer {
      max-width: 1400px;
      margin: 0 auto;
      padding: 0 20px;
    }
</style>

<body>
<!-- header -->
<header id="krds-header" >
	
	<!-- 헤더 컨텐츠 영역  -->
	<div class="header-in">
		<!-- 헤더 상단 기타메뉴 -->
		<div class="header-container">
			<div class="inner">
				
				<div class="header-branding">
					<h2 class="logo">
						<a href="#">
							<span class="sr-only">KRDS - Korea Movie System</span>
						</a>
					</h2>
					
					<div class="header-actions">
						<a href="#" class="btn-navi login">로그인</a>
						<button type="button" class="btn-navi join">회원가입</button>
						<div class="krds-drop-wrap my-drop">
							<button type="button" class="btn-navi my drop-btn">나의 GOV</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- //헤더 상단 기타메뉴 -->
		<%
    	String tab = (String) request.getAttribute("tab");
    	if (tab == null) tab = "performance"; // 기본 탭 지정
		%>
		
		<div class="krds-tab-area layer">
			  <div class="tab line full">
			    <nav class="krds-main-menu">
			      <div class="inner">
			        <ul class="gnb-menu">
			          <li>					<!-- 호출할 탭 경로 -->
			            <a href="${pageContext.request.contextPath}/performance/performanceList" class="gnb-main-trigger is-link <%= tab.equals("performance") ? "active" : "" %>" data-trigger="gnb">
			              공연/행사 정보 <i class="svg-icon ico-toggle"></i>
			            </a>
			          </li>
			          <li>					<!-- 호출할 탭 경로 -->
			            <a href="${pageContext.request.contextPath}/review/reviewList" class="gnb-main-trigger is-link <%= tab.equals("review") ? "active" : "" %>" data-trigger="gnb">
			              관람평 <i class="svg-icon ico-toggle"></i>
			            </a>
			          </li>
			        </ul>
			      </div>
			    </nav>
			  </div>
		</div>
		
		
		
</header>
<!-- //header -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

    <link href="../resources/css/PerformanceDetail.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />

<body>
    <section class>
        <div class="container">
            <div class="p-content">
                <div class="art_drop_box">
                    <nav aria-label="breadcrumb">
                    <ul class="sopt_list breadcrumb">
                        <li><span class="material-symbols-outlined">home</span></li>
                        <li><span class="separator">›</span> 공연/행사 정보</li>
                        <li><span class="separator">›</span> 공연행사명</li>
                    </ul>
                    </nav>
                
                    <div class="contents">
                       
                        <div class="performanceDetail-header">
                            <div class="performanceDetail-img">
                                <div class="img-wrap">
                                    <img class="imgUrl" src="https://culture.seoul.go.kr/cmmn/file/getImage.do?atchFileId=42afe00583eb4b0983dba37a04a41222&thumb=Y,K-핸드메이드페어" alt="행사 이미지" />
                                </div>
                            </div>
                            <div class="performanceDetail-info">
                                 <div class="title">공연/행사명</div>
                                <table class="performanceDetail-table">
                                    <tbody>
                                        <tr><th>기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;간</th><td>period</td></tr>
                                        <tr><th>이&nbsp;용&nbsp;대&nbsp;상</th><td>userTrg</td></tr>
                                        <tr><th>분&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;야</th><td>category</td></tr>
                                        <tr><th>장&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소</th><td>place</td></tr>
                                        <tr><th>가&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;격</th><td>price</td></tr>
                                        <tr><th>홈&nbsp;페&nbsp;이&nbsp;지</th><td>homepage</td></tr>
                                    </tbody>
                                </table>
                                <div class="btn-wrap">
                                    <button class="back-button">목록</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>

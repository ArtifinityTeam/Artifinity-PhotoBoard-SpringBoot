<%--
  Created by IntelliJ IDEA.
  User: 82109
  Date: 2024-05-17
  Time: 오전 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/section1.css">
    <link rel="stylesheet" href="css/section2.css">
    <link rel="stylesheet" href="css/section3.css">
    <link rel="stylesheet" href="css/section4.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/3.1.2/fullpage.min.css">
</head>
<body>
    <div id="fullpage">
        <div class="section section1">
            <div class="back"><a href="/"><img src="icons/HomePage_M_icon.png"></a></div>
            <div class="container1">
                <div class="text left-text">Green Creators Challenge Green Creators Challenge Green Creators Challenge</div>
                <div class="image">
                    <img src="images/main.jpg" alt="Green Creators Challenge Trophy">
                </div>
                <div class="text right-text">Green Creators Challenge Green Creators Challenge Green Creators Challenge</div>
                <div class="text-container">
                    <div class="title">
                        <div class="title1">‘ 지속가능한 혁신을 강조하는 공모전 ’</div>
                        <div class="title2">Green Creators Challenge</div>
                    </div>

                    <div class="button1">
                        <button id="seeMoreButton">자세히 보기</button>
                    </div>

                    <div class="footer1">@ Green Creators Challenge</div>
                </div>
            </div>
        </div>
        <div class="section section2">
<%--            <div class="back"><a href="/"><img src="icons/HomePage_M_icon.png"></a></div>--%>
            <div class="container2">
                <div class="left-side">
                    <!-- 왼쪽 섹션의 내용 -->
                    <img src="images/prizeGroup.png" alt="">
                </div>
                <div class="right-side">
                    <!-- 오른쪽 섹션의 내용 -->
                    <div class="title3">Green Creators Challenge</div>
                    <hr>
                    <p class="subtitle">‘ 지속가능한 혁신을 강조하는 공모전 ’</p>

                    <div class="content">
                        <div class="content-item">
                            <div class="content-title">공모주제</div>
                            <div class="vertical-line"></div>
                            <div class="content-desc">창의에 대한 당신의 시각</div>
                        </div>
                        <div class="content-item">
                            <div class="content-title">공모대상</div>
                            <div class="vertical-line"></div>
                            <div class="content-desc">사진의 진품을 원도로 가능한 모든 사람들</div>
                        </div>
                        <div class="content-item">
                            <div class="content-title">공모상품</div>
                            <div class="vertical-line"></div>
                            <div class="content-desc">
                                1등 - 30만원<br>
                                2등 - 15만원<br>
                                3등 - 5만원
                            </div>
                        </div>
                        <div class="content-item">
                            <div class="content-title">접수기간</div>
                            <div class="vertical-line"></div>
                            <div class="content-desc">2024년 5월 14일(화) ~ 2024년 9월 15일(일)</div>
                        </div>
                        <div class="content-item">
                            <div class="content-title">심사기준</div>
                            <div class="vertical-line"></div>
                            <div class="content-desc">창의성 / 실행가능성 / 환경적 영향</div>
                        </div>
                    </div>

                    <button><a href="/project_upload">업로드하러가기</a></button>
                    <br>
                    <button id="seeMoreButton2">자세한 설명보기</button>

                    <div class="footer2">
                        @ Green Creators Challenge
                    </div>

                </div>
            </div>

        </div>
        <div class="section section3">
<%--            <div class="back"><a href="/"><img src="icons/HomePage_W_icon.png"></a></div>--%>
            <div class="container3">
                <div class="hash image-container">
                    <img src="images/hash.png" alt="">
                </div>
                <div class="star image-container">
                    <img src="images/star.png" alt="">
                </div>
                <div class="detail">
                    <div class="box1">
                        <span class="detail-title">참여방법</span>
                        <div class="detail-text">자신의 환경에 대한 생각이 담긴 작품을 업로드 한 후<br>
                            페이지를 캡쳐해 해시태그와 함께 올려주세요</div>
                    </div>
                    <div class="box2">
                        <span class="detail-title">당첨자 발표</span>
                        <div class="detail-text">2024년 9월 15일 [일]<br>
                            공지사항에 발표</div>
                    </div>
                    <div class="box3">
                        <span class="detail-title">경품발송</span>
                        <div class="detail-text">2024년 9월 20일 [금]</div>
                    </div>
                </div>

                <div class="footer3">
                    @ Green Creators Challenge
                </div>
            </div>

        </div>
        <div class="section section4"  data-anchor="anchor">
<%--            <div class="back"><a href="/"><img src="icons/HomePage_W_icon.png"></a></div>--%>
            <div class="last">
                <div class="notice">NOTICE</div>
                <div class="tail">
                    공모전 규정<br>

                    참가 자격: 개인 또는 팀 (최대 5명)으로 참가 가능. 전 세계 누구나 참여 가능하나, 모든 팀원의 정보를 제출해야 함.<br>
                    제출 형식: 사진: JPEG 또는 PNG 형식, 최소 해상도 1920x1080 // 비디오: MP4 형식, 최대 길이 3분, 1080p 해상도<br>
                    제출 방법: 공모전 웹사이트를 통한 온라인 제출만 가능. 이메일이나 우편으로는 접수하지 않음.<br>
                    <br>
                    심사 기준<br>
                    창의성: 독창적이고 혁신적인 아이디어의 표현<br>
                    실행 가능성: 아이디어를 실제로 구현할 수 있는 가능성<br>
                    환경적 영향: 제안된 아이디어가 환경에 미치는 긍정적인 효과<br>
                    <br>
                    시상 내역<br>
                    1등: 30만원<br>
                    2등: 15만원<br>
                    3등: 5만원<br>
                    특별 인정상: 가장 사회적 영향력이 높은 작품에게 주어지는 상으로, 상품권 3만원<br>
                    <br>
                    유의사항<br>
                    모든 제출 작품은 참가자의 오리지널 작품이어야 하며, 제3자의 저작권을 침해하지 않아야 함.<br>
                    작품 제출 시 참가자는 공모전 주최측이 작품을 홍보 목적으로 사용할 권리를 부여하는 것에 동의하는 것으로 간주.<br>
                    모든 입상작은 공모전 종료 후 웹사이트 및 관련 이벤트에서 전시될 예정.<br>
                    <br>
                    추가 정보 및 문의<br>
                    FAQ 페이지 링크: 자주 묻는 질문과 답변<br>
                    문의처: 010-1234-5678<br>
                </div>

            </div>
            <div class="footer4">
                @ Green Creators Challenge
            </div>
        </div>

    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/3.1.2/fullpage.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var seeMoreButton = document.getElementById('seeMoreButton');
            var seeMoreButton2 = document.getElementById('seeMoreButton2');

            seeMoreButton.addEventListener('click', function(event) {
                event.preventDefault(); // <a> 태그의 기본 동작을 방지
                fullpage_api.moveTo('anchor'); // 'anchor'로 이동
                console.log("Moving to anchor section");
            });

            seeMoreButton2.addEventListener('click', function(event) {
                event.preventDefault(); // <a> 태그의 기본 동작을 방지
                fullpage_api.moveTo('anchor'); // 'anchor'로 이동
                console.log("Moving to anchor section");
            });
        });


        // fullPage.js 초기화 스크립트
        new fullpage('#fullpage', {
            autoScrolling: true,
            anchors: ['firstPage', 'secondPage', 'thirdPage', 'anchor'],
            menu: '#menu',
            scrollHorizontally: true,
            scrollOverflow: true  // 추가된 옵션
        });
    </script>
</body>
</html>

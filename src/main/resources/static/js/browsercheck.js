<script>

        /* [브라우저 ready 상태 정의 : 최초 호출 상태] */
        document.addEventListener("DOMContentLoaded", ready);
        function ready(){
            console.log("");            
            console.log("[window ready] : [start]");
            console.log("");
			console.log(document.referr);
			// [현재 접속된 url 정보 및 접속 브라우저 확인]
            // [카카오톡 인앱 브라우저 >> 안드로이드 모바일 기종 인 경우 >> 크롬 브라우저 이동 실시]
            var Url = location.href;
            var Agent = navigator.userAgent.toLowerCase();
            console.log("");            
            console.log("[window ready] : [접속 Url] : " + Url);
            console.log("[window ready] : [접속 Agent] : " + Agent);
            console.log("");
            //alert(Agent);
            if(Agent.includes("kakao")){ // 카카오 브라우저로 실행 시킨 경우
                // 먼저, 카카오 인앱 브라우저 닫기
                location.href = 'kakaotalk://inappbrowser/close';
                if(navigator.userAgent.match(/iPhone|iPad/i)){ // 아이폰 접속 경우
                    console.log("");
                    console.log("[window ready] : [접속 모바일] : " + "[아이폰]");
                    console.log("");
                }
                else { // 안드로이드 접속 경우
                    console.log("");
                    console.log("[window ready] : [접속 모바일] : " + "[안드로이드]");
                    console.log("");
                    // 크롬으로 새창 열기
                    location.href='intent://'+location.href.replace(/https?:\/\//i,'')+'#Intent;scheme=https;package=com.android.chrome;end';
                }
            }

        };


    </script>

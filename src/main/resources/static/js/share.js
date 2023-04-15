var csno;
var cskey;
function numberWithCommas(x) {
	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
};


$(document).on("click", "#btn_cancle", function() {
	console.log("클릭");
	customConfirmWithAction("장바구니 공유를 중지하시겠습니까?", "개인용 장바구니로 돌아갑니다.", "예", "아니요")
	  .then((result) => {
		if (result.isConfirmed) { // SweetAlert2에서 확인 버튼을 클릭한 경우
		  $.ajax({
			url: '/capi/cancle',
			type: 'post',
			contentType: "application/json",
			beforeSend: function(xhr) {
			  xhr.setRequestHeader(header, token);
			},
			success: function(data) {
			  console.log(data);
			  location.href = '/cart/mycart';
			},
			error: function(e) {
			  console.log(e);
			}
		  });
		}
	  });
  });
  


// SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
Kakao.init('26c618ece5bb5f3d4fd7d428c0f0e7d7');
// SDK 초기화 여부를 판단합니다.
console.log(Kakao.isInitialized());
function kakaoShare() {
	$.ajax({
		url: '/capi/getInfo',
		type: 'get',
		contentType: "application/json",
		success: function (result) {
			console.log(result);
			csno = result.result.csno;
			cskey = result.result.cskey;
			var url = 'https://hcart.shop/cart/group?csno=' + csno + '&cskey=' + cskey;
			console.log(csno, cskey);
			console.log(url);
			Kakao.Link.sendDefault({
				objectType: 'feed',
				content: {
					title: 'HCART와 함께하는 쇼핑',
					description: '공유 장바구니로 하나되어 하는 쇼핑',
					imageUrl: '/img/logo.png',
					link: {
						mobileWebUrl: url,
						webUrl: url,
					},
				},
				buttons: [
					{
						title: '공유 요청 확인하기',
						link: {
							mobileWebUrl: url,
							webUrl: url,
						},
					},
				],
				// 카카오톡 미설치 시 카카오톡 설치 경로이동
				installTalk: true,
			})
		}
	});

};



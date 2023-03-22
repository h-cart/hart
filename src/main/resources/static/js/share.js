  // SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
  Kakao.init('26c618ece5bb5f3d4fd7d428c0f0e7d7');
  
  // SDK 초기화 여부를 판단합니다.
  console.log(Kakao.isInitialized());

  function kakaoShare() {
	  var url = "";
	  $.ajax({
	url :'/capi/getInfo',
	type :'get',
	contentType : "application/json",
	success : function(result){
		console.log(result);
		var csno = result.result.csno;
		var cskey = result.result.cskey;
		url = 'https://localhost/cart/group?csno='+csno+'&cskey='+cskey
		console.log(csno,cskey);
		console.log(url);
		    Kakao.Link.sendDefault({
      objectType: 'feed',
      content: {
        title: 'HCART와 함께하는 쇼핑',
        description: '공유 장바구니로 하나되어 하는 쇼핑',
        imageUrl: 'https://img.freepik.com/premium-vector/sunflower-pot-illustration-cartoon-with-a-shopping-cart-cute-style-design-for-t-shirt-sticker-logo-element_152558-19556.jpg',
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
	}, error : function(err,xhr){
		console.log(err);
	}
});
	

  };
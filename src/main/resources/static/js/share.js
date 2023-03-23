var csno;
var cskey;

function cart_connect(csno){
		const sse = new EventSource("/capi/sse/"+csno);
	sse.addEventListener('connect', (e) => {
		const { data: receivedConnectData } = e;
		console.log('connect event data: ',receivedConnectData);  // "connected!"
	});
	
	
	sse.addEventListener('insert', (e) => {
		const { data: receivedConnectData } = e;
		CartList();  
	});
	
	/* 공유 장바구니 수량 변경 발생 시 이벤트를 받아, 화면 처리  */
	sse.addEventListener('update', e => {  
	    const { data: receivedUpdate} = e;
		let json = JSON.parse(receivedUpdate);
		let uid = json.pid;
		let amount = json.pamount;
		let pos = $("[data-value="+uid+"]").prop("id");
		console.log(uid, amount, pos);
		$("#quantity_"+pos).val(amount);
		calTotal(pos,amount);
	    
	});
	
	/* 공유 장바구니 상품 삭제 시 이벤트를 받아 화면 처리  */
	sse.addEventListener('remove', e => {  
	    const { data: receivedRemoves} = e;
	    console.log(receivedRemoves);
		let pids = JSON.parse(receivedRemoves);
		console.log(pids);
		for(var pid of pids){
			$("[data-value="+pid+"]").remove();
		}
		NoItem();
		getCheckboxValue();
	});
	
	/* 공유 장바구니 삭제 시 이벤트를 받아 화면 처리  */
	sse.addEventListener('delete', e => {  
	    const { data: receivedRemoves} = e;
			alert('장바구니 삭제');
			console.log('장바구니 삭제됨 ');
			location.href='/cart/mycart'
	});
	
	$(document).on("click","#btn_cancle",function(){
		$.ajax({
		        	url : '/capi/cancle',
		        	type : 'post',
		        	contentType : "application/json",
		        	beforeSend : function(xhr){
		        		xhr.setRequestHeader(header,token);
		        	},success : function(data){
		        		console.log(data);
		        		location.href= '/cart/mycart';
		        	}, error : function(e){
		        		console.log(e);
		        	}
		        	
		        })
	})
	
}

$.ajax({
	url :'/capi/getInfo',
	type :'get',
	contentType : "application/json",
	success : function(result){
		console.log(result);
		csno = result.result.csno;
		cskey = result.result.cskey;
		
		}
});
  
  // SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
  Kakao.init('26c618ece5bb5f3d4fd7d428c0f0e7d7');
  // SDK 초기화 여부를 판단합니다.
  console.log(Kakao.isInitialized());
  function kakaoShare() {
	
		var url = 'https://localhost/cart/group?csno='+csno+'&cskey='+cskey;
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
};
	
	
	
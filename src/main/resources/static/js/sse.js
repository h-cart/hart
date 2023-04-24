
const sse = new EventSource("/capi/sse");
	sse.addEventListener('connect', (e) => {
		const { data: receivedConnectData } = e;
		console.log('connect event data: ',receivedConnectData);  // "connected!"
	});
		
	sse.addEventListener('connect', (e) => {
		const { data: receivedConnectData } = e;
		console.log('connect event data: ',receivedConnectData);  // "connected!"
	});
	
	
	sse.addEventListener('insert', (e) => {
		const { data: receivedInsertData } = e;
		let json = JSON.parse(receivedInsertData);
		let pnameArr = json.pname.split(" ");
		let pname = pnameArr[1] +" "+pnameArr[2];
		let msg = json.mname+"님이 "+pname;
		msg = json.count==0? msg+" 상품을 담았습니다." :msg+"외 "+count+"개의 상품을 담았습니다.";
		$("#toast").addClass("show");
		showToast(msg, 1);
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
		let json = JSON.parse(receivedRemoves);
		let pnameArr = json.pname.split(" ");
		let pname = pnameArr[1] +" "+pnameArr[2];
		let msg = json.mname+"님이 "+pname;
		let count = json.count;
		msg = json.count==0? msg+" 상품을 삭제했습니다." :msg+"외 "+count+"개의 상품을 삭제했습니다.";
		let pids = json.pids;
		for(var pid of pids){
			$("[data-value="+pid+"]").remove();
		}
		$("#toast").addClass("show");
		showToast(msg, -1);
		NoItem();
		getCheckboxValue();
	});
	
	/* 공유 장바구니 삭제 시 이벤트를 받아 화면 처리  */
	sse.addEventListener('delete', e => {  
	    const { data: receivedRemoves} = e;
	    	customConfirmWithAction("공유 장바구니가 삭제됐습니다.","소유자가 장바구니를 삭제하였습니다.","장바구니로 이동","아니요")
	    	.then((result) => {
		if (result.isConfirmed) {
			location.href = '/cart/mycart'; // SweetAlert2에서 확인 버튼을 클릭한 경우
		}else {
			location.href="/";
		}
	  });
	});
	
	/* 공유 장바구니 참여 시 이벤트를 받아 화면 처리  */
	sse.addEventListener('join', e => {  
	    const { data: receivedJoins} = e;
		let msg = receivedJoins+" 님이 공유 장바구니에 참여했습니다.";
		console.log(msg);
		$("#toast").addClass("show");
		showToast(msg,1);
	});
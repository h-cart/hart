
function NoItem() {
	var emptyBasket = $('.pbody');
	getCheckboxValue();
	if (emptyBasket.children().length === 0) {
		var str = '';
		str += "<tr class='empty-basket'><td colspan='6'><img src='/img/icon.png' style='width:80px; height :80px; margin :0 auto 15px;'alt='icon' />"
		str += "</br>장바구니에 담긴 상품이 없습니다.</td></tr>"
		$(".pbody").html(str);
	}
};




$(document).on("click", ".thumb", function (e) {
	// 이벤트가 발생한 요소가 이미지이면 체크박스를 클릭한 것처럼 동작
	if ($(e.target).is("img")) {
		let thumbCheck = $(this).find(".checkbox");
		let isCheck = thumbCheck.prop("checked");
		thumbCheck.prop("checked",!isCheck);
	}
	// 이벤트가 발생한 요소가 체크박스이면 클릭 이벤트를 그대로 전파
	else if ($(e.target).is(".checkbox")) {
		e.stopPropagation();
	}
	getCheckboxValue();
});


$(document).on("click", ".cart__close", function () {
	var pid = $(this).closest('tr').data('value');
	deleteBtnEvent(pid);

})



$(document).on("click", ".btn_plus", function () {

	var pos = $(this).data('value');
	var quantity = $("#quantity_" + pos).val();
	console.log(pos);
	if (+quantity + 1 >= 6) {
		alert('6개 이상 상품 주문 불가능');
		return;
	}
	updateAmount($("#" + pos).data('value'), +quantity + 1, pos);


});

$(document).on("click", ".btn_minus", function () {

	var pos = $(this).data('value');
	var quantity = $("#quantity_" + pos).val();
	if (quantity == 1) {
		alert('1개 이하로 수량을 조절할 수 없습니다.');
		return;
	}
	updateAmount($("#" + pos).data('value'), +quantity - 1, pos);
})





var allCheck = $("#allCheck");
allCheck.on("click", function (e) {
	var list = $("input[type='checkbox']");
	var flag = $(this).prop("checked");
	list.prop("checked", flag);

	getCheckboxValue();
});


function getCheckboxValue() {
	var result = 0;
	var count = 0;
	var discount = 0;
	var totalLength = $(".checkbox").length;
	$("input[name=cartlist]:checked").each(function () {
		var pos = $(this).prop('id').split("_")[1];
		if ($("#discount_" + pos).data('value') > 0) {
			discount += ($("#discount_" + pos).data('value') - $("#price_" + pos).data('value')) * $("#quantity_" + pos).val();
		}
		count += 1;
		var price = $("#cprice_" + pos).data('value');
		result += +price;
	});
	$("#allCheck").prop("checked",(totalLength!=count || totalLength==0)?false:true);
	var delivery = result > 0 ? result >= 50000 ? 0 : 5000 : 0;
	var tprice = $(".tprice");
	var pdiscount = $(".discount");
	pdiscount.text(numberWithCommas(discount) + "원");
	pdiscount.data('value', discount);
	tprice.text(numberWithCommas(result) + "원");
	tprice.data('value', result);
};



function selectRemove(entryNumber) {

	var msgStr = "";
	msgStr = "선택하신 상품을 쇼핑백에서 삭제하시겠습니까?";
	var entryNumber = "";
	$("input:checkbox[name='cartlist']:checked").each(function () {
		entryNumber += $(this).closest("tr").data('value')+ ",";
	});
	entryNumber = entryNumber.substring(0, entryNumber.length - 1);
	console.log(entryNumber);
	deleteBtnEvent(entryNumber);

}
function deleteBtnEvent(param) {
	console.log(param);
	if (param.length == "") {
		alert("선택된 상품이 없습니다. <br /> 삭제할 상품을 선택해 주세요.");
		return false;
	}
	var pids = param.split(",");
	var cartDTOList = [];
	for (var i = 0; i < pids.length; i++) {
		cartDTOList.push(pids[i]);
	}
	console.log(param);
	console.log(cartDTOList);

	$.ajax({
		url: '/capi/removes',
		type: 'post',
		contentType: "application/json",
		data: JSON.stringify({
			pids: cartDTOList
		}),
		beforeSend: function (xhr) {
			xhr.setRequestHeader(header, token);
		}, success: function (data) {
			console.log(data);

			for (var pid of cartDTOList) {
				$("[data-value=" + pid + "]").remove();

			}
			NoItem();
		}, error: function (e) {
			console.log(e);
		}

	})
};

$(document).on("click", ".rbtn_plus", function () {

	var pos = $(this).data('value');
	var quantity = $("#quantity_" + pos).val();
	console.log(pos);
	if (+quantity + 1 >= 6) {
		alert('6개 이상 상품 주문 불가능');
		return;
	}
	$("#quantity_"+pos).val(+quantity+1);
	calPrice(pos);
});

$(document).on("click", ".rbtn_minus", function () {

	var pos = $(this).data('value');
	var quantity = $("#quantity_" + pos).val();
	console.log(pos);
	if (+quantity - 1 <=0) {
		alert('1개 이상 선택해주십시오.');
		return;
	}
	$("#quantity_"+pos).val(+quantity-1);
	calPrice(pos);
});

function calPrice(pos){
	var totalPrice = $("#cprice_"+pos);
	var price = $("#price_"+pos).data('value');
	var quantity = $("#quantity_"+pos).val();
	console.log(totalPrice,price,quantity);
	totalPrice.text(numberWithCommas(+price*+quantity)+"원");
	
}
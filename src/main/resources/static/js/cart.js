
function NoItem() {
	var emptyBasket = $('.pbody');
	if (emptyBasket.children().length === 0) {
		var str = '';
		str += "<tr class='empty-basket'><td colspan='6'><img src='/img/icon.png' style='width:80px; height :80px; margin :0 auto 15px;'alt='icon' />"
		str += "</br>장바구니에 담긴 상품이 없습니다.</td></tr>"
		$(".pbody").html(str);
	}
};

function numberWithCommas(x) {
	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
};



$(document).on("click", ".thumb", function (e) {
	// 이벤트가 발생한 요소가 이미지이면 체크박스를 클릭한 것처럼 동작
	if ($(e.target).is("img")) {
		$(this).find(".checkbox").click();
	}
	// 이벤트가 발생한 요소가 체크박스이면 클릭 이벤트를 그대로 전파
	else if ($(e.target).is(".checkbox")) {
		e.stopPropagation();
	}
	getCheckboxValue();
});


$(document).on("click", ".fa-close", function () {
	var pid = $(this).closest('tr').data('value');
	deleteBtnEvent(pid);

})

$(".primary-btn").on("click", function () {
	var form = $('<form></form>');
	form.attr('method', 'post');
	form.attr('action', '/order/list');
	var notSelect = false;
	$("input[name=cartlist]:checked").each(function () {
		notSelect = true;
		var pos = $(this).prop('id').split('_')[1];
		var pid = $('#' + pos).data('value');
		var quantity = $('#quantity_' + pos).val();

		form.append($('<input/>', { type: 'hidden', name: 'pids', value: pid }));
		form.append($('<input/>', { type: 'hidden', name: 'pamounts', value: quantity }));

	});
	if(!notSelect) {
		alert("한개 이상의 상품을 선택해주세용");
		return ;
	}
	form.append($('<input/>', { type: 'hidden', name: '_csrf', value: token }));
	form.appendTo('body');
	form.submit();
});


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







function getCheckboxValue() {
	var result = 0;
	var count = 0;
	var discount = 0;
	$("input[name=cartlist]:checked").each(function () {
		var pos = $(this).prop('id').split("_")[1];
		if ($("#discount_" + pos).data('value') > 0) {
			discount += ($("#discount_" + pos).data('value') - $("#price_" + pos).data('value')) * $("#quantity_" + pos).val();
		}
		count += 1;
		var price = $("#cprice_" + pos).data('value');
		result += +price;
	});

	$(".gl_check_all").prop("checked", false);
	var delivery = result > 0 ? result >= 50000 ? 0 : 5000 : 0;
	var tprice = $(".tprice");
	var pdiscount = $(".discount");
	pdiscount.text('₩' + numberWithCommas(discount));
	pdiscount.data('value', discount);
	tprice.text('₩' + numberWithCommas(result));
	tprice.data('value', result);
};



function selectRemove(entryNumber) {

	var msgStr = "";
	msgStr = "선택하신 상품을 쇼핑백에서 삭제하시겠습니까?";
	var entryNumber = "";
	$("input:checkbox[name='cartlist']:checked").each(function () {
		entryNumber += $(this).val() + ",";
	});
	entryNumber = entryNumber.substring(0, entryNumber.length - 1);
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

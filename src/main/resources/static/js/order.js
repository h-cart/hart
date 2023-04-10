$(".orderDirect").on("click", function () {
	var form = $('<form></form>');
	form.attr('method', 'post');
	form.attr('action', '/order/list');
	var notSelect = false;
	$("input[name=cartlist]:checked").each(function () {
		notSelect = true;
		var pos = $(this).prop('id').split('_')[1];
		var pid = $('#' + pos).data('value');
		var quantity = $('#quantity_' + pos).val();
		console.log(pos,pid,quantity);
		form.append($('<input/>', { type: 'hidden', name: 'pids', value: pid }));
		form.append($('<input/>', { type: 'hidden', name: 'pamounts', value: quantity }));

	});
	if (!notSelect) {
		alert("한개 이상의 상품을 선택해주세용");
		return;
	}
	form.append($('<input/>', { type: 'hidden', name: '_csrf', value: token }));
	form.appendTo('body');
	form.submit();
});



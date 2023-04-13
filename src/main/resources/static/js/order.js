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

		form.append($('<input/>', { type: 'hidden', name: 'pids', value: pid }));
		form.append($('<input/>', { type: 'hidden', name: 'pamounts', value: quantity }));

	});
	if (!notSelect) {
		var pid = $('#-1').data('value');
		var quantity = $('#quantity_-1').val();
		form.append($('<input/>', { type: 'hidden', name: 'pids', value: pid }));
		form.append($('<input/>', { type: 'hidden', name: 'pamounts', value: quantity }));
	}
	form.append($('<input/>', { type: 'hidden', name: '_csrf', value: token }));
	form.appendTo('body');
	form.submit();
});

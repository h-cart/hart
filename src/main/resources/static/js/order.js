$(".orderDirect").on("click", function () {
	var form = $('<form></form>');
	form.attr('method', 'post');
	form.attr('action', '/order/list');
	var notSelect = false;
	var pid = $('#-1').data('value');
	var quantity = $('#quantity_-1').val();
	form.append($('<input/>', { type: 'hidden', name: 'pids', value: pid }));
	form.append($('<input/>', { type: 'hidden', name: 'pamounts', value: quantity }));
	form.append($('<input/>', { type: 'hidden', name: '_csrf', value: token }));
	form.appendTo('body');
	form.submit();
});



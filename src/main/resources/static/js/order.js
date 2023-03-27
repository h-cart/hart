$(document).on("click","#orderDirect",function(e){
    e.preventDefault();
    var form = $('<form></form>');
	form.attr('method', 'post');
	form.attr('action', '/order/list');
	var qty = $("input[name=quantity]").val();
    var pid = $("input[name=pid]").val();
    form.append($('<input/>', { type: 'hidden', name: 'pids', value: pid }));
    form.append($('<input/>', { type: 'hidden', name: 'pamounts', value: qty }));
	form.append($('<input/>', { type: 'hidden', name: '_csrf', value: token }));
	form.appendTo('body');
	form.submit();
})
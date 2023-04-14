
function showToast(msg, tag) {
	$("#toast").attr("style", "visibility: visible;");
	$(".toast-content").html(msg);
	if (tag === 1) {
		$("#img_icon").attr("src", "/img/check.png");
	} else {
		$("#img_icon").attr("src", "/img/error.png");
	}
	setTimeout(() => $("#toast").attr("style", "visibility: hidden;"), 2000);
	
	}
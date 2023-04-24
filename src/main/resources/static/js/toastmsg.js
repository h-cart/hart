
function showToast(msg, tag) {
	$("#toast").attr("style", "visibility: visible;");
	$("#toast").attr("style", "border-radius: 25px;");
	$("#toast").attr("style", "background-color: transparent;");
	$("#toast").addClass("show");
	$(".toast-content").html(msg);
	if (tag === 1) {
		$("#img_icon").attr("src", "/img/check.png");
	} else {
		$("#img_icon").attr("src", "/img/error.png");
	}
	setTimeout(() => $("#toast").attr("style", "visibility: hidden;"), 5000);
	
	}
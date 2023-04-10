

$(document).ready(function() {
	$.ajax({
		type: "get",
		url: "/papi/clist",
		contentType: "application/json",
		success: function(data) {

			let str = "";
			for (var i = 0; i < data.result.length; i++) {
				console.log("number" + i + ": " + data.result[i].pcategory);
				str += `
		        	<li>
		        	  <a href="/product/list?pcno=${data.result[i].pcno}" tableindex="">${data.result[i].pcategory}</a>
		        	</li>
					`;
			}
			console.log(str);

			$("#listbig").append(str);
		},
		error: function() {
			console.log("실패");
		}
	});
});





// <![CDATA[  <-- For SVG support
if ('WebSocket' in window) {
	(function() {
		function refreshCSS() {
			var sheets = [].slice.call(document
				.getElementsByTagName("link"));
			var head = document.getElementsByTagName("head")[0];
			for (var i = 0; i < sheets.length; ++i) {
				var elem = sheets[i];
				var parent = elem.parentElement || head;
				parent.removeChild(elem);
				var rel = elem.rel;
				if (elem.href && typeof rel != "string"
					|| rel.length == 0
					|| rel.toLowerCase() == "stylesheet") {
					var url = elem.href.replace(
						/(&|\?)_cacheOverride=\d+/, '');
					elem.href = url
						+ (url.indexOf('?') >= 0 ? '&' : '?')
						+ '_cacheOverride='
						+ (new Date().valueOf());
				}
				parent.appendChild(elem);
			}
		}
		var protocol = window.location.protocol === 'http:' ? 'ws://'
			: 'wss://';
		var address = protocol + window.location.host
			+ window.location.pathname + '/ws';
		var socket = new WebSocket(address);
		socket.onmessage = function(msg) {
			if (msg.data == 'reload')
				window.location.reload();
			else if (msg.data == 'refreshcss')
				refreshCSS();
		};
		if (sessionStorage
			&& !sessionStorage
				.getItem('IsThisFirstTime_Log_From_LiveServer')) {
			console.log('Live reload enabled.');
			sessionStorage.setItem(
				'IsThisFirstTime_Log_From_LiveServer', true);
		}
	})();
} else {
	console
		.error('Upgrade your browser. This Browser is NOT supported WebSocket for Live-Reloading.');
}
// ]]>



// <![CDATA[  <-- For SVG support
if ('WebSocket' in window) {
	(function() {
		function refreshCSS() {
			var sheets = [].slice.call(document
				.getElementsByTagName("link"));
			var head = document.getElementsByTagName("head")[0];
			for (var i = 0; i < sheets.length; ++i) {
				var elem = sheets[i];
				var parent = elem.parentElement || head;
				parent.removeChild(elem);
				var rel = elem.rel;
				if (elem.href && typeof rel != "string"
					|| rel.length == 0
					|| rel.toLowerCase() == "stylesheet") {
					var url = elem.href.replace(
						/(&|\?)_cacheOverride=\d+/, '');
					elem.href = url
						+ (url.indexOf('?') >= 0 ? '&' : '?')
						+ '_cacheOverride='
						+ (new Date().valueOf());
				}
				parent.appendChild(elem);
			}
		}
		var protocol = window.location.protocol === 'http:' ? 'ws://'
			: 'wss://';
		var address = protocol + window.location.host
			+ window.location.pathname + '/ws';
		var socket = new WebSocket(address);
		socket.onmessage = function(msg) {
			if (msg.data == 'reload')
				window.location.reload();
			else if (msg.data == 'refreshcss')
				refreshCSS();
		};
		if (sessionStorage
			&& !sessionStorage
				.getItem('IsThisFirstTime_Log_From_LiveServer')) {
			console.log('Live reload enabled.');
			sessionStorage.setItem(
				'IsThisFirstTime_Log_From_LiveServer', true);
		}
	})();
} else {
	console
		.error('Upgrade your browser. This Browser is NOT supported WebSocket for Live-Reloading.');
}
// ]]>



// <![CDATA[  <-- For SVG support
if ('WebSocket' in window) {
	(function() {
		function refreshCSS() {
			var sheets = [].slice.call(document
				.getElementsByTagName("link"));
			var head = document.getElementsByTagName("head")[0];
			for (var i = 0; i < sheets.length; ++i) {
				var elem = sheets[i];
				var parent = elem.parentElement || head;
				parent.removeChild(elem);
				var rel = elem.rel;
				if (elem.href && typeof rel != "string"
					|| rel.length == 0
					|| rel.toLowerCase() == "stylesheet") {
					var url = elem.href.replace(
						/(&|\?)_cacheOverride=\d+/, '');
					elem.href = url
						+ (url.indexOf('?') >= 0 ? '&' : '?')
						+ '_cacheOverride='
						+ (new Date().valueOf());
				}
				parent.appendChild(elem);
			}
		}
		var protocol = window.location.protocol === 'http:' ? 'ws://'
			: 'wss://';
		var address = protocol + window.location.host
			+ window.location.pathname + '/ws';
		var socket = new WebSocket(address);
		socket.onmessage = function(msg) {
			if (msg.data == 'reload')
				window.location.reload();
			else if (msg.data == 'refreshcss')
				refreshCSS();
		};
		if (sessionStorage
			&& !sessionStorage
				.getItem('IsThisFirstTime_Log_From_LiveServer')) {
			console.log('Live reload enabled.');
			sessionStorage.setItem(
				'IsThisFirstTime_Log_From_LiveServer', true);
		}
	})();
} else {
	console
		.error('Upgrade your browser. This Browser is NOT supported WebSocket for Live-Reloading.');
}
// ]]>



// <![CDATA[  <-- For SVG support
if ('WebSocket' in window) {
	(function() {
		function refreshCSS() {
			var sheets = [].slice.call(document
				.getElementsByTagName("link"));
			var head = document.getElementsByTagName("head")[0];
			for (var i = 0; i < sheets.length; ++i) {
				var elem = sheets[i];
				var parent = elem.parentElement || head;
				parent.removeChild(elem);
				var rel = elem.rel;
				if (elem.href && typeof rel != "string"
					|| rel.length == 0
					|| rel.toLowerCase() == "stylesheet") {
					var url = elem.href.replace(
						/(&|\?)_cacheOverride=\d+/, '');
					elem.href = url
						+ (url.indexOf('?') >= 0 ? '&' : '?')
						+ '_cacheOverride='
						+ (new Date().valueOf());
				}
				parent.appendChild(elem);
			}
		}
		var protocol = window.location.protocol === 'http:' ? 'ws://'
			: 'wss://';
		var address = protocol + window.location.host
			+ window.location.pathname + '/ws';
		var socket = new WebSocket(address);
		socket.onmessage = function(msg) {
			if (msg.data == 'reload')
				window.location.reload();
			else if (msg.data == 'refreshcss')
				refreshCSS();
		};
		if (sessionStorage
			&& !sessionStorage
				.getItem('IsThisFirstTime_Log_From_LiveServer')) {
			console.log('Live reload enabled.');
			sessionStorage.setItem(
				'IsThisFirstTime_Log_From_LiveServer', true);
		}
	})();
} else {
	console
		.error('Upgrade your browser. This Browser is NOT supported WebSocket for Live-Reloading.');
}
// ]]>


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


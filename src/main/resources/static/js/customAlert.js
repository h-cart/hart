      function customConfirm(title, text, confirm, cancle) {
         Swal.fire({
            title: title,
            text: text,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#116530',
            cancelButtonText: cancle,
            cancelButtonColor: 'gray',
            confirmButtonText: confirm
         }).then((result) => {
            if (result.isConfirmed) {
               location.href = '/cart/mycart';
            }
         })
      }
      
            function qrConfirm(title, text, confirm, cancle) {
         Swal.fire({
            title: title,
            text: text,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#116530',
            cancelButtonText: '취소',
            cancelButtonColor: 'gray',
            confirmButtonText: '확인'
         }).then((result) => {
            if (result.isConfirmed) {
               location.href = '/cart/mycart';
            }else{
			scanQR();
}
         })
      }
      
      
      
          function customConfirmWithAction(title, text, confirm, cancle) {
         let result = Swal.fire({
            title: title,
            text: text,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#116530',
            cancelButtonText: cancle,
            cancelButtonColor: 'gray',
            confirmButtonText: confirm
         })
         return result;
      }
      
      
      
     function noConfirm(msg, title, resvNum) {
		swal({
			title : title,
			text : msg,
			type : "warning",
			showCancelButton : true,
			confirmButtonClass : "btn-danger",
			confirmButtonText : "예",
			cancelButtonText : "아니오",
			closeOnConfirm : false,
			closeOnCancel : true,
			showCancelButton :false
		}, function(isConfirm) {
			location.href="/cart/mycart"

		});
	}
      
      
      function customConfirm(title, text, confirm, cancle) {
         Swal.fire({
            title: title,
            text: text,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#116530',
            cancelButtonText: 취소,
            cancelButtonColor: 'gray',
            confirmButtonText: 확인
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
            cancelButtonText: 취소,
            cancelButtonColor: 'gray',
            confirmButtonText: 확인
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
            cancelButtonText: 취소,
            cancelButtonColor: 'gray',
            confirmButtonText: 확인
         })
         return result;
      }
      
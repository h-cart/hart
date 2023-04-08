      function customConfirm(title, text, confirm, cancle) {
         Swal.fire({
            title: title,
            text: text,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonText: cancle,
            cancelButtonColor: '#d33',
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
            confirmButtonColor: '#3085d6',
            cancelButtonText: cancle,
            cancelButtonColor: '#d33',
            confirmButtonText: confirm
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
            confirmButtonColor: '#3085d6',
            cancelButtonText: cancle,
            cancelButtonColor: '#d33',
            confirmButtonText: confirm
         })
         return result;
      }
      
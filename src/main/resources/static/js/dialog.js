function showModal(result,flag,title) {
  $("dialog").remove();
  var idx = 0;
  if(result.result!=null){
  var lives = result.result.lives;
  var recipes = result.result.recipes;
	}
   let dtitle = title==null?"장바구니에 담았습니다." :title;
   
  var str = `<style>
   dialog::-webkit-scrollbar {
      display: none;
   }
   .product__cart__item__pic {
		position: relative;
	}

	.checkbox {
		position: absolute;
		top: 0;
		left: 0;
		z-index: 1;

	}

	.price-box h5,
	.price-box h6 {
		
		display: inline-block;
		margin: 0;
	}

	.product__cart__item__text {
		overflow: hidden;
	}

	.product__cart__item__text .cost {
		color: #999;
		/* 회색으로 표시 */
		font-size: 0.8em;
		/* 작게 보이도록 설정 */
		text-decoration: line-through;
		/* 선 그어지도록 설정 */
	}

	.empty-basket {
		text-align: center;
		font-size: 20px;
		color: #666;
	}

	@media (max-width: 768px) {
		.empty-basket {
			font-size: 16px;
		}
	}

  #cart_product_img {
    width: 150px;
    height: 150px;
    display: block;
    margin: 30px auto;

  }
  .row{
	justify-content: center;
  }
  .col-5{
   color:
  }


  dialog {
   max-width: 526px;
  }
  
  .quantity {
	text-align : center;
}

</style>
	<dialog>
      <div class="container" style="width:500px;">
      <div>
      <h4 style="text-align: center" style="word-break: keep-all;">${dtitle}</h4>

       <div class="row row-col-lg-2" style="margin-top:20px; padding-bottom : 30px; border-bottom: 1px solid #e3e3e3">
        <div class="col-5">
          <button class="btn btn-primary btn-block dialog-close" type="button" style="word-break: keep-all; background-color: #116530; ">쇼핑 계속하기</button>
        </div>
        <div class="col-5">
          <button class="btn btn-primary btn-block" type="button" style="background-color:#ffff;color:darkslategray;border: 1px solid gray; word-break: keep-all;" onclick="location.href='/cart/mycart'">장바구니 가기</button>
        </div>
      </div>
    </div>
         <div class="shopping__cart__table">`;

  if (!flag) {
    if (lives.length != 0) {
      str += ` <div class="shop__sidebar__accordion" style="margin-top: 50px">
               <div class="accordion" id="accordionClass">
                  <div class="card">
                     <div class="card-heading active">
                        <a data-toggle="collapse" data-target="#collapseClass" class="" aria-expanded="true">라이브
                           클래스 추천</a>
                     </div>
                     <div id="collapseClass" class="collapse show" data-parent="#accordionClass" style="">
                        <table>
                           `;

      
      $.each(lives, function (index, value) {
        idx++;
        var status = value.lcstatus == 0 ? value.lcday + "방송예정" : value.lcstatus == 1 ? "LIVE 방송 중" : "VOD로 수강하기";
        str += `<tbody id="accordion${idx}"  class="accordion" data-parent="#accordionClass" style="">
        <tr id="${idx}" data-value="${value.lcid}">
        <td class="product__cart__item" colspan="2">
        <div class="product__cart__item__pic" style="
        margin-top: 20px;
    "><span class="thumb"><input name="cartlist" id="check_${idx}" type="checkbox"
                 class="checkbox"><img class="pimg"
                 src="${value.lcimg}" alt=""
                 style="width:90px;border-radius: 10px;height:90px;"></span></div>
        <div class="product__cart__item__text">
           <h6>${value.lcname}</h6><span class="price-box">
              <h5 class="price" id="price_${idx}" data-value="${value.lcprice}">${status}</h5>
           </span>
        </div>
     </td>
     <input type="hidden" id="quantity_${idx}" value="1"/>     
     <td data-value="${value.lcprice}" class="cart__price" id="cprice_${idx}">${numberWithCommas(value.lcprice)}원</td>
        <td data-toggle="collapse" data-target="#collapse${idx}" class="" aria-expanded="true"><span style="
        font-size: 12px;
        border: 1px solid gray;
        padding: 5px 9px;
        cursor: pointer;
        border-radius: 5px;
    ">관련상품</span></td>
        
     </tr></tbody><tbody id="collapse${idx}" class="collapse" data-parent="#accordion${idx}" style="">`;
        $.each(value.items, function (lindex, item) {
          console.log(idx);
          idx++;
          str += `<tr><td colspan="4">관련 상품목록</td></tr><tr id="${idx}" data-value="${item.pid}">
            <td class="product__cart__item" colspan="2">
               <div class="product__cart__item__pic"><span class="thumb"><input name="cartlist" id="check_${idx}" type="checkbox"
                        class="checkbox"><img class="pimg"
                        src="${item.pimg}" alt=""
                        style="width:90px;border-radius: 10px;height:90px;"></span></div>
               <div class="product__cart__item__text">
                  <h6>${item.pname}</h6><span class="price-box">
                     <h5 class="price" id="price_${idx}" data-value="${item.pprice}">${numberWithCommas(item.pprice)}원</h5>
                  </span>
               </div>
            </td>
            <td class="quantity__item">
               <div class="quantity">
                  <div class="pro-qty-2"><span data-value="${idx}" class="dec qtybtn rbtn_minus"
                        aria-hidden="true"><i class='fa-solid fa-minus fa-lg'></i></span><input id="quantity_${idx}" type="text" value="1" readonly="readonly"><span
                        data-value="${idx}" class="inc qtybtn rbtn_plus" aria-hidden="true"><i class='fa-solid fa-plus fa-lg'></i></span></div>
               </div>
            </td>
            <td data-value="${item.pprice}" class="cart__price" id="cprice_${idx}">${numberWithCommas(item.pprice)}원</td>
         </tr>`;
        });
        str += `</tbody>`;
      });
      str += `</tbody></table>
                     </div>
                  </div>
               </div>
            </div>`;
    }
    if (recipes.length != 0) {
      console.log(recipes);
      str += `            <div class="shop__sidebar__accordion" style="margin-top: 50px">
               <div class="accordion" id="accordionRecipe">
                  <div class="card">
                     <div class="card-heading active">
                        <a data-toggle="collapse" data-target="#collapseRecipe" class="" aria-expanded="true">인기있는
                           레시피</a>
                     </div>
                     <div id="collapseRecipe" class="collapse show" data-parent="#accordionRecipe" style="">
                        <div class="card-body">
                           <div class="shop__sidebar__tags">
                              `;
                              $.each(result.result.rcates,function(index,value){
                                 str+=`<a href="#" class="rcategory_${value.rcano}">${value.rcname}</a>`;
                              })
                              str+=`
                              
                           </div>
                        </div>
                        <table>
                           `;
      $.each(recipes, function (index, value) {
        idx++;
        str += `<tr id="accordion${idx}"  class="accordion rcategory_${value.rcano}" data-parent="#accordionRecipe" style="">
         <td class="product__cart__item">
            <div class="product__cart__item__pic"><span ><img class="pimg"
                     src="${value.rimg}" alt=""
                     style="width:90px;border-radius: 10px;height:90px;"></span></div>
            <div class="product__cart__item__text">
               <h6>${value.rtitle}</h6><span class="price-box">
                 <span style="
                 font-size: 13px;
             ">난이도 : ${value.rlevel}</span>
               </span>
            </div>
         </td>
         <td class="quantity__item">
            <div class="quantity">
               <div class="pro-qty-2"style="style="
               text-align: center;
           "><span style="font-size: 14px;">조리시간</br>${value.rtime}</span></div>
            </div>
         </td>
         <td data-toggle="collapse" data-target="#collapse${idx}" class="" aria-expanded="true"><span style="
         font-size: 12px;
         border: 1px solid gray;
         padding: 5px 9px;
         cursor: pointer;
         border-radius: 5px;
     ">관련상품</span></td>
         
      </tr><tbody id="collapse${idx}" class="collapse" data-parent="#accordion${idx}" style="">`;
        $.each(value.items, function (rindex, item) {
          idx++;
          str += `<tr id="${idx}" data-value="${item.pid}">
            <td class="product__cart__item">
               <div class="product__cart__item__pic"><span class="thumb"><input name="cartlist" id="check_${idx}" type="checkbox"
                        class="checkbox"><img class="pimg"
                        src="${item.pimg}" alt=""
                        style="width:90px;border-radius: 10px;height:90px;"></span></div>
               <div class="product__cart__item__text">
                  <h6>${item.pname}</h6><span class="price-box">
                     <h5 class="price" id="price_${idx}" data-value="${item.pprice}">${numberWithCommas(item.pprice)}원</h5>
                  </span>
               </div>
            </td>
            <td class="quantity__item">
               <div class="quantity">
                  <div class="pro-qty-2"><span data-value="${idx}" class="dec qtybtn rbtn_minus"
                        aria-hidden="true"><i class='fa-solid fa-minus fa-lg'></i></span><input id="quantity_${idx}" type="text" value="1" readonly="readonly"><span
                        data-value="${idx}" class="inc qtybtn rbtn_plus" aria-hidden="true"><i class='fa-solid fa-plus fa-lg'></i></span></div>
               </div>
            </td>
            <td data-value="${item.pprice}" class="cart__price" id="cprice_${idx}">${numberWithCommas(item.pprice)}원</td>
         </tr>`;
        });
        str += `</tbody>`;
      });
      str += `</table>
                     </div>
                  </div>
               </div>
            </div>`;
    }
    if((lives.length!=0 ||recipes.length!=0)){
    str += `<div onclick="addToCart(true)" class="addToCart" style="
   background-color: #116530;
   text-align: center; 
"><a href="#" class="primary-btn">장바구니 담기</a></div>`;
}
  }
  str += `</div>
      </div>
   </dialog>`;
  $("body").append(str);
  const dialog = document.querySelector("dialog");

  dialog.showModal();
  if(result.result==null){
	$("#cart_product_img").attr("src", "/img/logo.png");
}else {
  $("#cart_product_img").attr("src", result.result.pimg);
  $("#cart_item").text(result.result.pname);
  }
}
/*$(document).on("click","dialog",function(event){
	 if ($(event.target).closest('#dialog').length !== 0) {
    return;
  }

  // 다이얼로그를 숨기거나 제거하는 로직을 여기에 작성
  // 예시: 다이얼로그를 숨기기
  $(this).remove();
})
*/

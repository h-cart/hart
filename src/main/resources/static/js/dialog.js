
function showModal(result) {
   $("dialog").remove();
   var idx = 0;
   var lives = result.result.lives;
   var recipes = result.result.recipes;
   var str = `
	<dialog>
      <div class="container">
         <h2 style="text-align=center;">장바구니에 담겼습니다</h2>
         <div class="shopping__cart__table">`;
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
                           <tbody>`;
      $.each(lives, function (index, value) {
         var status = value.lcstatus == 0 ? value.lcday + "방송예정" : value.lcstatus == 1 ? "LIVE 방송 중" : "VOD로 수강하기";
         str += `
         <tr id="${idx}" data-value="${value.lcid}">
            <td class="product__cart__item">
               <div class="product__cart__item__pic"><span class="thumb"><input name="cartlist" id="check_${idx}" type="checkbox"
                        class="checkbox"><img class="pimg"
                        src="${value.lcimg}"
                        alt=""></span></div>
               <div class="product__cart__item__text">
                  <h6>${value.lcname}</h6><span class="price-box">
                     <h5 class="price" id="price_${idx}" data-value="${idx}">${value.lcteacher}</h5>
                  </span>
               </div>
            </td>
            <td class="quantity__item">
               <div class="quantity">
                  <div class="pro-qty-2">${status}<input id="quantity_${idx}" type="hidden" value="1"
                        readonly="readonly"></div>
               </div>
            </td>
            <td data-value="${value.lcprice}" class="cart__price" id="cprice_4">${numberWithCommas(value.lcprice)}원</td>
            
         </tr>`;
         $.each(value.items, function(lindex,item){
			console.log(idx);
            idx++;
            str +=`<tr id="${idx}" data-value="${item.pid}">
            <td class="product__cart__item">
               <div class="product__cart__item__pic"><span class="thumb"><input name="cartlist" id="check_${idx}" type="checkbox"
                        class="checkbox"><img class="pimg"
                        src="${item.pimg}" alt=""
                        style="width:90px; height:90px;"></span></div>
               <div class="product__cart__item__text">
                  <h6>${item.pname}</h6><span class="price-box">
                     <h5 class="price" id="price_${idx}" data-value="${item.pprice}">${numberWithCommas(item.pprice)}원</h5>
                  </span>
               </div>
            </td>
            <td class="quantity__item">
               <div class="quantity">
                  <div class="pro-qty-2"><span data-value="${idx}" class="fa fa-angle-left dec qtybtn rbtn_minus"
                        aria-hidden="true"></span><input id="quantity_${idx}" type="text" value="1" readonly="readonly"><span
                        data-value="${idx}" class="fa fa-angle-right inc qtybtn rbtn_plus" aria-hidden="true"></span></div>
               </div>
            </td>
            <td data-value="${item.pprice}" class="cart__price" id="cprice_${idx}">${numberWithCommas(item.pprice)}원</td>
         </tr>`
         })
         idx++;
      })
      str += `</tbody></table>
                     </div>
                  </div>
               </div>
            </div>`;
   }
   if (recipes.length != 0) {
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
                              <a href="#">국</a> <a href="#">찌개</a> <a href="#">볶음</a> <a href="#">안주</a>
                           </div>
                        </div>
                        
                           `;
      $.each(recipes, function (index, value) {
         
         str +=`<div id="collapse${idx}" class="collapse" data-parent="#accordionRecipe" style=""><table><tbody><tr>
         <td class="product__cart__item">
            <div class="product__cart__item__pic"><span ><img class="pimg"
                     src="${value.rimg}" alt=""
                     style="width:90px; height:90px;"></span></div>
            <div class="product__cart__item__text">
               <h6>${value.rtitle}</h6><span class="price-box">
                 <span>난이도 : ${value.rlevel}</span>
               </span>
            </div>
         </td>
         <td class="quantity__item">
            <div class="quantity">
               <div class="pro-qty-2"><span>조리시간 : ${value.rtime}</span></div>
            </div>
         </td>
         <td ><a data-toggle="collapse" data-target="#collapse${idx}" class="" aria-expanded="false">재료보기</a></span></td>
         </td>
      </tr>`
      ;
      $.each(recipes[index].items,function(rindex,item){
         idx++;
         str +=`<tr id="${idx}" data-value="${item.pid}">
            <td class="product__cart__item">
               <div class="product__cart__item__pic"><span class="thumb"><input name="cartlist" id="check_${idx}" type="checkbox"
                        class="checkbox"><img class="pimg"
                        src="${item.pimg}" alt=""
                        style="width:90px; height:90px;"></span></div>
               <div class="product__cart__item__text">
                  <h6>${item.pname}</h6><span class="price-box">
                     <h5 class="price" id="price_${idx}" data-value="${item.pprice}">${numberWithCommas(item.pprice)}원</h5>
                  </span>
               </div>
            </td>
            <td class="quantity__item">
               <div class="quantity">
                  <div class="pro-qty-2"><span data-value="${idx}" class="fa fa-angle-left dec qtybtn rbtn_minus"
                        aria-hidden="true"></span><input id="quantity_${idx}" type="text" value="1" readonly="readonly"><span
                        data-value="${idx}" class="fa fa-angle-right inc qtybtn rbtn_plus" aria-hidden="true"></span></div>
               </div>
            </td>
            <td data-value="${item.pprice}" class="cart__price" id="cprice_${idx}">${numberWithCommas(item.pprice)}원</td>
         </tr>`;
         
      });
      str+=`</tbody></table></div>`;
      });
      str += `
                     </div>
                  </div>
               </div>
            </div>`;
   }
   str += `
         </div>
      </div>
   </dialog>`;
   $("body").append(str);
   const dialog = document.querySelector("dialog");

   console.log("여기에요 여기", result);
   dialog.showModal();
}



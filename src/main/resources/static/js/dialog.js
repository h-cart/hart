
function showModal(result) {
   $("dialog").remove();
   var str = `
	<dialog>
      <div class="container">
         <h2 style="text-align=center;">장바구니에 담겼습니다</h2>
         <div class="shopping__cart__table">`;
   if (result.result.lives.length != 0) {
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
      $.each(result.result.lives, function () {
         str += `               <tr>
                                 <td class="product__cart__item">
                                    <div class="product__cart__item__pic">
                                       <img src="/img/shopping-cart/cart-2.jpg" alt="" />
                                    </div>
                                    <div class="product__cart__item__text">
                                       <h6>라이브 클래스 명</h6>
                                       <h5>더보기</h5>
                                    </div>
                                 </td>
                                 <td class="quantity__item">
                                    <div class="quantity">
                                       <div class="pro-qty-2">
                                          <input type="text" value="1" />
                                       </div>
                                    </div>
                                 </td>
                                 <td class="cart__price">$ 32.50</td>
                                 <td class="cart__close"><i class="fa fa-close"></i></td>
                              </tr>
                           </tbody>
                           `;
      })
      str += `</table>
                     </div>
                  </div>
               </div>
            </div>`;
   }
   if (result.result.recipes.length != 0) {
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
                        <table>
                           <tbody>
                              <tr>
                                 <td class="product__cart__item">
                                    <div class="product__cart__item__pic">
                                       <img src="/img/shopping-cart/cart-1.jpg" alt="" />
                                    </div>
                                    <div class="product__cart__item__text">
                                       <h6>레시피 설명</h6>
                                       <h6>레시피 설명</h6>
                                       <h6>레시피 설명</h6>
                                    </div>
                                 </td>
                                 <td class="quantity__item"></td>
                                 <td class="cart__price">더보기</td>
                              </tr>
                              <tr>
                                 <td class="product__cart__item">
                                    <div class="product__cart__item__pic">
                                       <img src="/img/shopping-cart/cart-2.jpg" alt="" />
                                    </div>
                                    <div class="product__cart__item__text">
                                       <h6>Diagonal Textured Cap</h6>
                                       <h5>$98.49</h5>
                                    </div>
                                 </td>
                                 <td class="quantity__item">
                                    <div class="quantity">
                                       <div class="pro-qty-2">
                                          <input type="text" value="1" />
                                       </div>
                                    </div>
                                 </td>
                                 <td class="cart__price">$ 32.50</td>
                                 <td class="cart__close"><i class="fa fa-close"></i></td>
                              </tr>
                              <tr>
                                 <td class="product__cart__item">
                                    <div class="product__cart__item__pic">
                                       <img src="/img/shopping-cart/cart-3.jpg" alt="" />
                                    </div>
                                    <div class="product__cart__item__text">
                                       <h6>Basic Flowing Scarf</h6>
                                       <h5>$98.49</h5>
                                    </div>
                                 </td>
                                 <td class="quantity__item">
                                    <div class="quantity">
                                       <div class="pro-qty-2">
                                          <input type="text" value="1" />
                                       </div>
                                    </div>
                                 </td>
                                 <td class="cart__price">$ 47.00</td>
                                 <td class="cart__close"><i class="fa fa-close"></i></td>
                              </tr>
                              <tr>
                                 <td class="product__cart__item">
                                    <div class="product__cart__item__pic">
                                       <img src="/img/shopping-cart/cart-4.jpg" alt="" />
                                    </div>
                                    <div class="product__cart__item__text">
                                       <h6>Basic Flowing Scarf</h6>
                                       <h5>$98.49</h5>
                                    </div>
                                 </td>
                                 <td class="quantity__item">
                                    <div class="quantity">
                                       <div class="pro-qty-2">
                                          <input type="text" value="1" />
                                       </div>
                                    </div>
                                 </td>
                                 <td class="cart__price">$ 30.00</td>
                                 <td class="cart__close"><i class="fa fa-close"></i></td>
                              </tr>
                           </tbody>
                        </table>
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

// link 요소를 생성하고 속성을 설정합니다.
var linkElement = document.createElement('link');
linkElement.href = '/css/dialog.css'; // CSS 파일 경로를 설정합니다.
linkElement.rel = 'stylesheet';
linkElement.type = 'text/css';

// link 요소를 head 요소에 추가합니다.
document.head.appendChild(linkElement);

function showModal(result, flag, title) {
	$("dialog").remove();
	var idx = 0;


	let dtitle = title == null ? "장바구니에 담았습니다." : title;

	var str = `
	<dialog class='recommand-layer'>
      <div class="container" style="padding : 0px 0px; min-width:400px" >
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

	if (!flag && result.result != null) {
		var lives = result.result.lives;
		var recipes = result.result.recipes;
		if (lives.length != 0) {
			str += ` <div class="shop__sidebar__accordion" style="margin-top: 50px">
               <div class="accordion" id="accordionClass">
                  <div class="card">
                     <div class="card-heading active">
                        <a data-toggle="collapse" data-target="#collapseClass" class="" aria-expanded="true">
                           간단한 쿠킹 클래스 어떠세요?</a>
                     </div>
                     <div id="collapseClass" class="collapse show" data-parent="#accordionClass" style="">
                        <table>
                           `;


			$.each(lives, function(index, value) {
				idx++;
				var status = value.lcstatus == 0 ? (value.lcday.substr(6)+"/"+value.lcday.substr(0,5)) + "&nbsp방송예정" : value.lcstatus == 1 ? "LIVE 방송 중" : "VOD로 수강하기";
				console.log(status);
				str += `
        <tr id="accordion${idx}"  class="accordion" data-parent="#accordionClass" style="">
       
        <td  class="product__cart__item">
        <div class="product__cart__item__pic" style="    width: fit-content;
    float: left;
    margin-right: 27px;
"><span class="thumb"><input name="cartlist" id="check_${idx}" type="checkbox"
                 class="checkbox"><img class="pimg"
                 src="${value.lcimg}" alt=""
                 style="width:90px;border-radius: 10px;height:90px;"></span></div>
        <div class="product__cart__item__text" style="padding-bottom:10px; max-width:260px;">
           <h6 data-pos=${value.lcid}>${value.lcname}</h6>
              <span style="font-size:13px;"class="price" id="price_${idx}" data-value="${value.lcprice}">${status}</span>
              </br>
              <div style="display:flex;margin-top: 5px;justify-content: space-between;">
              <span style="font-size:13px;" data-value="${value.lcprice}" class="cart__price" id="cprice_${idx}">${numberWithCommas(value.lcprice)}원</span>
           
                   <span data-toggle="collapse" data-target="#collapse${idx}" class="" aria-expanded="true" style="
        font-size: 12px;
        border: 1px solid gray;
        padding: 5px 9px;
        cursor: pointer;
        border-radius: 5px;
        
    ">관련상품</span>
    </div>
        </div>
     </td>

    <input type="hidden" id="${idx}" data-value=${value.lcid}>
    <input type="hidden" id="quantity_${idx}" value="1"/>    
     </tr><tbody id="collapse${idx}" class="collapse" data-parent="#accordion${idx}" style="">`;
				$.each(value.items, function(lindex, item) {
					console.log(idx);
					idx++;
					str += `<tr id="${idx}" data-value="${item.pid}">
            <td  class="product__cart__item">
               <div class="product__cart__item__pic" style="    width: fit-content;
    float: left;
    margin-right: 27px;
"><span class="thumb"><input name="cartlist" id="check_${idx}" type="checkbox"
                        class="checkbox"><img class="pimg"
                        src="${item.pimg}" alt=""
                        style="width:90px;border-radius: 10px;height:90px;"></span></div>
               <div class="product__cart__item__text" style="max-width:260px;">
                  <h6 data-pos=${item.pid}>${item.pname}</h6>
                     <input type="hidden" class="price" id="price_${idx}" data-value="${item.pprice}"/>
                  <div class="pro-qty-2" style="display: flex;justify-content: space-between;margin-top: 30px;"><div><span style="
    padding-right:10px;"data-value="${idx}" class="dec qtybtn rbtn_minus"
                        aria-hidden="true"><i class='fa-solid fa-minus fa-xs'></i></span><input style="border:none; text-align:center; width:20px;" id="quantity_${idx}" type="text" value="1" readonly="readonly"><span
                        data-value="${idx}" style="
    margin-left: 10px;
"class="inc qtybtn rbtn_plus" aria-hidden="true"><i class='fa-solid fa-plus fa-xs'></i></span></div>
<span style="
        font-size: 14px;
    " data-value="${item.pprice}" class="cart__price" id="cprice_${idx}">${numberWithCommas(item.pprice)}원</span>
</div>
               </div>
            </td>
            
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
                        <a data-toggle="collapse" data-target="#collapseRecipe" class="" aria-expanded="true">메뉴 고민될 때 이런 레시피는 어떨까요?</a>
                     </div>
                     <div id="collapseRecipe" class="collapse show" data-parent="#accordionRecipe" style="">
                        <div class="card-body">
                           <div class="shop__sidebar__tags">
                              `;
			$.each(result.result.rcates, function(index, value) {
				if (index == 0)
					str += `<a class="rcategory_${value.rcano} on">${value.rcname}</a>`;
				else
					str += `<a class="rcategory_${value.rcano}">${value.rcname}</a>`;
			})
			str += `
                              
                           </div>
                        </div>
                        <table>
                           `;
			$.each(recipes, function(index, value) {
				idx++;
				str += `<tr id="accordion${idx}"  class="accordion"data-parent="#accordionRecipe" data-rcano="${value.rcano}"style="`
				if (index != 0)
					str += `display:none;`
				str += `">
         <td class="product__cart__item" >
            <div class="product__cart__item__pic"style="    width: fit-content;
    float: left;
    margin-right: 27px;
"
        margin-top: 20px;
    "><span ><img class="pimg"
                     src="${value.rimg}" alt=""
                     style="width:90px;border-radius: 10px;height:90px;"></span></div>
            <div class="product__cart__item__text" style="padding-bottom:10px;">
               <h6>${value.rtitle}</h6><span class="price-box">
                 <span style="
                 font-size: 13px;
             ">난이도&nbsp : &nbsp${value.rlevel}</span>
               </span>
               </br>
               <span style="font-size: 13px;">조리시간&nbsp : &nbsp${value.rtime}</span>
               <span data-toggle="collapse" data-target="#collapse${idx}" class="" aria-expanded="true" style="
        font-size: 12px;
        border: 1px solid gray;
        padding: 5px 9px;
        cursor: pointer;
        border-radius: 5px;
        margin-left: 85px;
    ">관련상품</span>
            </div>
         </td>
         
         
         
      </tr><tbody id="collapse${idx}" class="collapse" data-parent="#accordion${idx}" style="">`;
				$.each(value.items, function(rindex, item) {
					idx++;
					str += `<tr id="${idx}" data-value="${item.pid}">
            <td class="product__cart__item">
               <div class="product__cart__item__pic" style="
    width: fit-content;
    float: left;
    margin-right: 27px;
"><span class="thumb"><input name="cartlist" id="check_${idx}" type="checkbox"
                        class="checkbox"><img class="pimg"
                        src="${item.pimg}" alt=""
                        style="width:90px;border-radius: 10px;height:90px;"></span></div>
               <div class="product__cart__item__text" style="max-width:260px;">
                  <h6 data-pos=${item.pid}  style="margin-bottom: 30px;">${item.pname}</h6>
                     <input type="hidden" class="price" id="price_${idx}" data-value="${item.pprice}"/>
               		<div class="pro-qty-2" style="display: flex;justify-content: space-between;"><div><span style="
    padding-right:10px;"data-value="${idx}" class="dec qtybtn rbtn_minus"
                        aria-hidden="true"><i class='fa-solid fa-minus fa-xs'></i></span><input style="border:none; text-align:center; width:20px;" id="quantity_${idx}" type="text" value="1" readonly="readonly"><span
                        data-value="${idx}" style="
    margin-left: 10px;
"class="inc qtybtn rbtn_plus" aria-hidden="true"><i class='fa-solid fa-plus fa-xs'></i></span></div>
<span data-value="${item.pprice}" style="" class="cart__price" id="cprice_${idx}">${numberWithCommas(item.pprice)}원</span>
</div>
               </div>
            </td>
            
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
		if ((lives.length != 0 || recipes.length != 0)) {
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
	console.log(result.result);
	if(result.result.lives.length==0&&result.result.recipes.length==0||flag){
		let dialogCart = dialog.querySelector(".shopping__cart__table");
      let ment  = !flag? "추천 레시피 / 클래스가 존재하지 않습니다.":"추천 상품을 담아 재추천하지 않습니다.";
      dialogCart.style.height='50vh';
      dialogCart.style.textAlign='center';
      dialogCart.style.display='flex';
      dialogCart.style.justifyContent='center';
      dialogCart.style.flexDirection="column";
      dialogCart.style.alignItems="center";
      let notExistComment = `<img src='/img/icon.png' style='width:80px; height :80px; margin-bottom:20px;'alt='icon' /><span>${ment}</span>`;
      dialogCart.innerHTML = notExistComment;
	}
	dialog.showModal();
	if (result.result == null) {
		$("#cart_product_img").attr("src", "/img/logo.png");
	} else {
		$("#cart_product_img").attr("src", result.result.pimg);
		$("#cart_item").text(result.result.pname);
	}
}
$("html").click(function(e) {
	if ($(e.target).hasClass('recommand-layer')) {
		$("dialog").remove();
	}
});

$(document).on("click", ".dialog-close", function() {
	$("dialog").remove();
})

$(document).on("click", ".shop__sidebar__tags a", function() {
	$(".shop__sidebar__tags a").removeClass("on");
	let rcano = $(this).prop("class").split("_")[1];
	console.log(rcano);
	$("#collapseRecipe tr").each(function() {
		if ($(this).hasClass("accordion")) $(this).css("display", "none");
	})
	$("#collapseRecipe tbody").each(function() {
		if ($(this).hasClass("show")) $(this).removeClass("show");
	})

	$("tr[data-rcano=" + rcano + "]").css("display", "");

	$(this).addClass("on");


})





function showModalLiveClass(result, flag, title) {
	console.log(result, flag);
	$("dialog").remove();
	var idx = 0;


	let dtitle = title == null ? "장바구니에 담았습니다." : title;

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
	<dialog class='recommand-layer'>
      <div class="container" style="padding: 0 0;" >
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

	str += ` <div class="shop__sidebar__accordion" style="margin-top: 50px">
               <div class="accordion" id="accordionClass">
                  <div class="card">
                     <div class="card-heading active">
                        <a data-toggle="collapse" data-target="#collapseClass" class="" aria-expanded="true">
                           쿠킹 클래스 관련 제품 어떠세요?</a>
                     </div>
                     <div id="collapseClass" class="collapse show" data-parent="#accordionClass" style="">
                        <table>
                           <tbody>`;

	$.each(result.result, function(lindex, item) {
		console.log(idx);
		idx++;
		str += `<tr id="${idx}" data-value="${item.pid}">
            <td class="product__cart__item">
               <div class="product__cart__item__pic" style="
    width: fit-content;
    float: left;
    margin-right: 27px;
"><span class="thumb"><input name="cartlist" id="check_${idx}" type="checkbox"
                        class="checkbox"><img class="pimg"
                        src="${item.pimg}" alt=""
                        style="width:90px;border-radius: 10px;height:90px;"></span></div>
               <div class="product__cart__item__text" style="max-width:260px;">
                  <h6 data-pos=${item.pid}  style="margin-bottom: 30px;">${item.pname}</h6>
                     <input type="hidden" class="price" id="price_${idx}" data-value="${item.pprice}"/>
               		<div class="pro-qty-2" style="display: flex;justify-content: space-between;"><div ><span style="font-size:inherit;  padding-right:10px;"data-value="${idx}" class="dec qtybtn rbtn_minus"
                        aria-hidden="true"><i class='fa-solid fa-minus fa-xs'></i></span><input style="border:none; text-align:center; width:20px;" id="quantity_${idx}" type="text" value="1" readonly="readonly"><span
                        data-value="${idx}" style="
    margin-left: 10px;
"class="inc qtybtn rbtn_plus" aria-hidden="true"><i class='fa-solid fa-plus fa-xs'></i></span></div>
<span data-value="${item.pprice}" style="font-size:inherit;" class="cart__price" id="cprice_${idx}">${numberWithCommas(item.pprice)}원</span>
</div>
               </div>
            </td>
         </tr>`;
	});
	str += `</tbody></table>
                     </div>
                  </div>
               </div>
            </div>`;

	str += `<div onclick="addToCart(true)" class="addToCart" style="
   background-color: #116530;
   text-align: center; 
"><a href="#" class="primary-btn">장바구니 담기</a></div>`;
	str += `</div>
      </div>
   </dialog>`;
	$("body").append(str);
	const dialog = document.querySelector("dialog");

	dialog.showModal();
}
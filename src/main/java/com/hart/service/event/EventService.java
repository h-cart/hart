package com.hart.service.event;

import java.sql.SQLException;
import java.util.List;

import com.hart.domain.ProductsVO;
import com.hart.domain.event.CRContentVO;
import com.hart.domain.event.CRIngredientVO;
import com.hart.domain.event.CRecipeVO;

/**
 * @since : 2023. 3. 16.
 * @FileName: EventService.java
 * @author : 이승규
 * @설명 :
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 16.     이승규
 *     </pre>
 */
public interface EventService {
	List<ProductsVO> getList(String keyword) throws SQLException;

	void register(CRecipeVO recipe, List<CRContentVO> content, List<CRIngredientVO> ingredient);
}

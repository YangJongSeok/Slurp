package com.Slurp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.Slurp.dto.CartgoodsDTO;
import com.Slurp.dto.GoodsCountDTO;
import com.Slurp.dto.GoodsDTO;
import com.Slurp.dto.OrderlistDTO;
import com.Slurp.dto.ReplygoodsDTO;
import com.Slurp.dto.ReplyreportDTO;
import com.Slurp.service.GoodsService;
import com.google.gson.Gson;

@Controller
public class GoodsController {

	ModelAndView mav;
	
	@Autowired
	GoodsService goodsSvc;

	/************** 제품 ********************/
	// 제품 상세보기 조회
	@RequestMapping("/goodsView")
	public ModelAndView goodsView(@RequestParam("gcode") String gcode, @RequestParam(value="page",defaultValue="1") int page) {
		System.out.println("제품보기 요청");
		System.out.println(gcode);
		mav = goodsSvc.getGoodsView(gcode, page);
		return mav;
	}
	// 제품 컬러 조회
	@RequestMapping("/getGoodsSize")
	public @ResponseBody String getGoodsSize(@RequestParam("gcolor") String gcolor, @RequestParam("gcode") String gcode) {
		System.out.println("제품 사이즈, 재고수량 요청");
		System.out.println("제품코드 : " + gcode);
		System.out.println("컬러 : " + gcolor);
		
		ArrayList<GoodsCountDTO> goodsSizeCount = goodsSvc.getGoodsSizeCount(gcolor, gcode);
		
		Gson gson = new Gson();
		String goodsCountDTOJson = gson.toJson(goodsSizeCount);
		System.out.println(goodsCountDTOJson);
		return goodsCountDTOJson;
	}

	// 재품 재고 조회
	@RequestMapping("/goodsCount")
	public @ResponseBody int goodsCount(String gcode, @RequestParam("color") String gcolor, @RequestParam("size") int gsize) {
		System.out.println("재고 조회 요청");
		System.out.println(gcode + " : " + gcolor + " : " + gsize);
		int getGoodsCount = goodsSvc.getGoodsCount(gcode, gcolor, gsize);
		return getGoodsCount;
	}
	
	/*********************** 찜목록 ****************************/
	// 찜 목록 추가
	@RequestMapping("/wishListAdd")
	public @ResponseBody int wishListAdd(@RequestParam("mid") String mid, @RequestParam("gcode") String gcode) {
		System.out.println("찜 목록 추가 요청");
		System.out.println("아이디 : " + mid + " 상품코드 : " + gcode);
		int insertResult = goodsSvc.wishAddList(mid, gcode);
		return insertResult;
	}
	
	// 찜 목록 제거
	@RequestMapping("/wishListDelete")
	public ModelAndView wishListDelete(String mid, String gcode) {
		System.out.println("찜 목록 삭제 요청");
		System.out.println("아이디 : " + mid + " 상품코드 : " + gcode);
		mav = goodsSvc.wishDelete(mid, gcode);
		return mav;
	}
	
	// 찜 목록 출력
	@RequestMapping("/wishList")
	public ModelAndView wishList(String mid) {
		System.out.println("찜 목록 요청");
		mav = goodsSvc.getWishList(mid);
		return mav;
	}
	
	
	/*********************** 장바구니 ****************************/
	// 장바구니 추가
	@RequestMapping("/cartListAdd")
	public @ResponseBody int cartListAdd(CartgoodsDTO cartgoodsDTO, String color, int size) {
		System.out.println("장바구니 추가 요청");
		int insertResult = goodsSvc.cartAddList(cartgoodsDTO, color, size);
		return insertResult;
	}
	
	// 장바구니 출력
	@RequestMapping("/shoppingCart")
	public ModelAndView shoppingCart(String mid) {
		System.out.println("장바구니 리스트 요청");
		mav = goodsSvc.getShoppingCart(mid);
		return mav;
	}
	
	// 장바구니 삭제
	@RequestMapping("/cartDelete")
	public ModelAndView cartDelete(String mid, int cg_code) {
		System.out.println("장바구니 삭제 요청");
		System.out.println(mid + " : " + cg_code);
		mav = goodsSvc.cartDelete(mid, cg_code);
		return mav;
	}
	
	// 장바구니 물건 수량 변경
	@ResponseBody
	@RequestMapping("/stockChange")
	public int stockChange(CartgoodsDTO cartgoodsDTO) {
		System.out.println("장바구니 수량 변경 요청");
		int updateResult = goodsSvc.stockChange(cartgoodsDTO);
		return updateResult;
	}

	/*********************** 제품 한건 구매 ***********************/
	// 한건 구매
	@RequestMapping("/goodsDirectPurchase")
	public ModelAndView goodsPurchase(String gcode, String mid, String color, int size, int gstock) {
		System.out.println("한건 구매 요청");
		System.out.println(gcode + " : " + mid + " : " + color + " : " + size + " : " + gstock);
		mav = goodsSvc.goodsPurchase(gcode, mid, color, size, gstock);
		return mav;
	}
	
	// 한건 구매 결제완료
	@RequestMapping("/directbuySuccess")
	public ModelAndView directbuySuccess(OrderlistDTO orderlistDTO, int mileage) {
		System.out.println("한건 구매완료");
		mav = goodsSvc.goodsDirectbuySuccess(orderlistDTO, mileage);
		return mav;
	}
	
	/************************ 여러건 구매********************************/
	// 여러건 구매
	@RequestMapping("/goodsManyPurchase")
	public ModelAndView goodsManyPurchase(String mid) {
		System.out.println("여러건 구매페이지 요청");
		mav = goodsSvc.goodsManyPurchase(mid);
		return mav;
	}
	
	// 여러건 구매 결제완료
	@RequestMapping("/cartbuySuccess")
	public ModelAndView cartbuySuccess(String mid, String o_request, int o_age, String mgender, String o_add, String o_phone, String mname, int mileage) {
		System.out.println("여러건 구매완료");
		mav = goodsSvc.cartbuySuccess(mid, o_request, o_age, mgender, o_add, o_phone, mname, mileage);
		return mav;
	}

	/************************** 07/28 ********************************/
	// 리뷰페이지 호출
	@RequestMapping("/review")
	public ModelAndView review(String mid, String gcode, int o_code, String url) {
		mav = goodsSvc.getReviewPage(mid, gcode, o_code, url);
		return mav;
	}
	
	// 리뷰 작성
	@RequestMapping(value = "reviewUpLoad")
    public ModelAndView requestupload(MultipartHttpServletRequest mtfRequest, ReplygoodsDTO replygoodsDTO, int o_code, String url) {
		System.out.println("리뷰 작성 요청");
        mav = goodsSvc.requestupload(mtfRequest, replygoodsDTO, o_code, url);
        return mav;
    }
	
	// 신고
	@RequestMapping("/declaration")
	public @ResponseBody int declaration(ReplyreportDTO replyreportDTO) {
		System.out.println("리뷰 신고 요청");
		System.out.println(replyreportDTO.getMid());
		int reviewDeclaration = goodsSvc.reviewDeclaration(replyreportDTO);
		return reviewDeclaration;
	}
	
	/**************************  상품 등록   
	 * @throws IOException 
	 * @throws IllegalStateException ******************************/ 
	/* GOODS 테이블 등록 */
	@RequestMapping("/goodsRegist")
	public ModelAndView goodsRegist(GoodsDTO goodsDTO) throws IllegalStateException, IOException {
		//GoodsController로 호출이 잘되었는지 확인
		System.out.println("/goodsRegist() 실행");
		
		//UUID사용하여 랜덤 생성된 값을 uuid에 저장.
		//생성시 UUID 형태이기 때문에 toString() 사용하여 String 형태로 변환. 
		String uuid = UUID.randomUUID().toString();
		
		//GCODE가 20자 제한이기 때문에 uuid에 substring 사용하여 [0]번 index부터 [20]-1 번 인덱스까지만 가져오도록 함.
		uuid = uuid.substring(0, 20); //뒤의 숫자 -1번 인덱스 까지임.
		System.out.println("20자로 잘린 uuid : " + uuid);
		
		//저장된 uuid의 값을 goodsDTO의 gcode에 입력함 
		goodsDTO.setGcode(uuid);
		System.out.println(goodsDTO); //상품 데이터 잘 넘어오나 확인
		
		//goodsDTO 값을 넘겨주면서 goodsSvc로 이동
		mav = goodsSvc.goodsRegist(goodsDTO);
		
		return mav;
	}
	
	/* GOODSCOUNT 상품 재고 등록 ajax*/
	@RequestMapping("/registGoodsCount")
	public @ResponseBody int goodsCountRegist(GoodsCountDTO goodsCountDTO) {
		//GoodsController로 호출이 잘되었는지 확인
		System.out.println("/goodsCountRegist() 실행");
		
		System.out.println("goodsCountDTO : " + goodsCountDTO);
		//goodsCountDTO 값을 넘겨주면서 goodsSvc로 이동
		int insertResult = goodsSvc.goodsCountRegist(goodsCountDTO);
		
		return insertResult;
	}
	
	/* GOODSCOUNT 현재 재고 조회 ajax*/
	@RequestMapping("/goodsCountState")
	public @ResponseBody ArrayList<GoodsCountDTO> goodsCountState(String gcode) {
		//GoodsController로 호출이 잘되었는지 확인
		System.out.println("/goodsCountState() 실행");
		
		System.out.println("gcode : " + gcode);
		//goodsCountDTO 값을 넘겨주면서 goodsSvc로 이동
		ArrayList<GoodsCountDTO> selectResult = goodsSvc.goodsCountState(gcode);
		
		return selectResult;
	}
	
	/* 상품삭제 */
	@RequestMapping("deleteGoodsCount")
	public ModelAndView deleteGoodsCount(String gcode) {
		//GoodsController로 호출이 잘되었는지 확인
		System.out.println("/deleteGoodsCount() 실행");
				
		System.out.println("gcode : " + gcode);
		//goodsCountDTO 값을 넘겨주면서 goodsSvc로 이동
		mav = goodsSvc.deleteGoodsCount(gcode);
		return mav;
	}
}

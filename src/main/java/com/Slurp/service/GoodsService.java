package com.Slurp.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.Slurp.dao.GoodsDAO;
import com.Slurp.dao.MemberDAO;
import com.Slurp.dto.CartgoodsDTO;
import com.Slurp.dto.GoodsColorDTO;
import com.Slurp.dto.GoodsCountDTO;
import com.Slurp.dto.GoodsDTO;
import com.Slurp.dto.MembersDTO;
import com.Slurp.dto.MembershipDTO;
import com.Slurp.dto.OrderlistDTO;
import com.Slurp.dto.PageDTO;
import com.Slurp.dto.ReplygoodsDTO;
import com.Slurp.dto.ReplyreportDTO;
import com.Slurp.dto.WishlistDTO;
import com.Slurp.dto.WishlistDTO2;

@Service
public class GoodsService {

	@Autowired
	GoodsDAO goodsDAO;
	
	@Autowired
	MemberDAO memDAO;
	
	/*************** 상품 등록 
	 * @throws IOException 
	 * @throws IllegalStateException *****************/
	// 상품등록 ***** 이미지 파일 처리 해야함 *****
	public ModelAndView goodsRegist(GoodsDTO goodsDTO) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Service 실행");

		ModelAndView mav = new ModelAndView();
		// 데이터를 DAO로 넘겨서 쿼리문 실행
		

		// 각각의 이미지 파일이 첨부 되었을때만 동작하도록
		if(goodsDTO.getGfile() != null) {
			 MultipartFile gfile = goodsDTO.getGfile();
			
			
			String gimg = "";
			System.out.println(gimg);
			
			String savePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Slurp\\resources\\images\\goods";
			
			if(!gfile.isEmpty()) {
				UUID uuid = UUID.randomUUID();
				gimg = uuid.toString() + "_" + gfile.getOriginalFilename();
				gfile.transferTo(new File(savePath,gimg));
			}
			
			goodsDTO.setGimg(gimg);
		}
		if (goodsDTO.getGfile1() != null) {
			MultipartFile gfile1 = goodsDTO.getGfile1();
			
			
			String gdtimg1 = "";
			System.out.println(gdtimg1);
			
			String savePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Slurp\\resources\\images\\goods";
			
			if(!gfile1.isEmpty()) {
				UUID uuid = UUID.randomUUID();
				gdtimg1 = uuid.toString() + "_" + gfile1.getOriginalFilename();
				gfile1.transferTo(new File(savePath,gdtimg1));
			}
			
			goodsDTO.setGdtimg1(gdtimg1);
		}
		if (goodsDTO.getGfile2() != null) {
			MultipartFile gfile2 = goodsDTO.getGfile2();
			
			
			String gdtimg2 = "";
			System.out.println(gdtimg2);
			
			String savePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Slurp\\resources\\images\\goods";
			
			if(!gfile2.isEmpty()) {
				UUID uuid = UUID.randomUUID();
				gdtimg2 = uuid.toString() + "_" + gfile2.getOriginalFilename();
				gfile2.transferTo(new File(savePath,gdtimg2));
			}
			
			goodsDTO.setGdtimg2(gdtimg2);
		}
		if (goodsDTO.getGfile3() != null) {
			MultipartFile gfile3 = goodsDTO.getGfile3();
			
			
			String gdtimg3 = "";
			System.out.println(gdtimg3);
			
			String savePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Slurp\\resources\\images\\goods";
			
			if(!gfile3.isEmpty()) {
				UUID uuid = UUID.randomUUID();
				gdtimg3 = uuid.toString() + "_" + gfile3.getOriginalFilename();
				gfile3.transferTo(new File(savePath,gdtimg3));
			}
			
			goodsDTO.setGdtimg3(gdtimg3);
		}
		if (goodsDTO.getGfile4() != null) {
			MultipartFile gfile4 = goodsDTO.getGfile4();
			
			
			String gdtimg4 = "";
			System.out.println(gdtimg4);
			
			String savePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Slurp\\resources\\images\\goods";
			
			if(!gfile4.isEmpty()) {
				UUID uuid = UUID.randomUUID();
				gdtimg4 = uuid.toString() + "_" + gfile4.getOriginalFilename();
				gfile4.transferTo(new File(savePath,gdtimg4));
			}
			
			goodsDTO.setGdtimg4(gdtimg4);
		}
		//goodsDTO의 값 출력해보기
		System.out.println("goodsDTO : " + goodsDTO);
		
		int insertResult = goodsDAO.goodsRegist(goodsDTO);
		if(insertResult > 0) {
			//등록한 업체의 아이디를 가져와서 상품재고화면 불러내는 컨트롤러 호출
			String cid = goodsDAO.getCid(goodsDTO.getCcode());
			mav.setViewName("redirect:/companyManageForm?cid=" + cid);
		}
		
		return mav;
	}
	
	// 상품 재고 화면 이동
	public ModelAndView registerGoodsCountForm(String gcode) {
		// TODO Auto-generated method stub
		System.out.println("Service 실행");

		ModelAndView mav = new ModelAndView();
		//가져온 상품코드 출력
		System.out.println("상품코드 : " + gcode);
		//상품코드로 상품정보 가져옴
		GoodsDTO goodDTO = goodsDAO.registerGoodsCountForm(gcode);
		if(goodDTO != null) {
			//상품의 재고량 가져오기
			ArrayList<GoodsCountDTO> goodsCountDTO = goodsDAO.getCountState(gcode);
			System.out.println();
			//가져온 상품데이터 저장 후 재고등록 화면으로 전송
			mav.addObject("goodsCountDTO", goodsCountDTO);
			mav.addObject("goodsDTO", goodDTO);
			mav.setViewName("Member/company/registerGoodsCountForm");
		}
		return mav;
	}
	
	// 상품 재고 등록
	public int goodsCountRegist(GoodsCountDTO goodsCountDTO) {
		// TODO Auto-generated method stub
		System.out.println("Service 실행");
		int insertResult = 0;
		// 데이터를 DAO로 넘겨서 쿼리문 실행
		System.out.println("goodsCountDTO : " + goodsCountDTO);
		//중복여부 가져오는용
		int goodsCountCheck = goodsDAO.getGoodsCount1(goodsCountDTO);
		//중복되었을 때 데이터 가져오는용
		GoodsCountDTO goodsCountCheckDTO = goodsDAO.getGoodsCount2(goodsCountDTO);
		System.out.println("새 재고 goodsCountDTO : " + goodsCountDTO + "기존 재고 goodsCountCheckDTO : " + goodsCountCheckDTO );
		if (goodsCountCheck != 1) { //중복 체크시에 컬러와 사이즈가 모두 있는게 아니라서 업데이트 말고 새롭게 인서트 해야하는 경우
			System.out.println("goodsCountCheck : " + goodsCountCheck);
			System.out.println("컬러도없고 사이즈도 없을 때 or 컬러는 존재하는데 사이즈를 처음으로 입력할 때");
			insertResult = goodsDAO.goodsCountRegist(goodsCountDTO); //재고 입력
			if (insertResult == 1) { //입력에 성공하면
				
				//추가로 입력한 재고컬러가 기존 컬러 테이블에 있는지 체크
				int checkResult = goodsDAO.goodsColorCheck(goodsCountDTO);
				if(checkResult == 0) { //기존 테이블에 없으면
					// GoodsColorDTO 인서트
					goodsDAO.goodsColorRegist(goodsCountDTO);
				}
			}
		} else { //중복 체크시에 컬러와 사이즈가 모두 있어서 업데이트 해야하는 경우
			// 컬러도 있고 사이즈도 있어서 수량만 업데이트하면 될 때
			System.out.println("컬러도 있고 사이즈도 있어서 수량만 업데이트하면 될 때");
			int gstock = goodsCountDTO.getGstock();  //추가할 수량
			int goodsSizeCheck = goodsCountCheckDTO.getGsize(); //기존 사이즈
			String goodsColorCheck = goodsCountDTO.getGcolor(); //기존 컬러
			int gstock1 = goodsCountCheckDTO.getGstock(); //기존 수량
			String gcode = goodsCountCheckDTO.getGcode();
			int sum = gstock1 + gstock; //기존 재고 - 감소할 재고의 값
			
			if(sum >= 0) { //재고 감소시킬때 - 되지 않도록 0까지 제한
				insertResult = goodsDAO.goodsCountUpdate1(goodsColorCheck, gstock, goodsSizeCheck, gcode);
			} else {
				insertResult = 3;
			}
		}
		// 결과 확인
		return insertResult;
	}
	
	/* GOODSCOUNT 현재 재고 조회 ajax*/
	public ArrayList<GoodsCountDTO> goodsCountState(String gcode) {
		// TODO Auto-generated method stub
		System.out.println("Service 실행");
		
		ArrayList<GoodsCountDTO> selectResult = goodsDAO.goodsCountState(gcode);
		System.out.println("selectResult : " + selectResult);
		return selectResult;
	}
	
	// 상품 삭제
	public ModelAndView deleteGoodsCount(String gcode) {
		System.out.println("Service 실행");
		ModelAndView mav = new ModelAndView();
		//가져온 상품코드 출력
		System.out.println("상품코드 : " + gcode);
		
		System.out.println("굿즈 컬러, 사이즈 삭제");
		GoodsDTO goodsDTO = goodsDAO.getGoodsView(gcode);
		System.out.println(goodsDTO);
		System.out.println("파일삭제");
		//지울 파일 이름
		String delGfile = goodsDTO.getGimg();
		String delGfile1 = goodsDTO.getGdtimg1();
		String delGfile2 = goodsDTO.getGdtimg2();
		String delGfile3 = goodsDTO.getGdtimg3();
		String delGfile4 = goodsDTO.getGdtimg4();
		
		String savePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Slurp\\resources\\images\\review";
		// 리뷰 불러오기
		ReplygoodsDTO replygoodsDTO = goodsDAO.getReplygoods1(gcode);
		System.out.println(replygoodsDTO);
		
		if(replygoodsDTO != null) {
			String replyimg1 = replygoodsDTO.getR_img1();
			String replyimg2 = replygoodsDTO.getR_img2();
			String replyimg3 = replygoodsDTO.getR_img3();
			
			// 리뷰 신고 삭제
			memDAO.deleteReport(replygoodsDTO.getR_num());
			
			// 리뷰 삭제
			int deleteResult4 = goodsDAO.deleteReplygoods(gcode);
			if(deleteResult4 > 0) {
				if(replyimg1 == null) {
					System.out.println("파일 없음");
				} else {
					File file = new File(savePath, replyimg1);
					file.delete();
				}
				if(replyimg2 == null) {
					System.out.println("파일 없음");
				} else {
					File file = new File(savePath, replyimg2);
					file.delete();
				}
				if(replyimg3 == null) {
					System.out.println("파일 없음");
				} else {
					File file = new File(savePath, replyimg3);
					file.delete();
				}
			}
		}
		
		// 찜, 장바구니 삭제
		memDAO.deleteWishList(gcode);
		memDAO.deleteCartList(gcode);
		
		// 굿즈삭제
		int deleteResult1 = goodsDAO.deleteGoodsCount2(gcode);
		int deleteResult2 = goodsDAO.deleteGoodsCount1(gcode);
		int deleteResult3 = goodsDAO.deleteGoodsCount(gcode);

		System.out.println("지울 파일 이름 : " + delGfile);
		savePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Slurp\\resources\\images\\goods";
		if(deleteResult3 > 0) {
			if(delGfile == null) {
				System.out.println("파일 없음");
			} else {
				File file = new File(savePath, delGfile);
				file.delete();
			}
			if(delGfile1 == null) {
				System.out.println("파일 없음");
			} else {
				File file = new File(savePath, delGfile1);
				file.delete();
			}
			if(delGfile2 == null) {
				System.out.println("파일 없음");
			} else {
				File file = new File(savePath, delGfile2);
				file.delete();
			}
			if(delGfile3 == null) {
				System.out.println("파일 없음");
			} else {
				File file = new File(savePath, delGfile3);
				file.delete();
			}
			if(delGfile4 == null) {
				System.out.println("파일 없음");
			} else {
				File file = new File(savePath, delGfile4);
				file.delete();
			}
			

		}
		mav.setViewName("home");
		return mav;
	}
	
	/******************* 제품 / 양종석 ***************************/
	// 제품 상세보기 조회
	public ModelAndView getGoodsView(String gcode, int page) {
		ModelAndView mav = new ModelAndView();
		
		// 굿즈 기본정보 가져오기
		GoodsDTO goodsDTO = goodsDAO.getGoodsView(gcode);
		System.out.println("goodsDTO : " + goodsDTO);
		
		if(goodsDTO == null) {
			mav.addObject("msg", "물품이 없습니다.");
			mav.setViewName("back");
			return mav;
		}
		// 굿즈의 컬러가 뭐가있는지 가져오기
		ArrayList<GoodsColorDTO> goodsColorList = goodsDAO.getGoodsColorList(gcode);
		System.out.println("goodsColor : " + goodsColorList);
		
		// 굿즈 리뷰 페이징
		int pageLimit = 3; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);
		
		// 굿즈 리뷰 가져오기
		ArrayList<ReplygoodsDTO> replygoodsDTO = goodsDAO.getReplygoods(gcode, startRow, endRow);

		int replyGoodsCount = goodsDAO.getreplyGoodsCount(gcode); // 리뷰의 전체 개수

		int maxPage = (int) (Math.ceil((double) replyGoodsCount / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		if (page > 1) {
			mav.addObject("pageReview", "1");
		} else {
			mav.addObject("pageReview", "0");
		}
		
		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		// 평점 평균
		int avgRating = goodsDAO.getAvgRating(gcode);
		
		ArrayList<Integer> ratingList = new ArrayList<Integer>();
		
		// 5점 리뷰 갯수
		int rating = goodsDAO.getRating(gcode, 5);
		ratingList.add(rating);
		// 4점 리뷰 갯수
		rating = goodsDAO.getRating(gcode, 4);
		ratingList.add(rating);
		// 3점 리뷰 갯수
		rating = goodsDAO.getRating(gcode, 3);
		ratingList.add(rating);
		// 2점 리뷰 갯수
		rating = goodsDAO.getRating(gcode, 2);
		ratingList.add(rating);
		// 1점 리뷰 갯수
		rating = goodsDAO.getRating(gcode, 1);
		ratingList.add(rating);
		
		
		mav.addObject("ratingList", ratingList);
		mav.addObject("rating", avgRating);
		mav.addObject("replyGoodsCount", replyGoodsCount);
		mav.addObject("page", pdto);
		mav.addObject("replygoodsDTO", replygoodsDTO);
		mav.addObject("goodsView", goodsDTO);
		mav.addObject("goodsColor", goodsColorList);
		mav.setViewName("order/purchase");
		return mav;
	}

	// 제품 컬러, 재고량 조회
	public ArrayList<GoodsCountDTO> getGoodsSizeCount(String gcolor, String gcode) {
		ArrayList<GoodsCountDTO> goodsSizeCount = goodsDAO.getGoodsSizeCountList(gcolor, gcode);
		return goodsSizeCount;
	}

	// 상품 재고 조회
	public int getGoodsCount(String gcode, String gcolor, int gsize) {
		int goodsCount = goodsDAO.getGoodsCount(gcode, gcolor, gsize);
		return goodsCount;
	}
	/*********************** 찜목록 - 양종석 ****************************/
	// 찜 목록 추가
	public int wishAddList(String mid,String gcode) {
		int insertResult = 0;
		
		WishlistDTO wishlistDTO = goodsDAO.getWishList(mid, gcode);
		
		if(wishlistDTO == null) {
			// 찜목록번호 MAX값 찾기
			int maxWishListNum = goodsDAO.getMaxWishListNum();
			maxWishListNum += 1;
			
			// DTO에 세팅
			wishlistDTO = new WishlistDTO();
			wishlistDTO.setWl_code(maxWishListNum);
			wishlistDTO.setMid(mid);
			wishlistDTO.setGcode(gcode);
			
			insertResult = goodsDAO.wishAdd(wishlistDTO);
		}
		return insertResult;
	}

	// 찜 목록 제거
	public ModelAndView wishDelete(String mid, String gcode) {
		ModelAndView mav = new ModelAndView();
		int deleteResult = goodsDAO.wishDelete(mid, gcode);
		if(deleteResult > 0) {
			System.out.println("찜 목록 삭제완료");
			mav.addObject("msg", "찜 목록에서 제거되었습니다.");
			mav.addObject("url", "wishList?mid="+mid);
			mav.setViewName("Success");
		}
		return mav;
	}
	
	// 찜 목록 출력
	public ModelAndView getWishList(String mid) {
		ModelAndView mav = new ModelAndView();
		ArrayList<WishlistDTO2> wishlistDTO = goodsDAO.getWishList2(mid);
		System.out.println(wishlistDTO);
		
		mav.addObject("wishlist", wishlistDTO);
		mav.setViewName("cartwish/wishlist");
		return mav;
	}
	
	/************************** 장바구니 - 양종석 ***************************/ 
	// 장바구니 추가
	public int cartAddList(CartgoodsDTO cartgoodsDTO, String color, int size) {
		// 장바구니 코드 생성
		int maxCartNum = goodsDAO.getMaxCartNum();
		maxCartNum += 1;
		
		// 총 금액계산
		int gprice = cartgoodsDTO.getGprice();
		int gstock = cartgoodsDTO.getGstock();
		
		// DTO에 세팅
		cartgoodsDTO.setGprice(gprice*gstock);
		cartgoodsDTO.setCg_code(maxCartNum);
		cartgoodsDTO.setGsize(size);
		cartgoodsDTO.setGcolor(color);
		System.out.println(cartgoodsDTO);
		
		// 이미 장바구니에 있는지 확인
		CartgoodsDTO alreadyCartgoodsDTO = goodsDAO.getAlreadyCart(cartgoodsDTO);
		if(alreadyCartgoodsDTO != null) {
			// 이미있으면 0 리턴
			return 0;
		} else {
			int insertResult = goodsDAO.cartAddList(cartgoodsDTO);
			if(insertResult > 0) {
				System.out.println("장바구니 추가 완료");
			} else {
				System.out.println("장바구니 추가 실패");
				return 2;
			}
		}
		return 1;
	}
	
	// 장바구니 리스트 출력
	public ModelAndView getShoppingCart(String mid) {
		ModelAndView mav = new ModelAndView();
		
		// 장바구니 리스트 조회
		ArrayList<CartgoodsDTO> cartgoodsDTO = goodsDAO.getShoppingCart(mid);
		
		// 멤버쉽 조회
		MembershipDTO membershipDTO = goodsDAO.getMemberShip(mid);
		
		// 총 가격 선언
		int sum = 0;
		for(int i = 0; i < cartgoodsDTO.size(); i++) {
			// 가격 값 가져오기
			int price = cartgoodsDTO.get(i).getGprice();
			// 총 가격 구하기
			sum += price;
		}
		
		// 배달료 계산
		int fee = 0;
		if(sum > 0 && sum < 50000) {
			fee = 2500;
		}
		
		System.out.println(sum + " : " + fee);
		
		// 배달료 포함 총 가격
		int totalPrice = sum + fee;
		
		mav.addObject("totalPrice", totalPrice);
		mav.addObject("fee", fee);
		mav.addObject("sum", sum);
		mav.addObject("membershipDTO", membershipDTO);
		mav.addObject("cartgoodsDTO", cartgoodsDTO);
		mav.setViewName("cartwish/shopping_cart");
		
		return mav;
	}

	// 장바구니 삭제
	public ModelAndView cartDelete(String mid, int cg_code) {
		ModelAndView mav = new ModelAndView();
		int deleteResult = goodsDAO.cartDelete(mid, cg_code);
		if(deleteResult > 0) {
			System.out.println("장바구니 삭제 완료");
			mav.addObject("msg", "상품이 삭제되었습니다.");
			mav.addObject("url", "shoppingCart?mid="+mid);
			mav.setViewName("Success");
		} else {
			System.out.println("장바구니 삭제 실패");
			mav.addObject("msg", "상품삭제에 실패하였습니다.");
			mav.setViewName("back");
		}
		return mav;
	}
	
	// 장바구니 수량 변경
	public int stockChange(CartgoodsDTO cartgoodsDTO) {
		// 수량 체크
		int goodsCount = goodsDAO.getGoodsCount(cartgoodsDTO.getGcode(), cartgoodsDTO.getGcolor(), cartgoodsDTO.getGsize());
		if(goodsCount < cartgoodsDTO.getGstock()) {
			System.out.println("수량변경 불가");
			return goodsCount;
		}
		// 수량변경
		int updateResult = goodsDAO.stockChange(cartgoodsDTO);
		if(updateResult > 0) {
			// 수량에 맞게 가격변경
			updateResult = goodsDAO.gpriceChange(cartgoodsDTO);
		}
		return updateResult;
	}
	
	/*********************** 제품 구매 - 양종석 ***********************/
	// 제품 한건 구매
	public ModelAndView goodsPurchase(String gcode, String mid, String color, int size, int gstock) {
		ModelAndView mav = new ModelAndView();
		
		// 회원정보
		MembersDTO membersDTO = memDAO.getMemberDTO(mid);
		MembershipDTO membershipDTO = memDAO.getMembershipDTO(mid);

		// 물품정보, 사이즈, 컬러, 수량
		GoodsCountDTO goodscountDTO = new GoodsCountDTO();
		goodscountDTO.setGcode(gcode);
		goodscountDTO.setGcolor(color);
		goodscountDTO.setGsize(size);
		goodscountDTO.setGstock(gstock);
		
		// 굿즈 가격
		int goodsPrice = goodsDAO.getGoodsPrice(gcode);
		
		// 총 가격 선언
		int sum = 0;
		sum = goodsPrice * gstock;
		
		// 배달료 계산
		int fee = 0;
		if(sum > 0 && sum < 50000) {
			fee = 2500;
		}
		System.out.println(sum + " : " + fee);
		
		// 물품 이름
		String gname = goodsDAO.getGname(gcode);
		
		mav.addObject("sum", sum);
		mav.addObject("fee", fee);
		mav.addObject("goodscountDTO", goodscountDTO);
		mav.addObject("gname", gname);
		mav.addObject("membersDTO", membersDTO);
		mav.addObject("membershipDTO", membershipDTO);
		mav.setViewName("order/directbuy");
		return mav;
	}

	// 한건 결제 성공
	public ModelAndView goodsDirectbuySuccess(OrderlistDTO orderlistDTO, int m_mileage) {
		ModelAndView mav = new ModelAndView();
		// 구매번호 MAX값 조회
		int maxO_code = goodsDAO.getMaxO_code();
		// 구매번호 Set
		orderlistDTO.setO_code(maxO_code+1);
		
		// 총 금액
		int sum = orderlistDTO.getO_gprice();
		
		// 업체코드
		String ccode = memDAO.getCcode(orderlistDTO.getGcode());
		System.out.println(ccode);
		orderlistDTO.setCcode(ccode);
		
		// 구매테이블에 추가
		int insertResult = goodsDAO.goodsBuySuccess(orderlistDTO);
		// 재고수량 마이너스
		if(insertResult > 0) {
			int updateResult = goodsDAO.goodsStockMinus(orderlistDTO.getGcode(), orderlistDTO.getGcolor(), orderlistDTO.getGsize(), orderlistDTO.getO_quantity());
			// 마일리지 사용
			if(m_mileage > 0) {
				updateResult = memDAO.mileageMinus(orderlistDTO.getMid(), m_mileage);
			}
			// 마일리지 적립, 구매금액 증가
			if(updateResult > 0) {
				
				// 마일리지 적립을 위한 회원등급 불러오기
				MembershipDTO membershipDTO = memDAO.getMembershipDTO(orderlistDTO.getMid());
			
				// 마일리지 적립
				double mileage = 0;
				if(membershipDTO.getM_rating().equals("VIP")) {
					mileage = (int)(Math.ceil((double)orderlistDTO.getO_gprice() * 0.05));
				} else if(membershipDTO.getM_rating().equals("Gold")) {
					mileage = (int)(Math.ceil((double)orderlistDTO.getO_gprice() * 0.01));
				} else if(membershipDTO.getM_rating().equals("Silver")) {
					mileage = (int)(Math.ceil((double)orderlistDTO.getO_gprice() * 0.005));
				} else if(membershipDTO.getM_rating().equals("Bronze")) {
					mileage = (int)(Math.ceil((double)orderlistDTO.getO_gprice() * 0.001));
				}
				
				System.out.println("회원 아이디 : " + orderlistDTO.getMid());
				System.out.println("회원등급 : " + membershipDTO.getM_rating());
				System.out.println("총 가격 : " + orderlistDTO.getO_gprice());
				System.out.println("적립마일리지 : " + mileage);
				
				// 증가 update
				updateResult = memDAO.mileageAndHispay(orderlistDTO.getMid(), sum, mileage);
				
				// 등급 판별을 위해 다시 회원등급 불러오기
				membershipDTO = memDAO.getMembershipDTO(orderlistDTO.getMid());
				
				// 회원 등급 매기기
				if(membershipDTO.getM_hispay() >= 3000000) {
					updateResult = memDAO.upM_ratingVIP(orderlistDTO.getMid());
				} else if (membershipDTO.getM_hispay() >= 1000000) {
					updateResult = memDAO.upM_ratingGold(orderlistDTO.getMid());
				} else if (membershipDTO.getM_hispay() >= 500000) {
					updateResult = memDAO.upM_ratingSilver(orderlistDTO.getMid());
				} else if (membershipDTO.getM_hispay() >= 100000) {
					updateResult = memDAO.upM_ratingBronze(orderlistDTO.getMid());
				}
				
				// 구매 완료 페이지
				mav.setViewName("/order/buySuccess");
			}
		}
		return mav;
	}

	// 여러건 구매 조회
	public ModelAndView goodsManyPurchase(String mid) {
		ModelAndView mav = new ModelAndView();
		
		// 회원정보
		MembersDTO membersDTO = memDAO.getMemberDTO(mid);
		MembershipDTO membershipDTO = memDAO.getMembershipDTO(mid);
		
		// 장바구니 불러오기
		ArrayList<CartgoodsDTO> cartgoodsList = goodsDAO.getShoppingCart(mid);
		
		// 총 가격 선언
		int sum = 0;
		for(int i = 0; i < cartgoodsList.size(); i++) {
			sum += cartgoodsList.get(i).getGprice();
		}

		// 배달료 계산
		int fee = 0;
		if(sum > 0 && sum < 50000) {
			fee = 2500;
		}
		System.out.println(sum + " : " + fee);
		
		mav.addObject("sum", sum);
		mav.addObject("fee", fee);
		mav.addObject("cartgoodsList", cartgoodsList);
		mav.addObject("membersDTO", membersDTO);
		mav.addObject("membershipDTO", membershipDTO);
		mav.setViewName("order/cartbuy");
		return mav;
	}
	
	// 여러건 구매 완료
	public ModelAndView cartbuySuccess(String mid, String o_request, int o_age, String mgender, String o_add, String o_phone, String mname, int m_mileage) {
		ModelAndView mav = new ModelAndView();
		// 장바구니 불러오기
		ArrayList<CartgoodsDTO> cartgoodsList = goodsDAO.getShoppingCart(mid);
		
		// 회원정보 (Set, 마일리지 적립)
		MembersDTO membersDTO = memDAO.getMemberDTO(mid);
		MembershipDTO membershipDTO = memDAO.getMembershipDTO(mid);
		
		// 총 합계금액
		int sum = 0;
		// 반복해서 계속 Insert
		for(int i = 0; i < cartgoodsList.size(); i++ ) {
			OrderlistDTO orderlistDTO = new OrderlistDTO();
			// 구매번호 MAX값 조회
			int maxO_code = goodsDAO.getMaxO_code();
			// 구매번호 Set
			orderlistDTO.setO_code(maxO_code+1);
			
			// 나머지 정보 Set
			orderlistDTO.setGcode(cartgoodsList.get(i).getGcode());
			// 업체코드
			String ccode = memDAO.getCcode(orderlistDTO.getGcode());
			
			orderlistDTO.setGname(cartgoodsList.get(i).getGname());
			orderlistDTO.setGcolor(cartgoodsList.get(i).getGcolor());
			orderlistDTO.setGsize(cartgoodsList.get(i).getGsize());
			orderlistDTO.setO_quantity(cartgoodsList.get(i).getGstock());
			orderlistDTO.setMid(mid);
			orderlistDTO.setMname(mname);
			orderlistDTO.setMgender(mgender);
			orderlistDTO.setO_age(o_age);
			orderlistDTO.setO_add(o_add);
			orderlistDTO.setO_phone(o_phone);
			orderlistDTO.setO_request(o_request);
			orderlistDTO.setO_gprice(cartgoodsList.get(i).getGprice());
			orderlistDTO.setCcode(ccode);
			sum += cartgoodsList.get(i).getGprice();
			
			// 구매테이블에 추가
			int insertResult = goodsDAO.goodsBuySuccess(orderlistDTO);
			if(insertResult > 0) {
				System.out.println(i + "건 주문 완료");
				// 재고수량 마이너스
				int updateResult = goodsDAO.goodsStockMinus(orderlistDTO.getGcode(), orderlistDTO.getGcolor(), orderlistDTO.getGsize(), orderlistDTO.getO_quantity());
			} else {
				System.out.println("구매 실패");
			}
			
		}
		
		// 마일리지 사용
		System.out.println(m_mileage + " : " + mid);
		if(m_mileage > 0) {
			int updateResult = memDAO.mileageMinus(mid, m_mileage);
		}
		
		// 마일리지 적립
		double mileage = 0;
		if(membershipDTO.getM_rating().equals("VIP")) {
			mileage = (int)(Math.ceil((double)sum * 0.05));
		} else if(membershipDTO.getM_rating().equals("Gold")) {
			mileage = (int)(Math.ceil((double)sum * 0.01));
		} else if(membershipDTO.getM_rating().equals("Silver")) {
			mileage = (int)(Math.ceil((double)sum * 0.005));
		} else if(membershipDTO.getM_rating().equals("Bronze")) {
			mileage = (int)(Math.ceil((double)sum * 0.001));
		}
		
		System.out.println("회원 아이디 : " + membersDTO.getMid());
		System.out.println("회원등급 : " + membershipDTO.getM_rating());
		System.out.println("총 가격 : " + sum);
		System.out.println("적립마일리지 : " + mileage);
		
		// 증가 update
		memDAO.mileageAndHispay(membersDTO.getMid(), sum, mileage);
		
		// 등급 판별을 위해 다시 회원등급 불러오기
		membershipDTO = memDAO.getMembershipDTO(mid);
		
		// 회원 등급 매기기
		if(membershipDTO.getM_hispay() >= 3000000) {
			memDAO.upM_ratingVIP(mid);
		} else if (membershipDTO.getM_hispay() >= 1000000) {
			memDAO.upM_ratingGold(mid);
		} else if (membershipDTO.getM_hispay() >= 500000) {
			memDAO.upM_ratingSilver(mid);
		} else if (membershipDTO.getM_hispay() >= 100000) {
			memDAO.upM_ratingBronze(mid);
		}
		
		// 장바구니 비우기
		memDAO.cartAllDelete(mid);
		
		// 구매 완료 페이지
		mav.setViewName("/order/buySuccess");
		return mav;
	}
	
	public ModelAndView getReviewPage(String mid, String gcode, int o_code, String url) {
		ModelAndView mav = new ModelAndView();
		GoodsDTO goodsDTO = goodsDAO.getGoodsView(gcode);
		
		mav.addObject("url", url);
		mav.addObject("goodsDTO", goodsDTO);
		mav.addObject("o_code", o_code);
		mav.setViewName("order/reviews");
		return mav;
	}
	
	// 리뷰작성
	public ModelAndView requestupload(MultipartHttpServletRequest mtfRequest, ReplygoodsDTO replygoodsDTO, int o_code, String url) {
		ModelAndView mav = new ModelAndView();
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		
		// 댓글최대번호
		int MaxR_num = goodsDAO.getMaxR_num();
		// 댓글번호 set
		int r_num = MaxR_num + 1;
		replygoodsDTO.setR_num(r_num);
		
		if(fileList.size() > 0) {
	        // 파일 경로 꼭 수정
	        String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Slurp\\resources\\images\\review\\";
	
	        for (MultipartFile mf : fileList) {
	            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
	            if(originFileName != "") {
	            	
		            long fileSize = mf.getSize(); // 파일 사이즈
	
		            System.out.println("originFileName : " + originFileName);
		            System.out.println("fileSize : " + fileSize);
	
		            String safeFile = path + System.currentTimeMillis() + originFileName;
		            String saveFileName = System.currentTimeMillis() + originFileName;
		            System.out.println("saveFileName : " + saveFileName);
		            if(replygoodsDTO.getR_img1() == null) {
		            	replygoodsDTO.setR_img1(saveFileName);
		            } else if(replygoodsDTO.getR_img2() == null) {
		            	replygoodsDTO.setR_img2(saveFileName);
		            } else if(replygoodsDTO.getR_img3() == null) {
		            	replygoodsDTO.setR_img3(saveFileName);
		            }
		            try {
		                mf.transferTo(new File(safeFile));
		            } catch (IllegalStateException e) {
		                e.printStackTrace();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
            }
		}
		
		System.out.println(replygoodsDTO);
		int insertResult = goodsDAO.InsertReplygoods(replygoodsDTO);
		
		// 후기작성 완료
		int updateResult = goodsDAO.reviewComplete(o_code);
		
		if(insertResult > 0) {
	        mav.addObject("msg", "후기를 작성하셨습니다.");
	        // 경로가 있느냐 없느냐에 따라 페이지이동
	        if(url.contains("orderListPlus")) {
	        	mav.addObject("url", "orderlistPlus?mid="+replygoodsDTO.getMid());
	        } else {
	        	mav.addObject("url", "showMemberInfo?mid="+replygoodsDTO.getMid());
	        }
	        mav.setViewName("Success");
		} else {
			mav.addObject("msg", "후기작성에 실패하셨습니다.");
			mav.setViewName("back");
		}
		return mav;
	}

	// 리뷰 신고
	public int reviewDeclaration(ReplyreportDTO replyreportDTO) {
		// 이미 했는지 확인
		ReplyreportDTO replyreport = goodsDAO.getReplyReport(replyreportDTO.getR_num(), replyreportDTO.getMid());
		int insertResult = 0;
		if(replyreport != null) {
			return insertResult;
		} else {
			// 신고 접수
			// 번호생성
			int maxRR_num = goodsDAO.getMaxRRnum();
			int rr_num = maxRR_num + 1;
			replyreportDTO.setRr_num(rr_num);
			
			insertResult = goodsDAO.replyReport(replyreportDTO);
		}
		return insertResult;
	}
	
	

	/********************************** 이성빈 *************************************/
	
	/***********************************
	 * 남성 상품 리스트 - 이성빈
	 **********************************/

	// 남성 상의 페이지
	public ModelAndView man_top(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		// GOODSDTO 에 업체이름 추가 **********************
		ArrayList<GoodsDTO> mantop = goodsDAO.getManTop(pdto);

		int manTopList = goodsDAO.getManTopList(); // 남성 상의 전체 개수

		int maxPage = (int) (Math.ceil((double) manTopList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("mantop", mantop);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_top");
		return mav;
	}

	// 남성 하의 페이지
	public ModelAndView man_pants(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manpants = goodsDAO.getManPants(pdto);

		int manPantsList = goodsDAO.getManPantsList(); // 남성 하의 전체 개수

		int maxPage = (int) (Math.ceil((double) manPantsList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manpants", manpants);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_pants");
		return mav;
	}

	// 남성 신발 페이지
	public ModelAndView man_shoes(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manshoes = goodsDAO.getManShoes(pdto);

		int manShoesList = goodsDAO.getManShoesList(); // 남성 신발 전체 개수

		int maxPage = (int) (Math.ceil((double) manShoesList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manshoes", manshoes);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_shoes");
		return mav;
	}

	// 남성 아우터 페이지
	public ModelAndView man_outer(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manouter = goodsDAO.getManOuter(pdto);

		int manOuterList = goodsDAO.getManOuterList(); // 남성 상의 전체 개수

		int maxPage = (int) (Math.ceil((double) manOuterList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manouter", manouter);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_outer");
		return mav;
	}

	/***********************************
	 * 남성 상품 리스트 끝
	 **********************************/

	/***********************************
	 * 남성 상품 정렬
	 **********************************/

	// 남성 상의 인기순
	public ModelAndView man_top_best(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> mantop_best = goodsDAO.getManTopBest(pdto);

		int manTopBestList = goodsDAO.getManTopBestList(); // 남성 상의 베스트 전체 개수

		int maxPage = (int) (Math.ceil((double) manTopBestList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("mantop", mantop_best);
		mav.addObject("type", 1); // 인기순 정렬 확인용
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_top");
		return mav;
	}

	// 남성 상의 등록일순
	public ModelAndView man_top_new(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> mantop_new = goodsDAO.getManTopNew(pdto);

		int manTopList = goodsDAO.getManTopList(); // 남성 상의 전체 개수

		int maxPage = (int) (Math.ceil((double) manTopList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("mantop", mantop_new);
		mav.addObject("type", 2);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_top");
		return mav;
	}

	// 남성 상의 낮은가격순
	public ModelAndView man_top_row(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> mantop_row = goodsDAO.getManTopRow(pdto);

		int manTopList = goodsDAO.getManTopList(); // 남성 상의 전체 개수

		int maxPage = (int) (Math.ceil((double) manTopList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("mantop", mantop_row);
		mav.addObject("type", 3);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_top");
		return mav;
	}

	// 남성 상의 높은가격순
	public ModelAndView man_top_high(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> mantop_high = goodsDAO.getManTopHigh(pdto);

		int manTopList = goodsDAO.getManTopList(); // 남성 상의 전체 개수

		int maxPage = (int) (Math.ceil((double) manTopList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("mantop", mantop_high);
		mav.addObject("type", 4);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_top");
		return mav;
	}

	// 남성 하의 인기순
	public ModelAndView man_pants_best(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manpants_best = goodsDAO.getManPantsBest(pdto);

		int manPantsBestList = goodsDAO.getManPantsBestList(); // 남성 하의 인기순 전체개수

		int maxPage = (int) (Math.ceil((double) manPantsBestList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manpants", manpants_best);
		mav.addObject("type", 1);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_pants");
		return mav;
	}

	// 남성 하의 등록일순
	public ModelAndView man_pants_new(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manpants_new = goodsDAO.getManPantsNew(pdto);

		int manPantsList = goodsDAO.getManPantsList();

		int maxPage = (int) (Math.ceil((double) manPantsList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manpants", manpants_new);
		mav.addObject("type", 2);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_pants");
		return mav;
	}

	// 남성 하의 낮은가격순
	public ModelAndView man_pants_row(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manpants_row = goodsDAO.getManPantsRow(pdto);

		int manPantsList = goodsDAO.getManPantsList();

		int maxPage = (int) (Math.ceil((double) manPantsList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manpants", manpants_row);
		mav.addObject("type", 3);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_pants");
		return mav;
	}

	// 남성 하의 높은가격순
	public ModelAndView man_pants_high(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manpants_high = goodsDAO.getManPantsHigh(pdto);

		int manPantsList = goodsDAO.getManPantsList();

		int maxPage = (int) (Math.ceil((double) manPantsList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manpants", manpants_high);
		mav.addObject("type", 4);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_pants");
		return mav;
	}

	// 남성 신발 인기순
	public ModelAndView man_shoes_best(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manshoes_best = goodsDAO.getManShoesBest(pdto);

		int manShoesBestList = goodsDAO.getManShoesBestList(); // 남성 신발 인기순 전체 개수

		int maxPage = (int) (Math.ceil((double) manShoesBestList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manshoes", manshoes_best);
		mav.addObject("type", 1);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_shoes");
		return mav;
	}

	// 남성 신발 등록일순
	public ModelAndView man_shoes_new(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manshoes_new = goodsDAO.getManShoesNew(pdto);

		int manShoesList = goodsDAO.getManShoesList(); // 남성 신발 전체 개수

		int maxPage = (int) (Math.ceil((double) manShoesList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manshoes", manshoes_new);
		mav.addObject("type", 2);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_shoes");
		return mav;
	}

	// 남성 신발 낮은가격순
	public ModelAndView man_shoes_row(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manshoes_row = goodsDAO.getManShoesRow(pdto);

		int manShoesList = goodsDAO.getManShoesList(); // 남성 신발 전체 개수

		int maxPage = (int) (Math.ceil((double) manShoesList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manshoes", manshoes_row);
		mav.addObject("type", 3);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_shoes");
		return mav;
	}

	// 남성 신발 높은가격순
	public ModelAndView man_shoes_high(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manshoes_high = goodsDAO.getManShoesHigh(pdto);

		int manShoesList = goodsDAO.getManShoesList(); // 남성 신발 전체 개수

		int maxPage = (int) (Math.ceil((double) manShoesList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manshoes", manshoes_high);
		mav.addObject("type", 4);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_shoes");
		return mav;
	}

	// 남성 아우터 인기순
	public ModelAndView man_outer_best(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manouter_best = goodsDAO.getManOuterBest(pdto);

		int manOuterBestList = goodsDAO.getManOuterBestList(); // 남성 아우터 인기순 전체 개수

		int maxPage = (int) (Math.ceil((double) manOuterBestList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manouter", manouter_best);
		mav.addObject("type", 1);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_outer");
		return mav;
	}

	// 남성 아우터 등록일순
	public ModelAndView man_outer_new(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manouter_new = goodsDAO.getManOuterNew(pdto);

		int manOuterList = goodsDAO.getManOuterList(); // 남성 상의 전체 개수

		int maxPage = (int) (Math.ceil((double) manOuterList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manouter", manouter_new);
		mav.addObject("type", 2);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_outer");
		return mav;
	}

	// 남성 아우터 낮은가격순
	public ModelAndView man_outer_row(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manouter_row = goodsDAO.getManOuterRow(pdto);

		int manOuterList = goodsDAO.getManOuterList(); // 남성 상의 전체 개수

		int maxPage = (int) (Math.ceil((double) manOuterList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manouter", manouter_row);
		mav.addObject("type", 3);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_outer");
		return mav;
	}

	// 남성 아우터 높은가격순
	public ModelAndView man_outer_high(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> manouter_high = goodsDAO.getManOuterHigh(pdto);

		int manOuterList = goodsDAO.getManOuterList(); // 남성 상의 전체 개수

		int maxPage = (int) (Math.ceil((double) manOuterList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("manouter", manouter_high);
		mav.addObject("type", 4);
		mav.addObject("page", pdto);
		mav.setViewName("goods/man/man_outer");
		return mav;
	}

	/***********************************
	 * 남성 상품 정렬 끝
	 **********************************/

	/***********************************
	 * 여성 상품 리스트
	 **********************************/

	// 여성 상의 페이지
	public ModelAndView woman_top(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		// GOODSDTO 에 업체이름 추가 **********************
		ArrayList<GoodsDTO> womantop = goodsDAO.getWomanTop(pdto);

		int womanTopList = goodsDAO.getWomanTopList(); // 남성 상의 전체 개수

		int maxPage = (int) (Math.ceil((double) womanTopList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womantop", womantop);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_top");
		return mav;
	}

	// 여성 하의 페이지
	public ModelAndView woman_pants(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		// GOODSDTO 에 업체이름 추가 **********************
		ArrayList<GoodsDTO> womanpants = goodsDAO.getWomanPants(pdto);

		int womanPantsList = goodsDAO.getWomanPantsList();

		int maxPage = (int) (Math.ceil((double) womanPantsList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanpants", womanpants);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_pants");
		return mav;
	}

	// 여성 신발 페이지
	public ModelAndView woman_shoes(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		// GOODSDTO 에 업체이름 추가 **********************
		ArrayList<GoodsDTO> womanshoes = goodsDAO.getWomanShoes(pdto);

		int womanShoesList = goodsDAO.getWomanShoesList();

		int maxPage = (int) (Math.ceil((double) womanShoesList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanshoes", womanshoes);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_shoes");
		return mav;
	}

	// 여성 아우터 페이지
	public ModelAndView woman_outer(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		// GOODSDTO 에 업체이름 추가 **********************
		ArrayList<GoodsDTO> womanouter = goodsDAO.getWomanOuter(pdto);

		int womanOuterList = goodsDAO.getWomanOuterList();

		int maxPage = (int) (Math.ceil((double) womanOuterList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanouter", womanouter);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_outer");
		return mav;
	}

	/***********************************
	 * 여성 상품 리스트 끝
	 **********************************/

	/***********************************
	 * 여성 상품 정렬
	 **********************************/

	// 여성 상의 인기순
	public ModelAndView woman_top_best(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> womantop_best = goodsDAO.getWomanTopBest(pdto);

		int womanTopBestList = goodsDAO.getWomanTopBestList(); // 남성 상의 전체 개수

		int maxPage = (int) (Math.ceil((double) womanTopBestList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womantop", womantop_best);
		mav.addObject("type", 1);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_top");
		return mav;
	}

	// 여성 상의 등록일순
	public ModelAndView woman_top_new(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> womantop_new = goodsDAO.getWomanTopNew(pdto);

		int womanTopList = goodsDAO.getWomanTopList();

		int maxPage = (int) (Math.ceil((double) womanTopList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womantop", womantop_new);
		mav.addObject("type", 2);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_top");
		return mav;
	}

	// 여성 상의 낮은가격순
	public ModelAndView woman_top_row(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> womantop_row = goodsDAO.getWomanTopRow(pdto);

		int womanTopList = goodsDAO.getWomanTopList();

		int maxPage = (int) (Math.ceil((double) womanTopList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womantop", womantop_row);
		mav.addObject("type", 3);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_top");
		return mav;
	}

	// 여성 상의 높은가격순
	public ModelAndView woman_top_high(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> womantop_high = goodsDAO.getWomanTopHigh(pdto);

		int womanTopList = goodsDAO.getWomanTopList();

		int maxPage = (int) (Math.ceil((double) womanTopList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womantop", womantop_high);
		mav.addObject("type", 4);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_top");
		return mav;
	}

	// 여성 하의 인기순
	public ModelAndView woman_pants_best(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		// GOODSDTO 에 업체이름 추가 **********************
		ArrayList<GoodsDTO> womanpants_best = goodsDAO.getWomanPantsBest(pdto);

		int womanPantsBestList = goodsDAO.getWomanPantsBestList(); // 여성 하의 인기순 전체 개수

		int maxPage = (int) (Math.ceil((double) womanPantsBestList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanpants", womanpants_best);
		mav.addObject("type", 1);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_pants");
		return mav;
	}

	// 여성 하의 등록일순
	public ModelAndView woman_pants_new(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		// GOODSDTO 에 업체이름 추가 **********************
		ArrayList<GoodsDTO> womanpants_new = goodsDAO.getWomanPantsNew(pdto);

		int womanPantsList = goodsDAO.getWomanPantsList();

		int maxPage = (int) (Math.ceil((double) womanPantsList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanpants", womanpants_new);
		mav.addObject("type", 2);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_pants");
		return mav;
	}

	// 여성 하의 낮은가격순
	public ModelAndView woman_pants_row(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		// GOODSDTO 에 업체이름 추가 **********************
		ArrayList<GoodsDTO> womanpants_row = goodsDAO.getWomanPantsRow(pdto);

		int womanPantsList = goodsDAO.getWomanPantsList();

		int maxPage = (int) (Math.ceil((double) womanPantsList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanpants", womanpants_row);
		mav.addObject("type", 3);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_pants");
		return mav;
	}

	// 여성 하의 높은가격순
	public ModelAndView woman_pants_high(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		// GOODSDTO 에 업체이름 추가 **********************
		ArrayList<GoodsDTO> womanpants_high = goodsDAO.getWomanPantsHigh(pdto);

		int womanPantsList = goodsDAO.getWomanPantsList();

		int maxPage = (int) (Math.ceil((double) womanPantsList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanpants", womanpants_high);
		mav.addObject("type", 4);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_pants");
		return mav;
	}

	// 여성 신발 인기순
	public ModelAndView woman_shoes_best(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> womanshoes_best = goodsDAO.getWomanShoesBest(pdto);

		int womanShoesBestList = goodsDAO.getWomanShoesBestList();

		int maxPage = (int) (Math.ceil((double) womanShoesBestList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanshoes", womanshoes_best);
		mav.addObject("type", 1);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_shoes");
		return mav;
	}

	// 여성 신발 등록일순
	public ModelAndView woman_shoes_new(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> womanshoes_new = goodsDAO.getWomanShoesNew(pdto);

		int womanShoesList = goodsDAO.getWomanShoesList();

		int maxPage = (int) (Math.ceil((double) womanShoesList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanshoes", womanshoes_new);
		mav.addObject("type", 2);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_shoes");
		return mav;
	}

	// 여성 신발 낮은가격순
	public ModelAndView woman_shoes_row(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> womanshoes_row = goodsDAO.getWomanShoesRow(pdto);

		int womanShoesList = goodsDAO.getWomanShoesList();

		int maxPage = (int) (Math.ceil((double) womanShoesList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanshoes", womanshoes_row);
		mav.addObject("type", 3);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_shoes");
		return mav;
	}

	// 여성 신발 높은가격순
	public ModelAndView woman_shoes_high(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> womanshoes_high = goodsDAO.getWomanShoesHigh(pdto);

		int womanShoesList = goodsDAO.getWomanShoesList();

		int maxPage = (int) (Math.ceil((double) womanShoesList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanshoes", womanshoes_high);
		mav.addObject("type", 4);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_shoes");
		return mav;
	}

	// 여성 아우터 인기순
	public ModelAndView woman_outer_best(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> womanouter_best = goodsDAO.getWomanOuterBest(pdto);

		int womanOuterBestList = goodsDAO.getWomanOuterBestList();

		int maxPage = (int) (Math.ceil((double) womanOuterBestList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanouter", womanouter_best);
		mav.addObject("type", 1);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_outer");
		return mav;
	}

	// 여성 아우터 등록일순
	public ModelAndView woman_outer_new(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> womanouter_new = goodsDAO.getWomanOuterNew(pdto);

		int womanOuterList = goodsDAO.getWomanShoesList();

		int maxPage = (int) (Math.ceil((double) womanOuterList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanouter", womanouter_new);
		mav.addObject("type", 2);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_outer");
		return mav;
	}

	// 여성 아우터 낮은가격순
	public ModelAndView woman_outer_row(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> womanouter_row = goodsDAO.getWomanOuterRow(pdto);

		int womanOuterList = goodsDAO.getWomanShoesList();

		int maxPage = (int) (Math.ceil((double) womanOuterList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanouter", womanouter_row);
		mav.addObject("type", 3);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_outer");
		return mav;
	}

	// 여성 아우터 높은가격순
	public ModelAndView woman_outer_high(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> womanouter_high = goodsDAO.getWomanOuterNew(pdto);

		int womanOuterList = goodsDAO.getWomanShoesList();

		int maxPage = (int) (Math.ceil((double) womanOuterList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("womanouter", womanouter_high);
		mav.addObject("type", 4);
		mav.addObject("page", pdto);
		mav.setViewName("goods/woman/woman_outer");
		return mav;
	}

	/***********************************
	 * 여성 상품 정렬 끝
	 **********************************/

	/***********************************
	 * New,Best 상품 페이지
	 **********************************/

	// NEW 상품 페이지 (현재시간 기준 일주일전, 최대 20개까지)
	public ModelAndView goods_new() {
		ModelAndView mav = new ModelAndView();

		ArrayList<GoodsDTO> goodsnew = goodsDAO.getGoodsNew();

		mav.addObject("goodsnew", goodsnew);
		mav.setViewName("goods/new");
		return mav;
	}

	// BEST 상품 페이지
	public ModelAndView goods_best() {
		ModelAndView mav = new ModelAndView();

		ArrayList<GoodsDTO> goodsbest = goodsDAO.getGoodsBest();

		mav.addObject("goodsbest", goodsbest);
		mav.setViewName("goods/best");
		return mav;
	}

	/***********************************
	 * New,Best 상품 페이지 끝
	 **********************************/

	/***********************************
	 * 브랜드관 상품 페이지
	 **********************************/

	// 무신사 스탠다드 상품 페이지
	public ModelAndView musinsa(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> musinsa = goodsDAO.getMusinsa(pdto);

		int musinsaList = goodsDAO.getMusinsaList();

		int maxPage = (int) (Math.ceil((double) musinsaList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("musinsa", musinsa);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/musinsa");
		return mav;
	}

	// 나이키 상품 페이지
	public ModelAndView nike(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> nike = goodsDAO.getNike(pdto);

		int nikeList = goodsDAO.getNikeList();

		int maxPage = (int) (Math.ceil((double) nikeList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("nike", nike);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/nike");
		return mav;
	}

	// 아디다스 상품 페이지
	public ModelAndView adidas(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> adidas = goodsDAO.getAdidas(pdto);

		int adidasList = goodsDAO.getAdidasList();

		int maxPage = (int) (Math.ceil((double) adidasList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("adidas", adidas);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/adidas");
		return mav;
	}

	// 자라 상품 페이지
	public ModelAndView zara(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> zara = goodsDAO.getZara(pdto);

		int zaraList = goodsDAO.getZaraList();

		int maxPage = (int) (Math.ceil((double) zaraList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("zara", zara);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/zara");
		return mav;
	}

	// 지오다노 상품 페이지
	public ModelAndView giordano(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> giordano = goodsDAO.getGiordano(pdto);

		int giordanoList = goodsDAO.getGiordanoList();

		int maxPage = (int) (Math.ceil((double) giordanoList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("giordano", giordano);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/giordano");
		return mav;
	}

	/***********************************
	 * 브랜드관 상품 페이지 끝
	 **********************************/

	/***********************************
	 * 브랜드관 상품 정렬 페이지
	 **********************************/

	// 무신사 스탠다드 베스트
	public ModelAndView musinsa_best(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> musinsa_best = goodsDAO.getMusinsaBest(pdto);

		int musinsaBestList = goodsDAO.getMusinsaBestList();

		int maxPage = (int) (Math.ceil((double) musinsaBestList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("musinsa", musinsa_best);
		mav.addObject("type", 1);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/musinsa");
		return mav;
	}

	// 무신사 스탠다드 최신순
	public ModelAndView musinsa_new(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> musinsa_new = goodsDAO.getMusinsaNew(pdto);

		int musinsaNewList = goodsDAO.getMusinsaNewList();

		int maxPage = (int) (Math.ceil((double) musinsaNewList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("musinsa", musinsa_new);
		mav.addObject("type", 2);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/musinsa");
		return mav;
	}

	// 무신사 스탠다드 낮은가격순
	public ModelAndView musinsa_row(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> musinsa_row = goodsDAO.getMusinsaRow(pdto);

		int musinsaRowList = goodsDAO.getMusinsaRowList();

		int maxPage = (int) (Math.ceil((double) musinsaRowList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("musinsa", musinsa_row);
		mav.addObject("type", 3);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/musinsa");
		return mav;
	}

	// 무신사 스탠다드 높은가격순
	public ModelAndView musinsa_high(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> musinsa_high = goodsDAO.getMusinsaHigh(pdto);

		int musinsaHighList = goodsDAO.getMusinsaHighList();

		int maxPage = (int) (Math.ceil((double) musinsaHighList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("musinsa", musinsa_high);
		mav.addObject("type", 4);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/musinsa");
		return mav;
	}

	// 나이키 인기순
	public ModelAndView nike_best(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> nike_best = goodsDAO.getNikeBest(pdto);

		int nikeBestList = goodsDAO.getNikeBestList();

		int maxPage = (int) (Math.ceil((double) nikeBestList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("nike", nike_best);
		mav.addObject("type", 1);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/nike");
		return mav;
	}

	// 나이키 최신순
	public ModelAndView nike_new(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> nike_new = goodsDAO.getNikeNew(pdto);

		int nikeNewList = goodsDAO.getNikeNewList();

		int maxPage = (int) (Math.ceil((double) nikeNewList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("nike", nike_new);
		mav.addObject("type", 2);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/nike");
		return mav;
	}

	// 나이키 낮은가격순
	public ModelAndView nike_row(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> nike_row = goodsDAO.getNikeRow(pdto);

		int nikeRowList = goodsDAO.getNikeRowList();

		int maxPage = (int) (Math.ceil((double) nikeRowList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("nike", nike_row);
		mav.addObject("type", 3);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/nike");
		return mav;
	}

	// 나이키 높은 가격순
	public ModelAndView nike_high(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> nike_high = goodsDAO.getNikeHigh(pdto);

		int nikeHighList = goodsDAO.getNikeHighList();

		int maxPage = (int) (Math.ceil((double) nikeHighList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("nike", nike_high);
		mav.addObject("type", 4);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/nike");
		return mav;
	}

	// 아디다스 인기순
	public ModelAndView adidas_best(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> adidas_best = goodsDAO.getAdidasBest(pdto);

		int adidasBestList = goodsDAO.getAdidasBestList();

		int maxPage = (int) (Math.ceil((double) adidasBestList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("adidas", adidas_best);
		mav.addObject("type", 1);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/adidas");
		return mav;
	}

	// 아디다스 최신순
	public ModelAndView adidas_new(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> adidas_new = goodsDAO.getAdidasNew(pdto);

		int adidasNewList = goodsDAO.getAdidasNewList();

		int maxPage = (int) (Math.ceil((double) adidasNewList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("adidas", adidas_new);
		mav.addObject("type", 2);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/adidas");
		return mav;
	}

	// 아디다스 낮은가격순
	public ModelAndView adidas_row(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> adidas_row = goodsDAO.getAdidasRow(pdto);

		int adidasRowList = goodsDAO.getAdidasRowList();

		int maxPage = (int) (Math.ceil((double) adidasRowList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("adidas", adidas_row);
		mav.addObject("type", 3);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/adidas");
		return mav;
	}

	// 아디다스 높은 가격순
	public ModelAndView adidas_high(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> adidas_high = goodsDAO.getAdidasHigh(pdto);

		int adidasHighList = goodsDAO.getAdidasHighList();

		int maxPage = (int) (Math.ceil((double) adidasHighList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("adidas", adidas_high);
		mav.addObject("type", 4);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/adidas");
		return mav;
	}

	// 자라 인기순
	public ModelAndView zara_best(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> zara_best = goodsDAO.getZaraBest(pdto);

		int zaraBestList = goodsDAO.getZaraBestList();

		int maxPage = (int) (Math.ceil((double) zaraBestList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("zara", zara_best);
		mav.addObject("type", 1);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/zara");
		return mav;
	}

	// 자라 최신순
	public ModelAndView zara_new(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> zara_new = goodsDAO.getZaraNew(pdto);

		int zaraNewList = goodsDAO.getZaraNewList();

		int maxPage = (int) (Math.ceil((double) zaraNewList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("zara", zara_new);
		mav.addObject("type", 2);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/zara");
		return mav;
	}

	// 자라 낮은가격순
	public ModelAndView zara_row(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> zara_row = goodsDAO.getZaraRow(pdto);

		int zaraRowList = goodsDAO.getZaraRowList();

		int maxPage = (int) (Math.ceil((double) zaraRowList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("zara", zara_row);
		mav.addObject("type", 3);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/zara");
		return mav;
	}

	// 자라 높은 가격순
	public ModelAndView zara_high(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> zara_high = goodsDAO.getZaraHigh(pdto);

		int zaraHighList = goodsDAO.getZaraHighList();

		int maxPage = (int) (Math.ceil((double) zaraHighList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("zara", zara_high);
		mav.addObject("type", 4);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/zara");
		return mav;
	}

	// 지오다노 인기순
	public ModelAndView giordano_best(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> giordano_best = goodsDAO.getGiordanoBest(pdto);

		int giordanoBestList = goodsDAO.getGiordanoBestList();

		int maxPage = (int) (Math.ceil((double) giordanoBestList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("giordano", giordano_best);
		mav.addObject("type", 1);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/giordano");
		return mav;
	}

	// 지오다노 최신순
	public ModelAndView giordano_new(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> giordano_new = goodsDAO.getGiordanoNew(pdto);

		int giordanoNewList = goodsDAO.getGiordanoNewList();

		int maxPage = (int) (Math.ceil((double) giordanoNewList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("giordano", giordano_new);
		mav.addObject("type", 2);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/giordano");
		return mav;
	}

	// 지오다노 낮은가격순
	public ModelAndView giordano_row(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> giordano_row = goodsDAO.getGiordanoRow(pdto);

		int giordanoRowList = goodsDAO.getGiordanoRowList();

		int maxPage = (int) (Math.ceil((double) giordanoRowList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("giordano", giordano_row);
		mav.addObject("type", 3);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/giordano");
		return mav;
	}

	// 지오다노 높은 가격순
	public ModelAndView giordano_high(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 16; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> giordano_high = goodsDAO.getGiordanoHigh(pdto);

		int giordanoHighList = goodsDAO.getGiordanoHighList();

		int maxPage = (int) (Math.ceil((double) giordanoHighList / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("giordano", giordano_high);
		mav.addObject("type", 4);
		mav.addObject("page", pdto);
		mav.setViewName("goods/brand/giordano");
		return mav;
	}

	/***********************************
	 * 브랜드관 상품 정렬 페이지 끝
	 **********************************/

	/***********************************
	 * 메인화면용 Ajax 리스트
	 **********************************/

	// 메인화면 New 리스트 출력 Ajax
	public ArrayList<GoodsDTO> getNewList() {
		ArrayList<GoodsDTO> getNewList = goodsDAO.getNewList();
		return getNewList;
	}

	// 메인화면 Best 리스트 출력 Ajax
	public ArrayList<GoodsDTO> getBestList() {
		ArrayList<GoodsDTO> getBesList = goodsDAO.getBestList();
		return getBesList;
	}

	

	




	/***********************************
	 * 메인화면용 Ajax 리스트 끝
	 **********************************/

}

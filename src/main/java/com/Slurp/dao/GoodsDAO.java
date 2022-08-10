package com.Slurp.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.Slurp.dto.CartgoodsDTO;
import com.Slurp.dto.GoodsColorDTO;
import com.Slurp.dto.GoodsCountDTO;
import com.Slurp.dto.GoodsDTO;
import com.Slurp.dto.MembershipDTO;
import com.Slurp.dto.OrderlistDTO;
import com.Slurp.dto.PageDTO;
import com.Slurp.dto.ReplygoodsDTO;
import com.Slurp.dto.ReplyreportDTO;
import com.Slurp.dto.WishlistDTO;
import com.Slurp.dto.WishlistDTO2;

public interface GoodsDAO {
	
	/*************** 상품 등록 *****************/
	// 상품등록
	@Insert("INSERT INTO GOODS(GCODE, GNAME, GPRICE, GCATE, GGENDER, CCODE, GCONTENT, GIMG, GDTIMG1, GDTIMG2, GDTIMG3, GDTIMG4, GDATE) "
			+ "VALUES(#{gcode}, #{gname}, #{gprice}, #{gcate}, #{ggender}, #{ccode}, #{gcontent}, #{gimg}, #{gdtimg1}, #{gdtimg2}, #{gdtimg3}, #{gdtimg4}, SYSDATE)")
	int goodsRegist(GoodsDTO goodsDTO);
	// 상품재고 화면 이동
	@Select("SELECT * FROM GOODS WHERE GCODE = #{gcode}")
	GoodsDTO registerGoodsCountForm(String gcode);
	// 상품재고 화면 이동 (상품 등록 후 cid받아오기)
	@Select("SELECT CID FROM COMPANY WHERE CCODE = #{ccode}")
	String getCid(String ccode);

	//상품재고 중복등록 체크
	@Select("SELECT COUNT(*) FROM GOODSCOUNT WHERE GCODE = #{gcode} AND GCOLOR = #{gcolor} AND GSIZE = #{gsize}") 
	int getGoodsCount1(GoodsCountDTO goodsCountDTO);
	//상품재고 중복등록 데이터 가져오기
	@Select("SELECT * FROM GOODSCOUNT WHERE GCODE = #{gcode} AND GCOLOR = #{gcolor} AND GSIZE = #{gsize}")
	GoodsCountDTO getGoodsCount2(GoodsCountDTO goodsCountDTO); 
	//상품 컬러는 있고 사이즈는 없을 때 사이즈 등록
	@Insert("INSERT INTO GOODSCOUNT(CCODE, GCODE, GSTOCK, GSIZE, GCOLOR) VALUES(#{ccode}, #{gcode}, #{gstock}, #{gsize}, #{gcolor})")
	int goodsCountUpdate(GoodsCountDTO goodsCountDTO);

	//재고수량 재고관리 페이지로 보내기위해
	@Select("SELECT GCOLOR,GSIZE,GSTOCK FROM GOODSCOUNT WHERE GCODE = #{gcode}")
	ArrayList<GoodsCountDTO> getCountState(String gcode);
	 //컬러도 있고 사이즈도 있어서 수량만 업데이트하면 될 때
	 @Update("UPDATE GOODSCOUNT SET GSTOCK = GSTOCK + #{gstock} WHERE  GSIZE = #{gsize} AND GCOLOR = #{gcolor} AND GCODE = #{gcode}")
	 int goodsCountUpdate1(@Param("gcolor") String goodsColorCheck, @Param("gstock") int gstock, @Param("gsize") int goodsSizeCheck, @Param("gcode") String gcode);
	 
		
	// 상품재고 등록
	@Insert("INSERT INTO GOODSCOUNT(CCODE, GCODE, GSTOCK, GSIZE, GCOLOR) VALUES(#{ccode}, #{gcode}, #{gstock}, #{gsize}, #{gcolor})")
	int goodsCountRegist(GoodsCountDTO goodsCountDTO);
	/* GOODSCOUNT 현재 재고 조회 ajax*/
	@Select("SELECT GCOLOR, GSIZE, GSTOCK FROM GOODSCOUNT WHERE GCODE = #{gcode} ORDER BY GCOLOR")
	ArrayList<GoodsCountDTO> goodsCountState(String gcode);
	//상품컬러 체크
	@Select("SELECT COUNT(*) FROM GOODSCOLOR WHERE GCODE = #{gcode} AND GCOLOR = #{gcolor}")
	int goodsColorCheck(GoodsCountDTO goodsCountDTO);
	// 상품컬러 등록
	@Insert("INSERT INTO GOODSCOLOR(GCODE, GCOLOR) VALUES(#{gcode}, #{gcolor})")
	int goodsColorRegist(GoodsCountDTO goodsCountDTO);
	
	//삭제 상품 이미지이름
	@Select("SELECT GIMG FROM GOODS WHERE GCODE = #{gcode}")
	String getGfile(String gcode);
	//삭제 상품 상세이미지이름1
	@Select("SELECT GDTIMG1 FROM GOODS WHERE GCODE = #{gcode}")
	String getGfile1(String gcode);
	//삭제 상품 상세이미지이름2
	@Select("SELECT GDTIMG2 FROM GOODS WHERE GCODE = #{gcode}")
	String getGfile2(String gcode);
	//삭제 상품 상세이미지이름3
	@Select("SELECT GDTIMG3 FROM GOODS WHERE GCODE = #{gcode}")
	String getGfile3(String gcode);
	//삭제 상품 상세이미지이름4
	@Select("SELECT GDTIMG4 FROM GOODS WHERE GCODE = #{gcode}")
	String getGfile4(String gcode);
	// 리뷰 가져오기
	@Select("Select * from replygoods where gcode = #{gcode}")
	ReplygoodsDTO getReplygoods1(String gcode);
	// 리뷰 삭제
	@Delete("Delete from replygoods where gcode = #{gcode}")
	int deleteReplygoods(String gcode);

	
	//상품 삭제
	@Delete("DELETE FROM GOODS WHERE GCODE = #{gcode}")
	int deleteGoodsCount(String gcode);
	@Delete("DELETE FROM GOODSCOUNT WHERE GCODE = #{gcode}")
	int deleteGoodsCount1(String gcode);
	@Delete("DELETE FROM GOODSCOLOR WHERE GCODE = #{gcode}")
	int deleteGoodsCount2(String gcode);

	/********************** 양종석 *************************/
	
	/*********** 상세보기페이지 **************/
	// 굿즈 기본정보 가져오기
	@Select("Select * from goods where gcode = #{gcode}")
	GoodsDTO getGoodsView(@Param("gcode") String gcode);

	// 굿즈 컬러 가져오기
	@Select("Select * from goodsColor where gcode = #{gcode}")
	ArrayList<GoodsColorDTO> getGoodsColorList(String gcode);

	// 굿즈 컬러별 재고량 가져오기
	@Select("Select * from goodsCount where gcode = #{gcode} and gcolor = #{gcolor} order by gsize")
	ArrayList<GoodsCountDTO> getGoodsSizeCountList(@Param("gcolor") String color, @Param("gcode") String gcode);

	/******************* 찜목록 ************************/
	// 찜목록 MAX값 찾기
	@Select("Select nvl(max(WL_CODE), 0) from wishlist")
	int getMaxWishListNum();

	// 찜목록 추가
	@Insert("Insert into WishList values(#{wl_code}, #{mid}, #{gcode})")
	int wishAdd(WishlistDTO wishlistDTO);

	// 찜목록 제거
	@Delete("Delete from WishList where mid = #{mid} and gcode = #{gcode}")
	int wishDelete(@Param("mid") String mid, @Param("gcode") String gcode);

	// 찜목록 확인
	@Select("Select wl_code from wishlist where mid = #{mid} and gcode = #{gcode}")
	String getWishSearch(@Param("mid") String mid, @Param("gcode") String gcode);
	
	/************************ 찜목록 & 장바구니 *******************************/
	// 찜목록에 이 제품이 있는지 확인
	@Select("Select * from wishlist where mid = #{mid} and gcode = #{gcode}")
	WishlistDTO getWishList(@Param("mid") String mid, @Param("gcode") String gcode);
	
	// 찜 목록 호출용
	@Select("Select WL.GCODE gcode, gs.gimg gimg, gs.gname gname, gs.gprice gprice from GOODS gs, WISHLIST wl where (gs.gcode = wl.gcode) AND wl.mid = #{mid}")
	ArrayList<WishlistDTO2> getWishList2(String mid);

	// 재품 재고 확인
	@Select("Select gstock from goodscount where gcode = #{gcode} and gcolor = #{gcolor} and gsize = #{gsize}")
	int getGoodsCount(@Param("gcode") String gcode, @Param("gcolor") String gcolor, @Param("gsize") int gsize);

	// 장바구니 MAX값 찾기
	@Select("Select nvl(max(CG_CODE), 0) from cartgoods")
	int getMaxCartNum();

	// 장바구니에 있는지 확인
	@Select("Select * from cartgoods where mid = #{mid} and gcode = #{gcode} and gcolor = #{gcolor} and gsize = #{gsize}")
	CartgoodsDTO getAlreadyCart(CartgoodsDTO cartgoodsDTO);

	// 장바구니에 insert
	@Insert("Insert into cartgoods values(#{cg_code}, #{mid}, #{gcode}, #{gstock}, #{gsize}, #{gcolor}, #{gprice})")
	int cartAddList(CartgoodsDTO cartgoodsDTO);

	// 장바구니 리스트 확인
	@Select("Select gs.gname, cg.cg_code, cg.mid, cg.gcode, cg.gstock, cg.gsize, cg.gcolor, cg.gprice, gs.gimg from cartgoods cg, goods gs where mid = #{mid} and gs.gcode = cg.gcode order by cg_code")
	ArrayList<CartgoodsDTO> getShoppingCart(String mid);

	// 장바구니 리스트에 물품사진 확인
	@Select("Select gs.gimg from goods gs, cartgoods cg where mid = #{mid} and gs.gcode = cg.gcode")
	String[] getGoodsImg(String mid);

	// 멤버쉽 조회
	@Select("Select * from membership where mid = #{mid}")
	MembershipDTO getMemberShip(String mid);

	// 장바구니 삭제
	@Delete("Delete from cartgoods where mid = #{mid} and cg_code = #{cg_code}")
	int cartDelete(@Param("mid") String mid, @Param("cg_code") int cg_code);

	// 장바구니 수량 변경
	@Update("Update Cartgoods set gstock = #{gstock} where cg_code = #{cg_code} and gcolor = #{gcolor} and gsize = #{gsize} and mid = #{mid}")
	int stockChange(CartgoodsDTO cartgoodsDTO);

	// 장바구니 가격 변경
	@Update("Update Cartgoods set gprice = #{gprice}*#{gstock} where cg_code = #{cg_code} and gcolor = #{gcolor} and gsize = #{gsize} and mid = #{mid}")
	int gpriceChange(CartgoodsDTO cartgoodsDTO);	
	
	/***************** 물품구매 **********************************/
	// 물품 한건 구매를 위한 이름 조회
	@Select("Select gname from goods where gcode = #{gcode}")
	String getGname(String gcode);

	// 물품 한건 구매를 위한 가격 조회
	@Select("Select gprice from goods where gcode = #{gcode}")
	int getGoodsPrice(String gcode);

	// MAX 구매번호 조회
	@Select("Select nvl(max(o_code), 0) from orderlist")
	int getMaxO_code();

	// 물품 구매 (Orderlist 추가)
	@Insert("Insert into orderlist(o_code, gcode, gname, gcolor, gsize, o_quantity, mid, mname, mgender, o_age, o_phone, o_add, o_request, o_gprice, o_date, o_state, ccode) values(#{o_code}, #{gcode}, #{gname}, #{gcolor}, #{gsize}, #{o_quantity}, #{mid}, #{mname}, #{mgender}, #{o_age}, #{o_phone}, #{o_add}, #{o_request}, #{o_gprice}, SYSDATE, '주문접수', #{ccode})")
	int goodsBuySuccess(OrderlistDTO orderlistDTO);

	// 물품 구매시 수량 마이너스
	@Update("Update goodscount set gstock = gstock-#{o_quantity} where gcode = #{gcode} and gcolor = #{gcolor} and gsize = #{gsize}")
	int goodsStockMinus(@Param("gcode") String gcode, @Param("gcolor") String gcolor, @Param("gsize") int gsize, @Param("o_quantity") int o_quantity);

	// 댓글번호
	@Select("Select nvl(max(r_num),0) from replygoods")
	int getMaxR_num();
	
	// 평가작성
	@Insert("Insert into replygoods(r_num, gcode, r_grade, r_reply, r_img1, r_img2, r_img3, mid) values(#{r_num}, #{gcode}, #{r_grade}, #{r_reply}, #{r_img1, jdbcType=VARCHAR}, #{r_img2, jdbcType=VARCHAR}, #{r_img3, jdbcType=VARCHAR}, #{mid})")
	int InsertReplygoods(ReplygoodsDTO replygoodsDTO);
	
	// 평가 불러오기
	@Select("Select rn, g.* from (select replygoods.*, ROW_NUMBER () over (order by r_num desc) as rn from replygoods where gcode = #{gcode}) g where rn BETWEEN #{startrow} AND #{endrow}")
	ArrayList<ReplygoodsDTO> getReplygoods(@Param("gcode") String gcode, @Param("startrow") int startRow, @Param("endrow") int endRow);
	
	// 굿즈 총 갯수
	@Select("Select count(*) from replygoods where gcode = #{gcode}")
	int getreplyGoodsCount(String gcode);
	
	// 굿즈 평점 평균
	@Select("select nvl(avg(R_GRADE), 0) from replygoods where gcode = #{gcode}")
	int getAvgRating(String gcode);
	
	// 이미 신고했는지 확인
	@Select("Select * from replyreport where mid = #{mid} and r_num = #{r_num}")
	ReplyreportDTO getReplyReport(@Param("r_num") int r_num, @Param("mid") String mid);
	
	// 신고테이블 최대번호
	@Select("Select nvl(max(rr_num), 0) from replyreport")
	int getMaxRRnum();

	// 신고
	@Insert("Insert into replyreport(rr_num, r_num, rr_content, mid) values(#{rr_num}, #{r_num}, #{rr_content}, #{mid})")
	int replyReport(ReplyreportDTO replyreportDTO);
	
	// 리뷰 갯수
	@Select("Select count(*) from replygoods where gcode = #{gcode} and r_grade = ${i}")
	int getRating(@Param("gcode") String gcode, @Param("i") int i);
	
	// 후기작성 완료
	@Update("Update orderlist set o_state = '후기작성 완료' where o_code = ${o_code}")
	int reviewComplete(int o_code);
	
	/********************* 이성빈 ****************************/
	
	/************ 남성 상품리스트 ************/
	// 남성 상의 페이지
	@Select("SELECT * FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN FROM GOODS G, COMPANY CP WHERE (G.CCODE = CP.CCODE) AND GCATE = 1 AND GGENDER = '남') WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManTop(PageDTO pdto);

	// 남성 상의 갯수
	@Select("SELECT COUNT(*) FROM GOODS WHERE GCATE = 1 AND GGENDER = '남'")
	int getManTopList();

	// 남성 하의 페이지
	@Select("SELECT * FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN FROM GOODS G, COMPANY CP WHERE (G.CCODE = CP.CCODE) AND GCATE = 2 AND GGENDER = '남') WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManPants(PageDTO pdto);

	// 남성 하의 개수
	@Select("SELECT COUNT(*) FROM GOODS WHERE GCATE = 2 AND GGENDER = '남'")
	int getManPantsList();

	// 남성 신발 페이지
	@Select("SELECT * FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN FROM GOODS G, COMPANY CP WHERE (G.CCODE = CP.CCODE) AND GCATE = 4 AND GGENDER = '남') WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManShoes(PageDTO pdto);

	// 남성 신발 개수
	@Select("SELECT COUNT(*) FROM GOODS WHERE GCATE = 4 AND GGENDER = '남'")
	int getManShoesList();

	// 남성 아우터 페이지
	@Select("SELECT * FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN FROM GOODS G, COMPANY CP WHERE (G.CCODE = CP.CCODE) AND GCATE = 3 AND GGENDER = '남') WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManOuter(PageDTO pdto);

	// 남성 아우터 개수
	@Select("SELECT COUNT(*) FROM GOODS WHERE GCATE = 3 AND GGENDER = '남'")
	int getManOuterList();

	/************ 남성 상품 정렬 ************/
	// 남성 상의 인기순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 1 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManTopBest(PageDTO pdto);

	// 남성 상의 인기순 전체개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 1 AND GGENDER = '남')\r\n")
	int getManTopBestList();

	// 남성 상의 등록일순
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 1 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManTopNew(PageDTO pdto);

	// 남성 상의 낮은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 1 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManTopRow(PageDTO pdto);

	// 남성 상의 높은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 1 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManTopHigh(PageDTO pdto);

	// 남성 하의 인기순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 2 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManPantsBest(PageDTO pdto);

	// 남성 하의 인기순 전체개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 2 AND GGENDER = '남')\r\n")
	int getManPantsBestList();

	// 남성 하의 등록일순
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 2 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManPantsNew(PageDTO pdto);

	// 남성 하의 낮은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 2 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManPantsRow(PageDTO pdto);

	// 남성 하의 높은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 2 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManPantsHigh(PageDTO pdto);

	// 남성 신발 인기순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 4 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManShoesBest(PageDTO pdto);

	// 남성 신발 인기순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 4 AND GGENDER = '남')\r\n")
	int getManShoesBestList();

	// 남성 신발 등록일순
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 4 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManShoesNew(PageDTO pdto);

	// 남성 신발 낮은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 4 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManShoesRow(PageDTO pdto);

	// 남성 신발 높은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 4 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManShoesHigh(PageDTO pdto);

	// 남성 아우터 인기순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 3 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManOuterBest(PageDTO pdto);

	// 남성 아우터 인기순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 3 AND GGENDER = '남')\r\n")
	int getManOuterBestList();

	// 남성 아우터 등록일순
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 3 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManOuterNew(PageDTO pdto);

	// 남성 아우터 낮은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 3 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManOuterRow(PageDTO pdto);

	// 남성 아우터 높은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 3 AND GGENDER = '남')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getManOuterHigh(PageDTO pdto);

	/************ 여성 상품리스트 ************/

	// 여성 상의 페이지
	@Select("SELECT * FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN FROM GOODS G, COMPANY CP WHERE (G.CCODE = CP.CCODE) AND GCATE = 1 AND GGENDER = '여') WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanTop(PageDTO pdto);

	// 여성 상의 개수
	@Select("SELECT COUNT(*) FROM GOODS WHERE GCATE = 1 AND GGENDER = '여'")
	int getWomanTopList();

	// 여성 하의 페이지
	@Select("SELECT * FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN FROM GOODS G, COMPANY CP WHERE (G.CCODE = CP.CCODE) AND GCATE = 2 AND GGENDER = '여') WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanPants(PageDTO pdto);

	// 여성 하의 개수
	@Select("SELECT COUNT(*) FROM GOODS WHERE GCATE = 2 AND GGENDER = '여'")
	int getWomanPantsList();

	// 여성 신발 페이지
	@Select("SELECT * FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN FROM GOODS G, COMPANY CP WHERE (G.CCODE = CP.CCODE) AND GCATE = 4 AND GGENDER = '여') WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanShoes(PageDTO pdto);

	// 여성 신발 개수
	@Select("SELECT COUNT(*) FROM GOODS WHERE GCATE = 4 AND GGENDER = '여'")
	int getWomanShoesList();

	// 여성 아우터 페이지
	@Select("SELECT * FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN FROM GOODS G, COMPANY CP WHERE (G.CCODE = CP.CCODE) AND GCATE = 3 AND GGENDER = '여') WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanOuter(PageDTO pdto);

	// 여성 아우터 개수
	@Select("SELECT COUNT(*) FROM GOODS WHERE GCATE = 3 AND GGENDER = '여'")
	int getWomanOuterList();

	/************ 여성 상품 정렬 ************/

	// 여성 상의 인기순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 1 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanTopBest(PageDTO pdto);

	// 여성 상의 인기순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 1 AND GGENDER = '여')\r\n")
	int getWomanTopBestList();

	// 여성 상의 등록일순
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 1 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanTopNew(PageDTO pdto);

	// 여성 상의 낮은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 1 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanTopRow(PageDTO pdto);

	// 여성 상의 높은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 1 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanTopHigh(PageDTO pdto);

	// 여성 하의 인기순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 2 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanPantsBest(PageDTO pdto);

	// 여성 하의 인기순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 2 AND GGENDER = '여')\r\n")
	int getWomanPantsBestList();

	// 여성 하의 등록일순
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 2 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanPantsNew(PageDTO pdto);

	// 여성 하의 낮은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 2 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanPantsRow(PageDTO pdto);

	// 여성 하의 높은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 2 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanPantsHigh(PageDTO pdto);

	// 여성 신발 인기순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 4 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanShoesBest(PageDTO pdto);

	// 여성 신발 인기순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 4 AND GGENDER = '여')\r\n")
	int getWomanShoesBestList();

	// 여성 신발 등록일순
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 4 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanShoesNew(PageDTO pdto);

	// 여성 신발 낮은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 4 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanShoesRow(PageDTO pdto);

	// 여성 신발 높은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 4 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanShoesHigh(PageDTO pdto);

	// 여성 아우터 인기순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 3 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanOuterBest(PageDTO pdto);

	// 여성 아우터 인기순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND GCATE = 3 AND GGENDER = '여')\r\n")
	int getWomanOuterBestList();

	// 여성 아우터 등록일순
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 3 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanOuterNew(PageDTO pdto);

	// 여성 아우터 낮은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 3 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanOuterRow(PageDTO pdto);

	// 여성 아우터 높은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND GCATE = 3 AND GGENDER = '여')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getWomanOuterHigh(PageDTO pdto);

	// *********************** NEW,BEST 상품 페이지 *************************
	// NEW 상품 페이지
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND G.GDATE > SYSDATE - 7) \r\n" + "WHERE RN BETWEEN 1 AND 30")
	ArrayList<GoodsDTO> getGoodsNew();

	// BEST 상품 페이지
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY TOP DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE))\r\n" + "WHERE RN BETWEEN 1 AND 30")
	ArrayList<GoodsDTO> getGoodsBest();

	// *********************** 브랜드관 상품 페이지 *************************
	// 무신사 상품 페이지
	@Select("SELECT * \r\n"
			+ "FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN \r\n"
			+ "    FROM GOODS G, COMPANY CP \r\n" + "    WHERE (G.CCODE = CP.CCODE) AND CNAME = 'MUSINSA') \r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getMusinsa(PageDTO pdto);

	// 무신사 상품 개수
	@Select("SELECT COUNT(*) \r\n" + "    FROM GOODS G, COMPANY CP \r\n"
			+ "    WHERE (G.CCODE = CP.CCODE) AND CNAME = 'MUSINSA'")
	int getMusinsaList();

	// 나이키 상품 페이지
	@Select("SELECT * \r\n"
			+ "FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN \r\n"
			+ "    FROM GOODS G, COMPANY CP \r\n" + "    WHERE (G.CCODE = CP.CCODE) AND CNAME = 'NIKE') \r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getNike(PageDTO pdto);

	// 나이키 상품 개수
	@Select("SELECT COUNT(*) \r\n" + "    FROM GOODS G, COMPANY CP \r\n"
			+ "    WHERE (G.CCODE = CP.CCODE) AND CNAME = 'NIKE'")
	int getNikeList();

	// 아디다스 상품 페이지
	@Select("SELECT * \r\n"
			+ "FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN \r\n"
			+ "    FROM GOODS G, COMPANY CP \r\n" + "    WHERE (G.CCODE = CP.CCODE) AND CNAME = 'ADIDAS') \r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getAdidas(PageDTO pdto);

	// 아디다스 상품 개수
	@Select("SELECT COUNT(*) \r\n" + "    FROM GOODS G, COMPANY CP \r\n"
			+ "    WHERE (G.CCODE = CP.CCODE) AND CNAME = 'ADIDAS'")
	int getAdidasList();

	// 자라 상품 페이지
	@Select("SELECT * \r\n"
			+ "FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN \r\n"
			+ "    FROM GOODS G, COMPANY CP \r\n" + "    WHERE (G.CCODE = CP.CCODE) AND CNAME = 'ZARA') \r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getZara(PageDTO pdto);

	// 자라 상품 개수
	@Select("SELECT COUNT(*) \r\n" + "    FROM GOODS G, COMPANY CP \r\n"
			+ "    WHERE (G.CCODE = CP.CCODE) AND CNAME = 'ZARA'")
	int getZaraList();

	// 지오다노 상품 페이지
	@Select("SELECT * \r\n"
			+ "FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN \r\n"
			+ "    FROM GOODS G, COMPANY CP \r\n" + "    WHERE (G.CCODE = CP.CCODE) AND CNAME = 'GIORDANO') \r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getGiordano(PageDTO pdto);

	// 지오다노 상품 개수
	@Select("SELECT COUNT(*) \r\n" + "    FROM GOODS G, COMPANY CP \r\n"
			+ "    WHERE (G.CCODE = CP.CCODE) AND CNAME = 'GIORDANO'")
	int getGiordanoList();

	// *********************** 브랜드관 상품 정렬 리스트 *************************

	// 무신사 스탠다드 인기순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND CNAME = 'MUSINSA')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getMusinsaBest(PageDTO pdto);

	// 무신사 스탠다드 인기순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND CNAME = 'MUSINSA')")
	int getMusinsaBestList();

	// 무신사 스탠다드 최신순
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'MUSINSA')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getMusinsaNew(PageDTO pdto);

	// 무신사 스탠다드 최신순 전체 개수
	@Select("SELECT COUNT(*)\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'MUSINSA')")
	int getMusinsaNewList();

	// 무신사 스탠다드 낮은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'MUSINSA')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getMusinsaRow(PageDTO pdto);

	// 무신사 스탠다드 낮은가격순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'MUSINSA')")
	int getMusinsaRowList();

	// 무신사 스탠다드 높은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'MUSINSA')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getMusinsaHigh(PageDTO pdto);

	// 무신사 스탠다드 높은가격순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'MUSINSA')")
	int getMusinsaHighList();

	// 나이키 인기순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND CNAME = 'NIKE')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getNikeBest(PageDTO pdto);

	// 나이키 인기순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND CNAME = 'NIKE')")
	int getNikeBestList();

	// 나이키 최신순
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'NIKE')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getNikeNew(PageDTO pdto);

	// 나이키 최신순 전체 개수
	@Select("SELECT COUNT(*)\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'NIKE')")
	int getNikeNewList();

	// 나이키 낮은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'NIKE')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getNikeRow(PageDTO pdto);

	// 나이키 낮은가격순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'NIKE')")
	int getNikeRowList();

	// 나이키 높은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'NIKE')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getNikeHigh(PageDTO pdto);

	// 나이키 높은가격순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'NIKE')")
	int getNikeHighList();

	// 아디다스 인기순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND CNAME = 'ADIDAS')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getAdidasBest(PageDTO pdto);

	// 아디다스 인기순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND CNAME = 'ADIDAS')")
	int getAdidasBestList();

	// 아디다스 최신순
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'ADIDAS')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getAdidasNew(PageDTO pdto);

	// 아디다스 최신순 전체 개수
	@Select("SELECT COUNT(*)\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'ADIDAS')")
	int getAdidasNewList();

	// 아디다스 낮은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'ADIDAS')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getAdidasRow(PageDTO pdto);

	// 아디다스 낮은가격순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'ADIDAS')")
	int getAdidasRowList();

	// 아디다스 높은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'ADIDAS')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getAdidasHigh(PageDTO pdto);

	// 아디다스 높은가격순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'ADIDAS')")
	int getAdidasHighList();

	// 자라 인기순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND CNAME = 'ZARA')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getZaraBest(PageDTO pdto);

	// 자라 인기순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND CNAME = 'ZARA')")
	int getZaraBestList();

	// 자라 최신순
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'ZARA')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getZaraNew(PageDTO pdto);

	// 자라 최신순 전체 개수
	@Select("SELECT COUNT(*)\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'ZARA')")
	int getZaraNewList();

	// 자라 낮은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'ZARA')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getZaraRow(PageDTO pdto);

	// 자라 낮은가격순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'ZARA')")
	int getZaraRowList();

	// 자라 높은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'ZARA')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getZaraHigh(PageDTO pdto);

	// 자라 높은가격순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'ZARA')")
	int getZaraHighList();

	// 지오다노 인기순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND CNAME = 'GIORDANO')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getGiordanoBest(PageDTO pdto);

	// 지오다노 인기순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROWNUM RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE ORDER BY TOP DESC) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE) AND CNAME = 'GIORDANO')")
	int getGiordanoBestList();

	// 지오다노 최신순
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'GIORDANO')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getGiordanoNew(PageDTO pdto);

	// 지오다노 최신순 전체 개수
	@Select("SELECT COUNT(*)\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'GIORDANO')")
	int getGiordanoNewList();

	// 지오다노 낮은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'GIORDANO')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getGiordanoRow(PageDTO pdto);

	// 지오다노 낮은가격순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'GIORDANO')")
	int getGiordanoRowList();

	// 지오다노 높은가격순
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'GIORDANO')\r\n"
			+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<GoodsDTO> getGiordanoHigh(PageDTO pdto);

	// 지오다노 높은가격순 전체 개수
	@Select("SELECT COUNT(*)\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY GPRICE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C \r\n" + "        WHERE (G.CCODE = C.CCODE) AND CNAME = 'GIORDANO')")
	int getGiordanoHighList();

	// *********************** 브랜드관 상품 정렬 리스트 끝 *************************

	// *********************** 메인화면 Ajax 리스트 *************************
	// 메인화면 New 리스트 Ajax
	@Select("SELECT *\r\n"
			+ "FROM (SELECT G.GCODE,G.GNAME,G.GPRICE,G.GCATE,G.GGENDER,G.CCODE,G.GCONTENT,G.GIMG,G.GDTIMG1,G.GDTIMG2,TO_CHAR(G.GDATE,'YYYY-MM-DD HH24:MI:SS'),CNAME,ROW_NUMBER() OVER(ORDER BY GDATE DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C\r\n" + "        WHERE (G.CCODE = C.CCODE) AND G.GDATE > SYSDATE - 7)\r\n"
			+ "WHERE RN BETWEEN 1 AND 8")
	ArrayList<GoodsDTO> getNewList();

	// 메인화면 Best 리스트 Ajax
	@Select("SELECT *\r\n" + "FROM (SELECT G.*,CNAME,ROW_NUMBER() OVER(ORDER BY TOP DESC) AS RN\r\n"
			+ "        FROM GOODS G, COMPANY C ,(SELECT GCODE,SUM(O_QUANTITY) AS TOP\r\n"
			+ "                                    FROM ORDERLIST O GROUP BY GCODE) O\r\n"
			+ "        WHERE (G.CCODE = C.CCODE) AND (G.GCODE = O.GCODE))\r\n" + "WHERE RN BETWEEN 1 AND 8")
	ArrayList<GoodsDTO> getBestList();

	
	
	
	
	


}

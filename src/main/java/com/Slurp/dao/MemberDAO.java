package com.Slurp.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.Slurp.dto.AdminReportDTO;
import com.Slurp.dto.CompanyDTO;
import com.Slurp.dto.GoodsDTO;
import com.Slurp.dto.MembersDTO;
import com.Slurp.dto.MembersInfoDTO;
import com.Slurp.dto.MembershipDTO;
import com.Slurp.dto.OrderlistDTO;
import com.Slurp.dto.PageDTO;
import com.Slurp.dto.ReplygoodsDTO;

public interface MemberDAO {
	
	/**************** 로그인 *******************/
	// 회원 로그인
	@Select("SELECT MID FROM MEMBERS WHERE MID = #{mid}")
	String memberLogin(MembersDTO mdto);
	// 암호화된 비밀번호 가져오기
	@Select("SELECT MPW FROM MEMBERS WHERE MID = #{mid}")
	String getMemberPw(@Param("mid")String mid);
	// 업체 로그인
	@Select("SELECT CID FROM COMPANY WHERE CID = #{cid}")
	String companyLogin(CompanyDTO cdto);
	// 암호화된 비밀번호 가져오기
	@Select("SELECT CPW FROM COMPANY WHERE CID = #{cid}")
	String getCompanyPw(CompanyDTO cdto);
	// 업체 로그인할때 세션에 ccheck 저장
	@Select("Select ccheck from company where cid = #{cid}")
	String companyCcheck(String cid);
	// 업체 로그인할때 세션에 ccode저장하기 위해
	@Select("SELECT CCODE FROM COMPANY WHERE CID = #{cid}")
	String getCcode1(CompanyDTO cdto);
	// 관리자 로그인
	@Select("SELECT AID FROM ADMIN WHERE AID = #{aid} AND APW = #{apw}")
	String adminLogin(@Param("aid") String aid, @Param("apw") String apw);
	// 업체승인 페이지
	@Select("SELECT * FROM (SELECT ROWNUM RN, COMPANY.* FROM COMPANY WHERE CCHECK = 0 ORDER BY CCODE) WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<CompanyDTO> getCompanyList(PageDTO pdto);
	// 승인필요한 업체의 전체 개수
	@Select("SELECT COUNT(*) FROM COMPANY WHERE CCHECK = 0")
	int getCompanyCheckListCount();
	// 업체승인 요청
	@Update("UPDATE COMPANY SET CCHECK = 1 WHERE CCODE = #{ccode}")
	int acceptResult(@Param("ccode") String ccode);
	// 업체승인 거절 요청
	@Delete("DELETE FROM COMPANY WHERE CCODE = #{ccode}")
	int refusalResult(@Param("ccode") String ccode);
	// 업체 상품관리 페이지
	@Select("SELECT * \r\n"
			+ "FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN \r\n"
			+ "    FROM GOODS G, COMPANY CP \r\n"
			+ "    WHERE (G.CCODE = CP.CCODE) AND CID = #{cid}) \r\n"
			+ "WHERE RN BETWEEN #{startRow} AND #{endRow}")
	ArrayList<GoodsDTO> getCompanyGoodsList(@Param("startRow")int startRow,@Param("endRow")int endRow,@Param("cid")String cid);
	// 업체 상품 전체개수
	@Select("SELECT COUNT(*) \r\n"
			+ "FROM (SELECT GCODE,GNAME,GPRICE,GCATE,GGENDER,G.CCODE,GCONTENT,GIMG,GDTIMG1,GDTIMG2,CP.CNAME,ROWNUM RN \r\n"
			+ "    FROM GOODS G, COMPANY CP \r\n"
			+ "    WHERE (G.CCODE = CP.CCODE) AND CID = #{cid})")
	int getCompanyGoodsCount(@Param("cid")String cid);
	// 관리자 신고 페이지
	@Select("SELECT * FROM (SELECT ROWNUM RN, REPLYREPORT.* FROM REPLYREPORT ORDER BY RR_NUM) WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<AdminReportDTO> getReportList(PageDTO pdto);
	// 관리자 신고 전체 개수
	@Select("SELECT COUNT(*) FROM REPLYREPORT")
	int getReportCount();
	// 신고된 평가내용 출력 Ajax
	@Select("SELECT REPLYGOODS.*,RR_NUM\r\n"
			+ "FROM REPLYGOODS, REPLYREPORT\r\n"
			+ "WHERE (REPLYGOODS.R_NUM = REPLYREPORT.R_NUM) AND RR_NUM = #{rr_num}")
	ReplygoodsDTO goodsReply(@Param("rr_num")int rr_num);
	// 신고내역 승인 (신고 삭제)
	@Delete("DELETE FROM REPLYREPORT WHERE R_NUM = #{r_num}")
	int deleteReport(@Param("r_num")int r_num);
	// 승인 후 평가 이미지
	@Select("SELECT R_IMG1,R_IMG2,R_IMG3 FROM REPLYGOODS WHERE R_NUM = #{r_num}")
	ReplygoodsDTO getReplyImg(@Param("r_num")int r_num);
	// 평가 삭제
	@Delete("DELETE FROM REPLYGOODS WHERE R_NUM = #{r_num}")
	int deleteReplyGoods(@Param("r_num")int r_num);
	// 삭제된 평가의 모든 신고글 삭제
	@Delete("DELETE FROM REPLYREPORT WHERE R_NUM = #{r_num}")
	int deleteReplyReport(@Param("r_num")int r_num);
	
	/************ 회원가입 ************/
	// 회원가입
	@Insert("Insert into members values(#{mid}, #{mpw}, #{mname}, #{mgender}, #{mbirth}, #{mphone}, #{madd}, #{memail}, 0)")
	int memberJoin(MembersDTO membersDTO);

	// 멤버쉽등록
	@Insert("Insert into membership values(#{mid}, 0, 0, '일반회원')")
	int membershipJoin(String mid);
	
	// 업체코드 최대값
	@Select("Select nvl(max(ccode), 0) from company")
	String maxCcode();

	// 업체회원가입
	@Insert("Insert into company(ccode, cid, cpw, cname, ctel, caddr, ccheck) values(#{ccode}, #{cid}, #{cpw}, #{cname}, #{ctel}, #{caddr}, 0)")
	int companyJoin(CompanyDTO companyDTO);

	/****** 아이디 중복체크 ******/
	// 일반회원 아이디 중복체크
	@Select("Select mid from members where mid = #{mid}")
	String midCheck(String id);
	
	// 업체회원 아이디 중복체크
	@Select("Select cid from company where cid = #{cid}")
	String cidCheck(String cid);
	/************************/
	
	// 카카오 회원 체크
	@Select("SELECT MID FROM MEMBERS WHERE MID = #{userId}")
	String kakaoCheck(String userId);
	
	// 카카오 회원 가입
	@Insert("INSERT INTO MEMBERS(MID,MNAME,MCHECK) VALUES(#{mid},#{nickname},1)")
	int kakaoRegist(@Param("mid") String mid,@Param("nickname") String nickname);
	
	/************************************* 양종석 추가 *****************************************/
	
	// 회원정보 불러오기
	@Select("Select * from members where mid = #{mid}")
	MembersDTO getMemberDTO(String mid);
	
	// 회원 멤버쉽 불러오기
	@Select("Select * from membership where mid = #{mid}")
	MembershipDTO getMembershipDTO(String mid);

	// 마일리지 적립, 구매금액 추가
	@Update("Update membership set m_mileage = m_mileage + #{mileage}, m_hispay = m_hispay + #{sum} where mid = #{mid}")
	int mileageAndHispay(@Param("mid") String mid, @Param("sum") int sum, @Param("mileage") double mileage);
	
	// 회원 등급 가져오기
	@Select("Select m_rating from membership where mid = #{mid}")
	String getM_rating(String mid);
	
	// 장바구니 비우기
	@Delete("Delete from cartgoods where mid = #{mid}")
	int cartAllDelete(String mid);
	
	/**************** 회원 등급 변경 *******************/
	@Update("Update membership set m_rating = 'VIP' where mid = #{mid}")
	int upM_ratingVIP(String mid);
	@Update("Update membership set m_rating = 'Gold' where mid = #{mid}")
	int upM_ratingGold(String mid);
	@Update("Update membership set m_rating = 'Silver' where mid = #{mid}")
	int upM_ratingSilver(String mid);
	@Update("Update membership set m_rating = 'Bronze' where mid = #{mid}")
	int upM_ratingBronze(String mid);
	/***********************************************/
	// 업체코드
	@Select("select cp.ccode from company cp, goods gs where (cp.ccode = gs.ccode) and gs.gcode = #{gcode}")
	String getCcode(String gcode);
	
	// 업체코드2
	@Select("select ccode from company where cid = #{cid}")
	String getCcode2(String cid);
	
	// 배송대기중인 orderlist
	@Select("Select * from orderlist where o_state = '주문접수' and ccode = #{ccode}")
	ArrayList<OrderlistDTO> getOrderlist(String ccode);
	
	// 배송대기중인 orderlist 숫자
	@Select("Select count(*) from orderlist where o_state = '주문접수' and ccode = #{ccode}")
	int getCompanyCheckListCount2(String ccode);
	
	// 주문 접수 확인
	@Update("Update orderlist set o_state = '배송시작' where o_code = #{o_code}")
	int deliveryCheck(int o_code);
	
	/**************** 추가 *********************/
	
	// 마일리지 감소
	@Update("Update membership set m_mileage = m_mileage - #{m_mileage} where mid = #{mid}")
	int mileageMinus(@Param("mid") String mid, @Param("m_mileage") int m_mileage);
	
	// 회원정보 보기
		@Select("SELECT M.MID, M.MPW, M.MNAME, M.MGENDER, M.MBIRTH, M.MPHONE, M.MADD, M.MEMAIL, MSH.M_MILEAGE, MSH.M_HISPAY, MSH.M_RATING "
				+ "FROM MEMBERS M,MEMBERSHIP MSH " + "WHERE (M.MID = MSH.MID) AND M.MID = #{loginId}")
		MembersInfoDTO showMemberInfo(String loginId);

		//회원정보 수정폼으로 이동
		@Select("SELECT MID, MPW, MNAME, MGENDER, TO_CHAR(MBIRTH,'YYYY-MM-DD') AS MBIRTH, MPHONE, MADD, MEMAIL FROM MEMBERS WHERE MID = #{loginId}")
		MembersInfoDTO myInfoModifyForm(String loginId);
				
		//수정폼 - 현재 비밀번호 일치하는지 확인
		@Select("SELECT COUNT(*) FROM MEMBERS WHERE MID = #{mid} AND MPW = #{encPw}")
		int pwCheck(@Param("mid") String mid, @Param("encPw") String encPw);
		//수정폼 - 신규 비밀번호 업데이트
		@Update("UPDATE MEMBERS SET MPW = #{mpw} WHERE MID = #{mid}")
		int modifyPw(@Param("mid") String mid, @Param("mpw") String mpw);
		
		//수정폼 - 신규 이메일 업데이트
		@Update("UPDATE MEMBERS SET MEMAIL = #{memail} WHERE MID = #{mid}")
		int modifyEmail(@Param("mid") String mid, @Param("memail") String memail);
		
		//수정폼 - 신규 전화번호 업데이트
		@Update("UPDATE MEMBERS SET MPHONE = #{mphone} WHERE MID = #{mid}")
		int modifyPhone(@Param("mid") String mid, @Param("mphone") String mphone);
		
		// 수정폼 - 신규 주소 업데이트
		@Update("UPDATE MEMBERS SET MADD = #{madd} WHERE MID = #{mid}")
		int modifyAddress(@Param("mid") String mid, @Param("madd") String madd);
		
		// 구매목록 보기 
		@Select("SELECT OL.O_CODE, OL.GCODE, OL.O_QUANTITY, OL.MID, OL.MNAME, OL.MGENDER, OL.O_AGE, OL.O_PHONE, OL.O_ADD, OL.O_REQUEST, OL.O_GPRICE, TO_CHAR(OL.O_DATE,'YYYY-MM-DD') AS O_DATE, G.GNAME, G.GIMG, OL.O_STATE "
				+ "FROM ORDERLIST OL, GOODS G " + "WHERE (OL.GCODE = G.GCODE) "
				+ "AND MID = #{loginId} ORDER BY OL.O_CODE DESC")
		ArrayList<OrderlistDTO> showOrderList(String loginId);
		
		// 구매한 물품의 사진
		@Select("Select gimg from goods where gcode = #{gcode}")
		String getGimgList(String gcode);
		
		// 구매확정
		@Update("Update orderlist set o_state = '구매완료' where mid = #{mid} and o_code = #{o_code}")
		int buyConfirmed(@Param("mid") String mid, @Param("o_code") int o_code);
		
		// 구매 자세히보기
		// 배송대기중인 orderlist 숫자
		@Select("Select count(*) from orderlist where mid = #{loginId}")
		int getMemberOrderlist(String loginId);
		
		// 구매목록 보기 
		@Select("Select rn, ol.* \r\n"
				+ "from (select OL.O_CODE, OL.GCODE, OL.O_QUANTITY, OL.MID, OL.MNAME, OL.MGENDER, OL.O_AGE, OL.O_PHONE, OL.O_ADD, OL.O_REQUEST, OL.O_GPRICE, TO_CHAR(OL.O_DATE,'YYYY-MM-DD') AS O_DATE, OL.GNAME, G.GIMG, OL.O_STATE, OL.GCOLOR, OL.GSIZE, ROW_NUMBER () over (order by ol.o_code desc) as rn from orderlist ol, goods g\r\n"
				+ "where mid = #{loginId} and (OL.GCODE = G.GCODE)) ol where rn BETWEEN #{startRow} AND #{endRow}")
		ArrayList<OrderlistDTO> showOrderList2(@Param("loginId") String loginId, @Param("startRow")int startRow, @Param("endRow")int endRow);
		
		/************** 회원탈퇴 ****************/
		// 멤버쉽 삭제
		@Delete("Delete from membership where mid = #{mid}")
		void deleteMembership(String mid);
		// 멤버삭제
		@Delete("Delete from members where mid = #{mid}")
		void deleteMember(String mid);
		// 장바구니 삭제
		@Delete("Delete from cartgoods where mid = #{mid}")
		void deleteCart(String mid);
		// 찜목록 삭제
		@Delete("Delete from wishlist where mid = #{mid}")
		void deleteWist(String mid);

		// 찜, 장바구니 삭제 (물품삭제시)
		@Delete("Delete from wishlist where gcode = #{gcode}")
		void deleteWishList(String gcode);
		@Delete("Delete from cartgoods where gcode = #{gcode}")
		void deleteCartList(String gcode);

		
}

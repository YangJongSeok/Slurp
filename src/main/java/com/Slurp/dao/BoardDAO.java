package com.Slurp.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.Slurp.dto.EventDTO;
import com.Slurp.dto.PageDTO;
import com.Slurp.dto.QnaDTO;
import com.Slurp.dto.QnaReplyDTO;

public interface BoardDAO {

	//이벤트 글 번호
	@Select("SELECT NVL(MAX(E_NUM),0) FROM EVENT")
	int getMaxNum(EventDTO eventDTO);
	//이벤트 글 등록
	@Insert("INSERT INTO EVENT(E_NUM, ETITLE, ECONTENT, AID, EIMG) VALUES(#{e_num}, #{etitle}, #{econtent}, #{aid}, #{eimg})")
	int eventRegist(EventDTO eventDTO);
	//이벤트 게시판 목록
	@Select("SELECT * FROM (SELECT ROWNUM RN, EVENT.* FROM EVENT ORDER BY E_NUM) WHERE RN BETWEEN #{startrow} AND #{endrow}")
	ArrayList<EventDTO> eventList(PageDTO pdto);
	// 이벤트 게시판 글 count
	@Select("Select count(*) from event")
	int getEventListCount();
	//이벤트 상세보기
	@Select("SELECT E_NUM, EIMG FROM EVENT WHERE E_NUM = #{e_num}")
	EventDTO eventView(int e_num);
	//이벤트 게시판 삭제할 글 이미지 찾기
	@Select("SELECT EIMG FROM EVENT WHERE E_NUM = #{e_num}")
	String getEimg(int e_num);
	//이벤트 게시판 삭제
	@Delete("DELETE FROM EVENT WHERE E_NUM = #{e_num}")
	int eventDelete(int e_num);
	
	//질문게시판 글 번호
	@Select("SELECT NVL(MAX(QNUM),0) FROM QNA")
	int getMaxNum1(QnaDTO qnaDTO);
	//질문게시판 글 등록
	@Insert("INSERT INTO QNA(QNUM, QTITLE, QCONTENT, MID, QCHECK, QDATE, SCHECK) VALUES(#{qnum}, #{qtitle}, #{qcontent}, #{mid}, #{qcheck}, SYSDATE, #{scheck})")
	int qnaRegist(QnaDTO qnaDTO);
	
	
	//질문게시판 목록 출력
	@Select("SELECT QNUM, QTITLE, QCONTENT, MID, QCHECK , TO_CHAR(QDATE,'YYYY-MM-DD') AS QDATE, SCHECK FROM QNA ORDER BY QNUM DESC") //질문게시판엔 날짜까지만 상세페이지에서 시간까지. *AS QDATE로 변수명 안맞추면 데이터 출력 안됨
	ArrayList<QnaDTO> qnaList();

	
	//질문게시판 상세페이지
	@Select("SELECT * FROM QNA WHERE QNUM = #{qnum}")
	QnaDTO qnaReply(int qnum);
	
	
	//질문 게시판 답변 번호
	@Select("SELECT NVL(MAX(QR_NUM),0) FROM QNAREPLY WHERE QR_QNUM = #{qr_qnum}")
	int getMaxNum2(@Param("qr_qnum") int qr_qnum);
	//질문 게시판 답변 등록[QNAREPLY]
	@Update("UPDATE QNAREPLY SET QR_NUM = #{qr_num}, AID = #{aid}, QR_CONTENT = #{qr_content}, QR_DATE = SYSDATE WHERE QR_QNUM = #{qr_qnum}")
	int qnaReplyRegist(@Param("qr_num") int qr_num, @Param("qr_qnum") int qr_qnum, @Param("aid") String aid, @Param("qr_content") String qr_content);
	//질문 게시판 답변 수정[QNAREPLY]
	@Update("UPDATE QNAREPLY SET QR_CONTENT = #{qr_content}, QR_DATE = SYSDATE WHERE QR_QNUM = #{qr_qnum}")
	int qnaReplyModify(@Param("qr_qnum")int qr_qnum, @Param("qr_content") String qr_content);
	
	// 질문게시판 작성후 대기완료
	@Update("Update qna set qcheck = '완료' where qnum = #{qr_qnum}")
	int stateUpdate(int qr_qnum);
	
	@Select("Select * from qna where mid = #{loginId} order by qnum desc")
	ArrayList<QnaDTO> getQnaList(String loginId);

	

}

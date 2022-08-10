package com.Slurp.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.Slurp.dto.FaqDTO;
import com.Slurp.dto.PageDTO;
import com.Slurp.dto.QnaDTO;
import com.Slurp.dto.QnaReplyDTO;

public interface CrudDAO {
	// Q&A 글번호
		@Select("SELECT NVL(MAX(QNUM),0) FROM QNA")
		int getMaxqnum();
		// Q&A 리스트
		@Select("SELECT QNUM,QTITLE,QCONTENT,MID,QCHECK,TO_CHAR(QDATE,'YYYY-MM-DD HH24:MI:SS') AS QDATE,SCHECK\r\n"
				+ "FROM (SELECT QNA.*,ROW_NUMBER() OVER(ORDER BY QNUM DESC) AS RN FROM QNA)\r\n"
				+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
		ArrayList<QnaDTO> getQnaList(PageDTO pdto);
		// Q&A 전체 개수
		@Select("SELECT COUNT(*) FROM QNA")
		int getQnaListCount();
		// Q&A 답변완료시 Q&A 보기 페이지에 보여줄 정보
		@Select("SELECT QR_NUM,QR_QNUM,AID,QR_CONTENT,TO_CHAR(QR_DATE,'YYYY-MM-DD HH24:MI:SS') FROM QNAREPLY WHERE QR_QNUM = #{qnum}")
		QnaReplyDTO getQnaReplyView(@Param(value="qnum")int qnum);
		// Q&A 제목으로 검색
		@Select("SELECT QNUM,QTITLE,QCONTENT,MID,QCHECK,TO_CHAR(QDATE,'YYYY-MM-DD HH24:MI:SS') AS QDATE,SCHECK\r\n"
				+ "FROM (SELECT QNA.*,ROW_NUMBER() OVER(ORDER BY QNUM DESC) AS RN FROM QNA WHERE QTITLE LIKE '%'||#{text}||'%')\r\n"
				+ "WHERE RN BETWEEN #{startRow} AND #{endRow}")
		ArrayList<QnaDTO> qnaTitleSearch(@Param("startRow")int startRow,@Param("endRow")int endRow,@Param("text")String text);
		// Q&A 제목으로 검색된 글의 개수
		@Select("SELECT COUNT(*) FROM QNA WHERE QTITLE LIKE '%'||#{text}||'%'")
		int getQnaTitleCount(@Param("text")String text);
		// Q&A 내용으로 검색
		@Select("SELECT QNUM,QTITLE,QCONTENT,MID,QCHECK,TO_CHAR(QDATE,'YYYY-MM-DD HH24:MI:SS') AS QDATE,SCHECK\r\n"
				+ "FROM (SELECT QNA.*,ROW_NUMBER() OVER(ORDER BY QNUM DESC) AS RN FROM QNA WHERE QCONTENT LIKE '%'||#{text}||'%')\r\n"
				+ "WHERE RN BETWEEN #{startRow} AND #{endRow}")
		ArrayList<QnaDTO> qnaContentSearch(@Param("startRow")int startRow, @Param("endRow")int endRow, @Param("text")String text);
		// Q&A 내용으로 검색된 글의 개수
		@Select("SELECT COUNT(*) FROM QNA WHERE QCONTENT LIKE '%'||#{text}||'%'")
		int getQnaContentCount(@Param("text")String text);
		// Q&A 작성자로 검색
		@Select("SELECT QNUM,QTITLE,QCONTENT,MID,QCHECK,TO_CHAR(QDATE,'YYYY-MM-DD HH24:MI:SS') AS QDATE,SCHECK\r\n"
				+ "FROM (SELECT QNA.*,ROW_NUMBER() OVER(ORDER BY QNUM DESC) AS RN FROM QNA WHERE MID LIKE '%'||#{text}||'%')\r\n"
				+ "WHERE RN BETWEEN #{startRow} AND #{endRow}")
		ArrayList<QnaDTO> qnaTconSearch(@Param("startRow")int startRow, @Param("endRow")int endRow, @Param("text")String text);
		// Q&A 작성자로 검색된 글의 개수
		@Select("SELECT COUNT(*) FROM QNA WHERE mid LIKE '%'||#{text}||'%'")
		int getQnaTconCount(@Param("text")String text);
		// Q&A 답변 리스트
		@Select("SELECT * FROM QNAREPLY WHERE QR_QNUM = #{qnum}")
		QnaReplyDTO qnaReply(int qnum);
		// Q&A 답변 리스트 (답변 / 대기 출력용)
		@Select("SELECT QR_CONTENT FROM QNAREPLY WHERE QR_QNUM = #{qnum}")
		String qnaReply1(int qnum);
		// Q&A 답변 리스트 (qr_qnum 확인용)
		@Select("SELECT NVL(MAX(QR_QNUM),0) FROM QNAREPLY WHERE QR_QNUM = #{qnum}")
		int getQr_qnum(int qnum);
		// Q&A 답변 리스트 (qr_qnum 입력용)
		@Insert("INSERT INTO QNAREPLY(QR_QNUM) VALUES(#{qnum})")
		void insertQr_qnum(int qnum);
		
		// FAQ 화면 리스트 출력
		@Select("SELECT *\r\n"
				+ "FROM (SELECT FAQ.*,ROW_NUMBER() OVER(ORDER BY FNUM DESC) AS RN FROM FAQ)\r\n"
				+ "WHERE RN BETWEEN #{startrow} AND #{endrow}")
		ArrayList<FaqDTO> getFaqList(PageDTO pdto);
		// FAQ 전체 개수
		@Select("SELECT count(*)\r\n"
				+ "FROM (SELECT FAQ.*,ROW_NUMBER() OVER(ORDER BY FNUM DESC) AS RN FROM FAQ)")
		int getFaqListCount();
		// FAQ 번호 생성
		@Select("SELECT NVL(MAX(FNUM),0) FROM FAQ")
		int getMaxfnum();
		// FAQ 작성
		@Insert("INSERT INTO FAQ VALUES(#{fnum},#{ftitle},#{fcontent},#{aid},#{fcate})")
		int faqWrite(FaqDTO fdto);
		// FAQ 제목으로 검색
		@Select("SELECT *\r\n"
				+ "FROM (SELECT FAQ.*,ROW_NUMBER() OVER(ORDER BY FNUM DESC) AS RN FROM FAQ WHERE FTITLE LIKE '%'||#{text}||'%')\r\n"
				+ "WHERE RN BETWEEN #{startRow} AND #{endRow}")
		ArrayList<FaqDTO> faqTitleSearch(@Param("startRow")int startRow, @Param("endRow")int endRow, @Param("text")String text);
		// FAQ 제목으로 검색된 전체 개수
		@Select("SELECT COUNT(*) FROM FAQ WHERE FTITLE LIKE '%'||#{text}||'%'")
		int getfaqTitleCount(String text);
		// FAQ 내용으로 검색
		@Select("SELECT *\r\n"
				+ "FROM (SELECT FAQ.*,ROW_NUMBER() OVER(ORDER BY FNUM DESC) AS RN FROM FAQ WHERE FCONTENT LIKE '%'||#{text}||'%')\r\n"
				+ "WHERE RN BETWEEN #{startRow} AND #{endRow}")
		ArrayList<FaqDTO> faqContentSearch(@Param("startRow")int startRow, @Param("endRow")int endRow, @Param("text")String text);
		// FAQ 내용으로 검색된 전체 개수
		@Select("SELECT COUNT(*) FROM FAQ WHERE FCONTENT LIKE '%'||#{text}||'%'")
		int getfaqContentCount(@Param("text")String text);
		// FAQ 제목/내용으로 검색
		@Select("SELECT *\r\n"
				+ "FROM (SELECT FAQ.*,ROW_NUMBER() OVER(ORDER BY FNUM DESC) AS RN FROM FAQ WHERE FTITLE LIKE '%'||#{text}||'%' OR FCONTENT LIKE '%'||#{text}||'%')\r\n"
				+ "WHERE RN BETWEEN #{startRow} AND #{endRow}")
		ArrayList<FaqDTO> faqTconSearch(@Param("startRow")int startRow, @Param("endRow")int endRow, @Param("text")String text);
		// FAQ 제목/내용으로 검색된 전체 개수
		@Select("SELECT COUNT(*) FROM FAQ WHERE FTITLE LIKE '%'||#{text}||'%' OR FCONTENT LIKE '%'||#{text}||'%'")
		int getfaqTconCount(@Param("text")String text);
		
	
}

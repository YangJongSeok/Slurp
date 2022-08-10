package com.Slurp.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.Slurp.dao.BoardDAO;
import com.Slurp.dto.CompanyDTO;
import com.Slurp.dto.EventDTO;
import com.Slurp.dto.PageDTO;
import com.Slurp.dto.QnaDTO;
import com.Slurp.dto.QnaReplyDTO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	//이벤트 등록  ***** 이미지 파일 처리 해야함 *****
	public ModelAndView eventRegist(EventDTO eventDTO) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Service 실행");
		
		ModelAndView mav = new ModelAndView();
		
		//이벤트 글번호 설정 getmaxNum 사용
		int maxNum = boardDAO.getMaxNum(eventDTO) + 1; //maxNum = 0 이기에 +1 하여 번호가 1 부터 시작하도록 설정
		//e_num에 maxNum의 값을 저장
		eventDTO.setE_num(maxNum);
		/***/
		MultipartFile efile = eventDTO.getEfile();
		
		
		String eimg = "";
		System.out.println(eimg);
		
		String savePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Slurp\\resources\\images\\event";
		
		if(!efile.isEmpty()) {
			UUID uuid = UUID.randomUUID();
			eimg = uuid.toString()+"_"+efile.getOriginalFilename();
			efile.transferTo(new File(savePath,eimg));
		}
		eventDTO.setEimg(eimg);
		
		//eventDTO의 값 출력해보기
		System.out.println("eventDTO : " + eventDTO);
		
		int insertResult = boardDAO.eventRegist(eventDTO);
		if(insertResult > 0) {
			mav.setViewName("redirect:/eventList");
		} else {
			mav.addObject("msg", "실패");
			File file = new File(savePath,eimg);
			file.delete();
			mav.setViewName("Success");
		}
		return mav;
		
		/***/
	
	}

	//이벤트 글 삭제
	public ModelAndView eventDelete(int e_num) {
		// TODO Auto-generated method stub
		System.out.println("Service 실행");
		
		ModelAndView mav = new ModelAndView();
		
		String delEimg = boardDAO.getEimg(e_num);
		
		System.out.println("delEimg : " + delEimg);
		String savePath = //파일 저장 경로
				"C:\\Users\\cntmd\\Documents\\workspace-spring-tool-suite-4-4.10.0.RELEASE\\Slurp4.0\\src\\main\\webapp\\resources\\images\\event";
		
		int delResult = boardDAO.eventDelete(e_num);
		
		if(delResult > 0) {
			if(delEimg == null) {
				System.out.println("파일 없음");
				mav.setViewName("redirect:/eventList");
			} else {
				File file = new File(savePath,delEimg);
				file.delete();
				System.out.println("파일 삭제");
				mav.setViewName("redirect:/eventList");
			}
		}
		return mav;

	}
	
	//이벤트 게시판 목록
	public ModelAndView eventList(int page) {
		System.out.println("Service 실행");
		
		ModelAndView mav = new ModelAndView();
		
		int pageLimit = 10; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);
		
		
		// event 목록을 가져오기위해 ArrayList 사용
		ArrayList<EventDTO> eventList = boardDAO.eventList(pdto);

		int eventListCount = boardDAO.getEventListCount();

		int maxPage = (int) (Math.ceil((double) eventListCount / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);
		
		mav.addObject("page", pdto);
	
		//가져온 데이터 확인
		System.out.println("eventList : " + eventList);
		//ArrayList를 저장
		mav.addObject("eventList", eventList);
		//화면이동
		mav.setViewName("crud/eventListForm");
		
		return mav;
	}
	
	////이벤트 상세보기
	public ModelAndView eventView(int e_num) {
		// TODO Auto-generated method stub
		System.out.println("Service 실행");
		
		ModelAndView mav = new ModelAndView();
		EventDTO eventDTO = boardDAO.eventView(e_num);
		
		mav.addObject("eventDTO",eventDTO);
		mav.setViewName("crud/eventViewForm");
		return mav;
	}
	
	//질문 게시판 글 등록 [QNA]
	public ModelAndView qnaRegist(QnaDTO qnaDTO) {
		// TODO Auto-generated method stub
		System.out.println("Service 실행");
		
		ModelAndView mav = new ModelAndView();
		
		//질문 게시글번호 설정 getmaxNum1 사용
		int maxNum = boardDAO.getMaxNum1(qnaDTO) + 1; //maxNum = 0 이기에 +1 하여 번호가 1 부터 시작하도록 설정
		//q_num에 maxNum의 값을 저장
		qnaDTO.setQnum(maxNum);
		
		//qnaDTO의 값 출력해보기
		System.out.println("qnaDTO : " + qnaDTO);
		// 데이터를 DAO로 넘겨서 쿼리문 실행
		int insertResult = boardDAO.qnaRegist(qnaDTO);
		// 결과 확인
		
		mav.setViewName("redirect:/qnaListForm");
		
		return mav;
	}

	
	//질문 게시판 상세페이지 출력
	public ModelAndView qnaReply(int qnum) {
		// TODO Auto-generated method stub
		System.out.println("Service 실행");
		
		ModelAndView mav = new ModelAndView();
		
		//qnum 번호에 해당하는 질문글을 가져오기
		QnaDTO qnaReply = boardDAO.qnaReply(qnum);
		//가져온 데이터 확인
		System.out.println("qnaReply : " + qnaReply);
		//qnaReply를 저장
		mav.addObject("qnaReply", qnaReply);
		//화면이동
		mav.setViewName("Success3");
		
		return mav;
	}

	//질문 게시판 답변 등록[QNAREPLY]
	public int qnaReplyRegist(String qr_content, int qr_qnum, String aid) {
		System.out.println("Service 실행");
		
		//질문 답변 글번호 설정 getmaxNum 사용
		int maxNum = boardDAO.getMaxNum2(qr_qnum) + 1; //maxNum = 0 이기에 +1 하여 번호가 1 부터 시작하도록 설정
		//qr_num에 maxNum의 값을 저장
		int qr_num = maxNum;
		
		System.out.println("답변번호 : " + qr_num + "글 번호 : " + qr_qnum + "아이디 : " + aid + "답변내용 : " + qr_content);
		// 데이터를 DAO로 넘겨서 쿼리문 실행
		int insertResult = boardDAO.qnaReplyRegist(qr_num, qr_qnum, aid, qr_content);
		
		// 결과 확인
		if(insertResult > 0) {
			boardDAO.stateUpdate(qr_qnum);
			System.out.println("boardDAO.stateUpdate()");
		}
		
		return insertResult;
	}

	//질문 게시판 답변 수정[QNAREPLY]
	public int qnaReplyModify(String qr_content, int qr_qnum, String aid) {
		// TODO Auto-generated method stub
		System.out.println("Service 실행");
		
	
		System.out.println("수정된 글 번호 : " + qr_qnum + "수정된 답변내용 : " + qr_content);
		// 데이터를 DAO로 넘겨서 쿼리문 실행
		int insertResult = boardDAO.qnaReplyModify( qr_qnum, qr_content);
		
		// 결과 확인
		
		
		return insertResult;
	}

	

	


}

package com.Slurp.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Slurp.dto.EventDTO;
import com.Slurp.dto.QnaDTO;
import com.Slurp.dto.QnaReplyDTO;
import com.Slurp.service.BoardService;
import com.Slurp.service.CrudService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {
	

	ModelAndView mav;
	
	@Autowired
	BoardService boardSvc;
	
	@Autowired
	private CrudService crudSvc;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/b", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/********************** 게시판[ 관리자 전용 ] 
	 * @throws IOException 
	 * @throws IllegalStateException **********************/
	// 이벤트 게시글 등록
	@RequestMapping("/eventRegist")
	public ModelAndView eventRegist(EventDTO eventDTO) throws IllegalStateException, IOException {
		//BoardController로 호출이 잘되었는지 확인
		System.out.println("/eventRegist() 실행");
		
		//eventDTO 값을 넘겨주면서 boardSvc로 이동
		mav = boardSvc.eventRegist(eventDTO);
		return mav;
		
	}
	
	//이벤트 게시판 목록
	@RequestMapping("/eventList")
	public ModelAndView eventList(@RequestParam(value="page", defaultValue="1")int page) {
		//BoardController로 호출이 잘되었는지 확인
		System.out.println("/eventList() 실행");
				
		//eventDTO 값을 넘겨주면서 boardSvc로 이동
		mav = boardSvc.eventList(page);
		return mav;
	}
	
	//이벤트 삭제
	@RequestMapping("eventDelete")
	public ModelAndView eventDelete(int e_num) {
		//BoardController로 호출이 잘되었는지 확인
		System.out.println("/eventDelete() 실행");
						
		//e_num 값을 넘겨주면서 boardSvc로 이동
		mav = boardSvc.eventDelete(e_num);
		return mav;
	}
	
	/********************** 게시판[ 일반사용자 전용 ] **********************/
	//이벤트 상세보기
	@RequestMapping("eventView")
	public ModelAndView eventView(int e_num) {
		//BoardController로 호출이 잘되었는지 확인
		System.out.println("/eventList() 실행");
		
		System.out.println("이벤트 번호 : " + e_num);
		// e_num 값을 넘겨주면서 boardSvc로 이동
		mav = boardSvc.eventView(e_num);
		return mav;
	}
	
	//질문 게시판 글 등록 [QNA]
	@RequestMapping("/qnaRegist")
	public ModelAndView qnaRegist(QnaDTO qnaDTO) {
		//BoardController로 호출이 잘되었는지 확인
		System.out.println("/qnaRegist() 실행");
				
		//qnaDTO 값을 넘겨주면서 boardSvc로 이동
		mav = boardSvc.qnaRegist(qnaDTO);
		return mav;
	}
	
	/*
	 * //질문 게시판 목록 출력
	 * 
	 * @RequestMapping("/qnaList") public ModelAndView qnaList() {
	 * //BoardController로 호출이 잘되었는지 확인 System.out.println("/qnaList() 실행");
	 * //boardSvc로 이동 mav = boardSvc.qnaList(); return mav; }
	 */
	
	//질문 게시판 상세페이지 출력 /**************  비밀글 기능 가능하면 추가해보기 세션아이디 & 작성자 아이디 ***************/
	@RequestMapping("/qnaReply")
	public ModelAndView qnaReply(int qnum) {
		// BoardController로 호출이 잘되었는지 확인
		System.out.println("/qnaReply() 실행");
		// 선택한 글번호 확인
		System.out.println("선택한 글 번호 : " + qnum);
		// 글번호를 넘겨주면서 boardSvc로 이동
		mav = boardSvc.qnaReply(qnum);
		return mav;
	}
	
	//질문 게시판 답변 등록[QNAREPLY]
	@RequestMapping("/qnaReplyRegist")
	public @ResponseBody int qnaReplyRegist(@RequestParam("qr_content") String qr_content, @RequestParam("qr_qnum") int qr_qnum, @RequestParam("aid") String aid) {
		// BoardController로 호출이 잘되었는지 확인
		System.out.println("/qnaReplyRegist() 실행");
		
		// 답변한 글번호,내용 확인
		System.out.println("답변한 글 번호 : " + qr_qnum);
		System.out.println("답변한 글 내용 : " + qr_content);
		System.out.println("답변한 아이디 : " + aid);
		
		//qnaDTO 값을 넘겨주면서 boardSvc로 이동
		int registResult = boardSvc.qnaReplyRegist(qr_content, qr_qnum, aid);
		return registResult;
	}
	
	//질문 게시판 답변 수정 데이터 받아오기[QNAREPLY]
	@RequestMapping("/getQnaReplyModify")
	public @ResponseBody QnaReplyDTO getQnaReplyModify(@RequestParam("qnum")int qnum) {
		System.out.println("/getQnaReplyModify() 실행");
		//글 번호 넘어오는거 확인
		System.out.println("답변된 글번호 : " + qnum);
		QnaReplyDTO qnaReply = crudSvc.qnaReply(qnum);
		
		return qnaReply;
	}
	//질문 게시판 답변 수정[QNAREPLY]
	@RequestMapping("/qnaReplyModify")
	public @ResponseBody int qnaReplyModify(@RequestParam("qr_content") String qr_content, @RequestParam("qr_qnum") int qr_qnum, @RequestParam("aid") String aid) {
		// BoardController로 호출이 잘되었는지 확인
		System.out.println("/qnaReplyModify() 실행");
		
		// 답변한 글번호,내용 확인
		System.out.println("답변한 글 번호 : " + qr_qnum);
		System.out.println("답변한 글 내용 : " + qr_content);
		System.out.println("답변한 아이디 : " + aid);
		
		//qnaDTO 값을 넘겨주면서 boardSvc로 이동
		int registResult = boardSvc.qnaReplyModify(qr_content, qr_qnum, aid);
		return registResult;
	}
}

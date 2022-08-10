package com.Slurp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Slurp.dto.FaqDTO;
import com.Slurp.dto.QnaReplyDTO;
import com.Slurp.service.CrudService;

@Controller
public class CrudController {
			
private ModelAndView mav;
	
	
	@Autowired
	private CrudService crudSvc;
	
	// Q&A 리스트 화면 이동
	@RequestMapping("/qnaListForm")
	public ModelAndView qnaListForm(@RequestParam(value="page",defaultValue="1")int page) {
		mav = crudSvc.qnaListForm(page);
		return mav;
	}
	
	/* QNA 작성 화면으로 이동  */
	@RequestMapping("/qnaWriteForm")
	public String qnaWriteForm() {
		return "crud/qnaWriteForm";
	}
		
	// Q&A 검색
	@RequestMapping("/qnaSearch")
	public ModelAndView qnaSearch(@RequestParam(value="type") String type,@RequestParam(value="text") String text,@RequestParam(value="page",defaultValue="1")int page) {
		mav = crudSvc.qnaSearch(type,text,page);
		return mav;
	}
	
	// Q&A 답변
		@RequestMapping("/qnaReplyList")
		public @ResponseBody QnaReplyDTO qnaReplyList(@RequestParam("qnum")int qnum) {
			System.out.println("/qnaReplyList() 실행");
			//글 번호 넘어오는거 확인
			System.out.println("답변된 글번호 : " + qnum);
			QnaReplyDTO qnaReply = crudSvc.qnaReply(qnum);
			
			return qnaReply;
		}
	
	
	// FAQ 리스트 화면 이동
	@RequestMapping("/faqListForm")
	public ModelAndView faqListForm(@RequestParam(value="page",defaultValue="1")int page) {
		mav = crudSvc.faqListForm(page);
		return mav;
	}
	
	/* FAQ 작성 화면으로 이동  */
	@RequestMapping("/faqWriteForm")
	public String faqWriteForm() {
		return "crud/faqWriteForm";
	}
	
	// FAQ 작성
	@RequestMapping("/faqWrite")
	public ModelAndView faqWrite(FaqDTO fdto) {
		mav = crudSvc.faqWrite(fdto);
		return mav;
	}
	
	// FAQ 검색
	@RequestMapping("/faqSearch")
	public ModelAndView faqSearch(@RequestParam(value="page",defaultValue="1")int page,@RequestParam("type")String type,@RequestParam("text")String text) {
		mav = crudSvc.faqSearch(page,type,text);
		return mav;
	}
	
	
	// 이벤트게시판 작성 화면 이동
	@RequestMapping("/eventWriteForm")
	public String eventWriteForm() {
		return "crud/eventWriteForm";
	}
	
	// 이벤트게시판 View 이동
	@RequestMapping("/eventViewForm")
	public String eventViewForm() {
		return "crud/eventViewForm";
	}
	
	
	
	
	
	
	
	
}

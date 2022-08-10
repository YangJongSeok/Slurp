package com.Slurp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.Slurp.dao.CrudDAO;
import com.Slurp.dto.FaqDTO;
import com.Slurp.dto.PageDTO;
import com.Slurp.dto.QnaDTO;
import com.Slurp.dto.QnaReplyDTO;

@Service
public class CrudService {
	@Autowired
	CrudDAO crudDAO;
	
	
	// Q&A 리스트 화면 이동
	public ModelAndView qnaListForm(int page) {
		System.out.println("qnaListForm SVC() 실행");
		ModelAndView mav = new ModelAndView();
		
		int pageLimit = 20; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page-1) * pageLimit + 1;
		int endRow = page * pageLimit;
		
		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<QnaDTO> qList = crudDAO.getQnaList(pdto);
		
		/*********** 답변완료 / 답변대기 출력 메소드 ************/
			for(int i = 0; i < qList.size(); i++  ) {
				String qCheck = crudDAO.qnaReply1(qList.get(i).getQnum());	
				if(qCheck != null) {
					qList.get(i).setQcheck("완료");
				}
			}
		
		int qListCount = crudDAO.getQnaListCount(); // 글의 전체개수 (20)
		
		int maxPage = (int)(Math.ceil((double)qListCount/pageLimit)); 
		int startPage = ((int)(Math.ceil((double)page/pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);
		
		mav.addObject("qList", qList);
		mav.addObject("page",pdto);
		mav.setViewName("crud/qnaListForm");
		
		return mav;
	}
	
	// Q&A 검색
	public ModelAndView qnaSearch(String type, String text, int page) {
		ModelAndView mav = new ModelAndView();
		
		int pageLimit = 10; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page-1) * pageLimit + 1;
		int endRow = page * pageLimit;
		
		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);
		
		// type 별 검색
		if(type.equals("title")) {
			
			ArrayList<QnaDTO> qdto = crudDAO.qnaTitleSearch(startRow,endRow,text);
			
			int qtitleCount = crudDAO.getQnaTitleCount(text); // 검색된 글의 전체개수 (20)
			
			
			int maxPage = (int)(Math.ceil((double)qtitleCount/pageLimit)); 
			int startPage = ((int)(Math.ceil((double)page/pageNumLimit)) - 1) * pageNumLimit + 1;
			int endPage = startPage + pageNumLimit - 1;
			
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			pdto.setPage(page);
			pdto.setStartpage(startPage);
			pdto.setEndpage(endPage);
			pdto.setMaxpage(maxPage);
			
			mav.addObject("qList", qdto);
			mav.addObject("type", type);
			mav.addObject("text", text);
			mav.addObject("page",pdto);
			mav.setViewName("crud/qnaListForm");
			
		}else if(type.equals("content")) {
			
			ArrayList<QnaDTO> qdto = crudDAO.qnaContentSearch(startRow,endRow,text);
			
			int qcontentCount = crudDAO.getQnaContentCount(text);
			
			
			int maxPage = (int)(Math.ceil((double)qcontentCount/pageLimit)); 
			int startPage = ((int)(Math.ceil((double)page/pageNumLimit)) - 1) * pageNumLimit + 1;
			int endPage = startPage + pageNumLimit - 1;
			
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			pdto.setPage(page);
			pdto.setStartpage(startPage);
			pdto.setEndpage(endPage);
			pdto.setMaxpage(maxPage);
			
			mav.addObject("qList", qdto);
			mav.addObject("type", type);
			mav.addObject("text", text);
			mav.addObject("page",pdto);
			mav.setViewName("crud/qnaListForm");
			
		}else {
			
			ArrayList<QnaDTO> qdto = crudDAO.qnaTconSearch(startRow,endRow,text);
			
			int qtconCount = crudDAO.getQnaTconCount(text);
			
			
			int maxPage = (int)(Math.ceil((double)qtconCount/pageLimit)); 
			int startPage = ((int)(Math.ceil((double)page/pageNumLimit)) - 1) * pageNumLimit + 1;
			int endPage = startPage + pageNumLimit - 1;
			
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			pdto.setPage(page);
			pdto.setStartpage(startPage);
			pdto.setEndpage(endPage);
			pdto.setMaxpage(maxPage);
			
			mav.addObject("qList", qdto);
			mav.addObject("type", type);
			mav.addObject("text", text);
			mav.addObject("page",pdto);
			mav.setViewName("crud/qnaListForm");
			
		}
		return mav;
	}
	
	// QNA 답변 리스트
	public QnaReplyDTO qnaReply(int qnum) {
		//답변 해당 글의 답변을 불러옴
		System.out.println("qnaReply.Svc() 실행");
		QnaReplyDTO qnaReply = crudDAO.qnaReply(qnum);
		/* qr_qnum이 0 이면 qr_qnum입력을 위해서. & 게시글 클릭시마다 qr_qnum 재 입력 방지를 위해서  */
		int getQr_qnum = crudDAO.getQr_qnum(qnum);
		if(getQr_qnum == 0) {
			crudDAO.insertQr_qnum(qnum);
			qnaReply = crudDAO.qnaReply(qnum);
		}
		System.out.println(qnaReply);
		
		return qnaReply;
	}
	
	// FAQ 화면 리스트 출력
	public ModelAndView faqListForm(int page) {
		ModelAndView mav = new ModelAndView();
		
		int pageLimit = 15; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page-1) * pageLimit + 1;
		int endRow = page * pageLimit;
		
		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);
		
		ArrayList<FaqDTO> fList = crudDAO.getFaqList(pdto);
		
		int fListCount = crudDAO.getFaqListCount(); // 글의 전체개수 (20)
		
		int maxPage = (int)(Math.ceil((double)fListCount/pageLimit)); 
		int startPage = ((int)(Math.ceil((double)page/pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);
		
		mav.addObject("fList", fList);
		mav.addObject("page",pdto);
		mav.setViewName("crud/faqListForm");
		
		return mav;
	}
	// FAQ 작성
	public ModelAndView faqWrite(FaqDTO fdto) {
		ModelAndView mav = new ModelAndView();
		
		// FAQ번호 생성
		int getMaxfnum = crudDAO.getMaxfnum() + 1;
		fdto.setFnum(getMaxfnum);
		
		int faqWrite = crudDAO.faqWrite(fdto);
		
		if(faqWrite > 0) {
			mav.setViewName("redirect:/faqListForm");
		}else {
			mav.addObject("msg", "FAQ 작성 실패");
			mav.setViewName("back");
		}

		return mav;
	}
	// FAQ 검색
	public ModelAndView faqSearch(int page,String type,String text) {
		ModelAndView mav = new ModelAndView();
		
		int pageLimit = 15; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page-1) * pageLimit + 1;
		int endRow = page * pageLimit;
		
		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);
		
		// type 별 검색
		if(type.equals("title")) {
			
			ArrayList<FaqDTO> fdto = crudDAO.faqTitleSearch(startRow,endRow,text);
			
			int ftitleCount = crudDAO.getfaqTitleCount(text);
			
			
			int maxPage = (int)(Math.ceil((double)ftitleCount/pageLimit)); 
			int startPage = ((int)(Math.ceil((double)page/pageNumLimit)) - 1) * pageNumLimit + 1;
			int endPage = startPage + pageNumLimit - 1;
			
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			pdto.setPage(page);
			pdto.setStartpage(startPage);
			pdto.setEndpage(endPage);
			pdto.setMaxpage(maxPage);
			
			mav.addObject("fList", fdto);
			mav.addObject("type", type);
			mav.addObject("text", text);
			mav.addObject("page",pdto);
			mav.setViewName("crud/faqListForm");
			
		}else if(type.equals("content")) {
			
			ArrayList<FaqDTO> fdto = crudDAO.faqContentSearch(startRow,endRow,text);
			
			int fcontentCount = crudDAO.getfaqContentCount(text);
			
			int maxPage = (int)(Math.ceil((double)fcontentCount/pageLimit)); 
			int startPage = ((int)(Math.ceil((double)page/pageNumLimit)) - 1) * pageNumLimit + 1;
			int endPage = startPage + pageNumLimit - 1;
			
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			pdto.setPage(page);
			pdto.setStartpage(startPage);
			pdto.setEndpage(endPage);
			pdto.setMaxpage(maxPage);
			
			mav.addObject("fList", fdto);
			mav.addObject("type", type);
			mav.addObject("text", text);
			mav.addObject("page",pdto);
			mav.setViewName("crud/faqListForm");
			
		}else {
			
			ArrayList<FaqDTO> fdto = crudDAO.faqTconSearch(startRow,endRow,text);
			
			int ftconCount = crudDAO.getfaqTconCount(text);
			
			
			int maxPage = (int)(Math.ceil((double)ftconCount/pageLimit)); 
			int startPage = ((int)(Math.ceil((double)page/pageNumLimit)) - 1) * pageNumLimit + 1;
			int endPage = startPage + pageNumLimit - 1;
			
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			pdto.setPage(page);
			pdto.setStartpage(startPage);
			pdto.setEndpage(endPage);
			pdto.setMaxpage(maxPage);
			
			mav.addObject("fList", fdto);
			mav.addObject("type", type);
			mav.addObject("text", text);
			mav.addObject("page",pdto);
			mav.setViewName("crud/faqListForm");
			
		}
		return mav;
	}
	
	/*
	 * // 이벤트 게시판 화면 이동 public ModelAndView eventListForm(int page) { ModelAndView
	 * mav = new ModelAndView(); mav.setViewName("crud/eventListForm"); return mav;
	 * }
	 */

	
}

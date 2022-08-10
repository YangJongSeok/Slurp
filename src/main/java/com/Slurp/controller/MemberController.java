package com.Slurp.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Slurp.dto.AddressDTO;
import com.Slurp.dto.CompanyDTO;
import com.Slurp.dto.MembersDTO;
import com.Slurp.dto.ReplygoodsDTO;
import com.Slurp.service.MemberService;

@Controller
public class MemberController {
	
	private ModelAndView mav;
	
	
	@Autowired
	private MemberService memSvc;
	
	@Autowired
	private HttpSession session;
	
	/****************** 로그인 **************************/
	// 회원 로그인
	@RequestMapping("/memberLogin")
	public ModelAndView memberLogin(MembersDTO mdto) {
		mav = memSvc.memberLogin(mdto);
		return mav;
	}
	// 카카오 로그인
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam(value = "code") String code, HttpSession session) {
		mav = new ModelAndView();
		// code로 토큰 발급
		String access_token = memSvc.getAccessToken(code);
		// 토큰으로 유저정보 가져옴
		HashMap<String, Object> userInfo = memSvc.getUserInfo(access_token);
        String userId = (String) userInfo.get("id");
        System.out.println("userInfo.nickname : " + userInfo.get("nickname"));
        // 카카오 회원 확인
        String kakaoCheck = memSvc.kakaoCheck(userId);
        String userNickname =  (String) userInfo.get("nickname");
        if(kakaoCheck != null) {
        	session.setAttribute("access_token", access_token);
        	session.setAttribute("loginId", userInfo.get("id"));
        	session.setAttribute("nickname", userInfo.get("nickname"));
        	session.setAttribute("authority", 3);
        	mav.setViewName("redirect:/home");
        }else {
        	// 카카오 회원 가입
        	int kakaoRegist = memSvc.kakaoRegist(userInfo);
        	
        	session.setAttribute("access_token", access_token);
        	session.setAttribute("loginId", userInfo.get("id"));
        	session.setAttribute("nickname", userInfo.get("nickname"));
        	session.setAttribute("authority", 3);
            mav.setViewName("redirect:/home");
        }
        	
        	
		return mav;
	}
	// 카카오 로그아웃
	@RequestMapping("/kakaoLogout")
	public ModelAndView kakaoLogout(HttpSession session) {
		mav = new ModelAndView();
		memSvc.kakaoLogout((String)session.getAttribute("access_token"));
		session.removeAttribute("access_token");
		session.removeAttribute("loginId");
		session.removeAttribute("authority");
		session.removeAttribute("nickname");
		mav.setViewName("redirect:/home");
		return mav;
	}
	// 로그아웃
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "home";
	}
	// 업체 로그인
	@RequestMapping("/companyLogin")
	public ModelAndView companyLogin(CompanyDTO cdto) {
		mav = memSvc.companyLogin(cdto);
		return mav;
	}
	// 관리자 로그인
	@RequestMapping("/adminLogin")
	public ModelAndView adminLogin(@Param("aid") String aid, @Param("apw") String apw) {
		mav = memSvc.adminLogin(aid,apw);
		return mav;
	}
	// 업체승인 페이지 요청
	@RequestMapping("/companyCheckForm")
	public ModelAndView companyCheckForm(@RequestParam(value="page",defaultValue="1")int page) {
		mav = memSvc.companyCheckForm(page);
		return mav;
	}
	// 업체승인 요청
	@RequestMapping("/companyAccept")
	public ModelAndView companyAccept(@Param("ccode") String ccode) {

		mav = memSvc.companyAccept(ccode);
		return mav;
	}
	// 업체승인 거절 요청
	@RequestMapping("/companyRefusal")
	public ModelAndView companyRefusal(@Param("ccode") String ccode) {
		mav = memSvc.companyRefusal(ccode);
		return mav;
	}
	// 업체 상품관리 페이지 이동
	@RequestMapping("/companyManageForm")
	public ModelAndView companyManageForm(@RequestParam(value="page",defaultValue="1")int page,@RequestParam("cid")String cid) {
		mav = memSvc.companyManageForm(page,cid);
		return mav;
	}	
	// 신고내역 화면으로 이동
	@RequestMapping("/adminReportForm")
	public ModelAndView adminReportForm(@RequestParam(value="page",defaultValue="1")int page) {
		mav = memSvc.adminReportForm(page);
		return mav;
	}
	// 신고된 평가 출력 Ajax
	@RequestMapping("/goodsReplyAjax")
	public @ResponseBody ReplygoodsDTO goodsReplyAjax(@RequestParam("rr_num")int rr_num) {
		ReplygoodsDTO goodsReply = memSvc.goodsReply(rr_num);
		return goodsReply;
	}
	// 신고내역 승인
	@RequestMapping("/reportAccess")
	public ModelAndView reportAccess(@RequestParam("r_num")int r_num) {
		mav = memSvc.reportAccess(r_num);
		return mav;
	}
	// 신고내역 거절
	@RequestMapping("/reportRefusal")
	public ModelAndView reportRefusal(@RequestParam("r_num")int r_num) {
		mav = memSvc.reportRefusal(r_num);
		return mav;
	}
		
	/************************** 회원가입 ********************************************/
	
	// 일반회원 가입
		@RequestMapping("/memberJoin")
		public ModelAndView memberJoin(MembersDTO membersDTO, AddressDTO addressDTO) {
			System.out.println("일반회원 가입요청");
			mav = memSvc.memberJoin(membersDTO, addressDTO);
			return mav;
		}
		
		// 일반회원 아이디 중복체크
		@ResponseBody
		@RequestMapping(value = "/midCheck")
		public int midCheck(@RequestParam("mid") String mid) {
			System.out.println("넘어온 아이디 값 : " + mid);
			int idCheck = memSvc.midCheck(mid);
			return idCheck;
		}
		
		// 업체가입
		@RequestMapping("/companyJoin")
		public ModelAndView companyJoin(CompanyDTO companyDTO, AddressDTO addressDTO) {
			System.out.println("업체회원 가입요청");
			mav = memSvc.companyJoin(companyDTO, addressDTO);
			return mav;
		}
		
		// 업체회원 아이디 중복체크
		
		@RequestMapping("/cidCheck")
		public @ResponseBody int cidCheck(@RequestParam("cid") String cid) {
			System.out.println("넘어온 아이디 값 : " + cid);
			int idCheck = memSvc.cidCheck(cid);
			return idCheck;
		}
		
	/*******************************************************/
		
		// 업체 주문접수 페이지 요청
		@RequestMapping("/companyDeliveryCheck")
		public ModelAndView companyDeliveryCheck(String cid, @RequestParam(value="page",defaultValue="1")int page) {
			mav = memSvc.companyDeliveryCheck(cid, page);
			return mav;
			}
		
		// 주문접수
		@RequestMapping("/deliveryCheck")
		public ModelAndView deliveryCheck(int o_code, String cid) {
			System.out.println("주문 접수 : " + o_code);
			mav = memSvc.deliveryCheck(o_code, cid);
			return mav;
		}
	
		/*******************************************************/
		
		/************* 마이페이지 7/14 이후 수정 **************/

		// 회원정보 보기
		@RequestMapping("/showMemberInfo")
		public ModelAndView showMemberInfo(@RequestParam("mid") String loginId) {
			// MemberController로 호출이 잘되었는지 확인
			System.out.println("/showMemberInfo() 실행");

			// 넘어온 로그인 아이디 확인.
			System.out.println("loginId : " + loginId);

			// memberService로 이동
			mav = memSvc.showMemberInfo(loginId);
			return mav;
		}

		// 회원정보 수정폼으로 이동
		@RequestMapping("/myInfoModifyForm")
		public ModelAndView myInfoModifyForm(@RequestParam("mid") String loginId) {
			// MemberController로 호출이 잘되었는지 확인
			System.out.println("/myInfoModifyForm() 실행");

			// 넘어온 로그인 아이디 확인.
			System.out.println("loginId : " + loginId);

			// memberService로 이동
			mav = memSvc.myInfoModifyForm(loginId);
			return mav;
		}

		// 수정폼 - 현재 비밀번호 일치하는지 확인
		@RequestMapping("/pwCheck")
		public @ResponseBody int pwCheck(String mid, String mpw) {
			// MemberController로 호출이 잘되었는지 확인
			System.out.println("/pwCheck() 실행");

			// 넘어온 아이디 비밀번호 확인.
			System.out.println("아이디 : " + mid + " 현재 비밀번호 : " + mpw);

			// memberService로 이동
			int checkResult = memSvc.pwCheck(mid, mpw);

			return checkResult;
		}

		// 수정폼 - 신규 비밀번호 업데이트
		@RequestMapping("/modifyPw")
		public @ResponseBody int modifyPw(String mid, @RequestParam("mpw") String mpw) {
			// MemberController로 호출이 잘되었는지 확인
			System.out.println("/modifyPw() 실행");

			// 넘어온 아이디 비밀번호 확인.
			System.out.println("아이디 : " + mid + " 신규 비밀번호 : " + mpw);

			// memberService로 이동
			int modifyResult = memSvc.modifyPw(mid, mpw);

			return modifyResult;
		}

		// 수정폼 - 신규 이메일 업데이트
		@RequestMapping("/modifyEmail")
		public @ResponseBody int modifyEmail(String mid, String memail) {
			// MemberController로 호출이 잘되었는지 확인
			System.out.println("/modifyEmail() 실행");

			// 넘어온 아이디 이메일 확인.
			System.out.println("아이디 : " + mid + " 신규 이메일 : " + memail);

			// memberService로 이동
			int modifyResult = memSvc.modifyEmail(mid, memail);

			return modifyResult;
		}

		// 수정폼 - 신규 전화번호 업데이트
		@RequestMapping("/modifyPhone")
		public @ResponseBody int modifyPhone(String mid, String mphone) {
			// MemberController로 호출이 잘되었는지 확인
			System.out.println("/modifyPhone() 실행");

			// 넘어온 아이디 전화번호 확인.
			System.out.println("아이디 : " + mid + " 신규 전화번호 : " + mphone);

			// memberService로 이동
			int modifyResult = memSvc.modifyPhone(mid, mphone);

			return modifyResult;
		}

		// 수정폼 - 신규 주소 업데이트
		@RequestMapping("/modifyAddress")
		public @ResponseBody int modifyAddress(String mid, AddressDTO addressDTO) {
			// MemberController로 호출이 잘되었는지 확인
			System.out.println("/modifyAddress() 실행");

			// 넘어온 아이디 주소 확인.
			System.out.println("아이디 : " + mid);
			System.out.println(addressDTO);
			
			// memberService로 이동
			int modifyResult = memSvc.modifyAddress(mid, addressDTO);

			return modifyResult;
		}

		// 구매 확정버튼
		@RequestMapping("/buyConfirmed")
		public @ResponseBody int buyConfirmed(String mid, int o_code) {
			int buyConfirmed = memSvc.buyConfirmed(mid, o_code);
			return buyConfirmed;
		}
		
		// 구매내역 자세히보기
		@RequestMapping("/orderlistPlus")
		public ModelAndView orderlistPlus(String mid, @RequestParam(value="page",defaultValue="1")int page) {
			mav = memSvc.orderlistPlus(mid, page);
			return mav;
		}
		
		// 회원탈퇴
		@RequestMapping("/memberDelete")
		public ModelAndView memberDelete(String mid) {
			System.out.println("회원탈퇴 요청");
			System.out.println("들어온 아이디 : " + mid);
			mav = memSvc.memberDelete(mid);
			return mav;
		}
		
}

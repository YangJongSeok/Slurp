package com.Slurp.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.Slurp.dao.BoardDAO;
import com.Slurp.dao.MemberDAO;
import com.Slurp.dto.AddressDTO;
import com.Slurp.dto.AdminReportDTO;
import com.Slurp.dto.CompanyDTO;
import com.Slurp.dto.GoodsDTO;
import com.Slurp.dto.MembersDTO;
import com.Slurp.dto.MembersInfoDTO;
import com.Slurp.dto.OrderlistDTO;
import com.Slurp.dto.PageDTO;
import com.Slurp.dto.QnaDTO;
import com.Slurp.dto.ReplygoodsDTO;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memDAO;

	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private HttpSession session;

	/************************ 로그인 ************************/

	// 회원 로그인
	public ModelAndView memberLogin(MembersDTO mdto) {
		ModelAndView mav = new ModelAndView();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String mid = mdto.getMid();
		String inputPw = mdto.getMpw();
		String mpw = memDAO.getMemberPw(mid);
		System.out.println("입력된 비번 : " + inputPw);
		System.out.println("DB에 저장된 암호화 비번 : " + mpw);
		if (passwordEncoder.matches(inputPw, mpw)) {
			System.out.println("계정정보 일치");
			String memberId = memDAO.memberLogin(mdto);
			System.out.println("로그인된 아이디 : " + memberId);
			session.setAttribute("loginId", memberId);
			session.setAttribute("authority", 3);
			mav.setViewName("home");
		} else {
			System.out.println("계정정보 불일치");
			mav.addObject("msg", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
			mav.setViewName("Member/member/loginForm");
		}
		return mav;
	}

	// 업체 로그인
	public ModelAndView companyLogin(CompanyDTO cdto) {
		ModelAndView mav = new ModelAndView();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String inputPw = cdto.getCpw();
		String ccheck = memDAO.companyCcheck(cdto.getCid());
		
		String cpw = memDAO.getCompanyPw(cdto);
		System.out.println("입력된 비번 : " + inputPw);
		System.out.println("DB에 저장된 암호화 비번 : " + cpw);
		if (passwordEncoder.matches(inputPw, cpw)) {
			System.out.println("계정정보 일치");
			String companyId = memDAO.companyLogin(cdto);
			String ccode = memDAO.getCcode1(cdto);
			session.setAttribute("cCode", ccode);
			session.setAttribute("ccheck", ccheck);
			session.setAttribute("loginId", companyId);
			session.setAttribute("authority", 2);
			mav.setViewName("home");
		} else {
			mav.addObject("msg", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
			mav.setViewName("Member/company/companyLoginForm");
		}

		return mav;
	}

	// 관리자 로그인
	public ModelAndView adminLogin(String aid, String apw) {
		ModelAndView mav = new ModelAndView();
		String adminId = memDAO.adminLogin(aid, apw);

		if (adminId != null) {
			session.setAttribute("loginId", adminId);
			session.setAttribute("authority", 1);
			mav.setViewName("home");
		} else {
			mav.addObject("msg", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
			mav.setViewName("Member/admin/adminLoginForm");
		}
		return mav;
	}

	// 업체승인 페이지 이동(페이징 처리)
	public ModelAndView companyCheckForm(int page) {
		ModelAndView mav = new ModelAndView();

		int pageLimit = 10; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<CompanyDTO> cList = memDAO.getCompanyList(pdto);

		int companyCheckListCount = memDAO.getCompanyCheckListCount(); // 승인필요한 업체의 전체 개수

		int maxPage = (int) (Math.ceil((double) companyCheckListCount / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("cList", cList);
		mav.addObject("page", pdto);
		mav.setViewName("Member/company/companyCheckForm");
		return mav;
	}

	// 업체승인 요청
	public ModelAndView companyAccept(String ccode) {
		ModelAndView mav = new ModelAndView();
		int acceptResult = memDAO.acceptResult(ccode);
		if (acceptResult > 0) {

			mav.setViewName("redirect:/companyCheckForm");
		} else {
			mav.addObject("msg", "승인실패");
			mav.setViewName("back");
		}
		return mav;
	}

	// 업체승인 거절 요청(업체 삭제)
	public ModelAndView companyRefusal(String ccode) {
		ModelAndView mav = new ModelAndView();
		int refusalResult = memDAO.refusalResult(ccode);
		if (refusalResult > 0) {

			mav.setViewName("redirect:/companyCheckForm");
		} else {
			mav.addObject("msg", "승인실패");
			mav.setViewName("back");
		}
		return mav;
	}
	
	// 업체 상품관리 페이지 이동
	public ModelAndView companyManageForm(int page,String cid) {
		ModelAndView mav = new ModelAndView();
		
		int pageLimit = 20; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<GoodsDTO> cgList = memDAO.getCompanyGoodsList(startRow,endRow,cid);

		int companyGoodsCount = memDAO.getCompanyGoodsCount(cid); 

		int maxPage = (int) (Math.ceil((double) companyGoodsCount / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("cgList", cgList);
		mav.addObject("page", pdto);
		mav.setViewName("Member/company/companyManageForm");
		
		return mav;
	}
	
	// Admin  신고내역 화면 이동
	public ModelAndView adminReportForm(int page) {
		ModelAndView mav = new ModelAndView();
		
		int pageLimit = 10; // 한페이지에 보여줄 글의 개수
		int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
		int startRow = (page - 1) * pageLimit + 1;
		int endRow = page * pageLimit;

		PageDTO pdto = new PageDTO();
		pdto.setStartrow(startRow);
		pdto.setEndrow(endRow);

		ArrayList<AdminReportDTO> rList = memDAO.getReportList(pdto);

		int reportCount = memDAO.getReportCount(); 

		int maxPage = (int) (Math.ceil((double) reportCount / pageLimit));
		int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
		int endPage = startPage + pageNumLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pdto.setPage(page);
		pdto.setStartpage(startPage);
		pdto.setEndpage(endPage);
		pdto.setMaxpage(maxPage);

		mav.addObject("rList", rList);
		mav.addObject("page", pdto);
		mav.setViewName("Member/admin/adminReportForm");
		
		return mav;
	}

	// 카카오 토큰 발급 요청
	public String getAccessToken(String code) {
		String access_token = "";
		String refresh_token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=61c5d95039b92ab8746d78d46afce77b");
			sb.append("&redirect_url=http://182.219.216.178:8090/Slurp/login");
			sb.append("&code=" + code);
			bw.write(sb.toString());
			bw.flush();

			// 결과코드가 200이면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON 타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON피싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			access_token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

			System.out.println("access_token : " + access_token);
			System.out.println("refresh_token : " + refresh_token);

			br.close();
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return access_token;
	}

	// 토큰 이용 정보 가져오기
	public HashMap<String, Object> getUserInfo(String access_token) {
		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			
			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String id = element.getAsJsonObject().get("id").getAsString();

			userInfo.put("nickname", nickname);
			userInfo.put("id", id);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	// 카카오 로그아웃
	public void kakaoLogout(String access_token) {
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + access_token);
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String result = "";
			String line = "";
			
			while((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response Body : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 신고된 평가 출력 Ajax
	public ReplygoodsDTO goodsReply(int rr_num) {
		ReplygoodsDTO goodsReply = memDAO.goodsReply(rr_num);
		return goodsReply;
	}
	
	// 신고내역 승인
	public ModelAndView reportAccess(int r_num) {
		ModelAndView mav = new ModelAndView();
		// 신고 삭제
		int deleteReport = memDAO.deleteReport(r_num);
		if(deleteReport > 0) {
			System.out.println("신고 삭제 성공");
			String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Slurp\\resources\\images\\review\\";
			// 이미지 출력 후 삭제
			ReplygoodsDTO replyImg = memDAO.getReplyImg(r_num);
			if(replyImg != null) {
				if(replyImg.getR_img1() != null) {
					File file = new File(path,replyImg.getR_img1());
					file.delete();
				}else if(replyImg.getR_img2() != null){
					File file = new File(path,replyImg.getR_img2());
					file.delete();
				}else if(replyImg.getR_img3() != null){
					File file = new File(path,replyImg.getR_img3());
					file.delete();
				}
			}
			// 평가 삭제
			int deleteReplyGoods = memDAO.deleteReplyGoods(r_num);
			if(deleteReplyGoods > 0) {
				System.out.println("평가 삭제 성공");
				// 삭제된 평가의 모든 신고글 삭제
				int deleteReplyReport = memDAO.deleteReplyReport(r_num);
				if(deleteReplyReport > 0) {
					System.out.println("삭제된 평가 신고글 전체 삭제 성공");
					mav.setViewName("redirect:/adminReportForm");
				}else {
					System.out.println("삭제된 평가 신고글 없음");
					mav.setViewName("redirect:/adminReportForm");
				}
			}else {
				System.out.println("평가 삭제 실패");
				mav.setViewName("redirect:/adminReportForm");
			}
		}else {
			System.out.println("신고 삭제 실패");
			mav.setViewName("redirect:/adminReportForm");
		}
		return mav;
	}
	
	// 신고내역 거절
	public ModelAndView reportRefusal(int r_num) {
		ModelAndView mav = new ModelAndView();
		int deleteReport = memDAO.deleteReport(r_num);
		if(deleteReport > 0) {
			System.out.println("신고내역 삭제 성공");
			mav.setViewName("redirect:/adminReportForm");
		}else {
			System.out.println("신고내역 삭제 실패");
			mav.setViewName("redirect:/adminReportForm");
		}
		return mav;
	}
	

	/******************************** 회원가입 ****************************/

	// 일반회원 가입
	public ModelAndView memberJoin(MembersDTO membersDTO, AddressDTO addressDTO) {
		ModelAndView mav = new ModelAndView();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		// 넘어온 데이터 확인
		System.out.println(membersDTO);

		System.out.println("비밀번호 : " + membersDTO.getMpw());
		String password = membersDTO.getMpw();
		String encryptPassword = passwordEncoder.encode(password);
		System.out.println("암호화된 비밀번호 : " + encryptPassword);
		membersDTO.setMpw(encryptPassword);

		// 주소값 합치기
		String madd = "[" + addressDTO.getPostcode() + "]" + " " + addressDTO.getAddress() + " "
				+ addressDTO.getDetailAddress() + " " + addressDTO.getExtraAddress();
		System.out.println("주소 : " + madd);
		membersDTO.setMadd(madd);

		// 회원가입
		int insertResult = memDAO.memberJoin(membersDTO);
		if (insertResult > 0) {
			insertResult = memDAO.membershipJoin(membersDTO.getMid());
			if (insertResult > 0) {
				System.out.println("일반회원 가입성공");
				mav.addObject("msg", "회원가입 되었습니다.");
				mav.addObject("url", "/Slurp");
				mav.setViewName("Success");
			}
		} else {
			System.out.println("일반회원 가입실패");
			mav.addObject("msg", "가입에 실패하셨습니다.");
			mav.setViewName("Fail");
		}
		return mav;
	}

	// 일반회원 아이디 중복체크
	public int midCheck(String mid) {
		int idCheck = 0;

		// 아이디 Select
		String idChecking = memDAO.midCheck(mid);
		System.out.println("찾은 아이디 값 : " + idChecking);

		// 사용가능 판단
		if (idChecking != null) {
			System.out.println("사용불가");
			idCheck = 1;
		} else {
			System.out.println("사용가능");
		}
		return idCheck;
	}

	// 업체회원 가입
	public ModelAndView companyJoin(CompanyDTO companyDTO, AddressDTO addressDTO) {
		ModelAndView mav = new ModelAndView();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		System.out.println("비밀번호 : " + companyDTO.getCpw());
		String password = companyDTO.getCpw();
		String encryptPassword = passwordEncoder.encode(password);
		System.out.println("암호화된 비밀번호 : " + encryptPassword);
		companyDTO.setCpw(encryptPassword);

		// 주소값 합치기
		String caddr = "[" + addressDTO.getPostcode() + "]" + " " + addressDTO.getAddress() + " "
				+ addressDTO.getDetailAddress() + " " + addressDTO.getExtraAddress();
		System.out.println("주소 : " + caddr);
		companyDTO.setCaddr(caddr);

		/************ 업체코드 **********/
		// 업체코드 맨 앞자리 영어
		String ccode = "C";
		// 업체코드 숫자의 제일 큰 값을 찾음
		String maxCcode = memDAO.maxCcode();

		// 코드 생성
		if (maxCcode.equals("0")) {
			ccode = ccode + "000" + 1;
			companyDTO.setCcode(ccode);
		} else {
			// 업체코드 찾은 값에서 숫자만 떼어냄
			maxCcode = maxCcode.substring(1);
			// 숫자를 String -> int타입으로 변경 후 1 더함
			int maxCcodeNum = Integer.parseInt(maxCcode);
			maxCcodeNum += 1;

			if (maxCcodeNum < 10) {
				ccode = ccode + "000" + maxCcodeNum;
			} else if (maxCcodeNum < 100) {
				ccode = ccode + "00" + maxCcodeNum;
			} else if (maxCcodeNum < 1000) {
				ccode = ccode + "0" + maxCcodeNum;
			} else if (maxCcodeNum < 10000) {
				ccode = ccode + maxCcodeNum;
			} else {
				System.out.println("업체 허용 수 초과");
				mav.addObject("msg", "업체 허용 수가 초과되었습니다. 관리자에게 문의하세요.");
				mav.setViewName("Fail");
			}
			System.out.println("등록될 업체코드 : " + ccode);
			companyDTO.setCcode(ccode);
		}
		/******** 업체코드 종료 *******/

		// 회원가입 진행
		int insertResult = memDAO.companyJoin(companyDTO);
		if (insertResult > 0) {
			System.out.println("업체회원 가입성공");
			mav.addObject("msg", "회원가입 되었습니다. 관리자의 승인 후 이용가능합니다.");
			mav.addObject("url", "/Slurp");
			mav.setViewName("Success");
		} else {
			System.out.println("업체회원 가입실패");
			mav.addObject("msg", "가입에 실패하셨습니다.");
			mav.setViewName("Fail");
		}
		return mav;
	}

	// 업체회원 아이디 중복체크
	public int cidCheck(String cid) {
		int idCheck = 0;

		// 아이디 Select
		String idChecking = memDAO.cidCheck(cid);
		System.out.println("찾은 아이디 값 : " + idChecking);

		// 사용가능 판단
		if (idChecking != null) {
			System.out.println("사용불가");
			idCheck = 1;
		} else {
			System.out.println("사용가능");
		}
		return idCheck;
	}
	
	// 카카오 회원체크
	public String kakaoCheck(String userId) {
		System.out.println(userId);
		String kakaoCheck = memDAO.kakaoCheck(userId);
		return kakaoCheck;
	}
	// 카카오 회원 가입
	public int kakaoRegist(HashMap<String, Object> userInfo) {
		System.out.println("카카오 회원가입");
		String mid = (String) userInfo.get("id");
		String nickname = (String) userInfo.get("nickname");
		
		int kakaoRegist = memDAO.kakaoRegist(mid,nickname);
		kakaoRegist = memDAO.membershipJoin(mid);
		return kakaoRegist;
	}
	
	// 주문 접수
			public ModelAndView companyDeliveryCheck(String cid, int page) {
				ModelAndView mav = new ModelAndView();
				
				int pageLimit = 10; // 한페이지에 보여줄 글의 개수
				int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
				int startRow = (page-1) * pageLimit + 1;
				int endRow = page * pageLimit;
				
				PageDTO pdto = new PageDTO();
				pdto.setStartrow(startRow);
				pdto.setEndrow(endRow);
				
				String ccode = memDAO.getCcode2(cid);
				ArrayList<OrderlistDTO> orderlistDTO = memDAO.getOrderlist(ccode);
				
				int companyCheckListCount = memDAO.getCompanyCheckListCount2(ccode); // 승인필요한 업체의 전체 개수
				
				int maxPage = (int)(Math.ceil((double)companyCheckListCount/pageLimit));
				int startPage = ((int)(Math.ceil((double)page/pageNumLimit)) - 1) * pageNumLimit + 1;
				int endPage = startPage + pageNumLimit - 1;
				
				if(endPage > maxPage) {
					endPage = maxPage;
				}
				
				pdto.setPage(page);
				pdto.setStartpage(startPage);
				pdto.setEndpage(endPage);
				pdto.setMaxpage(maxPage);
				
				mav.addObject("orderlistDTO", orderlistDTO);
				mav.addObject("page", pdto);
				mav.setViewName("Member/company/companyDeliveryCheck");
				return mav;
			}

			// 주문접수
			public ModelAndView deliveryCheck(int o_code, String cid) {
				ModelAndView mav = new ModelAndView();
				
				int updateResult = memDAO.deliveryCheck(o_code);
				
				if(updateResult > 0) {
					mav.setViewName("redirect:/companyDeliveryCheck?cid="+cid);
				}else {
					mav.addObject("msg", "주문정보 변경 실패");
					mav.setViewName("back");
				}
				return mav;
			}
	
			/******************* 마이페이지 7/14 이후 수정 ********************/

			// 회원정보 보기
			public ModelAndView showMemberInfo(String loginId) {
				System.out.println("/showMemberInfo() 실행");

				ModelAndView mav = new ModelAndView();
				// MembersDTO 객체를 만들어 DAO를 이용하여 가져온 회원정보를 저장
				MembersInfoDTO memDTO = memDAO.showMemberInfo(loginId);

				// 데이터 확인
				System.out.println("회원정보 memDTO : " + memDTO);

				// 가져온 정보를 객체명 memInfo에 저장
				mav.addObject("memInfo", memDTO);
				// 마이페이지 화면으로
				
				/************ 구매내역 불러오기 ************/
				System.out.println("/showOrderList() 실행");

				// MembersDTO 객체를 만들어 DAO를 이용하여 가져온 회원정보를 저장
				ArrayList<OrderlistDTO> orderList = memDAO.showOrderList(loginId);

				// 데이터 확인
				System.out.println("회원정보 orderList : " + orderList);

				// 가져온 구매목록을 객체명 purchaseList에 저장
				mav.addObject("orderList", orderList);
				
				/************ 질문내역 불러오기 ************/
				// qna목록을 가져오기위해 ArrayList 사용
				ArrayList<QnaDTO> qnaList = boardDAO.getQnaList(loginId);
				// 가져온 데이터 확인
				System.out.println("qnaList : " + qnaList);
				
				// ArrayList를 저장
				mav.addObject("qnaList", qnaList);
				mav.setViewName("/Member/member/myInfoForm");
				
				return mav;
			}

			// 회원정보 수정폼으로 이동
			public ModelAndView myInfoModifyForm(String loginId) {
				// TODO Auto-generated method stub
				System.out.println("Service 실행");

				ModelAndView mav = new ModelAndView();
				// MembersDTO 객체를 만들어 DAO를 이용하여 가져온 회원정보를 저장
				MembersInfoDTO memDTO = memDAO.myInfoModifyForm(loginId);

				// 데이터 확인
				System.out.println("memDTO : " + memDTO);

				// 가져온 정보를 객체명 memInfo에 저장
				mav.addObject("memInfo", memDTO);
				// 회원정보 수정 화면으로
				mav.setViewName("/Member/member/myInfoModifyForm");
				return mav;
			}

			// 수정폼 - 현재 비밀번호 일치하는지 확인
			public int pwCheck(String mid, String mpw) {
				// TODO Auto-generated method stub
				System.out.println("Service 실행");

				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String inputPw = mpw;
				String encPw = memDAO.getMemberPw(mid);
				System.out.println("입력된 비번 : " + inputPw);
				System.out.println("DB에 저장된 암호화 비번 : " + encPw);
				int checkResult = 0;
				if (passwordEncoder.matches(inputPw, encPw)) {
					int pwChecking = memDAO.pwCheck(mid, encPw);
					if (pwChecking > 0) {
						checkResult = 1;
					}
				}

				return checkResult;
			}

			// 수정폼 - 신규 비밀번호 업데이트
			public int modifyPw(String mid, String mpw) {
				// TODO Auto-generated method stub
				System.out.println("Service 실행");

				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String inputPw = mpw;
				String encPw = passwordEncoder.encode(inputPw);
				System.out.println("변경할 입력된 비번 : " + inputPw);
				System.out.println("암호화된 입력된 비밀번호 : " + encPw);
				int modifyResult = memDAO.modifyPw(mid, encPw);

				System.out.println("업데이트 결과 : " + modifyResult);
				return modifyResult;
			}

			// 수정폼 - 신규 이메일 업데이트
			public int modifyEmail(String mid, String memail) {
				// TODO Auto-generated method stub
				System.out.println("Service 실행");

				int modifyResult = memDAO.modifyEmail(mid, memail);

				System.out.println("업데이트 결과 : " + modifyResult);
				return modifyResult;
			}

			// 수정폼 - 신규 전화번호 업데이트
			public int modifyPhone(String mid, String mphone) {
				// TODO Auto-generated method stub
				System.out.println("Service 실행");

				int modifyResult = memDAO.modifyPhone(mid, mphone);

				System.out.println("업데이트 결과 : " + modifyResult);
				return modifyResult;
			}

			// 수정폼 - 신규 주소 업데이트
			public int modifyAddress(String mid, AddressDTO addressDTO) {
				System.out.println("Service 실행");
				// 주소값 합치기
				String madd = "[" + addressDTO.getPostcode() + "]" + " " + addressDTO.getAddress() + " "
						+ addressDTO.getDetailAddress() + addressDTO.getExtraAddress();
				System.out.println("주소 : " + madd);

				int modifyResult = memDAO.modifyAddress(mid, madd);

				System.out.println("업데이트 결과 : " + modifyResult);
				return 1;
			}

			// 구매 확정
			public int buyConfirmed(String mid, int o_code) {
				int buyConfirmed = 0;
				int updateResult = memDAO.buyConfirmed(mid, o_code);
				
				if(updateResult > 0) {
					buyConfirmed = 1;
				}
				return buyConfirmed;
			}

			// 구매내역 자세히보기
			public ModelAndView orderlistPlus(String mid, int page) {
				ModelAndView mav = new ModelAndView();
				String loginId = mid;
				
				int pageLimit = 10; // 한페이지에 보여줄 글의 개수
				int pageNumLimit = 5; // 한페이지에 보여줄 페이지 번호의 개수
				int startRow = (page - 1) * pageLimit + 1;
				int endRow = page * pageLimit;

				PageDTO pdto = new PageDTO();
				pdto.setStartrow(startRow);
				pdto.setEndrow(endRow);

				
				// MembersDTO 객체를 만들어 DAO를 이용하여 가져온 회원정보를 저장
				ArrayList<OrderlistDTO> orderList = memDAO.showOrderList2(loginId, startRow, endRow);

				int orderlistListCount = memDAO.getMemberOrderlist(loginId); // 승인필요한 업체의 전체 개수

				int maxPage = (int) (Math.ceil((double) orderlistListCount / pageLimit));
				int startPage = ((int) (Math.ceil((double) page / pageNumLimit)) - 1) * pageNumLimit + 1;
				int endPage = startPage + pageNumLimit - 1;

				if (endPage > maxPage) {
					endPage = maxPage;
				}

				pdto.setPage(page);
				pdto.setStartpage(startPage);
				pdto.setEndpage(endPage);
				pdto.setMaxpage(maxPage);

				// 데이터 확인
				System.out.println("회원정보 orderList : " + orderList);

				// 가져온 구매목록을 객체명 purchaseList에 저장
				mav.addObject("page", pdto);
				mav.addObject("orderList", orderList);
				mav.setViewName("Member/member/orderListPlus");
				return mav;
			}

			// 회원탈퇴
			public ModelAndView memberDelete(String mid) {
				ModelAndView mav = new ModelAndView();
				memDAO.deleteMembership(mid);
				memDAO.deleteMember(mid);
				memDAO.deleteCart(mid);
				memDAO.deleteWist(mid);
				session.invalidate();
				mav.setViewName("home");
				return mav;
			}

}

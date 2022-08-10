package com.Slurp.controller;

import java.text.DateFormat;
import java.util.ArrayList;
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

import com.Slurp.dto.GoodsDTO;
import com.Slurp.service.GoodsService;
import com.google.gson.Gson;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private ModelAndView mav;
	
	@Autowired
	private GoodsService goodsSvc;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	/* 메인화면으로 이동 home */
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	/* 메인화면에 New 리스트 출력 ajax */
	@RequestMapping("/newList")
	public @ResponseBody String newList() {
		ArrayList<GoodsDTO> newList = goodsSvc.getNewList();
		Gson gson = new Gson();
		String json_newList = gson.toJson(newList);
		return json_newList;
	}
	/* 메인화면에 Best 리스트 출력 ajax */
	@RequestMapping("/bestList")
	public @ResponseBody String bestList() {
		ArrayList<GoodsDTO> bestList = goodsSvc.getBestList();
		Gson gson = new Gson();
		String json_newList = gson.toJson(bestList);
		return json_newList;
	}
	/* 회원가입 화면으로 이동 registForm */
	@RequestMapping("/registForm")
	public String registForm() {
		return "Member/member/registForm";
	}
	/* 업체등록 화면으로 이동 companyRegist */
	@RequestMapping("/companyRegist")
	public String companyRegist() {
		return "Member/company/companyRegistForm";
	}
	/* 로그인 화면으로 이동 loginForm */
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "Member/member/loginForm";
	}
	/* 업체 로그인 화면으로 이동 companyLoginForm */
	@RequestMapping("/companyLoginForm")
	public String companyLoginForm() {
		return "Member/company/companyLoginForm";
	}
	/* 상품등록 화면으로 이동 registerGoodsForm */
	@RequestMapping("/registerGoodsForm")
	public String registerGoodsForm() {
		return "Member/company/registerGoodsForm";
	}
	/* 상품재고 화면로 이동 registerGoodsCountForm */
	@RequestMapping("/registerGoodsCountForm")
	public ModelAndView registerGoodsCountForm(String gcode) {
		mav = goodsSvc.registerGoodsCountForm(gcode);
		return mav;
	}
	/* 관리자 로그인 화면으로 이동 adminLoginForm */
	@RequestMapping("/adminLoginForm")
	public String adminLoginForm() {
		return "Member/admin/adminLoginForm";
	}
	/* 내정보 보기 화면으로 이동 myInfoForm */
	@RequestMapping("/myInfoForm")
	public String myInfoForm() {
		return "Member/member/myInfoForm";
	}
	
	

	/***********************************************/

	@RequestMapping("/man_top")// 남성 - 상의 이동
	public ModelAndView man_top(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_top(page);
		return mav;
	}
	
	@RequestMapping("/man_top_best")// 남성 - 상의 인기순
	public ModelAndView man_top_best(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_top_best(page);
		return mav;
	}
	
	@RequestMapping("/man_top_new")// 남성 - 상의 등록일순
	public ModelAndView man_top_new(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_top_new(page);
		return mav;
	}
	
	@RequestMapping("/man_top_row")// 남성 - 상의 낮은가격순
	public ModelAndView man_top_row(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_top_row(page);
		return mav;
	}
	
	@RequestMapping("/man_top_high")// 남성 - 상의 높은가격순
	public ModelAndView man_top_high(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_top_high(page);
		return mav;
	}
	
	@RequestMapping("/man_pants")// 남성 - 하의 이동
	public ModelAndView man_pants(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_pants(page);
		return mav;
	}
	
	@RequestMapping("/man_pants_best")// 남성 - 하의 인기순
	public ModelAndView man_pants_best(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_pants_best(page);
		return mav;
	}
	
	@RequestMapping("/man_pants_new")// 남성 - 하의 등록일순
	public ModelAndView man_pants_new(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_pants_new(page);
		return mav;
	}
	
	@RequestMapping("/man_pants_row")// 남성 - 하의 낮은가격순
	public ModelAndView man_pants_row(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_pants_row(page);
		return mav;
	}
	
	@RequestMapping("/man_pants_high")// 남성 - 하의 높은가격순
	public ModelAndView man_pants_high(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_pants_high(page);
		return mav;
	}
	
	@RequestMapping("/man_shoes")// 남성 - 신발 이동
	public ModelAndView man_shoes(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_shoes(page);
		return mav;
	}
	
	@RequestMapping("/man_shoes_best")// 남성 - 신발 인기순
	public ModelAndView man_shoes_best(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_shoes_best(page);
		return mav;
	}
	
	@RequestMapping("/man_shoes_new")// 남성 - 신발 등록일순
	public ModelAndView man_shoes_new(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_shoes_new(page);
		return mav;
	}
	
	@RequestMapping("/man_shoes_row")// 남성 - 신발 낮은가격순
	public ModelAndView man_shoes_row(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_shoes_row(page);
		return mav;
	}
	
	@RequestMapping("/man_shoes_high")// 남성 - 신발 높은가격순
	public ModelAndView man_shoes_high(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_shoes_high(page);
		return mav;
	}
	
	@RequestMapping("/man_outer")// 남성 - 아우터 이동
	public ModelAndView man_outer(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_outer(page);
		return mav;
	}
	
	@RequestMapping("/man_outer_best")// 남성 - 아우터 인기순
	public ModelAndView man_outer_best(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_outer_best(page);
		return mav;
	}
	
	@RequestMapping("/man_outer_new")// 남성 - 아우터 등록일순
	public ModelAndView man_outer_new(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_outer_new(page);
		return mav;
	}
	
	@RequestMapping("/man_outer_row")// 남성 - 아우터 낮은가격순
	public ModelAndView man_outer_row(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_outer_row(page);
		return mav;
	}
	
	@RequestMapping("/man_outer_high")// 남성 - 아우터 높은가격순
	public ModelAndView man_outer_high(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.man_outer_high(page);
		return mav;
	}
// ---------------------------------------------------------------------------------------------------------
	@RequestMapping("/woman_top")// 여성 - 상의 이동
	public ModelAndView woman_top(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_top(page);
		return mav;
	}
	
	@RequestMapping("/woman_top_best")// 여성 - 상의 인기순
	public ModelAndView woman_top_best(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_top_best(page);
		return mav;
	}
	
	@RequestMapping("/woman_top_new")// 여성 - 상의 등록일순
	public ModelAndView woman_top_new(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_top_new(page);
		return mav;
	}
	
	@RequestMapping("/woman_top_row")// 여성 - 상의 낮은가격순
	public ModelAndView woman_top_row(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_top_row(page);
		return mav;
	}
	
	@RequestMapping("/woman_top_high")// 여성 - 상의 높은가격순
	public ModelAndView woman_top_high(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_top_high(page);
		return mav;
	}
	
	@RequestMapping("/woman_pants")// 여성 - 하의 이동
	public ModelAndView woman_pants(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_pants(page);
		return mav;
	}
	
	@RequestMapping("/woman_pants_best")// 여성 - 하의 인기순
	public ModelAndView woman_pants_best(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_pants_best(page);
		return mav;
	}
	
	@RequestMapping("/woman_pants_new")// 여성 - 하의 등록일순
	public ModelAndView woman_pants_new(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_pants_new(page);
		return mav;
	}
	
	@RequestMapping("/woman_pants_row")// 여성 - 하의 낮은가격순
	public ModelAndView woman_pants_row(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_pants_row(page);
		return mav;
	}
	
	@RequestMapping("/woman_pants_high")// 여성 - 하의 높은가격순
	public ModelAndView woman_pants_high(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_pants_high(page);
		return mav;
	}
	
	@RequestMapping("/woman_shoes")// 여성 - 신발 이동
	public ModelAndView woman_shoes(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_shoes(page);
		return mav;
	}
	
	@RequestMapping("/woman_shoes_best")// 여성 - 신발 인기순
	public ModelAndView woman_shoes_best(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_shoes_best(page);
		return mav;
	}
	
	@RequestMapping("/woman_shoes_new")// 여성 - 신발 등록일순
	public ModelAndView woman_shoes_new(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_shoes_new(page);
		return mav;
	}
	
	@RequestMapping("/woman_shoes_row")// 여성 - 신발 낮은가격순
	public ModelAndView woman_shoes_row(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_shoes_row(page);
		return mav;
	}
	
	@RequestMapping("/woman_shoes_high")// 여성 - 신발 높은가격순
	public ModelAndView woman_shoes_high(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_shoes_high(page);
		return mav;
	}
	
	
	@RequestMapping("/woman_outer")// 여성 - 아우터 이동
	public ModelAndView woman_outer(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_outer(page);
		return mav;
	}
	
	@RequestMapping("/woman_outer_best")// 여성 - 아우터 인기순
	public ModelAndView woman_outer_best(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_outer_best(page);
		return mav;
	}
	
	@RequestMapping("/woman_outer_new")// 여성 - 아우터 등록일순
	public ModelAndView woman_outer_new(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_outer_new(page);
		return mav;
	}
	
	@RequestMapping("/woman_outer_row")// 여성 - 아우터 낮은가격순
	public ModelAndView woman_outer_row(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_outer_row(page);
		return mav;
	}
	
	@RequestMapping("/woman_outer_high")// 여성 - 아우터 높은가격순
	public ModelAndView woman_outer_high(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.woman_outer_high(page);
		return mav;
	}
// ---------------------------------------------------------------------------------------------------------
	@RequestMapping("/musinsa")// 브랜드관 - 무신사 스탠다드 이동
	public ModelAndView musinsa(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.musinsa(page);
		return mav;
	}
	
	@RequestMapping("/musinsa_best")// 브랜드관 - 무신사 스탠다드 인기순
	public ModelAndView musinsa_best(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.musinsa_best(page);
		return mav;
	}
	
	@RequestMapping("/musinsa_new")// 브랜드관 - 무신사 스탠다드 최신순
	public ModelAndView musinsa_new(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.musinsa_new(page);
		return mav;
	}
	
	@RequestMapping("/musinsa_row")// 브랜드관 - 무신사 스탠다드 낮은 가격순
	public ModelAndView musinsa_row(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.musinsa_row(page);
		return mav;
	}
	
	@RequestMapping("/musinsa_high")// 브랜드관 - 무신사 스탠다드 높은 가격순
	public ModelAndView musinsa_high(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.musinsa_high(page);
		return mav;
	}
	
	@RequestMapping("/nike")// 브랜드관 - 나이키 이동
	public ModelAndView nike(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.nike(page);
		return mav;
	}
	
	@RequestMapping("/nike_best")// 브랜드관 - 나이키 인기순
	public ModelAndView nike_best(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.nike_best(page);
		return mav;
	}
	
	@RequestMapping("/nike_new")// 브랜드관 - 나이키 최신순
	public ModelAndView nike_new(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.nike_new(page);
		return mav;
	}
	
	@RequestMapping("/nike_row")// 브랜드관 - 나이키 낮은가격순
	public ModelAndView nike_row(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.nike_row(page);
		return mav;
	}
	
	@RequestMapping("/nike_high")// 브랜드관 - 나이키 높은가격순
	public ModelAndView nike_high(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.nike_high(page);
		return mav;
	}
	
	@RequestMapping("/adidas")// 브랜드관 - 아디다스 이동
	public ModelAndView adidas(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.adidas(page);
		return mav;
	}
	
	@RequestMapping("/adidas_best")// 브랜드관 - 아디다스 인기순
	public ModelAndView adidas_best(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.adidas_best(page);
		return mav;
	}
	
	@RequestMapping("/adidas_new")// 브랜드관 - 아디다스 최신순
	public ModelAndView adidas_new(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.adidas_new(page);
		return mav;
	}
	
	@RequestMapping("/adidas_row")// 브랜드관 - 아디다스 낮은가격순
	public ModelAndView adidas_row(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.adidas_row(page);
		return mav;
	}
	
	@RequestMapping("/adidas_high")// 브랜드관 - 아디다스 높은가격순
	public ModelAndView adidas_high(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.adidas_high(page);
		return mav;
	}
	
	@RequestMapping("/zara")// 브랜드관 - 자라 이동
	public ModelAndView zara(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.zara(page);
		return mav;
	}
	
	@RequestMapping("/zara_best")// 브랜드관 - 자라 인기순
	public ModelAndView zara_best(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.zara_best(page);
		return mav;
	}
	
	@RequestMapping("/zara_new")// 브랜드관 - 자라 최신순
	public ModelAndView zara_new(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.zara_new(page);
		return mav;
	}
	
	@RequestMapping("/zara_row")// 브랜드관 - 자라 낮은가격순
	public ModelAndView zara_row(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.zara_row(page);
		return mav;
	}
	
	@RequestMapping("/zara_high")// 브랜드관 - 자라 높은가격순
	public ModelAndView zara_high(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.zara_high(page);
		return mav;
	}
	
	@RequestMapping("/giordano")// 브랜드관 - 지오다노 이동
	public ModelAndView giordano(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.giordano(page);
		return mav;
	}
	
	@RequestMapping("/giordano_best")// 브랜드관 - 지오다노 인기순
	public ModelAndView giordano_best(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.giordano_best(page);
		return mav;
	}
	
	@RequestMapping("/giordano_new")// 브랜드관 - 지오다노 최신순
	public ModelAndView giordano_new(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.giordano_new(page);
		return mav;
	}
	
	@RequestMapping("/giordano_row")// 브랜드관 - 지오다노 낮은가격순
	public ModelAndView giordano_row(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.giordano_row(page);
		return mav;
	}
	
	@RequestMapping("/giordano_high")// 브랜드관 - 지오다노 높은가격순
	public ModelAndView giordano_high(@RequestParam(value="page",defaultValue="1")int page) {
		mav = goodsSvc.giordano_high(page);
		return mav;
	}
// -----------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/best")// BEST 이동 이동
	public ModelAndView best() {
		mav = goodsSvc.goods_best();
		return mav;
	}
	
	@RequestMapping("/news")// NEW 이동
	public ModelAndView news() {
		mav = goodsSvc.goods_new();
		return mav;
	}
	
	@RequestMapping("/shopping_cart")// 장바구니 이동
	public String shopping_cart() {
		
		return "/cartwishorder/shopping_cart";
	}
	
	@RequestMapping("/purchase")// 구매화면 이동
	public String wish_list() {
		
		return "/cartwishorder/purchase";
	}
	
	
	
}

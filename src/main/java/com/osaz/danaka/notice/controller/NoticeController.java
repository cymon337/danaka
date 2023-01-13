package com.osaz.danaka.notice.controller;

import com.osaz.danaka.common.paging.Pagenation;
import com.osaz.danaka.common.paging.SelectCriteria;
import com.osaz.danaka.notice.model.dto.NoticeDTO;
import com.osaz.danaka.notice.model.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("notice")
public class NoticeController {

	private final NoticeService noticeService;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	/*
	@GetMapping : 페이지 매핑

	ModelAndView = DB에서 전달받은 결과 값 (Model)과 이동할 페이지 정보(View)를 담는 클래스
	  차이점: Model -> model.addAttribute를 사용하여 데이터만 저장
			 ModelAndView -> 데이터와 이동하고자 하는 View Page를 같이 저장 (addObject, setViewName)*/


	/*공지사항 전체보기 / 페이징 처리를 위한 총 갯수 조회와 공지사항 리스트 조회, 출력*/
	@GetMapping("noticeListView")
	public ModelAndView noticeListView(HttpServletRequest request, ModelAndView mv){

		/* 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다. 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다. */
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		/* 0보다 작은 숫자값을 입력해도 1페이지를 보여준다 */
		if(pageNo <= 0) {
			pageNo = 1;
		}

		/*검색기준과 검색어를 가져와 Map으로 담아줌*/
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		String memberCondition = request.getParameter("memberCondition");

		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		searchMap.put("memberCondition", memberCondition);

		/* 전체 게시물 수가 필요하다.
		 * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
		 * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
		 * */
		int totalCount = noticeService.selectTotalCount(searchMap);

		log.info("총 상품 수 = {}", totalCount);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10;		//얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;

		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		SelectCriteria selectCriteria = null;

		if(searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue, memberCondition);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}

		log.info("검색 조건 = {}", selectCriteria);

		/* DB 전체조회, 검색어 있을 경우 포함하여 조회 */
//		List<ProductDTO> productList = noticeService.selectListByCategory(selectCriteria);
		List<NoticeDTO> noticeList = noticeService.selectAllList(selectCriteria);
		log.info("상품 리스트 = {}" + noticeList);

		mv.addObject("notices", noticeList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("notice/noticeListView");

		return mv;
	}


//	공지사항 목록 조회
//	@GetMapping("noticeListView")
//	public ModelAndView noticeListView(ModelAndView mv) {
//
//		List<NoticeDTO> noticeList = noticeService.selectAllList();
//
////		noticeList.stream().forEach((notice -> System.out.println("notice = " + notice)));
//
//		mv.addObject("notices", noticeList);
//		mv.setViewName("notice/noticeListView");
//
//		return mv;
//	}

	/*@RequestParam = 뷰에서 전달받은 name값으로 매핑하여 값 전달 가능*/

//	공지사항 디테일뷰 (단일조회) HttpServletRequest request
	@GetMapping("noticeDetail")
	public ModelAndView selectOneNotice(@RequestParam(value = "noticeNo") String noticeNo, ModelAndView mv){

//		String noticeNo = request.getParameter("noticeNo");

		NoticeDTO notice = noticeService.selectOneNotice(noticeNo);

		if(noticeNo != null){
			noticeService.incrementNoticeCount(noticeNo);
			mv.addObject("notice", notice);
		}

		/*addObject : selectOneNotice을 통해 반환된 결과 값 notice를 mv객체에 "notice"라는 이름으로 보내줘서 뷰에서 사용 가능
		* setViewName : 페이지 이동할 경로 설정*/
		//mv.addObject("notice", notice);
		mv.setViewName("notice/noticeDetail");

		return mv;
	}

	/*공지사항 작성 페이지 연결 GET*/
	@GetMapping("noticeEnroll")
	public void insertNotice() {}

	/*공지사항 작성 처리 POST*/
	@PostMapping("noticeEnroll")
	public ModelAndView insertNotice(ModelAndView mv, NoticeDTO newNotice, RedirectAttributes rttr) {

		noticeService.insertNotice(newNotice);

		/*redirect 해줘야 POST처리 된 후 다시 값 불러와서 정상적인 화면 출력*/
		mv.setViewName("redirect:/notice/noticeListView");
		rttr.addFlashAttribute("successMessage","공지사항 등록 성공 !" );

		return mv;
	}

	/*공지사항 삭제*/
	@PostMapping("deleteNotice")
	public ModelAndView deleteNotice(@RequestParam(value = "noticeNo") String noticeNo, ModelAndView mv, RedirectAttributes rttr) {
		noticeService.deleteNotice(noticeNo);

		mv.setViewName("redirect:/notice/noticeListView");
		rttr.addFlashAttribute("successMessage","공지사항 삭제 성공 !" );

		return mv;
	}

	/*공지사항 수정 페이지 연결 GET*/
	@GetMapping("noticeModify")
	public ModelAndView updateNotice(@RequestParam(value = "noticeNo") String noticeNo, ModelAndView mv) {

		NoticeDTO notice = noticeService.selectOneNotice(noticeNo);

		mv.addObject("notice", notice);
		mv.setViewName("notice/noticeModify");

		return mv;
	}

	/*공지사항 작성 처리 POST*/
	@PostMapping("noticeModify")
	public ModelAndView updateNotice(NoticeDTO modifyNotice, ModelAndView mv, RedirectAttributes rttr) {
		noticeService.updateNotice(modifyNotice);

		mv.setViewName("redirect:/notice/noticeListView");
		rttr.addFlashAttribute("successMessage","공지사항 수정 성공 !" );

		return mv;
	}

}

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

	/*공지사항 전체보기 / 페이징 처리를 위한 총 갯수 조회와 공지사항 리스트 조회, 출력*/
	@GetMapping("noticeListView")
	public ModelAndView noticeListView(HttpServletRequest request, ModelAndView mv){

		/* 첫 페이지는 1페이지이다. 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 */
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

		/* 전체 게시물 수 조회, 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회 */
		int totalCount = noticeService.selectTotalCount(searchMap);

		log.info("총 상품 수 = {}", totalCount);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10;		//얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;

		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환 */
		SelectCriteria selectCriteria = null;

		if(searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue, memberCondition);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}

		log.info("검색 조건 = {}", selectCriteria);

		/* DB 전체조회, 검색어 있을 경우 포함하여 조회 */
		List<NoticeDTO> noticeList = noticeService.selectAllList(selectCriteria);
		log.info("상품 리스트 = {}" + noticeList);

		mv.addObject("notices", noticeList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("notice/noticeListView");

		return mv;
	}

	/* 공지사항 디테일뷰 (단일조회) */
	@GetMapping("noticeDetail")
	public ModelAndView selectOneNotice(@RequestParam(value = "noticeNo") String noticeNo, ModelAndView mv){

		NoticeDTO notice = noticeService.selectOneNotice(noticeNo);

		if(noticeNo != null){
			noticeService.incrementNoticeCount(noticeNo);
			mv.addObject("notice", notice);
		}

		mv.setViewName("notice/noticeDetail");

		return mv;
	}

	/* 공지사항 작성 페이지 연결 GET */
	@GetMapping("noticeEnroll")
	public void insertNotice() {}

	/* 공지사항 작성 처리 POST */
	@PostMapping("noticeEnroll")
	public ModelAndView insertNotice(ModelAndView mv, NoticeDTO newNotice, RedirectAttributes rttr) {

		noticeService.insertNotice(newNotice);

		mv.setViewName("redirect:/notice/noticeListView");
		rttr.addFlashAttribute("successMessage","공지사항 등록 성공 !" );

		return mv;
	}

	/* 공지사항 삭제 */
	@PostMapping("deleteNotice")
	public ModelAndView deleteNotice(@RequestParam(value = "noticeNo") String noticeNo, ModelAndView mv, RedirectAttributes rttr) {
		noticeService.deleteNotice(noticeNo);

		mv.setViewName("redirect:/notice/noticeListView");
		rttr.addFlashAttribute("successMessage","공지사항 삭제 성공 !" );

		return mv;
	}

	/* 공지사항 수정 페이지 연결 GET */
	@GetMapping("noticeModify")
	public ModelAndView updateNotice(@RequestParam(value = "noticeNo") String noticeNo, ModelAndView mv) {

		NoticeDTO notice = noticeService.selectOneNotice(noticeNo);

		mv.addObject("notice", notice);
		mv.setViewName("notice/noticeModify");

		return mv;
	}

	/* 공지사항 작성 처리 POST */
	@PostMapping("noticeModify")
	public ModelAndView updateNotice(NoticeDTO modifyNotice, ModelAndView mv, RedirectAttributes rttr) {
		noticeService.updateNotice(modifyNotice);

		mv.setViewName("redirect:/notice/noticeListView");
		rttr.addFlashAttribute("successMessage","공지사항 수정 성공 !" );

		return mv;
	}

}

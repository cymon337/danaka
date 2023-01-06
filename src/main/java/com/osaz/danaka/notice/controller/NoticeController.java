package com.osaz.danaka.notice.controller;

import com.osaz.danaka.notice.model.dto.NoticeDTO;
import com.osaz.danaka.notice.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

//	공지사항 목록 조회
	@GetMapping("noticeListView")
	public ModelAndView noticeListView(ModelAndView mv) {

		List<NoticeDTO> noticeList = noticeService.selectAllList();

//		noticeList.stream().forEach((notice -> System.out.println("notice = " + notice)));

		mv.addObject("notices", noticeList);
		mv.setViewName("notice/noticeListView");

		return mv;
	}

	/*@RequestParam = 뷰에서 전달받은 name값으로 매핑하여 값 전달 가능*/

//	공지사항 디테일뷰 (단일조회) HttpServletRequest request
	@GetMapping("noticeDetail")
	public ModelAndView selectOneNotice(@RequestParam(value = "noticeNo") String noticeNo, ModelAndView mv){

//		String noticeNo = request.getParameter("noticeNo");
		NoticeDTO notice = noticeService.selectOneNotice(noticeNo);
		noticeService.incrementNoticeCount(noticeNo);

		/*addObject : selectOneNotice을 통해 반환된 결과 값 notice를 mv객체에 "notice"라는 이름으로 보내줘서 뷰에서 사용 가능
		* setViewName : 페이지 이동할 경로 설정*/
		mv.addObject("notice", notice);
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

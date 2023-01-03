package com.osaz.danaka.notice.controller;

import com.osaz.danaka.notice.model.dto.NoticeDTO;
import com.osaz.danaka.notice.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("notice")
public class NoticeController {

	private final NoticeService noticeService;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@GetMapping("noticeListView")
	public ModelAndView noticeListView(ModelAndView mv) {

		List<NoticeDTO> noticeList = noticeService.selectAllList();

		noticeList.stream().forEach((notice -> System.out.println("notice = " + notice)));

		mv.addObject("notices", noticeList);

		mv.setViewName("notice/noticeListView");

		return mv;
	}
}

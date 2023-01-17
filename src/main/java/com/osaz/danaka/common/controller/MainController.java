package com.osaz.danaka.common.controller;

import com.osaz.danaka.notice.model.dto.NoticeDTO;
import com.osaz.danaka.notice.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final NoticeService noticeService;

    @Autowired
    public MainController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

//    /* 여러 경로들을 index페이지로 연결, 입력했을때 리턴값으로 이동 */
//    @GetMapping(value = {"/", "/main", "/main/index"})
//    public String main() {
//        return "main/index";
//    }

    /* 공지사항 디테일뷰 (최근 5개) */
    @GetMapping("/")
    public ModelAndView selectTop5Notice(ModelAndView mv){

        List<NoticeDTO> noticeList = noticeService.selectTop5Notice();

        if(noticeList != null){
            mv.addObject("notices", noticeList);
        }

        mv.setViewName("main/index");

        return mv;
    }

}


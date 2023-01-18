package com.osaz.danaka.admin.controller;

import com.osaz.danaka.admin.model.dto.AdminDTO;
import com.osaz.danaka.admin.model.service.AdminService;
import com.osaz.danaka.common.paging.Pagenation;
import com.osaz.danaka.common.paging.SelectCriteria;
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
@RequestMapping("admin")
public class AdminController {

	private final AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	// # update : 2023-01-18(최종수정)
	// # title : 회원 전체 조회
	// # author : 공성식
	// # description : 페이징 처리를 위한 총 갯수 조회와 공지사항 리스트 조회, 출력
	@GetMapping("adminMemberListView")
	public ModelAndView memberListView(HttpServletRequest request, ModelAndView mv){

		/* 첫 시작은 1페이지이다. 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 */
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		/* 0보다 작은 숫자값을 입력해도 1페이지 노출 */
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

		/* 전체 게시물 조회, 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회 */
		int totalCount = adminService.selectTotalCount(searchMap);

		log.info("총 회원 수 = {}", totalCount);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10;

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
		List<AdminDTO> memberList = adminService.selectAllList(selectCriteria);
		log.info("회원 리스트 = {}", memberList);

		mv.addObject("members", memberList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("admin/adminMemberListView");

		return mv;
	}

	// # update : 2023-01-18(최종수정)
	// # title : 회원 단일조회
	// # author : 공성식
	// # description : 회원 상세정보 출력
	@GetMapping("adminMemberDetail")
	public ModelAndView selectOneMember(@RequestParam(value = "userNo") String userNo, ModelAndView mv){

		AdminDTO member = adminService.selectOneMember(userNo);

		mv.addObject("member", member);
		log.info("회원정보 = {}", member);

		mv.setViewName("admin/adminMemberDetail");

		return mv;
	}

	// # update : 2023-01-18(최종수정)
	// # title : 회원 삭제
	// # author : 공성식
	// # description : 회원 번호를 기준으로 회원을 삭제(회원 상태변경)
	@PostMapping("deleteMember")
	public ModelAndView deleteNotice(@RequestParam(value = "userNo") String userNo, ModelAndView mv, RedirectAttributes rttr) {
		adminService.deleteMember(userNo);
		log.info("삭제할 회원번호 = {}", userNo);

		mv.setViewName("redirect:/admin/adminMemberListView");
		rttr.addFlashAttribute("successMessage","회원 삭제 성공 !" );

		return mv;
	}

	// # update : 2023-01-18(최종수정)
	// # title : 회원 정보 수정 연결
	// # author : 공성식
	// # description : 회원번호를 기준으로 상세정보를 조회하여 수정페이지에 연결하여 전달
	@GetMapping("adminMemberModify")
	public ModelAndView updateMember(@RequestParam(value = "userNo") String userNo, ModelAndView mv) {

		AdminDTO member = adminService.selectOneMember(userNo);
		log.info("회원정보 = {}", member);

		mv.addObject("member", member);
		mv.setViewName("admin/adminMemberModify");

		return mv;
	}

	// # update : 2023-01-18(최종수정)
	// # title : 회원 정보 수정 처리
	// # author : 공성식
	// # description : 입력된 값을 전달하여 정보 수정 처리
	@PostMapping("adminMemberModify")
	public ModelAndView updateNotice(AdminDTO modifyMember, ModelAndView mv, RedirectAttributes rttr) {
		adminService.updateMember(modifyMember);
		log.info("수정정보 = {}", modifyMember);

		mv.setViewName("redirect:/admin/adminMemberListView");
		rttr.addFlashAttribute("successMessage","회원정보 수정 성공 !" );

		return mv;
	}

}
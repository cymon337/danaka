package com.osaz.danaka.admin.model.service;

import com.osaz.danaka.admin.model.dao.AdminMapper;
import com.osaz.danaka.admin.model.dto.AdminDTO;
import com.osaz.danaka.common.paging.SelectCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.osaz.danaka.board.model.service
 * fileName       : boardService
 * author         : 공성식
 * date           : 2023-01-03
 * description    : 컨트롤러에서 받은 정보를 매퍼에 전달, 트랜젝션 처리
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminService {
	private final AdminMapper adminMapper;

	@Autowired
	public AdminService(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}

	// # update : 2023-01-18(최종수정)
	// # title : 총 상품 갯수 조회
	// # author : 공성식
	// # description : 페이징 처리용 총 상품 갯수 조회
	public int selectTotalCount(Map<String, String> searchMap) {
		return adminMapper.selectTotalCount(searchMap);
	}

	// # update : 2023-01-18(최종수정)
	// # title : 회원 전체 조회
	// # author : 공성식
	// # description : 회원리스트 전체 조회
	public List<AdminDTO> selectAllList(SelectCriteria selectCriteria) {
		return adminMapper.selectAllList(selectCriteria);
	}

	// # update : 2023-01-18(최종수정)
	// # title : 회원 조회
	// # author : 공성식
	// # description : 회원 상세정보 조회
	public AdminDTO selectOneMember(String userNo) {
		return adminMapper.selectOneMember(userNo);
	}

	// # update : 2023-01-18(최종수정)
	// # title : 회원 삭제
	// # author : 공성식
	// # description : 회원 정보 삭제 (회원 상태 변경)
	public void deleteMember(String userNo) {
		adminMapper.deleteMember(userNo);
	}

	// # update : 2023-01-18(최종수정)
	// # title : 회원 정보 수정
	// # author : 공성식
	// # description : 회원 정보 수정 처리
	public void updateMember(AdminDTO modifyMember) {
		adminMapper.updateMember(modifyMember);
	}

}
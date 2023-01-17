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
 * author         : MSI
 * date           : 2023-01-03
 * description    :
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminService {
	private final AdminMapper adminMapper;

	@Autowired
	public AdminService(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}

	// 페이징 처리용 총 상품 개수 조회
	public int selectTotalCount(Map<String, String> searchMap) {
		return adminMapper.selectTotalCount(searchMap);
	}

	/* 회원 전체 조회 */
	public List<AdminDTO> selectAllList(SelectCriteria selectCriteria) {
		return adminMapper.selectAllList(selectCriteria);
	}

	/* 회원 단일 조회 */
	public AdminDTO selectOneMember(String userNo) {
		return adminMapper.selectOneMember(userNo);
	}

	/* 회원 삭제 */
	public void deleteMember(String userNo) {
		adminMapper.deleteMember(userNo);
	}

	/* 회원정보 수정 */
	public void updateMember(AdminDTO modifyMember) {
		adminMapper.updateMember(modifyMember);
	}

}
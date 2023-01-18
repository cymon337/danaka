package com.osaz.danaka.notice.model.service;

import com.osaz.danaka.common.paging.SelectCriteria;
import com.osaz.danaka.notice.model.dao.NoticeMapper;
import com.osaz.danaka.notice.model.dto.NoticeDTO;
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
public class NoticeService {
	private final NoticeMapper noticeMapper;

	@Autowired
	public NoticeService(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	// # update : 2023-01-18(최종수정)
	// # title : 총 상품 갯수 조회
	// # author : 공성식
	// # description : 페이징 처리용 총 상품 갯수 조회
	public int selectTotalCount(Map<String, String> searchMap) {

		return noticeMapper.selectTotalCount(searchMap);
	}

	// # update : 2023-01-18(최종수정)
	// # title : 공지사항 전체 조회
	// # author : 공성식
	// # description : 공지사항리스트 전체 조회하여 반환
	public List<NoticeDTO> selectAllList(SelectCriteria selectCriteria) {
		return noticeMapper.selectAllList(selectCriteria);
	}

	// # update : 2023-01-18(최종수정)
	// # title : 공지사항 단일 조회
	// # author : 공성식
	// # description : 번호를 기준으로 조회한 1개의 공지사항 상세정보를 반환
	public NoticeDTO selectOneNotice(String noticeNo) {
		return noticeMapper.selectOneNotice(noticeNo);
	}

	// # update : 2023-01-18(최종수정)
	// # title : 공지사항 추가
	// # author : 공성식
	// # description : 입력받은 값을 전달하여 공지사항 추가
	public void insertNotice(NoticeDTO newNotice) {
		noticeMapper.insertNotice(newNotice);
	}

	// # update : 2023-01-18(최종수정)
	// # title : 공지사항 삭제
	// # author : 공성식
	// # description : 번호를 기준으로 공지사항 삭제 처리
	public void deleteNotice(String noticeNo) {
		noticeMapper.deleteNotice(noticeNo);
	}

	// # update : 2023-01-18(최종수정)
	// # title : 공지사항 수정
	// # author : 공성식
	// # description : 입력받은 값을 전달하여 수정 처리
	public void updateNotice(NoticeDTO modifyNotice) {
		noticeMapper.updateNotice(modifyNotice);
	}

	// # update : 2023-01-18(최종수정)
	// # title : 조회수 증가
	// # author : 공성식
	// # description : 호출시마다 조회수 +1 처리
	public void incrementNoticeCount(String noticeNo) {
		noticeMapper.incrementNoticeCount(noticeNo);
	}

	// # update : 2023-01-18(최종수정)
	// # title : 최근 공지사항 5개 조회
	// # author : 공성식
	// # description : 메인페이지 공지사항 미리보기 영역에 출력될 최근 공지사항 5개 조회
    public List<NoticeDTO> selectTop5Notice() { return noticeMapper.selectTop5Notice(); }
}
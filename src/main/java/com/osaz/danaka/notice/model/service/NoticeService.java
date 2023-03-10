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
 * author         : MSI
 * date           : 2023-01-03
 * description    :
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeService {
	private final NoticeMapper noticeMapper;

	@Autowired
	public NoticeService(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	/* 페이징 처리용 총 상품 개수 조회 */
	public int selectTotalCount(Map<String, String> searchMap) {

		return noticeMapper.selectTotalCount(searchMap);
	}

	/* 공지사항 전체 조회 */
	public List<NoticeDTO> selectAllList(SelectCriteria selectCriteria) {
		return noticeMapper.selectAllList(selectCriteria);
	}

	/* 공지사항 단일 조회 */
	public NoticeDTO selectOneNotice(String noticeNo) {
		return noticeMapper.selectOneNotice(noticeNo);
	}

	/* 공지사항 추가 */
	public void insertNotice(NoticeDTO newNotice) {
		noticeMapper.insertNotice(newNotice);
	}

	/* 공지사항 삭제 */
	public void deleteNotice(String noticeNo) {
		noticeMapper.deleteNotice(noticeNo);
	}
	
	/* 공지사항 수정 */
	public void updateNotice(NoticeDTO modifyNotice) {
		noticeMapper.updateNotice(modifyNotice);
	}
	
	/* 공지사항 조회수 증가 */
	public void incrementNoticeCount(String noticeNo) {
		noticeMapper.incrementNoticeCount(noticeNo);
	}

    public List<NoticeDTO> selectTop5Notice() {
		return noticeMapper.selectTop5Notice();
    }
}
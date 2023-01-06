package com.osaz.danaka.notice.model.service;

import com.osaz.danaka.notice.model.dto.NoticeDTO;
import com.osaz.danaka.notice.model.dao.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	/*공지사항 전체 조회*/
	public List<NoticeDTO> selectAllList() {
		return noticeMapper.selectAllList();
	}

	/*공지사항 단일 조회*/
	public NoticeDTO selectOneNotice(String noticeNo) {
		return noticeMapper.selectOneNotice(noticeNo);
	}

	/*공지사항 추가*/
	public void insertNotice(NoticeDTO newNotice) {
		noticeMapper.insertNotice(newNotice);
//		boolean int result = noticeMapper.insertNotice(newNotice);

//		if(result <= 0){
//			throw new Exception("공지사항 등록 실패");
//		}
//
//		return result > 0 ? true : false;
	}

	/*공지사항 삭제*/
	public void deleteNotice(String noticeNo) {
		noticeMapper.deleteNotice(noticeNo);
	}

	public void updateNotice(NoticeDTO modifyNotice) {
		noticeMapper.updateNotice(modifyNotice);
	}

	public void incrementNoticeCount(String noticeNo) {
		noticeMapper.incrementNoticeCount(noticeNo);
	}
}
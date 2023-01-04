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

	public List<NoticeDTO> selectAllList() {
		return noticeMapper.selectAllList();
	}

	public NoticeDTO selectOneNotice(String noticeNo) {
		return noticeMapper.selectOneNotice(noticeNo);
	}

}
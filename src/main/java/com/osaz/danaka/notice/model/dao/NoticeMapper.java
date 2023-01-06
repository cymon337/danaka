package com.osaz.danaka.notice.model.dao;

import com.osaz.danaka.notice.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    /*공지사항 전체 조회*/
    List<NoticeDTO> selectAllList();

    /*공지사항 단일 조회*/
    NoticeDTO selectOneNotice(String noticeNo);

    /*공지사항 추가*/
    void insertNotice(NoticeDTO newNotice);

    /*공지사항 삭제*/
    void deleteNotice(String noticeNo);

    void updateNotice(NoticeDTO modifyNotice);

    void incrementNoticeCount(String noticeNo);
}

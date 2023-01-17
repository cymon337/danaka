package com.osaz.danaka.notice.model.dao;

import com.osaz.danaka.common.paging.SelectCriteria;
import com.osaz.danaka.notice.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {

    /* 페이징 처리용 총 상품 개수 조회 */
    int selectTotalCount(Map<String, String> searchMap);

    /* 공지사항 전체 조회 */
    List<NoticeDTO> selectAllList(SelectCriteria selectCriteria);

    /* 공지사항 단일 조회 */
    NoticeDTO selectOneNotice(String noticeNo);

    /* 공지사항 추가 */
    void insertNotice(NoticeDTO newNotice);

    /* 공지사항 삭제 */
    void deleteNotice(String noticeNo);

    /* 공지사항 수정 */
    void updateNotice(NoticeDTO modifyNotice);

    /* 공지사항 조회수 증가 */
    void incrementNoticeCount(String noticeNo);
}

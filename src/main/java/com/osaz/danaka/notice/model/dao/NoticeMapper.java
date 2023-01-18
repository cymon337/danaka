package com.osaz.danaka.notice.model.dao;

import com.osaz.danaka.common.paging.SelectCriteria;
import com.osaz.danaka.notice.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {

    // # update : 2023-01-18(최종수정)
    // # title : 공지사항 매퍼
    // # author : 공성식
    // # description : NoticeMapper.xml과 연결하여 정보 송,수신하는 인터페이스

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

    /* 메인페이지 출력용 공지사항 5개 출력 */
    List<NoticeDTO> selectTop5Notice();
}

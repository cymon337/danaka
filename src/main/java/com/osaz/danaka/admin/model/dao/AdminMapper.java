package com.osaz.danaka.admin.model.dao;

import com.osaz.danaka.admin.model.dto.AdminDTO;
import com.osaz.danaka.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMapper {

    // # update : 2023-01-18(최종수정)
    // # title : 관리자 매퍼
    // # author : 공성식
    // # description : AdminMapper.xml과 연결하여 정보 송,수신하는 인터페이스

    /* 페이징 처리용 총 상품 개수 조회 */
    int selectTotalCount(Map<String, String> searchMap);

    /* 공지사항 전체 조회 */
    List<AdminDTO> selectAllList(SelectCriteria selectCriteria);

    /* 회원 단일 조회 */
    AdminDTO selectOneMember(String userNo);

    /* 회원 삭제 */
    void deleteMember(String userNo);

    /* 회원정보 수정 */
    void updateMember(AdminDTO modifyMember);
}

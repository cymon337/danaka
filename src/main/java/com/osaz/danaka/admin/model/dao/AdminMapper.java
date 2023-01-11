package com.osaz.danaka.admin.model.dao;

import com.osaz.danaka.admin.model.dto.AdminDTO;
import com.osaz.danaka.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMapper {

    // 페이징 처리용 총 상품 개수 조회
    int selectTotalCount(Map<String, String> searchMap);

    /*공지사항 전체 조회*/
    List<AdminDTO> selectAllList(SelectCriteria selectCriteria);

    /*공지사항 단일 조회*/
    AdminDTO selectOneMember(String userNo);

    /*공지사항 삭제*/
    void deleteMember(String userNo);

    void updateMember(AdminDTO modifyMember);
}

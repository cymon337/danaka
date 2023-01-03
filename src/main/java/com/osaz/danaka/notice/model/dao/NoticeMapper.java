package com.osaz.danaka.notice.model.dao;

import com.osaz.danaka.notice.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<NoticeDTO> selectAllList();


}

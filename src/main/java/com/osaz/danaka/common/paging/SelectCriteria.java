package com.osaz.danaka.common.paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectCriteria implements java.io.Serializable {

	private int pageNo;					//요청한 페이지 번호
	private int totalCount;				//전체 게시물 수
	private int limit;					//한 페이지에 보여줄 게시물 수
	private int buttonAmount;			//한 번에 보여줄 페이징 버튼의 갯수
	private int maxPage;				//가장 마지막 페이지
	private int startPage;				//한 번에 보여줄 페이징 버튼의 시작하는 페이지 수
	private int endPage;				//한 번에 보여줄 페이징 버튼의 마지막 페이지 수
	private int startRow;				//DB 조회 시 최신 글 부터 조회해야 하는 행의 시작 수
	private int endRow;					//DB 조회 시 최신글부터 조회해야 하는 행의 마지막 수
	private String searchCondition;		//검색 조건
	private String searchValue;			//검색어
	private String categoryCode;		//카테고리 코드
	private String orderCondition;		//정렬 조건


}

package com.osaz.danaka.common.paging;

import lombok.Data;

// # title : product 쪽 페이징 용 페이지네이션
// # author : 오승재
// # description : 페이징을 위한 클래쓰
@Data
// @NoArgsConstructor
// @AllArgsConstructor
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
	private String memberCondition;		//탈퇴 회원

	public SelectCriteria() {}

	/* 공통으로 조건 없을 경우 */
	public SelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount, int maxPage, int startPage, int endPage, int startRow, int endRow) {
		this.pageNo = pageNo;
		this.totalCount = totalCount;
		this.limit = limit;
		this.buttonAmount = buttonAmount;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.startRow = startRow;
		this.endRow = endRow;
	}

	// 성식 메인페이지 상품 검색
	public SelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount, int maxPage, int startPage, int endPage, int startRow, int endRow, String searchValue) {
		this.pageNo = pageNo;
		this.totalCount = totalCount;
		this.limit = limit;
		this.buttonAmount = buttonAmount;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.startRow = startRow;
		this.endRow = endRow;
		this.searchValue = searchValue;
	}

	// 성식 관리자 회원관리 검색
	public SelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount, int maxPage, int startPage, int endPage, int startRow, int endRow, String searchCondition, String searchValue, String memberCondition) {
		this.pageNo = pageNo;
		this.totalCount = totalCount;
		this.limit = limit;
		this.buttonAmount = buttonAmount;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.startRow = startRow;
		this.endRow = endRow;
		this.searchCondition = searchCondition;
		this.searchValue = searchValue;
		this.memberCondition = memberCondition;
	}

	// 승재 product
	public SelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount, int maxPage, int startPage,
			int endPage, int startRow, int endRow, String searchCondition, String searchValue, String categoryCode, String orderCondition) {
		super();
		this.pageNo = pageNo;
		this.totalCount = totalCount;
		this.limit = limit;
		this.buttonAmount = buttonAmount;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.startRow = startRow;
		this.endRow = endRow;
		this.searchCondition = searchCondition;
		this.searchValue = searchValue;
		this.categoryCode = categoryCode;
		this.orderCondition = orderCondition;
	}
}

package com.osaz.danaka.common;

public class Pagenation {
	
	/* 검색어가 없는 경우 페이징 처리만을 위한 용도 */
	public static SelectCriteria getSelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount, String categoryCode) {
		
		return getSelectCriteria(pageNo, totalCount, limit, buttonAmount, null, null, categoryCode);
	}
	
	/* 검색어가 존재하는 경우 검색 조건으로 select 후 페이징 처리를 하기 위한 용도 */
	public static SelectCriteria getSelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount, String searchCondition, String searchValue, String categoryCode) {
		
		/* pageNo와 totalCount가 넘어온 상태이기 때문에
		 * 페이징처리에 필요한 나머지 변수만 선언을 한다.
		 * */
		int maxPage;			//전체 페이지에서 가장 마지막 페이지
		int startPage;			//한번에 표시될 페이지 버튼의 시작할 페이지
		int endPage;			//한번에 표시될 페이지 버튼의 끝나는 페이지
		int startRow;
		int endRow;
		
		/* 총 페이지수 계산
		 * 예를 들면, 목록수가 123개 이면 페이지 수는 13 페이지임.
		 * 짜투리 목록이 최소 1개일 때, 1page 로 처리하기 위해
		 * 0.9를 더하기 함
		 * */
//		maxPage = (int)((double) totalCount / limit + 0.9);
		maxPage = (int) Math.ceil((double) totalCount / limit);

		/* 현재 페이지에 보여줄 시작 페이지 수 (10개씩 보여지게 할 경우)
		 * 아래쪽에 페이지 수가 10개씩 보여지게 한다면
		 * 1, 11, 21, 31, .....
		 * */
//		startPage = (((int)((double) pageNo / buttonAmount + 0.9)) - 1) * buttonAmount + 1;
		startPage = (int) (Math.ceil((double) pageNo / buttonAmount) - 1) * buttonAmount + 1;

		/* 목록 아래쪽에 보여질 마지막 페이지 수 (10, 20, 30, ....) */
		endPage = startPage + buttonAmount - 1;
		
		/* max 페이지가 더 작은 경우 마지막 페이지가 max페이지이다. */
		if(maxPage < endPage){
			endPage = maxPage;
		}
		
		/* 마지막 페이지는 0이 될 수 없기 때문에 게시물이 아무 것도 존재하지 않으면 max페이지와 endPage는 0이 된다. */
		if(maxPage == 0 && endPage == 0) {
			maxPage = startPage;
			endPage = startPage;
		}
		
		/* 조회할 시작 번호와 마지막 행 번호를 계산한다. */
		startRow = (pageNo - 1) * limit + 1;
		endRow = startRow + limit - 1;
		
		System.out.println("startRow : " + startRow);
		System.out.println("endRow : " + endRow);
		
		SelectCriteria selectCriteria = new SelectCriteria(pageNo, totalCount, limit, buttonAmount ,maxPage, startPage, endPage, startRow, endRow, searchCondition, searchValue, categoryCode);
		
		return selectCriteria;
	}
}

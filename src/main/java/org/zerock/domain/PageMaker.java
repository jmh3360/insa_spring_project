package org.zerock.domain;

import lombok.Data;

@Data
public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;
	
	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	private void calcData() {
//		엔드페이지 계산 현재 페이지가 13페이지라는 가정 하에 진행
//		Math.ceil(13/10) = 2// 2*displayPageNum(10)
//		end page 20
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		
		startPage = (endPage - displayPageNum) +1;
		
		int lastPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		
		if (endPage > lastPage) {
			endPage = lastPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage *cri.getPerPageNum() >= totalCount ? false : true;
	}
}

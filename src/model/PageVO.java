package model;

public class PageVO {
    private int pageNum  = 1; // 현재 페이지 번호
    private int totalCount; // 총 게시물 수
    private int startPage; // 페이지 목록 시작 번호
    private int endPage; // 페이지 목록 끝 번호
    private int prevPage; // 이전 페이지 번호
    private int nextPage; // 다음 페이지 번호
    private int pagePerNum  = 10; // 페이지당 게시물 수

    // 생성자
    public PageVO(int pageNum, int totalCount, int pagePerNum) {
        this.pageNum = pageNum;
        this.totalCount = totalCount;
        this.pagePerNum = pagePerNum;

        // 마지막 페이지 번호
        int endPageNum = (int) (Math.ceil(pageNum / (double) pagePerNum) * pagePerNum);

        // 시작 페이지 번호
        this.startPage = (endPageNum - pagePerNum) + 1;

        // 총 페이지 수
        int totalPageNum = (int) (Math.ceil(totalCount / (double) pagePerNum));

        // 끝 페이지 번호
        this.endPage = (totalPageNum < endPageNum) ? totalPageNum : endPageNum;

        // 이전 페이지 번호
        this.prevPage = (startPage > 1) ? startPage - 1 : 1;

        // 다음 페이지 번호
        this.nextPage = (endPage < totalPageNum) ? endPage + 1 : totalPageNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPagePerNum() {
        return pagePerNum;
    }

    public void setPagePerNum(int amount) {
        this.pagePerNum = amount;
    }

    @Override
    public String toString() {
        return "PageVO{" +
                "pageNum=" + pageNum +
                ", totalCount=" + totalCount +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", prevPage=" + prevPage +
                ", nextPage=" + nextPage +
                ", pagePerNum=" + pagePerNum +
                '}';
    }
}
package jf.service.weddinginvitationservice.model.response;

public class PaginationResponse {
    private Integer prevPageNo;
    private Integer currPageNo;
    private Integer nextPageNo;
    private int totalData;
    private int totalPage;
    private int pageSize;

    public Integer getPrevPageNo() {
        return prevPageNo;
    }

    public void setPrevPageNo(Integer prevPageNo) {
        this.prevPageNo = prevPageNo;
    }

    public Integer getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(Integer currPageNo) {
        this.currPageNo = currPageNo;
    }

    public Integer getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(Integer nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

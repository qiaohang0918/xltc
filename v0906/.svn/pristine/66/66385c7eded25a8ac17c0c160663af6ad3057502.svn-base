package com.qigan.qiganshop.util.result;

import java.util.Collections;
import java.util.List;

public class ListPageUtil<T> {

    private List<T> data;
 
    private int lastPage;

    private int nowPage;

    private int nextPage;
 
    private int pageSize;

    private int totalPage;

    private int totalCount;
    
    public ListPageUtil(List<T> data,int nowPage,int pageSize) {
        if (data == null || data.isEmpty()) {
            /*throw new IllegalArgumentException("data must be not empty!");*/
        }else{
        	this.data = data;
	        this.pageSize = pageSize;
	        this.nowPage = nowPage;
	        this.totalCount = data.size();
	        this.totalPage = (totalCount + pageSize - 1) / pageSize;
	        this.lastPage = nowPage-1>1? nowPage-1:1;
	        this.nextPage = nowPage>=totalPage? totalPage: nowPage + 1;
        }
    }
 
    /**
     * 得到分页后的数据
     *
     * @param pageNum 页码
     * @return 分页后结果
     */
    public List<T> getPagedList() {
        int fromIndex = (nowPage - 1) * pageSize;
        if (fromIndex >= data.size()) {
            return Collections.emptyList();//空数组
        }
        if(fromIndex<0){
            return Collections.emptyList();//空数组
        }
        int toIndex = nowPage * pageSize;
        if (toIndex >= data.size()) {
            toIndex = data.size();
        }
        return data.subList(fromIndex, toIndex);
    }
 
    public int getPageSize() {
        return pageSize;
    }
 
    public List<T> getData() {
        return data;
    }
    public int getLastPage() {
        return lastPage;
    }

    public int getNowPage() {
        return nowPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

}

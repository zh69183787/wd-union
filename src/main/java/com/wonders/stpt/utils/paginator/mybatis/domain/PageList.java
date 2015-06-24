package com.wonders.stpt.utils.paginator.mybatis.domain;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * 包含“分页”信息的List
 * 
 * <p>要得到总页数请使用 toPaginator().getTotalPages();</p>
 * 
 * @author badqiu
 * @author miemiedev
 */
public class PageList<E> extends ArrayList<E> {
    private static final long serialVersionUID = 1412759446332294208L;
    
    private Paginator paginator;

    private PageInfo pageInfo;

    public PageList() {}
    
	public PageList(Collection<? extends E> c) {
		super(c);
	}

	
	public PageList(Collection<? extends E> c,Paginator p) {
        super(c);
        this.paginator = p;

    }

    public PageList(Paginator p) {
        this.paginator = p;
    }


	/**
	 * 得到分页器，通过Paginator可以得到总页数等值
	 * @return
	 */
	protected Paginator getPaginator() {
		return paginator;
	}

    public PageInfo getPageInfo(){
        pageInfo = new PageInfo();
        if(this.paginator!=null){
	        pageInfo.setPageIndex(this.paginator.getPage());
	        pageInfo.setPageSize(this.paginator.getLimit());
	        pageInfo.setTotalPages(this.paginator.getTotalPages());
	        pageInfo.setTotalRows(this.paginator.getTotalCount());
        }else{
        	pageInfo.setPageIndex(0);
	        pageInfo.setPageSize(0);
	        pageInfo.setTotalPages(0);
	        pageInfo.setTotalRows(0);
        }
        return pageInfo;
    }
	
}

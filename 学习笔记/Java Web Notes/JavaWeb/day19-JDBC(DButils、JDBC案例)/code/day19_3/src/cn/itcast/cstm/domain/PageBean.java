package cn.itcast.cstm.domain;

import java.util.List;

/*
 * 封装分页数据
 * */
/**
 * @author Administrator
 *
 * @param <T>
 */
public class PageBean<T> {
	private List<T> beanList;//当前页数据，需要传递
	private int tr;//总记录数，需要传递
	private int pc;//当前页码，需要传递
	private int ps;//每页记录数，需要传递
	private String url;//多条件组合查询中的条件
	/*
	 * 总页数，总记录数除于每页记录数。由于这个记录数只能被外界读取，而不能修改，所以不提供set方法。
	 * 直接有get方法计算后返回即可。这个属性也可不要
	 * */
	//private int tp;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTp() {
		//计算出没有余数的情况下有几页
		int tp = tr / ps;
		//如果有余数，就多加一页
		return tr % ps == 0 ? tp : tp+1;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	public int getTr() {
		return tr;
	}

	public void setTr(int tr) {
		this.tr = tr;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}
}

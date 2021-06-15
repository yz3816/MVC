package com.mvc.web.entity.content;

import java.util.List;

public class etcList {

	private int count;
	private List<Notice> ns;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Notice> getNs() {
		return ns;
	}

	public void setNs(List<Notice> ns) {
		this.ns = ns;
	}

	public etcList(int count, List<Notice> ns) {

		this.count = count;
		this.ns = ns;
	}

	public etcList() {

	}

}

package com.mvc.web.entity.content;

import java.util.Date;

public class Picture {
	private int id;
	private String ptitle;
	private String writeid;
	private String path;
	private Date regdate;
	private String useFlag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPtitle() {
		return ptitle;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public String getWriteid() {
		return writeid;
	}

	public void setWriteid(String writeid) {
		this.writeid = writeid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	public Picture(int id, String ptitle, String writeid, String path, Date regdate, String useFlag) {

		this.id = id;
		this.ptitle = ptitle;
		this.writeid = writeid;
		this.path = path;
		this.regdate = regdate;
		this.useFlag = useFlag;
	}

	public Picture() {

	}

	@Override
	public String toString() {
		return "Picture [id=" + id + ", ptitle=" + ptitle + ", writeid=" + writeid + ", path=" + path + ", regdate="
				+ regdate + ", useFlag=" + useFlag + "]";
	}

}

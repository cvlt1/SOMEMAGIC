package com.itacademy.jd2.yi.cms.dao.api.filter;

public class ContentItemFilter extends AbstractFilter {

	
	private Integer siteId;
	private String html;
	private String title;
	
	
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	
	
	
}

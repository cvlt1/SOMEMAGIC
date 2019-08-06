package com.itacademy.jd2.yi.cms.dao.api.filter;

public class CssItemFilter extends AbstractFilter {
    private boolean fetchSite;
    private String content;
    
    

    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean getFetchSite() {
        return fetchSite;
    }

    public void setFetchSite(final boolean fetchSite) {
        this.fetchSite = fetchSite;
}
    

}

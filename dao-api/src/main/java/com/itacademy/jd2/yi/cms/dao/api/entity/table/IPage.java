package com.itacademy.jd2.yi.cms.dao.api.entity.table;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.PageStatus;

public interface IPage extends IBaseEntity {

	
	String toString();

	void setTitle(String title);

	String getTitle();

	void setCreator(IUserAccount creator);

	IUserAccount getCreator();

	void setStatus(PageStatus status);

	PageStatus getStatus();

	void setPath(String path);

	String getPath();

	void setTemplateId(ITemplate templateId);

	ITemplate getTemplateId();



	void setSiteId(ISite siteId);

	ISite getSiteId();

	void setParentId(IPage parentId);

	IPage getParentId();

}

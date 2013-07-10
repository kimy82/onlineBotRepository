package com.online.dao;

import com.online.model.Html;

public interface HtmlDao{

	void save( Html html );

	void update(Html html);

	Html load( Long id );
	
}

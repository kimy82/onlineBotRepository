package com.online.bo;

import com.online.exceptions.BOException;
import com.online.model.Image;

public interface ImageBo{

	Image load( Integer id ) throws BOException;

}

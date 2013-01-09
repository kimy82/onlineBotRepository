package com.online.bo;

import com.online.exceptions.BOException;
import com.online.model.Foro;

public interface ForoBo {

	void save(Foro foro) throws BOException,Exception;

}

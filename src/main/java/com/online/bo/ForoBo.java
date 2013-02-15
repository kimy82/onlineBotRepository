package com.online.bo;

import com.online.exceptions.BOException;
import com.online.model.Foro;
import com.online.model.ForoBeguda;

public interface ForoBo {

	void save(Foro foro) throws BOException,Exception;
	void saveBeguda(ForoBeguda foro) throws BOException, Exception;

}

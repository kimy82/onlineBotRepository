package com.online.dao;

import com.online.model.Foro;
import com.online.model.ForoBeguda;

public interface ForoDao {

	void save(Foro foro);
	void saveBeguda(ForoBeguda foro);
}

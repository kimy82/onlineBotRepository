package com.online.dao;

import java.util.List;

import com.online.model.VotacioTMP;


public interface VotacionsDao {
	
	void saveTMP(VotacioTMP votacioTMP);	
	void deleteAllTMP();
	List<VotacioTMP> getAll();
	VotacioTMP get(Long idplat, Long idUser);
	int count(Long idplat , int star);
	
}

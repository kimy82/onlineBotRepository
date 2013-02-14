package com.online.dao;

import java.util.List;

import com.online.model.VotacioTMP;
import com.online.model.VotacioTMPBeguda;


public interface VotacionsDao {
	
	void saveTMP(VotacioTMP votacioTMP);	
	void saveTMPBeguda(VotacioTMPBeguda votacioTMP);
	void deleteAllTMP();
	List<VotacioTMP> getAll();
	VotacioTMP get(Long idplat, Long idUser);
	int count(Long idplat , int star);
	VotacioTMP getLast(Long idplat, Long idUser);
	VotacioTMPBeguda getBeguda(Long idBeguda, Long idUser);
	int countBeguda(Long idbeguda , int star);
	
}

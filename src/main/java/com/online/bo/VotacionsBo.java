package com.online.bo;

import java.util.List;

import com.online.exceptions.BOException;
import com.online.model.VotacioTMP;



public interface VotacionsBo {
	
	void saveTMP(VotacioTMP votacioTMP)throws BOException;
	void deleteAllTMP()throws BOException;
	List<VotacioTMP> getAll()throws BOException;
	int count(Long idplat , int star)throws BOException;
	VotacioTMP get(Long idplat, Long idUser)throws BOException;
	VotacioTMP getLast(Long idplat, Long idUser)throws BOException;

}

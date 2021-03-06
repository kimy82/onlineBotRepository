package com.online.bo.impl;

import java.util.List;

import com.online.bo.VotacionsBo;
import com.online.dao.VotacionsDao;
import com.online.exceptions.BOException;
import com.online.model.VotacioTMP;
import com.online.model.VotacioTMPBeguda;

public class VotacionsBoImpl implements VotacionsBo {

	private VotacionsDao votacionsDao;

	public int count(Long idplat , int star)throws BOException{
		return this.votacionsDao.count(idplat, star);
	}
	
	public int countBeguda(Long idbeguda , int star)throws BOException{
		return this.votacionsDao.countBeguda(idbeguda, star);
	}
	
	
	public VotacioTMP getLast(Long idplat, Long idUser)throws BOException{
		try{
			return	this.getLast(idplat, idUser);
		}catch(Exception e){
			throw new BOException(e,"Error retrieving last vote");
		}
	}
	
	public VotacioTMP get(Long idplat, Long idUser)throws BOException {
		
		if(idplat==null || idUser==null){
			throw new BOException("Null params to get the tmp votacio");
		}
		return this.votacionsDao.get(idplat, idUser);
		
	}
	public VotacioTMPBeguda getBeguda(Long idBeguda, Long idUser)throws BOException {

		if(idBeguda==null || idUser==null){
			throw new BOException("Null params to get the tmp votacio");
		}
		return this.votacionsDao.getBeguda(idBeguda, idUser);
	}
	public void saveTMP(VotacioTMP votacioTMP) throws BOException {
		checkVotacioToSave(votacioTMP);
		this.votacionsDao.saveTMP(votacioTMP);
	}
	
	public void saveTMPBeguda(VotacioTMPBeguda votacioTMP) throws BOException {
		checkVotacioBegudaToSave(votacioTMP);
		this.votacionsDao.saveTMPBeguda(votacioTMP);
	}

	public void deleteAllTMP() throws BOException {

		this.votacionsDao.deleteAllTMP();
	}

	public List<VotacioTMP> getAll() {

		return this.votacionsDao.getAll();
	}

	// PRIVATE METHODS

	private void checkVotacioToSave(VotacioTMP votacioTMP) throws BOException {

		if (votacioTMP == null || votacioTMP.getPlatId() == null
				|| votacioTMP.getUserId() == null) {
			throw new BOException("Null votacio to save");
		}
	}
	
	private void checkVotacioBegudaToSave(VotacioTMPBeguda votacioTMP) throws BOException {

		if (votacioTMP == null || votacioTMP.getBegudaId() == null
				|| votacioTMP.getUserId() == null) {
			throw new BOException("Null votacio to save");
		}
	}

	// SETTERS
	public void setVotacionsDao(VotacionsDao votacionsDao) {
		this.votacionsDao = votacionsDao;
	}

}

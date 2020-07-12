package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.QueryCriteria;
import com.dass.pawning.domain.SystemProgram;


public interface SystemProgramService {
	public List<SystemProgram> getAllSystemPrograms(List<QueryCriteria> criteriaList)throws PawnException;
}

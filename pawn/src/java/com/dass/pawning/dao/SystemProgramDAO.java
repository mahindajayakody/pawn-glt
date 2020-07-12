package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.QueryCriteria;
import com.dass.pawning.domain.SystemProgram;


public interface SystemProgramDAO {
	public List<SystemProgram> getAllSystemPrograms(List<QueryCriteria> criteriaList);
}

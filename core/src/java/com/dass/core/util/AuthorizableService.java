package com.dass.core.util;

import com.dass.core.bean.Trace;
import com.dass.core.exception.PawnException;

public interface AuthorizableService {
    public void authorizeCreation(UserConfig userConfig,Trace trace,boolean authorize)throws PawnException;
	public void authorizeDeletion(UserConfig userConfig,Trace trace,boolean authorize)throws PawnException;
	public void authorizeUpdation(UserConfig userConfig,Trace trace,boolean authorize)throws PawnException;
}

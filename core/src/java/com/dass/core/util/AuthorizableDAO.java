package com.dass.core.util;

import com.dass.core.bean.Trace;

public interface AuthorizableDAO {
    public void authorizeCreation(UserConfig userConfig,Trace trace,boolean authorize);
	public void authorizeDeletion(UserConfig userConfig,Trace trace,boolean authorize);
	public void authorizeUpdation(UserConfig userConfig,Trace trace,boolean authorize);
}

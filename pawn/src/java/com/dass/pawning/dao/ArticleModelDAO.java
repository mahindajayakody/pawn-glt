package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.ArticleModel;

public interface ArticleModelDAO extends AuthorizableDAO{
	public void createArticaleModel(UserConfig userConfig,ArticleModel articleModel);
	public void updateArticaleModel(UserConfig userConfig,ArticleModel articleModel);
	public void removeArticaleModel(UserConfig userConfig,ArticleModel articleModel);

	public ArticleModel getArticaleModelById(UserConfig userConfig,int recordId);
	public ArticleModel getArticaleModelByCode(UserConfig userConfig,String code);
	public DataBag getAllArticaleModel(UserConfig userConfig,List<QueryCriteria> criteriaList);
	public ArticleModel getArticaleModelByCodeAndProductId(UserConfig userConfig,String code,int productId) ;
}

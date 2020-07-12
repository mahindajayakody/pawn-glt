package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.ArticleModel;
import com.dass.pawning.domain.Article;


public interface ArticleService extends AuthorizableService{
	public void createArticle(UserConfig userConfig,Article article)throws PawnException;
	public void updateArticle(UserConfig userConfig,Article article)throws PawnException;
	public void removeArticle(UserConfig userConfig,Article article)throws PawnException;

	public Article getArticleById(UserConfig userConfig,int recordId)throws PawnException;
	public Article getArticleByCode(UserConfig userConfig,String code)throws PawnException;
	public DataBag getAllArticle(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;
	public Article getArticleByCodeAndArticleModel(UserConfig userConfig, String code,int articleModelId) throws PawnException;
}

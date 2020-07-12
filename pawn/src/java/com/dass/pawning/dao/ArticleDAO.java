package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Article;

public interface ArticleDAO extends AuthorizableDAO{
	public void createArticle(UserConfig userConfig,Article article);
	public void updateArticle(UserConfig userConfig,Article article);
	public void removeArticle(UserConfig userConfig,Article article);

	public Article getArticleById(UserConfig userConfig,int recordId);
	public Article getArticleByCode(UserConfig userConfig,String code);
	public DataBag getAllArticle(UserConfig userConfig,List<QueryCriteria> criteriaList);
	public Article getArticleByCodeAndArticleModel(UserConfig userConfig, String code,int articleModelId);
}

package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.ArticleDAO;
import com.dass.pawning.domain.Article;
import com.dass.pawning.service.ArticleService;

public class ArticleServiceImpl extends AuthorizableServiceImpl implements ArticleService {
	private ArticleDAO articleDAO;

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	public void createArticle(UserConfig userConfig, Article article)throws PawnException {
		articleDAO.createArticle(userConfig, article);
	}

	public void removeArticle(UserConfig userConfig, Article article)throws PawnException {
		articleDAO.removeArticle(userConfig, article);
	}

	public void updateArticle(UserConfig userConfig, Article article)throws PawnException {
		articleDAO.updateArticle(userConfig, article);
	}

	public DataBag getAllArticle(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException {
		return articleDAO.getAllArticle(userConfig, criteriaList);
	}

	public Article getArticleByCode(UserConfig userConfig, String code)throws PawnException {
		return articleDAO.getArticleByCode(userConfig, code);
	}

	public Article getArticleById(UserConfig userConfig, int recordId)throws PawnException {
		return articleDAO.getArticleById(userConfig, recordId);
	}

	public Article getArticleByCodeAndArticleModel(UserConfig userConfig, String code, int articleModelId) throws PawnException {
		return articleDAO.getArticleByCodeAndArticleModel(userConfig, code, articleModelId);
	}
}

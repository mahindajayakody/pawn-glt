package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.ArticleModelDAO;
import com.dass.pawning.domain.ArticleModel;
import com.dass.pawning.service.ArticleModelService;

public class ArticleModelServiceImpl extends AuthorizableServiceImpl implements ArticleModelService {
	private ArticleModelDAO articleModelDAO;

	public void setArticaleModelDAO(ArticleModelDAO articleModelDAO) {
		this.articleModelDAO = articleModelDAO;
	}

	public void createArticleModel(UserConfig userConfig,ArticleModel articleModel) throws PawnException {
		articleModelDAO.createArticaleModel(userConfig, articleModel);
	}

	public DataBag getAllArticaleModel(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException {
		return articleModelDAO.getAllArticaleModel(userConfig, criteriaList);
	}

	public ArticleModel getArticleModelByCode(UserConfig userConfig, String code)throws PawnException {
		return articleModelDAO.getArticaleModelByCode(userConfig, code);
	}

	public ArticleModel getArticleModelById(UserConfig userConfig, int recordId)throws PawnException {
		return articleModelDAO.getArticaleModelById(userConfig, recordId);
	}

	public void removeArticleModel(UserConfig userConfig,ArticleModel articleModel) throws PawnException {
		articleModelDAO.removeArticaleModel(userConfig, articleModel);
	}

	public void updateArticleModel(UserConfig userConfig,ArticleModel articleModel) throws PawnException {
		articleModelDAO.updateArticaleModel(userConfig, articleModel);
	}

	public ArticleModel getArticaleModelByCodeAndProductId(UserConfig userConfig, String code, int productId) throws PawnException {
		return articleModelDAO.getArticaleModelByCodeAndProductId(userConfig, code, productId);
	}
}

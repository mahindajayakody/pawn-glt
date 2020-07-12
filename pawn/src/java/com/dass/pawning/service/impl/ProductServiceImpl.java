package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.ProductDAO;
import com.dass.pawning.domain.Product;
import com.dass.pawning.service.ProductService;

public class ProductServiceImpl extends AuthorizableServiceImpl implements ProductService {
	private ProductDAO productDAO;

	public void setProductDAO(ProductDAO productDAO){
		this.productDAO = productDAO;
	}

	public void createProduct(UserConfig userConfig, Product product)throws PawnException {
		productDAO.createProduct(userConfig, product);
	}

	public void removeProduct(UserConfig userConfig, Product product)throws PawnException {
		productDAO.removeProduct(userConfig, product);
	}

	public void updateProduct(UserConfig userConfig, Product product)throws PawnException {
		productDAO.updateProduct(userConfig, product);
	}

	public DataBag getAllProduct(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException {
		return productDAO.getAllProduct(userConfig, criteriaList);
	}

	public Product getProductByCode(UserConfig userConfig, String code)throws PawnException {
		return productDAO.getProductByCode(userConfig, code);
	}

	public Product getProductById(UserConfig userConfig, int recordId)throws PawnException {
		return productDAO.getProductById(userConfig, recordId);
	}
}

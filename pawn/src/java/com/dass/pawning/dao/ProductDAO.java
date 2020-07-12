package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Product;


public interface ProductDAO extends AuthorizableDAO{
	public void createProduct(UserConfig userConfig,Product product);
	public void updateProduct(UserConfig userConfig,Product product);
	public void removeProduct(UserConfig userConfig,Product product);

	public Product getProductById(UserConfig userConfig,int recordId);
	public Product getProductByCode(UserConfig userConfig,String code);
	public DataBag getAllProduct(UserConfig userConfig,List<QueryCriteria> criteriaList);

}

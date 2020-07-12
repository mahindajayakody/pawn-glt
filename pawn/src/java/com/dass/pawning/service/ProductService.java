package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Product;


public interface ProductService extends AuthorizableService{
	public void createProduct(UserConfig userConfig,Product product)throws PawnException;
	public void updateProduct(UserConfig userConfig,Product product)throws PawnException;
	public void removeProduct(UserConfig userConfig,Product product)throws PawnException;

	public Product getProductById(UserConfig userConfig,int recordId)throws PawnException;
	public Product getProductByCode(UserConfig userConfig,String code)throws PawnException;
	public DataBag getAllProduct(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;

}

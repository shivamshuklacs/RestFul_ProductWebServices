package com.zensar.services.business;

import java.util.ArrayList;
import java.util.List;

import com.zensar.daos.ProductDao;
import com.zensar.daos.ProductDaoImpl;
import com.zensar.entities.Product;



/*
 * Author: SHIVAM SHUKLA
 * Creation Date: 26th July 2019 11.40AM
 * Modified Date: 26th July 2019 11.40AM
 * Version: 1.0
 * Copyright: Zensar Technologies. All rights reserved. 
 * Description: Business Object Interface implementor Class.
 * It encapsulates business logic of application component product.
 * It interacts with DAO layer to get data from database.
 * It also interacts with presentation layer to give data 
 * as per business requirement.
 * */
public class ProductServiceImpl implements ProductService {

	private ProductDao dao;
	
	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = new ProductDaoImpl();
	}
	
	@Override
	public void create(Product product) {
		// TODO Auto-generated method stub
		dao.insert(product);
	}

	@Override
	public void edit(Product product) {
		// TODO Auto-generated method stub
		Product dbProduct = findProductById(product.getProductId());
		if(dbProduct!=null) {
			dbProduct.setName(product.getName());
			dbProduct.setBrand(product.getBrand());
			dbProduct.setPrice(product.getPrice());
			dao.update(dbProduct);
			
		}
	}

	@Override
	public void remove(Product product) {
		// TODO Auto-generated method stub
		Product dbProduct= findProductById(product.getProductId());
		if(dbProduct!=null) {
				dao.delete(dbProduct);
		}
		else
			System.out.println("Product not found");
	}

	@Override
	public Product findProductById(int productId) {
		// TODO Auto-generated method stub
		return dao.getById(productId);
	}

	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public int getProductCount() {
		// TODO Auto-generated method stub
		return findAllProducts().size();
	}

	@Override
	public List<Product> findProductsByBrand(String brand) {
		// TODO Auto-generated method stub
		List<Product> pList = findAllProducts();
		List<Product> pBList = new ArrayList<>();
		for(Product p:pList) {
			if(p.getBrand().equals(brand))
				pBList.add(p);
		}
		return pBList;
	}

	@Override
	public List<Product> findProductsByPriceRange(float minPrice, float maxPrice) {
		// TODO Auto-generated method stub
		List<Product> pList = findAllProducts();
		List<Product> pPList = new ArrayList<>();
		for(Product p:pList) {
			if( minPrice<=(p.getPrice()) && (p.getPrice())<=maxPrice)
				pPList.add(p);
		}
		return pPList;
	}

}

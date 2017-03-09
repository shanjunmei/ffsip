package com.ffzx.common.service;

import com.ffzx.orm.common.GenericExample;

import java.util.List;

public interface BaseService<T, PK> {

	public int add(T entity);

	public int deleteById(String id);

	public int update(T entity);
	
	public int updateSelective(T entity);

	public T findById(String id);

	public <EX extends GenericExample<?>> List<T> selectByExample(EX example);

	public <EX extends GenericExample<?>> int updateByExample(T entity,EX example);

	public <EX extends GenericExample<?>> int countByExample(EX example);

	public T findByCode(String code);

	public List<T> selectByEntity(T entity);

}

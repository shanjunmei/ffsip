package com.ffzx.common.service.impl;

import com.ffzx.common.service.BaseService;
import com.ffzx.ffsip.util.CodeGenerator;
import com.ffzx.orm.common.BaseEntity;
import com.ffzx.orm.common.GenericExample;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class BaseServiceImpl<T, PK> implements BaseService<T, PK> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public BaseServiceImpl() {
        logger.debug(getClass() + " init");
    }
    // @Resource
    // Mapper<T> mapper;

    public abstract Mapper<T> getMapper();/* {
        throw new RuntimeException(
				"type[" + getClass() + "] unsupported  getMapper() method");
	}*/

    @Override
    public int add(T entity) {
        String id = CodeGenerator.getUUID();

        ((BaseEntity) entity).setId(id);
        if (StringUtils.isBlank(((BaseEntity) entity).getCode())) {
            String code = CodeGenerator.code();
            ((BaseEntity) entity).setCode(code);
        }
        Date date = new Date();
        ((BaseEntity) entity).setCreateDate(date);
        ((BaseEntity) entity).setLastUpdateDate(date);
        return getMapper().insert(entity);
    }

    @Override
    public int deleteById(String id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int update(T entity) {
        if(entity instanceof  BaseEntity){
            ((BaseEntity) entity).setLastUpdateDate(new Date());
        }
        return getMapper().updateByPrimaryKey(entity);
    }

    @Override
    public int updateSelective(T entity) {
        if(entity instanceof  BaseEntity){
            ((BaseEntity) entity).setLastUpdateDate(new Date());
        }
        return getMapper().updateByPrimaryKeySelective(entity);
    }

    @Override
    public T findById(String id) {
        return (T) getMapper().selectByPrimaryKey(id);
    }

    @Override
    public <EX extends GenericExample<?>> List<T> selectByExample(EX example) {
        return (List<T>) getMapper().selectByExample(example);
    }

    @Override
    public <EX extends GenericExample<?>> int updateByExample(T record, EX example) {
        return getMapper().updateByExampleSelective(record, example);
    }

    @Override
    public <EX extends GenericExample<?>> int countByExample(EX example) {
        return getMapper().selectCountByExample(example);

    }

    @Override
    public T findByCode(String code) {
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        List<T> dataList = getMapper().select((T) params);
        if (dataList.size() > 0) {
            return dataList.get(0);
        }
        return null;
    }

    @Override
    public List<T> selectByEntity(T entity) {
        return getMapper().select(entity);
    }
}

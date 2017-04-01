package com.ffzx.common.controller;

import com.ffzx.common.service.BaseService;
import com.ffzx.common.utils.ResultVo;
import com.ffzx.common.utils.WebUtils;
import com.ffzx.ffsip.model.User;
import com.ffzx.orm.common.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.ParameterizedType;
import java.util.Date;


public abstract class BaseEditController<T, PK> extends CoreController {


    private Class<T> entityClass;

    public BaseEditController() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        logger.debug(getClass() + " init ,entityClass = " + entityClass);
        // System.out.println(((ParameterizedType)new GT1().getClass().getGenericSuperclass()));

    }




    protected User getCurrentUser() {
        return WebUtils.getSessionAttribute("loginUser");

    }

    protected Class<T> getEntityClass() {
        return entityClass;
    }

    public String getBasePath() {
        return "/" + getEntityClass().getSimpleName();
    }

    public String getListPath() {
        return getBasePath() + "/List";
    }

    public String getFormPath() {
        return getBasePath() + "/Form";
    }

    public abstract BaseService<T, PK> getService();


    public T createEntity() {
        T entity = null;
        try {
            entity = getEntityClass().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    /**
     * example : User/Form;
     *
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping("toForm")
    public String toForm(PK id, ModelMap modelMap) {
        T entity = getService().findById((String) id);
        if (entity == null) {
            entity = createEntity();
        }
        modelMap.put("entity", entity);
        return getFormPath();
    }

    /**
     * example : User/List;
     *
     * @return
     */
    @RequestMapping("toList")
    public String toList() {
        return getListPath();
    }

    @RequestMapping("form")
    @ResponseBody
    public T findByPK(PK id) {
        return getService().findById((String) id);
    }

    @RequestMapping("findByCode")
    @ResponseBody
    public T findByCode(String code) {
        return getService().findByCode(code);
    }


    @RequestMapping("save")
    @ResponseBody
    public ResultVo save(T entity) {
        ResultVo resultVo = new ResultVo();
        boolean isCreate = false;
        if (entity instanceof BaseEntity) {
            Date current = new Date();
            User user = getCurrentUser();
            ((BaseEntity) entity).setLastUpdateDate(current);
            if (user != null) {
                ((BaseEntity) entity).setLastUpdateBy(user.getCode());
            }
            if (StringUtils.isBlank(((BaseEntity) entity).getId())) {
                isCreate = true;
                ((BaseEntity) entity).setCreateDate(current);
                if (user != null) {
                    ((BaseEntity) entity).setCreateBy(user.getCode());
                }
            }
        }
        int ret = 0;
        if (isCreate) {
            getService().add(entity);
        } else {
            getService().updateSelective(entity);
        }
        if (ret > 0) {
            //成功
        }
        resultVo.setStatus("success");
        resultVo.setUrl(getBasePath() + "/toList.do");
        return resultVo;
    }

    @RequestMapping("update")
    @ResponseBody
    public int update(T entity) {
        return getService().updateSelective(entity);
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultVo delete(PK id) {
        ResultVo resultVo = new ResultVo();
        int ret = getService().deleteById((String) id);
        resultVo.setStatus("success");
        resultVo.setInfoStr("删除成功");
        return resultVo;
    }
}

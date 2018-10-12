package com.tiantian.service;

import java.util.List;

import com.tiantian.mapper.BaseMapper;

public abstract class BaseService<T> {

    public abstract BaseMapper<T> getMapper();

    public void add(T t) throws Exception {
        getMapper().add(t);
    }

    public void update(T t) throws Exception {
        getMapper().update(t);
    }

    public void updateBySelective(T t) {
        getMapper().updateBySelective(t);
    }

    public void delete(Object... ids) throws Exception {
        if (ids == null || ids.length < 1) {
            return;
        }
        for (Object id : ids) {
            getMapper().delete(id);

        }
    }

    public T queryById(Object id) throws Exception {
        return getMapper().queryById(id);
    }

}

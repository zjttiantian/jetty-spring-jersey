package com.tiantian.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 * @author tiantian
 *
 */
public class BaseBean {
    private Integer id;
	private Date createTime;
	private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



}

package com.dao;

import com.github.pagehelper.Page;
import com.pojo.CheckGroup;
import com.pojo.CheckItem;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);
    public void setCheckGroupAndCheckItem(Map map);
    public Page<CheckGroup> findByCondition(String queryString);
    public CheckGroup findById(Integer id);
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    public void edit(CheckGroup checkGroup);
    public void deleteAssocication(Integer id);
    public List<CheckGroup> findAll();

    public long findCountByCheckItemId(Integer id);
    public void deleteById(Integer id);
}

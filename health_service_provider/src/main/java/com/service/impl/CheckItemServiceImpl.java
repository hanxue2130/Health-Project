package com.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.dao.CheckItemDao;
import com.entity.PageResult;
import com.entity.QueryPageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pojo.CheckItem;
import com.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }
//    public PageResult pageQuery(QueryPageBean queryPageBean){
//        Integer currentPage = queryPageBean.getCurrentPage();
//        Integer pageSize = queryPageBean.getPageSize();
//        String queryString = queryPageBean.getQueryString();
//
//        PageHelper.startPage(currentPage,pageSize);
//        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
//        long total = page.getTotal();
//        List<CheckItem> rows = page.getResult();
//        return new PageResult(total, rows);
//    }
public PageResult pageQuery(QueryPageBean queryPageBean) {
    Integer currentPage = queryPageBean.getCurrentPage();
    Integer pageSize = queryPageBean.getPageSize();
    String queryString = queryPageBean.getQueryString();//查询条件
    //完成分页查询，基于mybatis框架提供的分页助手插件完成
    PageHelper.startPage(currentPage,pageSize);
    //select * from t_checkitem limit 0,10
    Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
    long total = page.getTotal();
    List<CheckItem> rows = page.getResult();
    return new PageResult(total,rows);
    }


    public void delete(Integer id) throws RuntimeException {
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            throw new RuntimeException("Current method has been related to another group");
        }
        checkItemDao.deleteById(id);
    }
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

}

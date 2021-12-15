package com.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.constant.MessageConstant;
import com.entity.PageResult;
import com.entity.QueryPageBean;
import com.entity.Result;
import com.pojo.CheckItem;
import com.service.CheckItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference // check from zookeper
    private CheckItemService checkItemService;

    @RequestMapping("/add")
//    @PostMapping
    public Result add(@RequestBody CheckItem checkItem){ //@RequestParam binding the names
    try{
        checkItemService.add(checkItem);
    }catch (Exception e){
        e.printStackTrace();
        //服务调用失败
        return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return  new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.pageQuery(queryPageBean);
        return pageResult;
    }
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            checkItemService.delete(id);
        }
        catch (RuntimeException e){
            return new Result(false,e.getMessage()); }
        catch (Exception e){
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }
    @RequestMapping("/edit") public Result edit(@RequestBody CheckItem checkItem){
        try {
        checkItemService.edit(checkItem); }
        catch (Exception e){
                return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
                }
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS); }

    @RequestMapping("/findById") public Result findById(Integer id){
        try{CheckItem checkItem = checkItemService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem); }
        catch (Exception e){ e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL); } }

    @RequestMapping("/findAll") public Result findAll(){
        try{
            List<CheckItem> list = checkItemService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list); }
        catch (Exception e){ e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL); } }
}

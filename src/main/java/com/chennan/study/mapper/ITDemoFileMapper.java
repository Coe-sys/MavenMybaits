package com.chennan.study.mapper;

import com.chennan.study.database.data.TDemoFile;

import java.util.List;

/**
 * demo class
 * 数据库访问映射接口
 * @author 陈楠
 * @date 2019/11/28 16:24
 * To change this template use File | Settings | File Templates.
 */

public interface ITDemoFileMapper {
    /**
     * 查询方法
     * @return
     * @throws Exception
     */
    public List<TDemoFile> selectAll() throws Exception;

    /**
     * 通过id查询
     * @param id
     * @return
     * @throws Exception
     */
    public TDemoFile gitById(Integer id) throws  Exception;

    /**
     * 插入方法
     * @param tDemoFile
     * @throws Exception
     */
    public void insert(TDemoFile tDemoFile) throws Exception;

    /**
     * 更新方法
     * @param tDemoFile
     * @throws Exception
     */
    public void update(TDemoFile tDemoFile) throws Exception;

    /**
     * 删除方法
     * @param ids
     * @throws Exception
     */
    public void delete(String ids) throws Exception;

    /**
     * 动态sql语句删除
     * @param ids
     * @throws Exception
     */
    public void deleteBatch(String[] ids) throws Exception;
}

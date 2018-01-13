package org.xteam.plus.mars.dao;

import org.xteam.plus.mars.domain.Product;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_PRODUCT表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_PRODUCT]表Mapper接口
 */

public interface ProductDao {

    /**
     * 获取
     * @param product
     * @return int
     */
    public Product get(Product product) throws Exception;

    /**
     * 新增
     * @param product
     * @return int 记录数
     */
    public int insert(Product product) throws Exception;

    /**
     * 批量新增
     * @param list   List<Product>
     * @return int  记录数
     */
    public int batchInsert(List<Product> list) throws Exception;

    /**
     * 删除
     * @param product
     * @return int
     */
    public int delete(Product product) throws Exception;

    /**
     * 更新
     * @param product
     * @return int 记录数
     */
    public int update(Product product) throws Exception;

    /**
     * 查询
     * @param product
     * @return List<Product>
     */
    public List<Product> query(Product product) throws Exception;

    /**
     * 查询记录数
     * @param  product
     * @return List<Product>
     */
    public Integer queryCount(Product product) throws Exception;
}

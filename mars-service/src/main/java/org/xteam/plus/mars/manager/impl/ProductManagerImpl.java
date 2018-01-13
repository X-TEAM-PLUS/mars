package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.ProductDao;
import org.xteam.plus.mars.domain.Product;
import org.xteam.plus.mars.manager.ProductManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_PRODUCT表Manager接口实现类
 */

@Service
public class ProductManagerImpl implements ProductManager {
    private static final Log log = LogFactory.getLog(ProductManagerImpl.class);
    @javax.annotation.Resource
    private ProductDao productDao;


    @Override
    public Product get(Product product) throws Exception {
        return productDao.get(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Product product) throws Exception {
        return productDao.insert(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<Product> list) throws Exception {
        return productDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Product product) throws Exception {
        return productDao.delete(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Product product) throws Exception {
        return productDao.update(product);
    }

    @Override
    public List<Product> query(Product product) throws Exception {
        return productDao.query(product);
    }

    @Override
    public Integer queryCount(Product product) throws Exception {
        return productDao.queryCount(product);
    }

}

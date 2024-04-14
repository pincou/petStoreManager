package org.csu.adminsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.adminsys.entity.Category;
import org.csu.adminsys.entity.Item;
import org.csu.adminsys.entity.Product;
import org.csu.adminsys.Mappers.CategoryMapper;
import org.csu.adminsys.Mappers.ItemMapper;
import org.csu.adminsys.Mappers.ProductMapper;
import org.csu.adminsys.service.CatalogService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ItemMapper itemMapper;
    @Override
    public Category findCategoryById(String catid) {
        return categoryMapper.selectById(catid);
    }

    @Override
    public List<Category> findAllCategorys() {
        return categoryMapper.selectList(null);
    }

    @Override
    public void updateCategoryById(Category category) {
        categoryMapper.updateById(category);
    }

    @Override
    public void insertCategory(Category category) {
           categoryMapper.insert(category);
    }

    @Override
    public void deleteCategoryById(String catid) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("catid",catid);
        categoryMapper.delete(categoryQueryWrapper);
    }

    @Override
    public Product findProductById(String productid) {
        return productMapper.selectById(productid);
    }

    @Override
    public Product findProductByName(String productName) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("name",productName);
        return productMapper.selectOne(productQueryWrapper);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("category",category);
        return productMapper.selectList(productQueryWrapper);
    }

    @Override
    public List<Product> findAllProducts() {
        return productMapper.selectList(null);
    }

    @Override
    public void updateProductById(Product product) {
            productMapper.updateById(product);
    }

    @Override
    public void insertProduct(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void deleteProductById(String productid) {
        productMapper.deleteById(productid);
    }

    @Override
    public Item findItemById(String itemid) {
        return itemMapper.selectById(itemid);
    }

    @Override
    public List<Item> findItemByName(String productName) {
       QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
       productQueryWrapper.eq("name",productName);
        String productid =  productMapper.selectOne(productQueryWrapper).getProductId();
        QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("productid",productid);
        return itemMapper.selectList(itemQueryWrapper);
    }

    @Override
    public List<Item> findItemByCategory(String categoryId) {
        List<Item> list = new ArrayList<>();
        List<Product> productList = findProductByCategory(categoryId);
        for(int i = 0;i<productList.size();i++){
            String productid = productList.get(i).getProductId();
            QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
            itemQueryWrapper.eq("productid",productid);
            List<Item> itemList = itemMapper.selectList(itemQueryWrapper);
            for(int k=0;k<itemList.size();k++){
                list.add(itemList.get(k));
            }
        }
        return list;
    }

    @Override
    public List<Item> allItems() {
        return itemMapper.selectList(null);
    }

    @Override
    public void updateItemById(Item item) {
        itemMapper.updateById(item);
    }
    @Override
    public void insertItem(Item item) {
        itemMapper.insert(item);
    }

    @Override
    public void deleteItemById(String itemid) {
        QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("itemid",itemid);
        itemMapper.delete(itemQueryWrapper);
    }
}

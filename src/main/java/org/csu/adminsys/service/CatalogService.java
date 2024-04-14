package org.csu.adminsys.service;

import org.csu.adminsys.entity.Category;
import org.csu.adminsys.entity.Item;
import org.csu.adminsys.entity.Product;

import java.util.List;

public interface CatalogService {
    //Category
     Category findCategoryById(String catid);
     List<Category> findAllCategorys();
     void updateCategoryById(Category category);
     void insertCategory(Category category);
     void deleteCategoryById(String catid);

    //Product
     Product findProductById(String productid);
     Product findProductByName(String productName);
     List<Product> findProductByCategory(String category);
     List<Product> findAllProducts();
     void updateProductById(Product product);
     void insertProduct(Product product);
     void deleteProductById(String productid);

    //Item
     Item findItemById(String itemid);
     List<Item> findItemByName(String productName);
     List<Item> findItemByCategory(String categoryId);
     List<Item> allItems();
     void updateItemById(Item item);
     void insertItem(Item item);
     void deleteItemById(String itemid);
}

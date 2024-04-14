package org.csu.adminsys.Controller;

import org.springframework.ui.Model;
import org.csu.adminsys.Mappers.CategoryMapper;
import org.csu.adminsys.Mappers.ItemMapper;
import org.csu.adminsys.Mappers.ProductMapper;
import org.csu.adminsys.entity.Category;
import org.csu.adminsys.entity.Product;
import org.csu.adminsys.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PetsManageController {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    CatalogService catalogService;

    @GetMapping("/petsmanage")
    public String PetsManage(Model model){
        List<Category> categories=catalogService.findAllCategorys();
        model.addAttribute("categories",categories);
        return "petsmanage";
    }



}

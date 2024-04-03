package com.merchstore.controller;

import com.merchstore.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/collections/{category}")
    public ModelAndView getProductList(@PathVariable("category") String category) {
        ModelAndView modelAndView = new ModelAndView("home_product_list");
        modelAndView.addObject("products", productService.getProducts(category));
        return modelAndView;
    }

    @GetMapping ("/collections/{category}/products/{id}")
    public ModelAndView productOverview(@PathVariable("id") Long id, @PathVariable String category) {
        ModelAndView modelAndView = new ModelAndView("product_overview");
        modelAndView.addObject("product", productService.getById(id));
        return modelAndView;
    }

}

package com.merchstore.controller;

import com.merchstore.model.Product;
import com.merchstore.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = { "/collections/", "/collections"})
    public ModelAndView home(@RequestParam(name = "page", defaultValue = "1") int page) {
        ModelAndView modelAndView = new ModelAndView("product_list");
        Page<Product> p = productService.getAllProducts(page);
        modelAndView.addObject("products", p.getContent());
        modelAndView.addObject("pages", p.getTotalPages());
        return modelAndView;
    }

    @GetMapping("/collections/search")
    public ModelAndView search(@PathParam("q") String q) {
        if (q.isEmpty())
            return new ModelAndView("redirect:/collections");

        ModelAndView modelAndView = new ModelAndView("product_list");
        modelAndView.addObject("q", q);
        modelAndView.addObject("products", productService.search(q));
        return modelAndView;
    }


    @GetMapping("/collections/{category}")
    public ModelAndView getProductsByCategory(
            @PathVariable("category") String category,
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        ModelAndView modelAndView = new ModelAndView("product_list");
        Page<Product> p = productService.getProductsByCategory(category, page);
        modelAndView.addObject("products", p.getContent());
        modelAndView.addObject("pages", p.getTotalPages());
        return modelAndView;
    }

    @GetMapping ("/collections/{category}/products/{id}")
    public ModelAndView productOverview(@PathVariable("id") Long id, @PathVariable String category) {
        ModelAndView modelAndView = new ModelAndView("product_overview");
        modelAndView.addObject("product", productService.getById(id));
        modelAndView.addObject("suggestions", productService.getSuggestions(id));
        return modelAndView;
    }
}

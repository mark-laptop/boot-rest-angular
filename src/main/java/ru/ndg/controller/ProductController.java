package ru.ndg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ndg.exception.NotFoundException;
import ru.ndg.model.Product;
import ru.ndg.service.ProductService;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<Product> getAllProducts(@RequestParam(required = false) Map<String, String> params,
                                        @RequestParam(name = "page", required = false) Integer page) {
        if (page == null ||  page < 1) {
            page = 0;
        } else page -= 1;
        return productService.getAllProducts(params, page);
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable(name = "id") Long id) {
        return productService.getProductById(id).orElseThrow(() -> new NotFoundException("Not found product by id: " + id));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveOrUpdateProduct(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.saveOrUpdateProduct(product);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProductById(@PathVariable(name = "id") Long id) {
        productService.deleteProductById(id);
    }

    @DeleteMapping
    public void deleteAllProduct() {
        productService.deleteAllProduct();
    }
}

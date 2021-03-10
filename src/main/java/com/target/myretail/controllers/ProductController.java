package com.target.myretail.controllers;

import com.target.myretail.Model.Product;
import com.target.myretail.service.ProductService;
import com.target.myretail.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author VALARMATHI
 * Controller to get the Product related data
 */
@RestController
@RequestMapping("/products")
class ProductController {

    @Value("${target.restUrl:http://redsky.target.com/v1/pdp/tcin/}")
    private String TARGET_RESOURCE_URL;

    @Value("${target.restParm:?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics}")
    private String TARGET_RESOURCE_PARAM;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAllProducts(@RequestParam(value = "page",
            required = false, defaultValue = "1") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size ) throws Exception {
        List<Product> products = productService.findAll(page,size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/name{Do not use redskyURL doesnt work}")
    public ResponseEntity<?> getProductsById(@PathVariable("id") String id) throws Exception {

        String response
                = restTemplate().getForObject(TARGET_RESOURCE_URL + "/" + id + TARGET_RESOURCE_PARAM, String.class);
        if (response == null) {
            return new ResponseEntity<>(new ApiResponse("Product: " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductDetailsById(@PathVariable("id") String id) throws Exception {
        Product product = new Product();
        Optional<Product> optional = productService.findProductsById(id);
        if (optional.isPresent()) {
            {
                product.setId(id);
                product.setCurrentPrice(optional.get().getCurrentPrice());
                product.setName(optional.get().getName());
            }
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/product")
    ResponseEntity<?> updateProductDetails(@PathVariable("id") String id, @RequestBody Product product) throws Exception {
        Product newProduct = productService.updateProduct(product);
        if (newProduct == null) {
            return new ResponseEntity<>(new ApiResponse("Product: " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/price")
    ResponseEntity<?> getProductPriceById(@PathVariable("id") String id) throws Exception {
        Optional<Product> optional = productService.findProductsById(id);
        Product product = new Product();
        optional.ifPresent(price -> product.setCurrentPrice(optional.get().getCurrentPrice()));
        if (!optional.isPresent()) {
            return new ResponseEntity<>(new ApiResponse("Product: " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.getCurrentPrice(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/product")
    void deleteProductsById(@PathVariable("id") String id) throws Exception { productService.deleteProductsById(id); }

    @DeleteMapping(value = "/products")
    void deleteAllProducts() throws Exception { productService.deleteProducts();   }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoNotFound(Exception ex) {
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}

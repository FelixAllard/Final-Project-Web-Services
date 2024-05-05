package org.champqcsoft.productservice.presentationlayer;


import org.champqcsoft.productservice.buisnesslayer.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping()
    public ResponseEntity<List<ProductResponseModel>> getProducts(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseModel> getProductByProductId(@PathVariable String productId){
        return ResponseEntity.ok().body(productService.getProductByProductIdentifier_productId(productId));
    }
    @PostMapping
    public ResponseEntity<ProductResponseModel> createClient(@RequestBody ProductRequestModel productRequestModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequestModel));
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseModel> updateClient(@RequestBody ProductRequestModel productRequestModel, @PathVariable String productId){
        return ResponseEntity.ok().body(productService.updateProduct(productRequestModel, productId));
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productId){
        productService.removeProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

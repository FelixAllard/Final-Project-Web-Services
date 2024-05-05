package org.champqcsoft.productservice.dataaccesslayer;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductByProductIdentifier_ProductId(String productId);

    void deleteProductByProductIdentifier_ProductId(String productId);

    boolean existsProductByProductIdentifier_ProductId(String productId);
}




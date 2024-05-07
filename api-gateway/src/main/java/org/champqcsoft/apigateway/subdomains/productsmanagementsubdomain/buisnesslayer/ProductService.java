package org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.buisnesslayer;


import org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.presentationlayer.ProductRequestModel;
import org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.presentationlayer.ProductResponseModel;

import java.util.List;
public interface ProductService {
    List<ProductResponseModel> getAllProducts();

    ProductResponseModel getProductByProductIdentifier_productId(String productId);

    ProductResponseModel createProduct(ProductRequestModel productRequestModel);

    ProductResponseModel updateProduct(ProductRequestModel productRequestModel, String productId);

    void removeProduct(String productId);

}

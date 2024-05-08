package org.champqcsoft.apigateway.buisnesslayer.products;


import org.champqcsoft.apigateway.presentationlayer.products.ProductRequestModel;
import org.champqcsoft.apigateway.presentationlayer.products.ProductResponseModel;

import java.util.List;
public interface ProductService {
    List<ProductResponseModel> getAllProducts();

    ProductResponseModel getProductByProductIdentifier_productId(String productId);

    ProductResponseModel createProduct(ProductRequestModel productRequestModel);

    ProductResponseModel updateProduct(ProductRequestModel productRequestModel, String productId);

    void removeProduct(String productId);

}

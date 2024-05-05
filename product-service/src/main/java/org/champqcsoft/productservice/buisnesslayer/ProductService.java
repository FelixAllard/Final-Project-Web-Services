package org.champqcsoft.productservice.buisnesslayer;


import org.champqcsoft.productservice.presentationlayer.ProductRequestModel;
import org.champqcsoft.productservice.presentationlayer.ProductResponseModel;

import java.util.List;
public interface ProductService {
    List<ProductResponseModel> getAllProducts();

    ProductResponseModel getProductByProductIdentifier_productId(String productId);

    ProductResponseModel createProduct(ProductRequestModel productRequestModel);

    ProductResponseModel updateProduct(ProductRequestModel productRequestModel, String productId);

    void removeProduct(String productId);

}

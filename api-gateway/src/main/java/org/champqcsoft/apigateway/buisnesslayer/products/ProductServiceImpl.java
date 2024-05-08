package org.champqcsoft.apigateway.buisnesslayer.products;


import jakarta.transaction.Transactional;
import org.champqcsoft.apigateway.commons.enums.Currency;
import org.champqcsoft.apigateway.commons.enums.Price;
import org.champqcsoft.apigateway.commons.identifiers.ProductIdentifier;
import org.champqcsoft.apigateway.domainclientlayer.products.ProductServiceClient;
import org.champqcsoft.apigateway.mapperlayer.products.ProductResponseMapper;
import org.champqcsoft.apigateway.mapperlayer.products.ProductResponseMapper;

import org.champqcsoft.apigateway.presentationlayer.products.ProductRequestModel;
import org.champqcsoft.apigateway.presentationlayer.products.ProductResponseModel;
import org.champqcsoft.apigateway.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductServiceClient productServiceProduct;
    private final ProductResponseMapper productResponseMapper;
    public ProductServiceImpl(ProductServiceClient productServiceProduct, ProductResponseMapper productResponseMapper){
        this.productServiceProduct = productServiceProduct;
        this.productResponseMapper = productResponseMapper;
    }

    @Override
    public List<ProductResponseModel> getAllProducts() {

        return productResponseMapper.entityListToResponseModelList(productServiceProduct.getAllProducts());
    }

    @Override
    public ProductResponseModel getProductByProductIdentifier_productId(String productId) {
        return productResponseMapper.entityToResponseModel(productServiceProduct.findProductByProductIdentifier_ProductId(productId));

    }

    @Override
    public ProductResponseModel createProduct(ProductRequestModel productRequestModel) {
        return productResponseMapper.entityToResponseModel(productServiceProduct.createProduct(productRequestModel));
    }

    @Override
    public ProductResponseModel updateProduct(ProductRequestModel productRequestModel, String productId) {
        return productResponseMapper.entityToResponseModel(productServiceProduct.updateProductByProduct_Id(productRequestModel, productId));
    }

    @Override
    public void removeProduct(String productId) {
        productServiceProduct.deleteProductByProduct_Id(productId);
    }
}

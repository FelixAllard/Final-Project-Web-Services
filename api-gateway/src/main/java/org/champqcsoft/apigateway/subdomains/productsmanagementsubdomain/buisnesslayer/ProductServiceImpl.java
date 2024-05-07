package org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.buisnesslayer;


import org.champqcsoft.apigateway.commons.enums.Currency;
import org.champqcsoft.apigateway.commons.enums.Price;
import org.champqcsoft.apigateway.commons.identifiers.ProductIdentifier;
import org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.dataaccesslayer.*;
import org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.datamapperlayer.ProductRequestMapper;
import org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.datamapperlayer.ProductResponseMapper;
import org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.presentationlayer.ProductRequestModel;
import org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.presentationlayer.ProductResponseModel;
import org.champqcsoft.apigateway.utils.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductResponseMapper productResponseMapper;
    private final ProductRequestMapper productRequestMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductResponseMapper productResponseMapper,
                              ProductRequestMapper productRequestMapper) {
        this.productRepository = productRepository;
        this.productResponseMapper = productResponseMapper;
        this.productRequestMapper = productRequestMapper;
    }

    @Override
    public List<ProductResponseModel> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productResponseMapper.entityListToResponseModelList(products);
    }

    @Override
    public ProductResponseModel getProductByProductIdentifier_productId(String productId) {
        if(!productRepository.existsProductByProductIdentifier_ProductId(productId))
            throw new NotFoundException("Unknown productId " + productId);
        Product products = productRepository.findProductByProductIdentifier_ProductId(productId);
        return productResponseMapper.entityToResponseModel(products);


    }

    @Override
    public ProductResponseModel createProduct(ProductRequestModel productRequestModel) {
        //We don't depend on other stuff
        Price price = new Price(
                productRequestModel.getValue(),
                Currency.valueOf(productRequestModel.getCurrency())
        );
        Image image = new Image(
                productRequestModel.getUrl(),
                productRequestModel.getAltText()
        );
        Date date = new Date(
                productRequestModel.getDay(),
                productRequestModel.getMonth(),
                productRequestModel.getYear()
        );
        PalletImportedFrom palletImportedFrom = new PalletImportedFrom(
                productRequestModel.getPalletId(),
                productRequestModel.getManufacturer(),
                date

        );
        CategoryArticle categoryArticle = new CategoryArticle(
                productRequestModel.getCategoryName(),
                productRequestModel.getCategoryDescription()
        );



        Product product = productRequestMapper.requestModelToEntity(
                productRequestModel,
                new ProductIdentifier(),
                palletImportedFrom,
                price,
                categoryArticle,
                image
        );
        return productResponseMapper.entityToResponseModel(productRepository.save(product));
    }

    @Override
    public ProductResponseModel updateProduct(ProductRequestModel productRequestModel, String productId) {
        if(!productRepository.existsProductByProductIdentifier_ProductId(productId))
            throw new NotFoundException("Unknown productId " + productId);


        Product product = productRepository.findProductByProductIdentifier_ProductId(productId);
        Price price = new Price(
                productRequestModel.getValue(),
                Currency.valueOf(productRequestModel.getCurrency())
        );
        Image image = new Image(
                productRequestModel.getUrl(),
                productRequestModel.getAltText()
        );
        Date date = new Date(
                productRequestModel.getDay(),
                productRequestModel.getMonth(),
                productRequestModel.getYear()
        );
        PalletImportedFrom palletImportedFrom = new PalletImportedFrom(
                productRequestModel.getPalletId(),
                productRequestModel.getManufacturer(),
                date

        );
        CategoryArticle categoryArticle = new CategoryArticle(
                productRequestModel.getCategoryName(),
                productRequestModel.getCategoryDescription()
        );
        Product updatedProduct = productRequestMapper.requestModelToEntity(
                productRequestModel,
                new ProductIdentifier(),
                palletImportedFrom,
                price,
                categoryArticle,
                image
        );
        updatedProduct.setId(product.getId());
        return productResponseMapper.entityToResponseModel(productRepository.save(updatedProduct));
    }

    @Transactional
    @Override
    public void removeProduct(String productId) {
        if(!productRepository.existsProductByProductIdentifier_ProductId(productId))
            throw new NotFoundException("Unknown productId " + productId);
        productRepository.deleteProductByProductIdentifier_ProductId(productId);
    }
}

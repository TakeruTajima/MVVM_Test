package com.mr2.mvvm_test.sample.domain.outside.product;

public interface ProductRepository {
    void save(Product product);
    Product findOne(ProductId productId);
}

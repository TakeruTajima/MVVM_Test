package com.mr2.mvvm_test.sample.domain.outside.commodity;

import com.mr2.mvvm_test.sample.domain.outside.product.ProductId;

public interface CommodityRepository {
    Commodity get(ProductId productId);
    //会社IDから商品を取得する
}

package com.test.productsupplier;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.util.StringUtils;

import static com.test.productsupplier.QProductSupplier.*;

/**
 * @author huisheng.jin
 * @version 2019/11/27.
 */
class SearchSupplierSpecification {
    static Predicate toPredicate(SearchSupplier searchSupplier) {
        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isEmpty(searchSupplier.getSupplierName())) {
            builder.and(productSupplier.supplierName.eq(searchSupplier.getSupplierName()));
        }
        builder.and(productSupplier.shopId.eq(searchSupplier.getShopId()));
        return builder.getValue();
    }
}

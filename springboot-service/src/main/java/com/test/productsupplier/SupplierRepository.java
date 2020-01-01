package com.test.productsupplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author huisheng.jin
 * @version 2019/12/10.
 */
public interface SupplierRepository extends JpaRepository<ProductSupplier, String>, QuerydslPredicateExecutor<ProductSupplier> {
}

package com.test.productsupplier;

import com.test.common.ddd.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author huisheng.jin
 * @version 2019/12/10.
 */
@Entity
@Table(name = "product_supplier")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted=0")
@SQLDelete(sql = "update product_supplier set deleted = 1 where id = ?")
class ProductSupplier extends BaseEntity {
    private String supplierNumber;
    private String supplierName;
    private String contactPerson;
    private String phoneNumber;
    private Boolean enable;
    private String shopId;

    static ProductSupplier create(SaveSupplierCommand saveSupplierCommand) {
        return ProductSupplier.builder()
                .contactPerson(saveSupplierCommand.getContactPerson())
                .enable(saveSupplierCommand.getEnable())
                .phoneNumber(saveSupplierCommand.getPhoneNumber())
                .supplierName(saveSupplierCommand.getSupplierName())
                .supplierNumber(saveSupplierCommand.getSupplierNumber())
                .shopId(saveSupplierCommand.getShopId())
                .build();
    }

    void update(SaveSupplierCommand saveSupplierCommand) {
        this.supplierName = saveSupplierCommand.getSupplierName();
        this.supplierNumber = saveSupplierCommand.getSupplierNumber();
        this.contactPerson = saveSupplierCommand.getContactPerson();
        this.phoneNumber = saveSupplierCommand.getPhoneNumber();
        this.enable = saveSupplierCommand.getEnable();
    }
}

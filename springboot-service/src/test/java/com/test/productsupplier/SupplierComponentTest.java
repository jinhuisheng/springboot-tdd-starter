package com.test.productsupplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author huisheng.jin
 * @version 2019/12/10.
 */
@SpringBootTest
class SupplierComponentTest {
    @Autowired
    private SupplierApplicationService supplierApplicationService;
    @Autowired
    private SupplierRepository supplierRepository;

    @BeforeEach
    void setUp() {
        supplierRepository.deleteAll();
    }

    @Test
    @DisplayName("添加商品供应商成功")
    void should_create_supplier_success() {
        SaveSupplierCommand saveSupplierCommand = givenCreateProductSupplierCommand("13213232222", "supplierName");
        String result = supplierApplicationService.save(saveSupplierCommand);

        assertThat(result.isEmpty()).isEqualTo(false);
        assertThat(supplierRepository.findById(result).isPresent()).isEqualTo(true);
    }

    @Test
    @DisplayName("修改供应商成功")
    void should_edit_supplier_success() {
        SaveSupplierCommand createSupplierCommand = givenCreateProductSupplierCommand("13213232222", "supplierName");
        ProductSupplier createdSupplier = ProductSupplier.create(createSupplierCommand);
        supplierRepository.save(createdSupplier);
        SaveSupplierCommand editSupplierCommand = givenEditProductSupplierCommand(createdSupplier.getId(), "15333885889", "加多宝");

        String result = supplierApplicationService.save(editSupplierCommand);

        assertThat(result).isEqualTo(createdSupplier.getId());
        ProductSupplier editedProductSupplier = supplierRepository.findById(createdSupplier.getId()).get();
        assertThat(editedProductSupplier.getPhoneNumber()).isEqualTo("15333885889");
        assertThat(editedProductSupplier.getSupplierName()).isEqualTo("加多宝");
    }

    @Test
    @DisplayName("删除供应商成功")
    void should_del_supplier_success() {
        SaveSupplierCommand createSupplierCommand = givenCreateProductSupplierCommand("13213232222", "supplierName");
        ProductSupplier createdSupplier = ProductSupplier.create(createSupplierCommand);
        supplierRepository.save(createdSupplier);

        supplierApplicationService.delete(createdSupplier.getId());

        Optional<ProductSupplier> deletedProductSupplier = supplierRepository.findById(createdSupplier.getId());
        assertThat(deletedProductSupplier.isPresent()).isEqualTo(false);
    }

    SaveSupplierCommand givenCreateProductSupplierCommand(String phoneNumber, String supplierName) {
        SaveSupplierCommand saveSupplierCommand = new SaveSupplierCommand();
        saveSupplierCommand.setContactPerson("contactPerson");
        saveSupplierCommand.setEnable(false);
        saveSupplierCommand.setPhoneNumber(phoneNumber);
        saveSupplierCommand.setSupplierName(supplierName);
        saveSupplierCommand.setSupplierNumber("232222");
        saveSupplierCommand.setShopId("232222");
        return saveSupplierCommand;
    }

    SaveSupplierCommand givenEditProductSupplierCommand(String supplierId, String phoneNumber, String supplierName) {
        SaveSupplierCommand saveSupplierCommand = givenCreateProductSupplierCommand(phoneNumber, supplierName);
        saveSupplierCommand.setSupplierId(supplierId);
        return saveSupplierCommand;
    }

}
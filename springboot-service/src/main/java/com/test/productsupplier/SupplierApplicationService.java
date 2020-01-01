package com.test.productsupplier;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author huisheng.jin
 * @version 2019/12/10.
 */
@Service
public class SupplierApplicationService {
    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierApplicationService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    String save(SaveSupplierCommand saveSupplierCommand) {
        if (saveSupplierCommand.isNew()) {
            return add(saveSupplierCommand);
        } else {
            return edit(saveSupplierCommand);
        }
    }

    private String edit(SaveSupplierCommand saveSupplierCommand) {
        ProductSupplier editSupplier = supplierRepository.findById(saveSupplierCommand.getSupplierId()).get();
        editSupplier.update(saveSupplierCommand);
        supplierRepository.save(editSupplier);
        return editSupplier.getId();
    }

    private String add(SaveSupplierCommand saveSupplierCommand) {
        ProductSupplier productSupplier = ProductSupplier.create(saveSupplierCommand);
        supplierRepository.save(productSupplier);
        return productSupplier.getId();
    }

    Page<ProductSupplier> search(Pageable pageable, SearchSupplier searchSupplier) {
        Predicate predicate = SearchSupplierSpecification.toPredicate(searchSupplier);
        return supplierRepository.findAll(predicate, pageable);
    }

    void delete(String supplierId) {
        ProductSupplier productSupplier = supplierRepository.findById(supplierId).get();
        supplierRepository.delete(productSupplier);
    }
}

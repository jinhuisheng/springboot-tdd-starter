package com.test.productsupplier;

import com.test.common.utils.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author huisheng.jin
 * @version 2019/12/10.
 */
@RestController
@RequestMapping("/api/v1/product-supplier")
@Api(tags = "产品供应商")
public class ProductSupplierController {
    private SupplierApplicationService supplierApplicationService;

    @Autowired
    public ProductSupplierController(SupplierApplicationService supplierApplicationService) {
        this.supplierApplicationService = supplierApplicationService;
    }

    @PostMapping
    @ApiOperation(value = "保存供应商")
    public RestResult save(@RequestBody @Valid SaveSupplierCommand saveSupplierCommand) {
        String result = supplierApplicationService.save(saveSupplierCommand);
        return RestResult.success(result);
    }

    @PostMapping("search")
    @ApiOperation(value = "分页数据获取,URL传参：?page=0&size=10")
    public Page<ProductSupplier> getSupplierPage(@PageableDefault Pageable pageable, @RequestBody @Valid SearchSupplier searchSupplier) {
        return supplierApplicationService.search(pageable, searchSupplier);
    }

    @PostMapping("/{supplier-id}/del")
    @ApiOperation(value = "删除供应商")
    public RestResult delete(@PathVariable(name = "supplier-id") String supplierId) {
        supplierApplicationService.delete(supplierId);
        return RestResult.success();
    }
}

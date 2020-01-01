package com.test.productsupplier;

import com.test.common.utils.constants.RegexStringEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author huisheng.jin
 * @version 2019/12/10.
 */
@Getter
@Setter
public class SaveSupplierCommand {
    @NotBlank(message = "供应商编号不能为空")
    private String supplierNumber;
    @NotBlank(message = "供应商名称不能为空")
    private String supplierName;
    @NotBlank(message = "联系人不能为空")
    private String contactPerson;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = RegexStringEnum.PHONE, message = "请输入11位手机号码")
    private String phoneNumber;
    private Boolean enable;
    private String supplierId;
    private String shopId;

    public boolean isNew() {
        return StringUtils.isEmpty(supplierId);
    }
}

package com.test.productsupplier;

import com.test.common.exception.GlobalExceptionHandler;
import com.test.common.utils.RestResultCode;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author huisheng.jin
 * @version 2019/9/30.
 */
class SupplierApiTest {

    @InjectMocks
    private ProductSupplierController productSupplierController;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private SupplierApplicationService supplierApplicationService;

    private String rootUrl = "/api/v1/product-supplier";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productSupplierController, globalExceptionHandler)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();

        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    @DisplayName("创建商品供应商成功")
    void should_create_supplier_success() {
        SaveSupplierCommand command = givenCreateProductSupplierCommand("13213232222", "supplierName");
        Mockito.when(supplierApplicationService.save(command)).thenReturn("supplierId");
        given()
                .contentType(JSON)
                .body(command)

                .when()
                .post(rootUrl)

                .then()
                .statusCode(200)
                .contentType(JSON)
                .log().ifValidationFails()
                .body("code", equalTo(RestResultCode.SUCCESS));
    }

    @Test
    @DisplayName("创建商品失败，参数校验失败")
    void should_create_supplier_fail_with_validate() {
        SaveSupplierCommand command = givenFailedValidateCommand();
        Mockito.when(supplierApplicationService.save(command)).thenReturn("supplierId");
        given()
                .contentType(JSON)
                .body(command)

                .when()
                .post(rootUrl)

                .then()
                .statusCode(200)
                .contentType(JSON)
                .log().ifValidationFails()
                .body("code", equalTo(RestResultCode.FAILURE));
    }

    protected SaveSupplierCommand givenFailedValidateCommand() {
        SaveSupplierCommand saveSupplierCommand = new SaveSupplierCommand();
        saveSupplierCommand.setContactPerson("");
        saveSupplierCommand.setEnable(false);
        saveSupplierCommand.setPhoneNumber("");
        saveSupplierCommand.setSupplierName("");
        saveSupplierCommand.setSupplierNumber("");
        return saveSupplierCommand;
    }

    @Test
    @DisplayName("分页查询商品供应商成功")
    void should_query_page_success() {
        ArrayList<ProductSupplier> shopList = new ArrayList<>();
        shopList.add(createProductSupplier());

        Pageable pageable = PageRequest.of(0, 20);
        PageImpl<ProductSupplier> page = new PageImpl<>(shopList, pageable, shopList.size());
        Mockito.when(supplierApplicationService.search(any(), any())).thenReturn(page);

        SearchSupplier searchSupplier = new SearchSupplier();
        searchSupplier.setShopId("shopId");
        searchSupplier.setSupplierName("supplierName");

        given()
                .param("page", 0)
                .param("size", 20)
                .contentType(JSON)
                .body(searchSupplier)

                .when()
                .post(rootUrl + "/search")

                .then()
                .statusCode(200)
                .contentType(JSON)
                .log().ifValidationFails()
                .body("totalElements", is(1))
                .body("number", is(0))
                .body("size", is(20))
                .body("content.size()", is(1));
    }

    private ProductSupplier createProductSupplier() {
        return ProductSupplier.builder()
                .contactPerson("person")
                .enable(true)
                .phoneNumber("13211234456")
                .shopId("shopId")
                .supplierName("supplierName")
                .supplierNumber("13311212234")
                .build();
    }

    @Test
    @DisplayName("删除商品供应商成功")
    void should_delete_supplier_success() {
        given()
                .contentType(JSON)

                .when()
                .post(rootUrl + "/1abb6320-f15f-41f4-9db8-3cad5d83da7e/del")

                .then()
                .statusCode(200)
                .contentType(JSON)
                .log().ifValidationFails()
                .body("code", equalTo(RestResultCode.SUCCESS));
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

}

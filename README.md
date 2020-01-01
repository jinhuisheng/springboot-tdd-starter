# springboot-tdd-starter

使用测试组件
=== 

* Rest-assured controllerTest
* junit5 + mockito service test
* 集成测试  @SpringBootTest 

集成测试与Controller单元测试的区别在于，集成测试是一个“黑盒”测试，它测试的是整个系统所有组件，而单元测试是“白盒”的，它只测试Controller这一层代码是否工作正常。


## 注意事项：
1、如果是真实项目中使用，最好把junit从junit5改为junit4，因为如果要模拟静态方法，私有方法等，使用PowerMock是不兼容junit5的。


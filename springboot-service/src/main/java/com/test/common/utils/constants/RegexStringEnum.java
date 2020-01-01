package com.test.common.utils.constants;

/**
 * @author huisheng.jin
 * @version 2019/5/28
 */
public interface RegexStringEnum {
    String PHONE = "^1[0-9]{10}$";
    String PASSWORD = "((?=.*[a-zA-Z])(?=.*\\d)|(?=.*[a-zA-Z])(?=.*[~!@#$%^&*()_+`\\-={}:\";'<>?,.\\/])|(?=.*\\d)(?=.*[~!@#$%^&*()_+`\\-={}:\";'<>?,.\\/])|(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&*()_+`\\-={}:\";'<>?,.\\/])).{6,16}";
    String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
}

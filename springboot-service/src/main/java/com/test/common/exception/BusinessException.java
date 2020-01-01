package com.test.common.exception;

/**
 * 业务异常，用于业务逻辑不正确的逻辑
 * 比如：用户不存在....等
 * 通过异常代替条件返回
 *
 * @author huisheng.jin
 * @version 2019/12/5
 */
public class BusinessException extends BaseException {
    public BusinessException(String tipMessage) {
        super(tipMessage);
    }
}

package com.mall.bit.cqt.mall.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 请求参数错误处理
 */
public class RequestAssets {
    public static void assetsError(BindingResult errors){
        if (errors.hasErrors()) {
            List<ObjectError> errorList = errors.getAllErrors();
            StringBuilder sb = new StringBuilder();
            //String desc = "";
            for (ObjectError error : errorList) {
                FieldError fieldError = (FieldError) error;
                //desc = fieldError.getDefaultMessage();
                sb.append(fieldError.getDefaultMessage() + "|");
            }
            throw new RuntimeException(sb.toString());
        }
    }
}

package com.test.azkaban.response;

import lombok.Data;

/**
 * 登录返回信息
 *
 * @author: Junjie Zhang
 * @date: 2021/9/28
 */
@Data
public class LoginResponse extends BaseResponse{
    private String sessionId;
}

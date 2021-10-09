package com.test.azkaban.response;

import lombok.Data;

import java.util.Objects;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2021/9/28
 */
@Data
public class BaseResponse {

	public static final String SUCCESS = "success";
	public static final String ERROR = "error";

	/**
	 * 响应状态
	 */
	private String status;
	/**
	 * 错误类型(单纯为了映射Azkaban)
	 */
	private String error;
	/**
	 * 详细信息
	 */
	private String message;

	/**
	 * 更正信息
	 */
	public void correction() {
		if (!ERROR.equals(this.status) && Objects.isNull(this.error)) {
			this.status = SUCCESS;
		} else {
			this.status = ERROR;
			if (Objects.isNull(this.error)) {
				this.error = this.message;
			} else if (Objects.isNull(this.message)) {
				this.message = this.error;
			}
		}
	}

}

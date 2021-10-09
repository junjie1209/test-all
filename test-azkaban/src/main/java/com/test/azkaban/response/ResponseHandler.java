package com.test.azkaban.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * 响应处理器,对azkaban的响应进行统一处理
 *
 * @author: Junjie Zhang
 * @date: 2021/9/28
 */
public class ResponseHandler {

	private static Logger logger = LoggerFactory.getLogger(ResponseHandler.class);

	public static <T extends BaseResponse> T handle(Request request, Class<T> tClass) {
		T response = null;
		try {
			Response res = request.execute();
			HttpEntity entity = res.returnResponse().getEntity();
			response = handle(entity, tClass);
		} catch (Exception e) {
			try {
				response = tClass.newInstance();
				response.setStatus(T.ERROR);
				response.setError(e.getMessage());
				response.correction();
			} catch (Exception ea) {
				logger.warn(ea.getMessage());
			}
		}
		return response;
	}

	public static BaseResponse handle(Request request) {
		return handle(request, BaseResponse.class);
	}

	public static BaseResponse handle(String content) {
		return handle(content, BaseResponse.class);
	}

	public static <T extends BaseResponse> T handle(HttpEntity entity, Class<T> tClass) {
		T response = null;
		try {
			String content = EntityUtils.toString(entity);
			response = handle(content, tClass);
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return response;
	}

	public static <T extends BaseResponse> T handle(String content, Class<T> tClass) {
		T response = null;

		try {
			response = new ObjectMapper().readValue(content, tClass);
			if (Objects.nonNull(response.getError())) {
				response.setStatus(T.ERROR);
			}
			response.correction();
		} catch (JsonProcessingException e) {
			logger.error("jackson转换获取response类失败", e);
			try {
				response = tClass.getDeclaredConstructor().newInstance();
				response.setStatus(T.ERROR);
				response.setError(content);
				response.correction();
			} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e0) {
				logger.error("根据无参构造方法初始化类异常", e0);
			}
		}
		return response;
	}

}

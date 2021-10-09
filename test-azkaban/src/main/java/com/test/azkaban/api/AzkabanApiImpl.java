package com.test.azkaban.api;

import com.test.azkaban.response.BaseResponse;
import com.test.azkaban.response.LoginResponse;
import com.test.azkaban.response.ResponseHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2021/9/28
 */
public class AzkabanApiImpl implements AzkabanApi {

	private String username;
	private String password;
	private String uri;
	private String sessionId;

	@Override
	public LoginResponse login() throws IOException {
		Response response = Request.Post(uri)
				.bodyForm(Form.form()
						.add("action", "login")
						.add("username", username)
						.add("password", password).build())
				.execute();
		HttpEntity entity = response.returnResponse().getEntity();
		String content = EntityUtils.toString(entity).replace("session.id", "sessionId");
		LoginResponse loginResponse = ResponseHandler.handle(content, LoginResponse.class);
        if (StringUtils.isNotEmpty(loginResponse.getSessionId())){
        	this.sessionId = loginResponse.getSessionId();
		}
		return loginResponse;
	}

	@Override
	public BaseResponse createProject(String name, String desc) {
		Request request = Request.Post(uri + "/manager")
				.bodyForm(Form.form()
						.add("session.id", sessionId)
						.add("action", "create")
						.add("name", name)
						.add("description", desc).build());
		return ResponseHandler.handle(request);
	}

	@Override
	public BaseResponse deleteProject(String name) {
		return null;
	}

}

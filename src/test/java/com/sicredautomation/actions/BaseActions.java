package com.sicredautomation.actions;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

public class BaseActions {

	protected Response response;

	@Step
	public int getStatusCode() throws Exception {
		return this.response.then().extract().statusCode();
	}

	@Step
	public String getResponse() throws Exception {
		return this.response.asString();
	}
}

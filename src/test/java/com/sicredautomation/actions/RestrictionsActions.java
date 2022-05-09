package com.sicredautomation.actions;

import static com.sicredautomation.utils.Constants.*;
import static net.serenitybdd.rest.SerenityRest.*;

import jline.internal.Log;

import net.thucydides.core.annotations.Step;

public class RestrictionsActions extends BaseActions {

	@Step
	public void get_restrictions_by_document(String document) throws Exception {
		String url = String.format("%s/%s", RESTRICTIONS_ENDPOINT, document);

		this.response = given().contentType("application/json").when().get(url);
		Log.info(String.format("\n\n#RESPONSE\nStatus Code => %s\nBody Response => %s\n\n\n", this.response.getStatusCode(), this.response.asString()));
	}
}

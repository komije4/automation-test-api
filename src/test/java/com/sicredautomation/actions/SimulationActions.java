package com.sicredautomation.actions;

import static com.sicredautomation.utils.Constants.*;
import static net.serenitybdd.rest.SerenityRest.*;

import com.sicredautomation.request.SimulationRequest;

import jline.internal.Log;

import net.thucydides.core.annotations.Step;

public class SimulationActions extends BaseActions {

	@Step
	public void post_simulation(SimulationRequest request) throws Exception {
		String url = SIMULATION_ENDPOINT;

		Log.info(String.format("\n\n#REQUEST\nMethod => POST, %s\nBody Request => %s", url, request.toString()));

		this.response = given().contentType("application/json").body(request).when().post(url);

		Log.info(String.format("\n\n#RESPONSE\nStatus Code => %s\nBody Response => %s\n\n\n", this.response.getStatusCode(), this.response.asString()));
	}

	@Step
	public void put_simulation(String document, SimulationRequest request) throws Exception {
		String url = String.format("%s/%s", SIMULATION_ENDPOINT, document);

		Log.info(String.format("\n\n#REQUEST\nMethod => PUT, %s\nBody Request => %s", url, request.toString()));

		this.response = given().contentType("application/json").body(request).when().put(url);

		Log.info(String.format("\n\n#RESPONSE\nStatus Code => %s\nBody Response => %s\n\n\n", this.response.getStatusCode(), this.response.asString()));
	}

	@Step
	public void get_simulation_by_document(String document) throws Exception {
		String url = String.format("%s/%s", SIMULATION_ENDPOINT, document);

		Log.info(String.format("\n\n#REQUEST\nMethod => GET, %s\nBody Request => %s", url, null));

		this.response = given().contentType("application/json").when().get(url);

		Log.info(String.format("\n\n#RESPONSE\nStatus Code => %s\nBody Response => %s\n\n\n", this.response.getStatusCode(), this.response.asString()));
	}

	@Step
	public void delete_simulation_by_document(String document) throws Exception {
		String url = String.format("%s/%s", SIMULATION_ENDPOINT, document);

		Log.info(String.format("\n\n#REQUEST\nMethod => DELETE, %s\nBody Request => %s", url, null));

		this.response = given().contentType("application/json").when().delete(url);

		Log.info(String.format("\n\n#RESPONSE\nStatus Code => %s\nBody Response => %s\n\n\n", this.response.getStatusCode(), this.response.asString()));
	}
}

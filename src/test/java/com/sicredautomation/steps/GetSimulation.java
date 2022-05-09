package com.sicredautomation.steps;

import static org.junit.Assert.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import com.sicredautomation.actions.SimulationActions;
import com.sicredautomation.request.SimulationRequest;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;

import com.sicredautomation.response.BaseErrorResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetSimulation {

	@Steps
	private SimulationActions actions;

	private String document;

	private SimulationRequest request;

	@Given("^os campos de JSON de consultar de simulação$")
	public void the_simulation_registration_JSON_fields() throws Exception {
		this.request = new SimulationRequest();
		this.document = this.request.getDocument();
	}

	@Given("^que eu tenho um documento não existente$")
	public void that_I_have_a_non_existing_document() throws Exception {
		this.request = new SimulationRequest();
		this.document = "not-exists";
	}

	@When("^faço uma solicitação GET para o serviço de consultar de simulação$")
	public void i_make_a_GET_request_to_the_simulation_registration_service() throws Exception {
		actions.get_simulation_by_document(this.document);
	}

	@Then("^o codigo de resposta HTTP do serviço de consultar simulação deve ser igual (\\d+)$")
	public void the_HTTP_response_code_must_be_the_same(int statusCode) throws Exception {
		assertEquals(statusCode, this.actions.getStatusCode());
	}

	@And("^deve exibir a mensagem \"([^\"]*)\" do serviço de consultar simulação$")
	public void should_display_the_message(String message) throws Exception {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		String response = this.actions.getResponse();

		BaseErrorResponse res = gson.fromJson(response, BaseErrorResponse.class);

		assertEquals(message, res.getMessage());
	}

	@And("^deve exibir a mensagem de erro \"([^\"]*)\" do serviço de consultar simulação$")
	public void should_display_the_message_error(String message) throws Exception {
		String response = this.actions.getResponse();

		Boolean exists = response.contains(message);

		assertTrue(exists);
	}
}

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
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PostSimulation {

	@Steps
	private SimulationActions actions;

	private SimulationRequest request;

	@Given("^os campos de JSON de cadastro de simulação$")
	public void the_simulation_registration_JSON_fields() throws Exception {
		this.request = new SimulationRequest();
	}

	@Given("^os campos de JSON de cadastro de simulação \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void the_simulation_registration_JSON_fields_dynamic(String name, String document, String email, Long value,
			Integer installments, Boolean insurance) throws Exception {

		this.request = new SimulationRequest();
		this.request.setName(Strings.isNullOrEmpty(name) ? null : name);
		this.request.setDocument(Strings.isNullOrEmpty(document) ? null : document);
		this.request.setEmail(Strings.isNullOrEmpty(email) ? null : email);
		this.request.setValue(value);
		this.request.setInstallments(installments);
		this.request.setInsurance(insurance);
	}

	@Given("^que eu tenho uma simulação cadastrada$")
	public void that_I_have_a_simulation_registered() throws Exception {
		this.request = new SimulationRequest();
	}

	@When("^faço uma solicitação POST para o serviço de cadastro de simulação$")
	public void i_make_a_POST_request_to_the_simulation_registration_service() throws Exception {
		actions.post_simulation(this.request);
	}

	@Then("^o codigo de resposta HTTP do serviço de simulação deve ser igual (\\d+)$")
	public void the_HTTP_response_code_must_be_the_same(int statusCode) throws Exception {
		assertEquals(statusCode, this.actions.getStatusCode());
	}

	@And("^deve exibir a mensagem \"([^\"]*)\" do serviço de simulação$")
	public void should_display_the_message(String message) throws Exception {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		String response = this.actions.getResponse();

		BaseErrorResponse res = gson.fromJson(response, BaseErrorResponse.class);

		assertEquals(message, res.getMessage());
	}

	@And("^deve exibir a mensagem de erro \"([^\"]*)\" do serviço de simulação$")
	public void should_display_the_message_error(String message) throws Exception {
		String response = this.actions.getResponse();

		Boolean exists = response.contains(message);

		assertTrue(exists);
	}
}

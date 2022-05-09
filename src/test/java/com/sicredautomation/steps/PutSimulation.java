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

public class PutSimulation {

	@Steps
	private SimulationActions actions;

	private String document;

	private SimulationRequest request;

	@Given("^os campos de JSON de atualização de simulação$")
	public void the_simulation_registration_JSON_fields() throws Exception {
		this.request = new SimulationRequest();
		this.document = this.request.getDocument();
	}

	@Given("^que eu tenho um cpf não existente$")
	public void that_I_have_a_non_existing_document() throws Exception {
		this.request = new SimulationRequest();
		this.document = "not-exists";
	}

	@Given("^os campos de JSON de atualização de simulação \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void the_simulation_registration_JSON_fields_dynamic(String name, String document, String email, Long value,
			Integer installments, Boolean insurance) throws Exception {

		this.request = new SimulationRequest();
		this.document = this.request.getDocument();
		this.request.setName(Strings.isNullOrEmpty(name) ? null : name);
		this.request.setDocument(Strings.isNullOrEmpty(document) ? null : document);
		this.request.setEmail(Strings.isNullOrEmpty(email) ? null : email);
		this.request.setValue(value);
		this.request.setInstallments(installments);
		this.request.setInsurance(insurance);
	}

	@When("^faço uma solicitação PUT para o serviço de atualização de simulação$")
	public void i_make_a_PUT_request_to_the_simulation_registration_service() throws Exception {
		actions.put_simulation(this.document, this.request);
	}

	@Then("^o codigo de resposta HTTP do serviço de atualizar simulação deve ser igual (\\d+)$")
	public void the_HTTP_response_code_must_be_the_same(int statusCode) throws Exception {
		assertEquals(statusCode, this.actions.getStatusCode());
	}

	@And("^deve exibir a mensagem \"([^\"]*)\" do serviço de atualizar simulação$")
	public void should_display_the_message(String message) throws Exception {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		String response = this.actions.getResponse();

		BaseErrorResponse res = gson.fromJson(response, BaseErrorResponse.class);

		assertEquals(message, res.getMessage());
	}

	@And("^deve exibir a mensagem de erro \"([^\"]*)\" do serviço de atualizar simulação$")
	public void should_display_the_message_error(String message) throws Exception {
		String response = this.actions.getResponse();

		Boolean exists = response.contains(message);

		assertTrue(exists);
	}
}

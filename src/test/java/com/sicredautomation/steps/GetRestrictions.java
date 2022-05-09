package com.sicredautomation.steps;

import static org.junit.Assert.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import com.sicredautomation.actions.RestrictionsActions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;

import com.sicredautomation.response.BaseErrorResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetRestrictions {

	@Steps
	private RestrictionsActions actions;

	private String document;

	@Given("^dato que eu tenha um cpf \"([^\"]*)\" com restrições cadastrado$")
	public void since_i_have_a_cpf_with_restrictions_registered(String document) throws Exception {
		this.document = document;
	}

	@When("^faço uma solicitação GET para o serviço de consulta de restrições por cpf$")
	public void i_make_a_GET_request_to_the_constraint_query_service_by_document() throws Exception {
		actions.get_restrictions_by_document(this.document);
	}

	@Then("^o codigo de resposta HTTP do serviço de restrições deve ser igual (\\d+)$")
	public void the_HTTP_response_code_must_be_the_same(int statusCode) throws Exception {
		assertEquals(statusCode, this.actions.getStatusCode());
	}

	@And("^deve exibir a mensagem \"([^\"]*)\" do serviço de restrições$")
	public void should_display_the_message(String message) throws Exception {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		String response = this.actions.getResponse();

		BaseErrorResponse res = gson.fromJson(response, BaseErrorResponse.class);

		assertEquals(message, res.getMessage());
	}
}

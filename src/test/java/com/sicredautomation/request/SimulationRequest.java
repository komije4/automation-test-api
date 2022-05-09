package com.sicredautomation.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import gherkin.deps.com.google.gson.Gson;

public class SimulationRequest {

	@SerializedName("nome")
	@Expose
	private String name = "Automation Test";

	@SerializedName("cpf")
	@Expose
	private String document = "06956502038";

	@SerializedName("email")
	@Expose
	private String email = "test.email@automation.com";

	@SerializedName("valor")
	@Expose
	private Long value = 1000L;

	@SerializedName("parcelas")
	@Expose
	private Integer installments = 2;

	@SerializedName("seguro")
	@Expose
	private Boolean insurance = true;

	public String getName() {
		return name;
	}

	public String getDocument() {
		return document;
	}

	public String getEmail() {
		return email;
	}

	public Long getValue() {
		return value;
	}

	public Integer getInstallments() {
		return installments;
	}

	public Boolean getInsurance() {
		return insurance;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

	public void setInsurance(Boolean insurance) {
		this.insurance = insurance;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
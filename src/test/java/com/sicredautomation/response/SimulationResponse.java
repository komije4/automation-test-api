package com.sicredautomation.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimulationResponse {

	@SerializedName("nome")
	@Expose
	private String name;

	@SerializedName("cpf")
	@Expose
	private String document;

	@SerializedName("email")
	@Expose
	private String email;

	@SerializedName("valor")
	@Expose
	private Long value;

	@SerializedName("parcelas")
	@Expose
	private Integer installments;

	@SerializedName("seguro")
	@Expose
	private Boolean insurance;

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

}
@simulation @simulation_put
Feature: Atualizar uma simulação

	A simulação é um atualização que ficará registrado informações importantes
	sobre o crédito como valor, parcelas, dados de contato, etc...

	@successs @simulation_put_success
	Scenario: PUT - Atualização de simulação com sucesso
		Given os campos de JSON de atualização de simulação
		When faço uma solicitação PUT para o serviço de atualização de simulação
		Then o codigo de resposta HTTP do serviço de atualizar simulação deve ser igual 200

	@except @simulation_put_exists
	Scenario: PUT (NEGATIVO) - Atualização de simulação - Existencia
		Given que eu tenho um cpf não existente
		When faço uma solicitação PUT para o serviço de atualização de simulação
		Then o codigo de resposta HTTP do serviço de atualizar simulação deve ser igual 404
		And deve exibir a mensagem "CPF not-exists não encontrado" do serviço de atualizar simulação

	@except @simulation_put_except
	Scenario Outline: PUT (NEGATIVO) - Atualização de simulação - Campos obrigatórios
		Given os campos de JSON de atualização de simulação "<name>" "<document>" "<email>" "<value>" "<installments>" "<insurance>"
		When faço uma solicitação PUT para o serviço de atualização de simulação
		Then o codigo de resposta HTTP do serviço de atualizar simulação deve ser igual 400
		And deve exibir a mensagem de erro "<message>" do serviço de atualizar simulação

		Examples:
			| name            | document    | email                     | value | installments | insurance | message                                |
			| Automation Test | 06956502038 | test.email@automation.com | 1000  | 1            | true      | Parcelas deve ser igual ou maior que 2 |

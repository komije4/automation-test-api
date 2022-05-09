@simulation @simulation_post
Feature: Criar uma simulação

	A simulação é um cadastro que ficará registrado informações importantes
	sobre o crédito como valor, parcelas, dados de contato, etc...

	@successs @simulation_post_success
	Scenario: POST - Criação de simulação com sucesso
		Given os campos de JSON de cadastro de simulação
		When faço uma solicitação POST para o serviço de cadastro de simulação
		Then o codigo de resposta HTTP do serviço de simulação deve ser igual 201

	@except @simulation_post_duplicity
	Scenario: POST (NEGATIVO) - Criação de simulação - Duplicidade
		Given que eu tenho uma simulação cadastrada
		When faço uma solicitação POST para o serviço de cadastro de simulação
		Then o codigo de resposta HTTP do serviço de simulação deve ser igual 400
		And deve exibir a mensagem "CPF duplicado" do serviço de simulação

	@except @simulation_post_except
	Scenario Outline: POST (NEGATIVO) - Criação de simulação - Campos obrigatórios
		Given os campos de JSON de cadastro de simulação "<name>" "<document>" "<email>" "<value>" "<installments>" "<insurance>"
		When faço uma solicitação POST para o serviço de cadastro de simulação
		Then o codigo de resposta HTTP do serviço de simulação deve ser igual 400
		And deve exibir a mensagem de erro "<message>" do serviço de simulação

		Examples:
			| name            | document    | email                     | value | installments | insurance | message                                |
			|                 | 64651130070 | test.email@automation.com | 1000  | 2            | true      | Nome não pode ser vazio                |
			| Automation Test |             | test.email@automation.com | 1000  | 2            | true      | CPF não pode ser vazio                 |
			| Automation Test | 64651130070 |                           | 1000  | 2            | true      | E-mail não deve ser vazio              |
			| Automation Test | 64651130070 | test.email@automation.com |       | 2            | true      | Valor não pode ser vazio               |
			| Automation Test | 64651130070 | test.email@automation.com | 1000  |              | true      | Parcelas não pode ser vazio            |
			| Automation Test | 64651130070 | test.email@automation.com | 1000  | 1            | true      | Parcelas deve ser igual ou maior que 2 |

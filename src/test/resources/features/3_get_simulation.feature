@simulation @simulation_get
Feature: Consultar uma simulação

	A simulação é um consultar que ficará registrado informações importantes
	sobre o crédito como valor, parcelas, dados de contato, etc...

	@successs @simulation_get_success
	Scenario: GET - Consultar de simulação com sucesso
		Given os campos de JSON de consultar de simulação
		When faço uma solicitação GET para o serviço de consultar de simulação
		Then o codigo de resposta HTTP do serviço de consultar simulação deve ser igual 200

	@except @simulation_get_exists
	Scenario: GET (NEGATIVO) - Consultar de simulação - Existencia
		Given que eu tenho um documento não existente
		When faço uma solicitação GET para o serviço de consultar de simulação
		Then o codigo de resposta HTTP do serviço de consultar simulação deve ser igual 404
		And deve exibir a mensagem "CPF not-exists não encontrado" do serviço de consultar simulação


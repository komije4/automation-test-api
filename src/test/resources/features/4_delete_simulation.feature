@simulation @simulation_delete
Feature: Deletar uma simulação

	A simulação é um deletar que ficará registrado informações importantes
	sobre o crédito como valor, parcelas, dados de contato, etc...

	@successs @simulation_delete_success
	Scenario: DELETE - Deletar de simulação com sucesso
		Given os campos de JSON de deletar de simulação
		When faço uma solicitação DELETE para o serviço de deletar de simulação
		Then o codigo de resposta HTTP do serviço de deletar simulação deve ser igual 200


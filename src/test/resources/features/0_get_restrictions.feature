@restrictions @restrictions_get
Feature: Consultar uma restrição pelo CPF

	O endpoint de Restrições tem a finalidade de consultar o CPF informando,
	retornando se ele possui ou não uma restrição.
	- Se não possui restrição do HTTP Status 204 é retornado
	- Se possui restrição o HTTP Status 200 é retornado com a mensagem "O CPF
	99999999999 tem problema"

	@successs @not_restrictions_get_document
	Scenario: GET - Consulta de CPF sem restrição
		Given dato que eu tenha um cpf "65523153095" com restrições cadastrado
		When faço uma solicitação GET para o serviço de consulta de restrições por cpf
		Then o codigo de resposta HTTP do serviço de restrições deve ser igual 204

	@successs @restrictions_get_document
	Scenario Outline: GET - Consulta de CPF com restrição
		Given dato que eu tenha um cpf "<document>" com restrições cadastrado
		When faço uma solicitação GET para o serviço de consulta de restrições por cpf
		Then o codigo de resposta HTTP do serviço de restrições deve ser igual 200
		And deve exibir a mensagem "<message>" do serviço de restrições

		Examples:
			| document    | message                        |
			| 97093236014 | O CPF 97093236014 tem problema |
			| 60094146012 | O CPF 60094146012 tem problema |
			| 84809766080 | O CPF 84809766080 tem problema |
			| 62648716050 | O CPF 62648716050 tem problema |
			| 26276298085 | O CPF 26276298085 tem problema |
			| 01317496094 | O CPF 01317496094 tem problema |
			| 55856777050 | O CPF 55856777050 tem problema |
			| 19626829001 | O CPF 19626829001 tem problema |
			| 24094592008 | O CPF 24094592008 tem problema |
			| 58063164083 | O CPF 58063164083 tem problema |
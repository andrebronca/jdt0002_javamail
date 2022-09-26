package br.com.emailproject.dto;

import br.com.emailproject.model.Email;

public class EmailLayout {
	private final String Q1 = "<br>";
	private final String Q2 = "<br><br>";

	//mais métodos para outros perfis
	public Email montarEmailAdministrador(String destinatario, String assunto) {
		StringBuilder sb = new StringBuilder();
		sb.append("A/C Administrador").append(Q2).append("Solicito alteração de senha do sistema!" + Q2);

		gerarAssinatura(sb);
		gerarRodape(sb);
		return new Email(destinatario, assunto, sb.toString());
	}

	private String gerarAssinatura(StringBuilder sb) {
		return sb.append("Att.:")
				.append(Q1)
				.append("Operador de caixa")	//pode ser feito dinâmico
				.append(Q2)
				.toString();
	}

	private String gerarRodape(StringBuilder sb) {
		return sb.append("<hr>").append("E-mail automático. Favor não responder esse e-mail" + Q1).toString();
	}
}

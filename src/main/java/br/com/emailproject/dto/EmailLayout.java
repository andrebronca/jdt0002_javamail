package br.com.emailproject.dto;

import br.com.emailproject.model.Email;

public class EmailLayout {
	
	public Email montarEmailAdministrador(String destinatario, String assunto) {
		StringBuilder sb = new StringBuilder();
		return new Email(destinatario,assunto, sb.toString());
	}
}

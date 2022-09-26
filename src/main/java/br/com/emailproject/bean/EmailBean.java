package br.com.emailproject.bean;

import java.io.Serializable;

//wildfly implementa a especifica��o jee completa
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.emailproject.dto.EmailLayout;
import br.com.emailproject.model.Email;
import br.com.emailproject.service.EmailService;

@Named
@RequestScoped
public class EmailBean implements Serializable {

	private static final long serialVersionUID = 4538755582654584073L;
	private final String DESTINATARIO = "bronca.andre@gmail.com";
	private final String ASSUNTO = "Envio e-mail adm jdt0002";

	@Inject
	private EmailService emailService;

	public String enviarEmail() {
		emailService.enviar(montarEmail());
		return null;
	}

	private Email montarEmail() {
		EmailLayout layout = new EmailLayout();
		return layout.montarEmailAdministrador(DESTINATARIO, ASSUNTO);
	}
}

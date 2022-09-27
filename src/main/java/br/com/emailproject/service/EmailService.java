package br.com.emailproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.emailproject.model.Email;
import br.com.emailproject.util.LogUtil;

@Stateless
public class EmailService extends Thread {

	private List<Email> emails;

	public void enviar(Email email) {
		emails = new ArrayList<>();
		emails.add(email);
		send();
	}

	public void enviar(List<Email> emails) {
		this.emails = emails;
		send();
	}
	
	//por ser uma classe stateless tem que utilizar esse recurso, pois a classe possui propriedade
	private EmailService copy() {
		EmailService es = new EmailService();
		es.emails = emails;
		return es;
	}
	
	private void send() {
		new Thread(this.copy()).start();
	}
	
	@Override
	public void run() {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", true);
		//obtendo valores do arquivo: standalone.xml do WildFly
		props.put("mail.smtp.host", System.getProperty("email-project.mail.smtp.host"));
		props.put("mail.smtp.port", System.getProperty("email-project.mail.smtp.port"));
		
		Session session = Session.getInstance(props);
		session.setDebug(false);
		
		for (Email email : emails) {
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(System.getProperty("email-project.mail.from")));
				
				//emails separados por '/'
				if(email.getDestinatario().contains("/")) {
					List<InternetAddress> emailsLocal = new ArrayList<>();
					for(String e : email.getDestinatario().split("/")) {
						emailsLocal.add(new InternetAddress(e));
					}
				}
				
			} catch (AddressException e) {
				LogUtil.getLogger(EmailService.class).error("Address : Erro ao enviar e-mail: "+ e.getMessage());
			} catch (MessagingException e) {
				LogUtil.getLogger(EmailService.class).error("Messaging : Erro ao enviar e-mail: "+ e.getMessage());
			}
		}
	}
}

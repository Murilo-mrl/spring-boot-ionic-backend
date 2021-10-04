package com.aprendendo.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.aprendendo.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}

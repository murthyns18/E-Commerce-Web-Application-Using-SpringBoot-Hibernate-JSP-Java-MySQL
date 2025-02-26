package com.ns.ecommerce.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService 
{

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String to, String subject, String body, boolean isHtml)
	{
		try 
		{
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true); // `true` for multipart messages

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, isHtml); // Set `isHtml` to true for HTML content

			mailSender.send(message);
		} 
		catch (MessagingException e)
		{
			throw new RuntimeException("Failed to send email", e);
		}
	}
	public void sendEmail(String to, String subject, String body) 
	{
		sendEmail(to, subject, body, true); // Default to HTML emails
	}

}
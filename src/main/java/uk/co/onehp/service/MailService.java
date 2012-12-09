package uk.co.onehp.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface MailService {

	void sendMail() throws AddressException, MessagingException;
}

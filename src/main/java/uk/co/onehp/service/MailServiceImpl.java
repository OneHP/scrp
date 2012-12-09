package uk.co.onehp.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService {

	private static final String AUTH = System.getenv("scrp.auth");
	private static final String HOST = System.getenv("scrp.host");
	private static final String PASS = System.getenv("scrp.password");
	private static final String PORT = System.getenv("scrp.port");
	private static final String TO = System.getenv("scrp.to");
	private static final String USER = System.getenv("scrp.user");

	@Override
	public void sendMail() throws AddressException, MessagingException {
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.user", USER);
		props.put("mail.smtp.password", PASS);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.auth", AUTH);

		Session session = Session.getDefaultInstance(props, null);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(USER));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		message.setSubject("Barclays Scrape");
		message.setText("Found Barclays");
		
		Transport transport = session.getTransport("smtp");
		transport.connect(HOST, USER, PASS);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}

}

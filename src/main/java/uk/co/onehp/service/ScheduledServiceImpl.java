package uk.co.onehp.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service("scheduledService")
public class ScheduledServiceImpl implements ScheduledService {

	static final Logger LOG = LoggerFactory
			.getLogger(ScheduledServiceImpl.class);

	@Autowired
	RestOperations restOperations;

	@Autowired
	MailService mailService;

	// private final Pattern pattern =
	private final Pattern pattern = Pattern
			.compile("[bB][aA][rR][cC][lL][aA][yY]");

	// private final Pattern pattern = Pattern.compile("[aA][wW][aA][kK][uU]");

	@Override
	@Scheduled(cron = "0 30 8 * * *")
	public void scrape() {
		LOG.info("scrape");
		String response = this.restOperations.getForObject(
				"http://www.justice.gov.uk/courts/court-lists/list-cause-rcj",
				String.class, new Object[] {});

		Matcher matcher = this.pattern.matcher(response);
		boolean find = matcher.find();
		LOG.info("" + find);

		try {
			if (find) {
				mailService.sendMail();
			}
		} catch (AddressException e) {
			LOG.error(e.getLocalizedMessage());
		} catch (MessagingException e) {
			LOG.error(e.getLocalizedMessage());
		}
	}

}

package uk.co.onehp.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service("scheduledService")
public class ScheduledServiceImpl implements ScheduledService {

	static final Logger LOG = LoggerFactory.getLogger(ScheduledServiceImpl.class);

	@Autowired
	RestOperations restOperations;

	// private final Pattern pattern =
	// Pattern.compile("[bB][aA][rR][cC][lL][aA][yY]");
	private final Pattern pattern = Pattern.compile("[hH][oO][lL][mM][aA][nN]");

	@Override
	@Scheduled(cron = "0 * * * * *")
	public void scrape() {
		LOG.info("scrape");
		String response = this.restOperations.getForObject(
				"http://www.justice.gov.uk/courts/court-lists/list-cause-rcj", String.class, new Object[] {});

		Matcher matcher = this.pattern.matcher(response);
		LOG.info("" + matcher.find());
	}

}

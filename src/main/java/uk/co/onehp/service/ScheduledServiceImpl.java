package uk.co.onehp.service;

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

	@Override
	@Scheduled(cron = "* * * * * *")
	public void scrape() {
		LOG.info("scrape");
		String response = this.restOperations.getForObject("http://qrym5z41qhtr19k9.browserver.org", String.class,
				new Object[] {});
		LOG.info(response);
	}

}

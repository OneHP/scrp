package uk.co.onehp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("scheduledService")
public class ScheduledServiceImpl implements ScheduledService {

	static final Logger LOG = LoggerFactory.getLogger(ScheduledServiceImpl.class);

	@Override
	@Scheduled(cron = "* * * * * *")
	public void scrape() {
		LOG.info("scrape");
	}

}

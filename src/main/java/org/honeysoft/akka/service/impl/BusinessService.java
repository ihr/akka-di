package org.honeysoft.akka.service.impl;

import org.honeysoft.akka.service.IBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BusinessService implements IBusinessService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doBusiness(Object o) {
        logger.info("Doing business with {}", o);
    }
}

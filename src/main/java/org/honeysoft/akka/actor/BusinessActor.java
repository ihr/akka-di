package org.honeysoft.akka.actor;

import akka.actor.UntypedActor;
import org.honeysoft.akka.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessActor extends UntypedActor {

    @Autowired
    private IBusinessService businessService;

    @Override
    public void onReceive(Object o) throws Exception {
        businessService.doBusiness(o);
    }
}

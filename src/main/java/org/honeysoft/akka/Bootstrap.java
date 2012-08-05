package org.honeysoft.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.honeysoft.akka.actor.BusinessActor;
import org.honeysoft.akka.di.DependencyInjectionProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@ComponentScan({"org.honeysoft.akka.service"})
public class Bootstrap {

    public static final String BUSINESS_ACTOR = "honeysoft-business-actor";
    public static final String ACTOR_SYSTEM = "honeysoft-actor-actorSystem";
    private ActorSystem actorSystem;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean(name = ACTOR_SYSTEM, destroyMethod = "shutdown")
    public ActorSystem actorSystem() {
        actorSystem = ActorSystem.create(ACTOR_SYSTEM);
        return actorSystem;
    }

    @Bean(name = BUSINESS_ACTOR)
    @DependsOn({ACTOR_SYSTEM})
    public ActorRef businessActor() {
        return actorSystem.actorOf(//
                new DependencyInjectionProps(applicationContext, BusinessActor.class), BUSINESS_ACTOR);
    }

}

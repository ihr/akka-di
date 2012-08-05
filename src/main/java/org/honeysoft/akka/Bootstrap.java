/*
 * Copyright (c) 2012 Ivan Hristov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

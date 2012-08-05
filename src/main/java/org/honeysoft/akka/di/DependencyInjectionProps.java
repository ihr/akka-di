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

package org.honeysoft.akka.di;

import akka.actor.Props;
import akka.actor.UntypedActorFactory;
import org.springframework.context.ApplicationContext;

public class DependencyInjectionProps extends Props {
    /**
     * No-args constructor that sets all the default values.
     */
    public DependencyInjectionProps(ApplicationContext applicationContext, Class<?> actorClass) {
        super(new SpringUntypedActorFactory(actorClass, applicationContext));
    }

    /**
     * Java API.
     */
    public DependencyInjectionProps(ApplicationContext applicationContext, UntypedActorFactory factory) {
        super(new SpringUntypedActorFactory(factory, applicationContext));
    }

}

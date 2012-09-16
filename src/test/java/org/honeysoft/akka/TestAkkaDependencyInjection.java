/*
 * Copyright (c) 2012 Ivan Hristov <hristov[DOT]iv[AT]gmail[DOT]com>
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
import org.honeysoft.akka.service.IBusinessService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.fest.reflect.core.Reflection.field;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {Bootstrap.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAkkaDependencyInjection {

    @Autowired
    @Qualifier(Bootstrap.BUSINESS_ACTOR)
    private ActorRef businessActor;

    @Autowired
    private IBusinessService businessService;

    @Mock
    private Logger mockBusinessServiceLogger;

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldInjectBusinessService() {
        //GIVEN
        field("logger").ofType(Logger.class).in(businessService).postDecorateWith(mockBusinessServiceLogger);

        //WHEN
        businessActor.tell("McDonald");

        //THEN
        verify(mockBusinessServiceLogger, times(1)).info(anyString(), anyObject());
    }
}

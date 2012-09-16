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
package org.honeysoft.akka.actor;

import akka.actor.ActorRef;
import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;
import org.fest.assertions.Assertions;
import org.honeysoft.akka.Bootstrap;
import org.honeysoft.akka.service.IBusinessService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.fest.reflect.core.Reflection.field;

/**
 * Testing {@link BusinessActor}
 */
@ContextConfiguration(classes = {Bootstrap.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class BusinessActorTest {

    @Autowired
    @Qualifier(Bootstrap.BUSINESS_ACTOR)
    private ActorRef businessActorRef;

    @Autowired
    private IBusinessService businessService;

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldBeValidWhenNoOneIsNull() throws Exception {
        //GIVEN
        final ConcurrentMap<String, Object> threadSafeMap = new ConcurrentHashMap<String, Object>(1);
        field("logger").ofType(Logger.class).in(businessService).postDecorateWith(new TestLogger(threadSafeMap));

        //WHEN
        String testString = "test-string";
        businessActorRef.tell(testString);

        //THEN
        Awaitility.waitAtMost(Duration.FIVE_SECONDS).until(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !threadSafeMap.isEmpty();
            }
        });

        Assertions.assertThat(threadSafeMap).hasSize(1);
        Assertions.assertThat(threadSafeMap.values().iterator().next()).isEqualTo(testString);
    }

    private static final class TestLogger implements Logger {

        private final ConcurrentMap<String, Object> map;

        private TestLogger(ConcurrentMap<String, Object> map) {
            this.map = map;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public boolean isTraceEnabled() {
            return false;
        }

        @Override
        public void trace(String msg) {
        }

        @Override
        public void trace(String format, Object arg) {
        }

        @Override
        public void trace(String format, Object arg1, Object arg2) {
        }

        @Override
        public void trace(String format, Object[] argArray) {
        }

        @Override
        public void trace(String msg, Throwable t) {
        }

        @Override
        public boolean isTraceEnabled(Marker marker) {
            return false;
        }

        @Override
        public void trace(Marker marker, String msg) {
        }

        @Override
        public void trace(Marker marker, String format, Object arg) {
        }

        @Override
        public void trace(Marker marker, String format, Object arg1, Object arg2) {
        }

        @Override
        public void trace(Marker marker, String format, Object[] argArray) {
        }

        @Override
        public void trace(Marker marker, String msg, Throwable t) {
        }

        @Override
        public boolean isDebugEnabled() {
            return false;
        }

        @Override
        public void debug(String msg) {
        }

        @Override
        public void debug(String format, Object arg) {
        }

        @Override
        public void debug(String format, Object arg1, Object arg2) {
        }

        @Override
        public void debug(String format, Object[] argArray) {
        }

        @Override
        public void debug(String msg, Throwable t) {
        }

        @Override
        public boolean isDebugEnabled(Marker marker) {
            return false;
        }

        @Override
        public void debug(Marker marker, String msg) {
        }

        @Override
        public void debug(Marker marker, String format, Object arg) {
        }

        @Override
        public void debug(Marker marker, String format, Object arg1, Object arg2) {
        }

        @Override
        public void debug(Marker marker, String format, Object[] argArray) {
        }

        @Override
        public void debug(Marker marker, String msg, Throwable t) {
        }

        @Override
        public boolean isInfoEnabled() {
            return false;
        }

        @Override
        public void info(String msg) {
        }

        @Override
        public void info(String format, Object arg) {
            map.put(format, arg);
        }

        @Override
        public void info(String format, Object arg1, Object arg2) {
        }

        @Override
        public void info(String format, Object[] argArray) {
        }

        @Override
        public void info(String msg, Throwable t) {
        }

        @Override
        public boolean isInfoEnabled(Marker marker) {
            return false;
        }

        @Override
        public void info(Marker marker, String msg) {
        }

        @Override
        public void info(Marker marker, String format, Object arg) {
        }

        @Override
        public void info(Marker marker, String format, Object arg1, Object arg2) {
        }

        @Override
        public void info(Marker marker, String format, Object[] argArray) {
        }

        @Override
        public void info(Marker marker, String msg, Throwable t) {
        }

        @Override
        public boolean isWarnEnabled() {
            return false;
        }

        @Override
        public void warn(String msg) {
        }

        @Override
        public void warn(String format, Object arg) {
        }

        @Override
        public void warn(String format, Object[] argArray) {
        }

        @Override
        public void warn(String format, Object arg1, Object arg2) {
        }

        @Override
        public void warn(String msg, Throwable t) {
        }

        @Override
        public boolean isWarnEnabled(Marker marker) {
            return false;
        }

        @Override
        public void warn(Marker marker, String msg) {
        }

        @Override
        public void warn(Marker marker, String format, Object arg) {
        }

        @Override
        public void warn(Marker marker, String format, Object arg1, Object arg2) {
        }

        @Override
        public void warn(Marker marker, String format, Object[] argArray) {
        }

        @Override
        public void warn(Marker marker, String msg, Throwable t) {
        }

        @Override
        public boolean isErrorEnabled() {
            return false;
        }

        @Override
        public void error(String msg) {
        }

        @Override
        public void error(String format, Object arg) {
        }

        @Override
        public void error(String format, Object arg1, Object arg2) {
        }

        @Override
        public void error(String format, Object[] argArray) {
        }

        @Override
        public void error(String msg, Throwable t) {
        }

        @Override
        public boolean isErrorEnabled(Marker marker) {
            return false;
        }

        @Override
        public void error(Marker marker, String msg) {
        }

        @Override
        public void error(Marker marker, String format, Object arg) {
        }

        @Override
        public void error(Marker marker, String format, Object arg1, Object arg2) {
        }

        @Override
        public void error(Marker marker, String format, Object[] argArray) {
        }

        @Override
        public void error(Marker marker, String msg, Throwable t) {
        }
    }
}

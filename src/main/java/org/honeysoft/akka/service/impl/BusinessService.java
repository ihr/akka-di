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

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
 
 
package org.piangles.backbone.services.logging;

import java.lang.reflect.Method;

import javax.management.RuntimeErrorException;

import org.piangles.core.services.Request;
import org.piangles.core.services.remoting.AbstractService;

public class LoggingControllerServiceImpl extends AbstractService
{
	private LoggingServiceImpl loggingServiceImpl = null;
	private Method recordMethod = null;

	public LoggingControllerServiceImpl(LoggingServiceImpl loggingServiceImpl)
	{
		super(loggingServiceImpl);
		
		this.loggingServiceImpl = loggingServiceImpl;
		try
		{
			recordMethod = loggingServiceImpl.getClass().getMethod("record", LogEvent.class);
		}
		catch (Exception e)
		{
			throw new RuntimeErrorException(new Error(this.getClass().getCanonicalName()), e.getMessage());
		}
	}

	protected Method lookupMethod(String endpoint, Object[] args)
	{
		return recordMethod;
	}
	
	@Override
	protected Object process(Method method, Object[] args, Request request) throws Exception
	{
		loggingServiceImpl.record((LogEvent)args[0]);

		return null;
	}
}

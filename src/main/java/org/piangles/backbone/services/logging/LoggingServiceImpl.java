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

import java.util.Properties;

import org.piangles.backbone.services.config.DefaultConfigProvider;
import org.piangles.backbone.services.logging.dao.LoggingDAO;
import org.piangles.backbone.services.logging.dao.LoggingMongoDAOImpl;
import org.piangles.backbone.services.logging.dao.LoggingRDBMSDAOImpl;
import org.piangles.core.email.EmailSupport;
import org.piangles.core.util.abstractions.ConfigProvider;

/**
 * Implementation will NOT implement the interface as 
 * all calls debug/info/warn/error/fatal will be converted
 * to RECORD method.
 * 
 * @author Vemuri Saradhi
 *
 */
public final class LoggingServiceImpl
{
	private static final String COMPONENT_ID = "14fe64ea-d15a-4c8b-af2f-f2c7efe1943b";
	private static final String DEFAULT_DAO_TYPE = "NoSql";
	private static final String DAO_TYPE = "DAOType";

	private LoggingDAO loggingDAO = null;
	
	public LoggingServiceImpl() throws Exception
	{
		ConfigProvider cp = new DefaultConfigProvider("LoggingService", COMPONENT_ID);
		Properties props = cp.getProperties();
		if (DEFAULT_DAO_TYPE.equals(props.getProperty(DAO_TYPE)))
		{
			loggingDAO = new LoggingMongoDAOImpl(cp);
		}
		else
		{
			loggingDAO = new LoggingRDBMSDAOImpl(cp);
		}
		System.out.println("Starting LoggingService with DAO: " + loggingDAO.getClass());
	}
	
	public void record(LogEvent event)
	{
		try
		{
			loggingDAO.insertLog(event);
		}
		catch (Exception e)
		{
			e.printStackTrace(System.out);
			System.out.println(e.getMessage());
			EmailSupport.notify(e, e.getMessage());
		}
	}
}

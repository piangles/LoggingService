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
 
 
 
package org.piangles.backbone.services.logging.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.piangles.backbone.services.logging.LogEvent;
import org.piangles.core.dao.DAOException;
import org.piangles.core.dao.rdbms.AbstractDAO;
import org.piangles.core.dao.rdbms.StatementPreparer;
import org.piangles.core.resources.ResourceManager;
import org.piangles.core.util.abstractions.ConfigProvider;

public class LoggingRDBMSDAOImpl extends AbstractDAO implements LoggingDAO
{
	private static final String INSERT_LOG_SP = "logs.insert_log_event";
	
	public LoggingRDBMSDAOImpl(ConfigProvider cp) throws Exception
	{
		super.init(ResourceManager.getInstance().getRDBMSDataStore(cp));
	}
	
	@Override
	public void insertLog(LogEvent event) throws DAOException
	{
		super.executeSP(INSERT_LOG_SP, 13, new StatementPreparer()
		{
			@Override
			public void prepare(CallableStatement call) throws SQLException
			{
				call.setString(1, event.getTraceId() != null? event.getTraceId().toString():null);
				call.setTimestamp(2, new Timestamp(event.getLoggedTimeStamp().getTime()));
				call.setString(3, event.getSystemInfo().getHostName());
				call.setString(4, event.getSystemInfo().getLoginId());
				call.setString(5, event.getSystemInfo().getProcessName());
				call.setString(6, event.getSystemInfo().getProcessId());
				call.setString(7, event.getSystemInfo().getThreadId());
				call.setInt(8, event.getCategory().getLevel());
				call.setString(9, event.getCategory().toString());
				call.setString(10, event.getClassName());
				call.setString(11, event.getLineNumber());
				call.setString(12, event.getMessage());
				call.setString(13, event.getExceptionStackTrace());
			}
		});
	}
}

package org.piangles.backbone.services.logging.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.piangles.backbone.services.config.DefaultConfigProvider;
import org.piangles.backbone.services.logging.LogEvent;
import org.piangles.core.dao.DAOException;
import org.piangles.core.dao.rdbms.AbstractDAO;
import org.piangles.core.dao.rdbms.StatementPreparer;
import org.piangles.core.resources.ResourceManager;

public class LoggingDAOImpl extends AbstractDAO implements LoggingDAO
{
	private static final String COMPONENT_ID = "14fe64ea-d15a-4c8b-af2f-f2c7efe1943b";
	private static final String INSERT_LOG_SP = "Backbone.InsertLog";
	
	public LoggingDAOImpl() throws Exception
	{
		super.init(ResourceManager.getInstance().getRDBMSDataStore(new DefaultConfigProvider("LoggingService", COMPONENT_ID)));
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

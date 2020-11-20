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
	private static final String DEFAULT_TYPE = "Mongo";
	private static final String TYPE = "Type";

	private LoggingDAO loggingDAO = null;
	
	public LoggingServiceImpl() throws Exception
	{
		ConfigProvider cp = new DefaultConfigProvider("LoggingService", COMPONENT_ID);
		Properties props = cp.getProperties();
		if (DEFAULT_TYPE.equals(props.getProperty(TYPE)))
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

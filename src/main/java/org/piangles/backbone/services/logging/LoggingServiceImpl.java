package org.piangles.backbone.services.logging;

import org.piangles.backbone.services.logging.dao.LoggingDAO;
import org.piangles.backbone.services.logging.dao.LoggingDAOImpl;

import org.piangles.backbone.services.logging.LogEvent;
import org.piangles.core.dao.DAOException;
import org.piangles.core.email.EmailSupport;

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
	private LoggingDAO loggingDAO = null;
	
	public LoggingServiceImpl() throws Exception
	{
		loggingDAO = new LoggingDAOImpl();
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
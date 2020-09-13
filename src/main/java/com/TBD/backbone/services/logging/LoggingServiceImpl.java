package com.TBD.backbone.services.logging;

import com.TBD.backbone.services.logging.dao.LoggingDAO;
import com.TBD.backbone.services.logging.dao.LoggingDAOImpl;
import com.TBD.core.dao.DAOException;
import com.TBD.core.email.EmailSupport;

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

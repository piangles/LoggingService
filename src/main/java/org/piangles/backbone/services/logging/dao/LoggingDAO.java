package org.piangles.backbone.services.logging.dao;

import org.piangles.backbone.services.logging.LogEvent;
import org.piangles.core.dao.DAOException;

public interface LoggingDAO
{
	public void insertLog(LogEvent event) throws DAOException;
}

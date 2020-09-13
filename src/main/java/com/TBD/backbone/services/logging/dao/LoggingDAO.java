package com.TBD.backbone.services.logging.dao;

import com.TBD.backbone.services.logging.LogEvent;
import com.TBD.core.dao.DAOException;

public interface LoggingDAO
{
	public void insertLog(LogEvent event) throws DAOException;
}

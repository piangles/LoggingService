package org.piangles.backbone.services.logging.dao;

import org.piangles.backbone.services.logging.LogEvent;
import org.piangles.core.dao.DAOException;
import org.piangles.core.dao.nosql.AbstractDAO;
import org.piangles.core.resources.ResourceManager;
import org.piangles.core.util.abstractions.ConfigProvider;

public class LoggingMongoDAOImpl extends AbstractDAO<LogEvent> implements LoggingDAO
{
	public LoggingMongoDAOImpl(ConfigProvider cp) throws Exception
	{
		super.init(ResourceManager.getInstance().getMongoDataStore(cp));
	}
	
	@Override
	public void insertLog(LogEvent event) throws DAOException
	{
		super.create(event);
	}

	@Override
	protected Class<LogEvent> getTClass()
	{
		return LogEvent.class;
	}
}

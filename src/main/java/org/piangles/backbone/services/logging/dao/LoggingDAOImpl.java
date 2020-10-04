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

public class LoggingDAOImpl extends AbstractNoSqlDAO
{
	private static final String COMPONENT_ID = "14fe64ea-d15a-4c8b-af2f-f2c7efe1943b";
	private static final String INSERT_LOG_SP = "Backbone.InsertLog";
	
	public LoggingDAOImpl() throws Exception
	{
		/*
		 * 
		//TODO: pass a NOSQLDataStore object to AbstractNoSqlDAO constructor just like RDBMSDataStore
		super.init(ResourceManager.getInstance().getRDBMSDataStore(new DefaultConfigProvider("LoggingService", COMPONENT_ID)));
		*
		*/
		
	}
	
	@Override
	public void insertLog(LogEvent event) throws DAOException
	{
		super.insertLog(event);
	}
}

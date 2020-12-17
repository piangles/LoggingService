package org.piangles.backbone.services.logging;

import java.lang.reflect.Method;

import javax.management.RuntimeErrorException;

import org.piangles.core.services.Request;
import org.piangles.core.services.remoting.AbstractService;

public class LoggingControllerServiceImpl extends AbstractService
{
	private LoggingServiceImpl loggingServiceImpl = null;
	private Method recordMethod = null;

	public LoggingControllerServiceImpl(LoggingServiceImpl loggingServiceImpl)
	{
		super(loggingServiceImpl);
		
		this.loggingServiceImpl = loggingServiceImpl;
		try
		{
			recordMethod = loggingServiceImpl.getClass().getMethod("record", LogEvent.class);
		}
		catch (Exception e)
		{
			throw new RuntimeErrorException(new Error(this.getClass().getCanonicalName()), e.getMessage());
		}
	}

	protected Method lookupMethod(String endpoint, Object[] args)
	{
		return recordMethod;
	}
	
	@Override
	protected Object process(Method method, Object[] args, Request request) throws Exception
	{
		loggingServiceImpl.record((LogEvent)args[0]);

		return null;
	}
}

package org.piangles.backbone.services.logging;

import java.lang.reflect.Method;

import org.piangles.core.services.Request;
import org.piangles.core.services.remoting.AbstractService;

public class LoggingControllerServiceImpl extends AbstractService
{
	private LoggingServiceImpl loggingService = null;

	public LoggingControllerServiceImpl(LoggingServiceImpl loggingService)
	{
		super(loggingService);
		this.loggingService = loggingService;
	}

	@Override
	protected Object process(Method method, Object[] args, Request request) throws Exception
	{
		loggingService.record((LogEvent)args[0]);

		return null;
	}
}

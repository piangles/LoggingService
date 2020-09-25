package org.piangles.backbone.services.logging;

import org.piangles.backbone.services.logging.LogEvent;
import org.piangles.core.services.Request;
import org.piangles.core.services.Response;
import org.piangles.core.services.Service;

public class LoggingControllerServiceImpl implements Service
{
	private LoggingServiceImpl loggingService = null;

	public LoggingControllerServiceImpl(LoggingServiceImpl LoggingService)
	{
		this.loggingService = LoggingService;
	}

	@Override
	public Response process(Request request)
	{
		Object[] args = request.getParameters();
		loggingService.record((LogEvent)args[0]);

		return null;
	}
}

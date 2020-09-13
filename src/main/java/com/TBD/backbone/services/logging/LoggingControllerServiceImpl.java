package com.TBD.backbone.services.logging;

import com.TBD.core.services.Request;
import com.TBD.core.services.Response;
import com.TBD.core.services.Service;

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

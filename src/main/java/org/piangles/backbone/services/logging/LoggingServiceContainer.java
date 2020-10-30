package org.piangles.backbone.services.logging;

import org.piangles.core.email.EmailSupport;
import org.piangles.core.services.Service;
import org.piangles.core.services.remoting.AbstractContainer;
import org.piangles.core.services.remoting.ContainerException;

public class LoggingServiceContainer extends AbstractContainer
{
	public static void main(String[] args)
	{
		LoggingServiceContainer container = new LoggingServiceContainer();
		try
		{
			container.performSteps();
		}
		catch (ContainerException e)
		{
			EmailSupport.notify(e, e.getMessage());
			System.exit(-1);
		}
	}

	public LoggingServiceContainer()
	{
		super(LoggingService.NAME);
	}
	
	@Override
	protected Object createServiceImpl() throws ContainerException
	{
		Object service = null;
		try
		{
			service = new LoggingServiceImpl();
		}
		catch (Exception e)
		{
			throw new ContainerException(e);
		}
		return service;
	}

	@Override
	protected Service createControllerServiceDelegate()
	{
		return new LoggingControllerServiceImpl(getServiceImpl());
	}
}

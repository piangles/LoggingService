package org.piangles.backbone.services.logging;

import com.TBD.core.email.EmailSupport;
import com.TBD.core.services.Service;
import com.TBD.core.services.remoting.AbstractContainer;
import com.TBD.core.services.remoting.ContainerException;

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
		super("LoggingService");
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

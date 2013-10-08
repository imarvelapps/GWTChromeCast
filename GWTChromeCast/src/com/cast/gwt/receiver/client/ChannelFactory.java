package com.cast.gwt.receiver.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Base class of cast channel factory. The subclass must override
 * getDebugString(), and dispatch {@code cast.receiver.ChannelFactoryEvent}
 * event whose 'type' is cast.receiver.ChannelFactory.CHANNEL_CREATED and
 * 'channel' is the new channel created (or call onChannelCreated(channel)).
 * 
 * @author Akash-Gupta
 * 
 */
public class ChannelFactory extends JavaScriptObject
{
	protected ChannelFactory()
	{
		super();
	}

}

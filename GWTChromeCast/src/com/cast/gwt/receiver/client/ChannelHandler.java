/**
 * 
 */
package com.cast.gwt.receiver.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * @author Akash-Gupta
 * 
 */
public class ChannelHandler extends JavaScriptObject
{
	protected ChannelHandler()
	{
		super();
	}

	public static ChannelHandler create(String debugString)
	{
		return ChannelHandlerImpl.create(debugString);
	}


	public final void addEventListener(String event,
			EventHandler eventHandler)
	{
		ChannelHandlerImpl.addEventListener(this, event, eventHandler);
	}

	public final void addChannelFactory(ChannelFactory createChannelFactory)
	{
		ChannelHandlerImpl.addChannelFactory(this, createChannelFactory);
	}

	public final JsArray<Channel> getChannels()
	{
		return ChannelHandlerImpl.getChannels(this);
	}

}

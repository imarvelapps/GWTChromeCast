/**
 * 
 */
package com.cast.gwt.receiver.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * cast.receiver.ChannelHandler(debugString) Cast channel handler to manage
 * channels and handle events targeted to the channels. There are 2 ways to
 * handle channel events: if the caller uses google closure lib, inherit this
 * class and override onMessage(event), and optionally onOpen(event),
 * onClosed(event), and onError(event). if the caller doesn't use google closure
 * lib, listen to {@code cast.receiver.Channel.EventType} events. The target of
 * each event, i.e. event.target is channel where that event happened.
 * Parameters: {string} debugString String used in logging.
 * 
 * @author Akash-Gupta
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

	public final void addEventListener(String event, EventHandler eventHandler)
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

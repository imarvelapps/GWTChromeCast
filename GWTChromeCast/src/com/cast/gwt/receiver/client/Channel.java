package com.cast.gwt.receiver.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

/**
 * Base class of cast channel. It sends and receives cast messages. The subclass
 * must override open(), close(), isOpen(), send(message), and getDebugString(),
 * and dispatch cast.receiver.Channel.EventType.OPEN, CLOSED, ERROR, and MESSAGE
 * events (or call onOpen(), onClosed(), onError(), and onMessage(message)
 * respectively) whenever corresponding underlying events happen. Typically, it
 * is created by Channel
 */
public class Channel extends JavaScriptObject
{

	protected Channel()
	{
		super();
	}

	/**
	 * Sends a message via underlying transport.
	 */
	public final void send(JSONObject data)
	{
		ChannelImpl.send(this, data.getJavaScriptObject());
	}

}

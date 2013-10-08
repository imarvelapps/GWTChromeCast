package com.cast.gwt.receiver.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Event;

public class MessageEvent extends Event
{
	/**
	 * Event which contains message object. Parameters: {string} type Event Type.
	 * {Object|string} message Parsed message object, or raw string message.
	 * {goog.events.EventTarget=} opt_target Reference to the object that is the
	 * target of this event.
	 */
	protected MessageEvent()
	{
	}

	public final native JavaScriptObject message()/*-{
		return this.message;
	}-*/;

	public final native String type()/*-{
		return this.type;
	}-*/;

	public final native JavaScriptObject target()/*-{
		return this.target;
	}-*/;
}

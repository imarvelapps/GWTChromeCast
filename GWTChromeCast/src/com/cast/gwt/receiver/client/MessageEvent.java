package com.cast.gwt.receiver.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Event;

public class MessageEvent extends Event
{

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

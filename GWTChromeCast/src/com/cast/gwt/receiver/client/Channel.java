package com.cast.gwt.receiver.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

public class Channel extends JavaScriptObject
{
	protected Channel()
	{
		super();
	}

	public final void send(JSONObject data)
	{
		ChannelImpl.send(this,data.getJavaScriptObject());
	}

}

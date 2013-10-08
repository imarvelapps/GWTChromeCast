package com.cast.gwt.receiver.client;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

public class Receiver extends JavaScriptObject
{

	protected Receiver()
	{
		super();
	}

	public final static Receiver create(String activityName,
			List<String> protocols, JavaScriptObject opt_activityContext,
			int opt_pingIntervalSec)
	{
		JsArrayString jsArrayString = JavaScriptObject.createArray().cast();

		for (String protocol : protocols)
		{
			jsArrayString.push(protocol);

		}
		return ReceiverImpl.create(activityName, jsArrayString,
				opt_activityContext, opt_pingIntervalSec);
	}

	public final ChannelFactory createChannelFactory(String namespace)
	{
		return ReceiverImpl.createChannelFactory(this, namespace);
	}

	public final void start()
	{
		ReceiverImpl.start(this);
	}

}

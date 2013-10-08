package com.cast.gwt.receiver.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

public class ReceiverImpl
{

	public native static Receiver create(String activityName,
			JsArrayString protocols, JavaScriptObject opt_activityContext,
			int opt_pingIntervalSec) /*-{
		return new $wnd.cast.receiver.Receiver(activityName, protocols,
				opt_activityContext, opt_pingIntervalSec);
	}-*/;

	public native static ChannelFactory createChannelFactory(Receiver receiver,
			String namespace)
	/*-{
		return receiver.createChannelFactory(namespace);
	}-*/;

	public native static void start(Receiver receiver)
	/*-{
		return receiver.start();
	}-*/;

}

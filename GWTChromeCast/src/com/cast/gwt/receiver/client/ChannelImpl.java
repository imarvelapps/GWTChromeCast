package com.cast.gwt.receiver.client;

import com.google.gwt.core.client.JavaScriptObject;

public class ChannelImpl
{

	public native static void send(Channel channel, JavaScriptObject javaScriptObject)
	/*-{
		console.log("IN SEND METHOD = " + javaScriptObject + "  " + channel + "  isopen = " + channel.isOpen());
		channel.send(javaScriptObject);
	}-*/;

}

package com.cast.gwt.receiver.client;

import com.google.gwt.core.client.JsArray;

public class ChannelHandlerImpl
{

	public native static ChannelHandler create(String debugString)
	/*-{
		return new $wnd.cast.receiver.ChannelHandler(debugString);
	}-*/;

	public native static void addEventListener(ChannelHandler channelHandler,
			String eventType, EventHandler eventHandler)
	/*-{
		channelHandler
				.addEventListener(
						eventType,
						function(e) {
							eventHandler.@com.cast.gwt.receiver.client.EventHandler::onEvent(Lcom/google/gwt/user/client/Event;)(e);
						});
	}-*/;

	public native static void addChannelFactory(ChannelHandler channelHandler,
			ChannelFactory channelFactory)
	/*-{
		channelHandler.addChannelFactory(channelFactory);
	}-*/;

	public native static JsArray<Channel> getChannels(
			ChannelHandler channelHandler)
	/*-{
			return channelHandler.getChannels();
	}-*/;

}

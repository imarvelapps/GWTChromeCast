package com.cast.gwt.receiver.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

public class RemoteMediaImpl
{

	public native static RemoteMedia create() /*-{
		return new $wnd.cast.receiver.RemoteMedia();
	}-*/;

	public native static String NAMESPACE()
	/*-{
		return $wnd.cast.receiver.RemoteMedia.NAMESPACE;
	}-*/;

	public native static void addChannelFactory(RemoteMedia remoteMedia,
			JavaScriptObject createChannelFactory)
	/*-{
		remoteMedia.addChannelFactory(createChannelFactory);
	}-*/;

	public native static void setMediaElement(RemoteMedia remoteMedia,
			Element element)
	/*-{
		remoteMedia.setMediaElement(element);
	}-*/;

	public native static JavaScriptObject getStatus(RemoteMedia remoteMedia)
	/*-{
		return remoteMedia.getStatus();
}-*/;

}

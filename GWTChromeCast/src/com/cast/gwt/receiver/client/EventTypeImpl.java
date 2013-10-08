package com.cast.gwt.receiver.client;

public class EventTypeImpl
{

	public native static String OPEN() /*-{
		return $wnd.cast.receiver.Channel.EventType.OPEN;
	}-*/;
	
	public native static String CLOSED() /*-{
	return $wnd.cast.receiver.Channel.EventType.CLOSED;
}-*/;
	
	public native static String ERROR() /*-{
	return $wnd.cast.receiver.Channel.EventType.ERROR;
}-*/;
	
	public native static String MESSAGE() /*-{
	return $wnd.cast.receiver.Channel.EventType.MESSAGE;
}-*/;
	
}

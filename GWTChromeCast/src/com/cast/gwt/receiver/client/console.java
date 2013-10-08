package com.cast.gwt.receiver.client;

public class console
{
	public native static void log(String log)
	/*-{
		console.log(log);
	}-*/;

	public native static void info(String info)
	/*-{
		console.info(info);
	}-*/;
}

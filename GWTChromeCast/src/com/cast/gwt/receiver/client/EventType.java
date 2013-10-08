package com.cast.gwt.receiver.client;

import com.google.gwt.user.client.Event;

public class EventType extends Event
{

	protected EventType()
	{
		super();
	}
	
	public static String OPEN()
	{
		return EventTypeImpl.OPEN();
	}

	public static String CLOSED()
	{
		return EventTypeImpl.CLOSED();
	}

	public static String ERROR()
	{
		return EventTypeImpl.ERROR();
	}

	public static String MESSAGE()
	{
		return EventTypeImpl.MESSAGE();
	}
}

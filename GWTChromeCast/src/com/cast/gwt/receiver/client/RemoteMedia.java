package com.cast.gwt.receiver.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

public class RemoteMedia extends JavaScriptObject
{

	protected RemoteMedia()
	{
		super();
	}

	public final static String NAMESPACE()
	{
		return RemoteMediaImpl.NAMESPACE();
	}

	public final static RemoteMedia create()
	{
		return RemoteMediaImpl.create();
	}

	public final void addChannelFactory(
			JavaScriptObject createChannelFactory)
	{
		RemoteMediaImpl.addChannelFactory(this, createChannelFactory);
	}

	public final void setMediaElement(Element element)
	{
		RemoteMediaImpl.setMediaElement(this, element);
		
	}

	public final JavaScriptObject getStatus()
	{
		return RemoteMediaImpl.getStatus(this);

	}

}

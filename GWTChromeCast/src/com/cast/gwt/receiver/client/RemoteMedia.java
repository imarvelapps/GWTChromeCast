package com.cast.gwt.receiver.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

/**
 * cast.receiver.RemoteMedia() Default implementation of RAMP protocol handler.
 * It listens to {@code cast.receiver.Channel.EventType} to handle messages of
 * 'ramp' namespace on channels created by channel factories added by
 * addChannelFactory(). Please see a typical usage with cast.receiver.Receiver.
 * Without receiver, it can be used like:
 * 
 * var rampHandler = new cast.receiver.RemoteMedia();
 * rampHandler.addChannelFactory(new cast.receiver.ChannelFactory()); // To
 * override default behavior.. rampHandler.onLoad = function() { ... };
 * 
 * The caller may override onOpen(event), onMessage(event), onLoad(message),
 * onInfo(), onPlay(opt_position), onStop(), onVolume(opt_volume, opt_muted),
 * and getStatus(). If {@code cast.receiver.RemoteMedia.onLoad} is overriden,
 * the subclass must call {@code cast.receiver.RemoteMedia.sendSuccessResponse}
 * or {@code cast.receiver.RemoteMedia.sendErrorResponse} to indicate when the
 * load operation is completed.
 * 
 * @author Akash-Gupta
 * 
 */
public class RemoteMedia extends JavaScriptObject
{

	protected RemoteMedia()
	{
		super();
	}

	/*
	 * Protocol namespace for RAMP.
	 */
	public final static String NAMESPACE()
	{
		return RemoteMediaImpl.NAMESPACE();
	}

	public final static RemoteMedia create()
	{
		return RemoteMediaImpl.create();
	}

	public final void addChannelFactory(JavaScriptObject createChannelFactory)
	{
		RemoteMediaImpl.addChannelFactory(this, createChannelFactory);
	}

	/**
	 * Associates the media element to this handler
	 * 
	 * @param element
	 *          element DOM element of media to be handled.
	 */
	public final void setMediaElement(Element element)
	{
		RemoteMediaImpl.setMediaElement(this, element);

	}

	/**
	 * Returns payload representing current media playback status.
	 * 
	 * @return JavaScriptObject status
	 */
	public final JavaScriptObject getStatus()
	{
		return RemoteMediaImpl.getStatus(this);

	}

}

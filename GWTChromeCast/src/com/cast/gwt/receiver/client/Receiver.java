package com.cast.gwt.receiver.client;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

/**
 * cast.receiver.Receiver(activityName, protocols, opt_activityContext,
 * opt_pingIntervalSec) Cast receiver initializes
 * {@code cast.receiver.ConnectionService} internally and provides
 * {@code cast.receiver.ChannelFactoryWithNamespace} for each protocol namespace
 * transmitted through rendezvous service. The caller should implement their own
 * channel handler for the protocol namespace. Cast receiver lib provides a
 * default implementation of RAMP protocol handler,
 * {@code cast.receiver.RemoteMedia}. The typical usage looks like: var receiver
 * = new cast.receiver.Receiver('TestApp', ['ramp', 'other']);
 * 
 * var rampHandler = new cast.receiver.RemoteMedia();
 * rampHandler.addChannelFactory(receiver.createChannelFactory('ramp'));
 * 
 * var channelHandler = new cast.receiver.ChannelHandler('other');
 * channelHandler.addChannelFactory(receiver.createChannelFactory('other'));
 * 
 * receiver.start();
 * 
 * @param String
 *          activityName Cast activity name.
 * @param List
 *          <String> protocols Array of protocol namespaces supported by cast
 *          activity being registered.
 * @param JavaScriptObject
 *          opt_activityContext Cast activity context used in connection
 *          service.
 * @param int opt_pingIntervalSec Heartbeat ping interval in seconds, default to
 *        5.
 * 
 * @author Akash-Gupta
 * 
 */
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

package com.cast.gwt.client;

import java.util.ArrayList;

import com.cast.gwt.receiver.client.JavaScriptObjectHelper;
import com.cast.gwt.receiver.client.Receiver;
import com.cast.gwt.receiver.client.RemoteMedia;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.media.client.Video;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTChromeCast implements EntryPoint
{
	Video video;
	Receiver receiver;
	RemoteMedia remoteMedia;
	SimplePanel status;

	@Override
	public void onModuleLoad()
	{
		video = Video.createIfSupported();
		video.addStyleName("vidEl");
		RootPanel.get().add(video);

		Image img = new Image("../images/loading.gif");
		status = new SimplePanel(img);
		status.getElement().setId("status");
		status.addStyleName("loading");
		RootPanel.get().add(status);

		RootPanel.get().add(new HTML("TEST"));

		String appId = "ce2ad55e-2181-44bb-b5e7-9e65e0510d86";
		ArrayList<String> protocol = new ArrayList<String>(1);
		protocol.add(RemoteMedia.NAMESPACE());

		receiver = Receiver.create(appId, protocol,
				JavaScriptObject.createObject(), 5);
		remoteMedia = RemoteMedia.create();

		remoteMedia.addChannelFactory(receiver.createChannelFactory(RemoteMedia
				.NAMESPACE()));

		receiver.start();
		remoteMedia.setMediaElement(video.getElement());

		new Timer()
		{

			@Override
			public void run()
			{

				JavaScriptObject remoteMediaStatus = remoteMedia.getStatus();
				int st = JavaScriptObjectHelper.getAttributeAsInt(remoteMediaStatus,
						"state");
				int currentTime = 0;

				try
				{
					currentTime = JavaScriptObjectHelper.getAttributeAsInt(
							remoteMediaStatus, "current_time");
				}
				catch (Exception E)
				{

				}

				if (st == 0 || currentTime == 0)
				{
					status.getElement().getStyle().setDisplay(Display.BLOCK);
				}
				else
				{
					if (st == 1 && currentTime > 0)
					{
						status.getElement().setInnerHTML("Paused...");
						status.getElement().getStyle().setDisplay(Display.BLOCK);
					}
					else
					{
						status.getElement().setInnerHTML(currentTime + "");
						status.getElement().getStyle().setDisplay(Display.NONE);
						video.getElement().getStyle().setDisplay(Display.BLOCK);
					}
				}

			}
		}.scheduleRepeating(1000);

	}

	private final native JavaScriptObject getJSObjectForString(String s)
	/*-{
		var string = s;
		return string;
	}-*/;

}

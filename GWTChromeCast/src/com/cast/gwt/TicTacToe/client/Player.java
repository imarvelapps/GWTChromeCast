package com.cast.gwt.TicTacToe.client;

import com.cast.gwt.receiver.client.Channel;
import com.google.gwt.core.client.JavaScriptObject;

public class Player extends JavaScriptObject
{
	protected Player()
	{
		// TODO Auto-generated constructor stub
	}

	public final native void setChannel(Channel channel)
	/*-{
		this.channel = channel;
	}-*/;

	public final native Channel getChannel()
	/*-{
		return this.channel;
	}-*/;

	public final native String getPlayer()
	/*-{
		return this.player;
	}-*/;

	public final native void setPlayer(String player)
	/*-{
		this.player = player;
	}-*/;

	public final native void setName(String name)
	/*-{
		this.name = name;
	}-*/;

	public final native String getName()
	/*-{
		return this.name;
	}-*/;
}

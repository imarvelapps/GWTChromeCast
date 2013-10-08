package com.cast.gwt.TicTacToe.client;

import java.util.ArrayList;

import com.cast.gwt.receiver.client.Channel;
import com.cast.gwt.receiver.client.Receiver;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class TicTacToeApp implements EntryPoint
{
	Receiver chromecastApp;
	SimplePanel status;

	public static String PROTOCOL = "com.google.chromecast.demo.tictactoe";

	@Override
	public void onModuleLoad()
	{
		test();
		
		Canvas canvas = Canvas.createIfSupported();
		canvas.getCanvasElement().setId("board");
		canvas.addStyleName("canvas");

		RootPanel.get().add(canvas);

		Context2d context = canvas.getContext2d();
		context.getCanvas().setWidth(Window.getClientWidth());
		context.getCanvas().setHeight(Window.getClientHeight());
		Board mBoard = new Board(context);

		String appId = "ce2ad55e-2181-44bb-b5e7-9e65e0510d86";
		ArrayList<String> protocol = new ArrayList<String>(1);
		protocol.add(PROTOCOL);

		chromecastApp = Receiver.create(appId, protocol,
				JavaScriptObject.createObject(), 5);

		mBoard.clear();
		mBoard.drawGrid();

		TicTacToe gameEngine = new TicTacToe(mBoard);
		gameEngine.mChannelHandler.addChannelFactory(chromecastApp
				.createChannelFactory(PROTOCOL));
		chromecastApp.start();
	}

	private void test()
	{
		JSONObject data2 = new JSONObject();
		data2.put("event", new JSONString("joined"));
		data2.put("message", new JSONString("test"));
		test1(data2.toString());
	}
	
	public native static void test1(String data2)
	/*-{
		alert(data2);
	}-*/;
	

}

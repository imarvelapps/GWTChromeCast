package com.cast.gwt.TicTacToe.client;

import java.util.ArrayList;

import com.cast.gwt.receiver.client.Receiver;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

public class TicTacToeApp implements EntryPoint
{
	public static String PROTOCOL = "com.google.chromecast.demo.tictactoe";

	/**
	 * Initializes the receiver application and game objects, and starts the
	 * receiver.
	 */
	@Override
	public void onModuleLoad()
	{
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

		Receiver chromecastApp = Receiver.create(appId, protocol,
				JavaScriptObject.createObject(), 5);

		mBoard.clear();
		mBoard.drawGrid();

		TicTacToe gameEngine = new TicTacToe(mBoard);
		gameEngine.mChannelHandler.addChannelFactory(chromecastApp
				.createChannelFactory(PROTOCOL));
		chromecastApp.start();
	}
}

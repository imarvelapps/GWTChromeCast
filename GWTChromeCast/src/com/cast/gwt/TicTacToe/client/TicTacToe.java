package com.cast.gwt.TicTacToe.client;

import com.cast.gwt.receiver.client.Channel;
import com.cast.gwt.receiver.client.ChannelHandler;
import com.cast.gwt.receiver.client.EventHandler;
import com.cast.gwt.receiver.client.EventType;
import com.cast.gwt.receiver.client.MessageEvent;
import com.cast.gwt.receiver.client.console;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;

/**
 * Tic Tac Toe Gameplay with Chromecast This file exposes cast.TicTacToe as an
 * object containing a ChannelHandler and capable of receiving and sending
 * messages to the sender application.
 */
public class TicTacToe
{

	private static enum PLAYER
	{
		O, X;
	};

	public Board mBoard;
	private Player mPlayer1;
	private Player mPlayer2;
	private String mCurrentPlayer;
	public ChannelHandler mChannelHandler;

	/**
	 * Creates a TicTacToe object with an optional board and attaches a
	 * cast.receiver.ChannelHandler, which receives messages from the channel
	 * between the sender and receiver.
	 * 
	 * @param Board
	 *          opt_board an optional game board.
	 */
	public TicTacToe(Board mBoard)
	{
		this.mBoard = mBoard;
		this.mPlayer1 = null;
		this.mPlayer2 = null;

		this.mChannelHandler = ChannelHandler.create("TicTacToeDebug");

		// Adds event listening functions to TicTacToe.prototype.
		this.mChannelHandler.addEventListener(EventType.MESSAGE(),
				new EventHandler()
				{

					@Override
					public void onEvent(Event event)
					{
						onMessage((MessageEvent) event);
					}
				});

		this.mChannelHandler.addEventListener(EventType.OPEN(), new EventHandler()
		{

			@Override
			public void onEvent(Event event)
			{
				onChannelOpened(event);

			}
		});

		this.mChannelHandler.addEventListener(EventType.CLOSED(),
				new EventHandler()
				{

					@Override
					public void onEvent(Event event)
					{
						onChannelClosed(event);
					}
				});

	}

	/**
	 * Channel opened event; checks number of open channels.
	 * 
	 * @param event
	 *          the channel open event.
	 */
	protected void onChannelOpened(Event event)
	{
		console.log("onChannelOpened. Total number of channels: "
				+ (this.mChannelHandler.getChannels()).length());

	}

	/**
	 * Channel closed event; if all devices are disconnected, closes the
	 * application.
	 * 
	 * @param event
	 *          event the channel close event.
	 */
	private void onChannelClosed(Event event)
	{
		console.log("onChannelClosed. Total number of channels: "
				+ (mChannelHandler.getChannels()).length());

		if ((this.mChannelHandler.getChannels()).length() == 0)
		{
			Window.open("", "_parent", "");
			closeBrowser();
		}

	}

	public native void closeBrowser()
	/*-{
		$wnd.close();
	}-*/;

	/**
	 * Message received event; determines event message and command, and choose
	 * function to call based on them.
	 * 
	 * @param MessageEvent
	 *          event the event to be processed.
	 */
	public void onMessage(MessageEvent event)
	{
		JSONObject message = new JSONObject(event.message());
		Channel channel = (Channel) event.target();
		console.log("********onMessage******** " + (message));
		console.log("mPlayer1: " + this.mPlayer1);
		console.log("mPlayer2: " + this.mPlayer2);

		if (message.get("command").isString().stringValue()
				.equalsIgnoreCase("join"))
		{
			this.onJoin(channel, message);
		}
		else if (message.get("command").isString().stringValue()
				.equalsIgnoreCase("leave"))
		{
			this.onLeave(channel);
		}
		else if (message.get("command").isString().stringValue()
				.equalsIgnoreCase("move"))
		{
			this.onMove(channel, message);
		}
		else if (message.get("command").isString().stringValue()
				.equalsIgnoreCase("board_layout_request"))
		{
			this.onBoardLayoutRequest(channel);
		}
		else
		{
			console.log("Invalid message command: "
					+ message.get("command").isString().stringValue());
		}
	}

	/**
	 * Request event for the board layout: sends the current layout of pieces on
	 * the board through the channel.
	 * 
	 * @param Channel
	 *          channel the channel the event came from.
	 */
	private void onBoardLayoutRequest(Channel channel)
	{
		console.log("****onBoardLayoutRequest");
		JSONArray array = new JSONArray();
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				array.set(i * 3 + j, new JSONNumber(this.mBoard.mBoard[i][j]));
			}
		}

		JSONObject data = new JSONObject();
		data.put("event", new JSONString("board_layout_response"));
		data.put("board", array);

		channel.send(data);

	}

	/**
	 * Move event: checks whether a valid move was made and updates the board as
	 * necessary.
	 * 
	 * @param Channel
	 *          channel the source of the move, which determines the player.
	 * @param JSONObject
	 *          message contains the row and column of the move.
	 */
	private void onMove(Channel channel, JSONObject message)
	{

		console.log("****onMove: " + message);
		boolean isMoveValid;

		if ((this.mPlayer1 == null) || (this.mPlayer2 == null))
		{
			console.log("Looks like one of the players is not there");
			console.log("mPlayer1: " + this.mPlayer1);
			console.log("mPlayer2: " + this.mPlayer2);
			return;
		}

		if (this.mPlayer1.getChannel() == channel)
		{
			if (this.mPlayer1.getPlayer() == this.mCurrentPlayer)
			{
				if (this.mPlayer1.getPlayer() == PLAYER.X.name())
				{
					isMoveValid = this.mBoard.drawCross(message.get("row").isNumber()
							.doubleValue(), message.get("column").isNumber().doubleValue());
				}
				else
				{
					isMoveValid = this.mBoard.drawNaught(message.get("row").isNumber()
							.doubleValue(), message.get("column").isNumber().doubleValue());
				}
			}
			else
			{
				console.log("Ignoring the move. It\"s not your turn.");
				this.sendError(channel, "It\"s not your turn.");
				return;
			}
		}
		else if (this.mPlayer2.getChannel() == channel)
		{
			if (this.mPlayer2.getPlayer() == this.mCurrentPlayer)
			{
				if (this.mPlayer2.getPlayer() == PLAYER.X.name())
				{
					isMoveValid = this.mBoard.drawCross(message.get("row").isNumber()
							.doubleValue(), message.get("column").isNumber().doubleValue());
				}
				else
				{
					isMoveValid = this.mBoard.drawNaught(message.get("row").isNumber()
							.doubleValue(), message.get("column").isNumber().doubleValue());
				}
			}
			else
			{
				console.log("Ignoring the move. It\"s not your turn.");
				this.sendError(channel, "It\"s not your turn.");
				return;
			}
		}
		else
		{
			console.log("Ignorning message. Someone other than the current"
					+ "players sent a move.");
			this.sendError(channel, "You are not playing the game");
			return;
		}

		if (isMoveValid == false)
		{
			this.sendError(channel, "Your last move was invalid");
			return;
		}

		boolean isGameOver = this.mBoard.isGameOver();

		JSONObject data = new JSONObject();
		data.put("event", new JSONString("moved"));
		data.put("player", new JSONString(this.mCurrentPlayer));
		data.put("row", message.get("row").isNumber());
		data.put("column", message.get("column").isNumber());
		data.put("game_over", JSONBoolean.getInstance(isGameOver));

		this.broadcast(data);

		console.log("isGameOver: " + isGameOver);
		console.log("winningLoc: " + this.mBoard.getWinningLocation());

		// When the game should end
		if (isGameOver == true)
		{
			this.broadcastEndGame(this.mBoard.getGameResult(),
					this.mBoard.getWinningLocation());
		}
		// Switch current player
		this.mCurrentPlayer = (this.mCurrentPlayer
				.equalsIgnoreCase(PLAYER.X.name()) ? TicTacToe.PLAYER.O.name()
				: TicTacToe.PLAYER.X.name());

	}

	private void broadcastEndGame(String gameResult, int winningLocation)
	{
		console.log("****endGame");
		this.mPlayer1 = null;
		this.mPlayer2 = null;

		JSONObject data = new JSONObject();
		data.put("event", new JSONString("endgame"));
		data.put("end_state", new JSONString(gameResult));
		data.put("winning_location", new JSONNumber(winningLocation));

		this.broadcast(data);

	}

	private void sendError(Channel channel, String errorMessage)
	{
		JSONObject data = new JSONObject();
		data.put("event", new JSONString("board_layout_response"));
		data.put("message", new JSONString(errorMessage));

		channel.send(data);
	}

	/**
	 * Player leave event: determines which player left and unregisters that
	 * player, and ends the game if all players are absent.
	 * 
	 * @param Channel
	 *          channel the channel of the leaving player.
	 */
	private void onLeave(Channel channel)
	{
		console.log("****OnLeave");

		if (this.mPlayer1 != null && this.mPlayer1.getChannel() == channel)
		{
			this.mPlayer1 = null;
		}
		else if (this.mPlayer2 != null && this.mPlayer2.getChannel() == channel)
		{
			this.mPlayer2 = null;
		}
		else
		{
			console.log("Neither player left the game");
			return;
		}
		console.log("mBoard.GameResult: " + this.mBoard.getGameResult());
		if (this.mBoard.getGameResult() == null)
		{
			this.mBoard.setGameAbandoned();
			this.broadcastEndGame(this.mBoard.getGameResult(), 0);
		}

	}

	/**
	 * Player joined event: registers a new player who joined the game, or
	 * prevents player from joining if invalid.
	 * 
	 * @param Channel
	 *          channel the channel the message came from.
	 * @param JSONObject
	 *          message the name of the player who just joined.
	 */
	private void onJoin(Channel channel, JSONObject message)
	{
		console.log("****onJoin: " + message);

		if ((this.mPlayer1 != null) && (this.mPlayer1.getChannel() == channel))
		{
			this.sendError(channel, "You are already " + this.mPlayer1.getPlayer()
					+ " You aren\"t allowed to play against yourself.");
			return;
		}
		if ((this.mPlayer2 != null) && (this.mPlayer2.getChannel() == channel))
		{
			this.sendError(channel, "You are already " + this.mPlayer2.getPlayer()
					+ " You aren\"t allowed to play against yourself.");
			return;
		}

		if (this.mPlayer1 == null)
		{
			this.mPlayer1 = JavaScriptObject.createObject().cast();
			this.mPlayer1.setName(message.get("name").isString().stringValue());
			this.mPlayer1.setChannel(channel);
		}
		else if (this.mPlayer2 == null)
		{
			this.mPlayer2 = JavaScriptObject.createObject().cast();
			this.mPlayer2.setName(message.get("name").isString().stringValue());
			this.mPlayer2.setChannel(channel);
		}
		else
		{
			console.log("Unable to join a full game.");
			this.sendError(channel, "Game is full.");
			return;
		}

		console.log("mPlayer1: " + this.mPlayer1);
		console.log("mPlayer2: " + this.mPlayer2);

		if (this.mPlayer1 != null && this.mPlayer2 != null)
		{
			this.mBoard.reset();
			this.startGame_();

		}
	}

	private void startGame_()
	{
		console.log("****startGame");
		int firstPlayer = (int) Math.floor((Math.random() * 10) % 2);
		this.mPlayer1.setPlayer((firstPlayer == 0) ? TicTacToe.PLAYER.X.name()
				: TicTacToe.PLAYER.O.name());
		this.mPlayer2.setPlayer((firstPlayer == 0) ? TicTacToe.PLAYER.O.name()
				: TicTacToe.PLAYER.X.name());
		this.mCurrentPlayer = PLAYER.X.name();

		JSONObject data1 = new JSONObject();
		data1.put("event", new JSONString("joined"));
		data1.put("player", new JSONString(this.mPlayer1.getPlayer()));
		data1.put("opponent", new JSONString(this.mPlayer2.getName()));

		this.mPlayer1.getChannel().send(data1);

		JSONObject data2 = new JSONObject();
		data2.put("event", new JSONString("joined"));
		data2.put("player", new JSONString(this.mPlayer2.getPlayer()));
		data2.put("opponent", new JSONString(this.mPlayer1.getName()));

		this.mPlayer2.getChannel().send(data2);
	}

	/**
	 * Broadcasts a message to all of this object's known channels.
	 * 
	 * @param JSONObject
	 *          message the message to broadcast.
	 */
	private void broadcast(JSONObject message)
	{
		JsArray<Channel> arr = this.mChannelHandler.getChannels();
		for (int i = 0; i < arr.length(); i++)
		{
			arr.get(i).send(message);
		}

	}

}

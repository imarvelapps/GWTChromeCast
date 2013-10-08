GWTChromeCast
=============

GWT wrapper library for ChromeCast Receiver with TicTacToe example.


The project has two main modules,

1) Receiver, which is the GWT wrapper library around the Chrome cast receiver API ( https://developers.google.com/cast/reference/receiver/jsdoc/)

2) TicTacToe, a GWT version of sample TicTacToe app hosted at https://github.com/googlecast/cast-android-tictactoe


If you want to build you own chromecast custom app, read this https://developers.google.com/cast/devprev

You will need to whitelist an application(Url) form google and you will receive an AppID, which you can use to create your own receiver.


B. Casting to the Receiver

At line 58 in GameActivity.java, the code specifies the app name to "TicTacToe".
This is a whitelisted app name at which the original TicTacToe receiver is located, so
you can simply build and run the app without worrying about whitelisting your
own receiver or if you want to to use GWT receiver change the AppId to "ce2ad55e-2181-44bb-b5e7-9e65e0510d86"
which is whitlisted id for hosted GWT app.

OPTIONAL: Setting up Your Own Receiver

Instead of using the TicTacToe receiver, you can upload your own receiver copy
so that you can cast to it or optionally modify its behavior. You must already
have a whitelisted receiver URL (your_domain.com/your_receiver_name.html)
which you can replace with the TicTacToe App's receiver.

1. In the com.cast.gwt.TicTacToe.client.TicTacToeApp.java at line 41 change the appId to your 
own AppID, and upload all four files to your whitelisted directory.
2. At line 58 of GameActivity, replace "TicTacToe" with your own AppID.
3. Run the TicTacToe app; you should now be able to connect to your own version
of the TicTacToe receiver.

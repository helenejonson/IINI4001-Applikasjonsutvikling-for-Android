package com.example.myapplication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private final static String TAG = "ServerThread";
    private final static int PORT = 12345;

    public void run() {
        ServerSocket ss 	= null;
        Socket s 			= null;
        PrintWriter out 	= null;
        BufferedReader in 	= null;

        try{
            ss = new ServerSocket(PORT);
            Log.i(TAG, "client connected...");
            while (true){
                s = ss.accept();
                new ClientHandlerThread(s).start();
                Log.i(TAG, "Handler");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG,"Cant start socket");
        }finally{//close sockets!!
            try{
                out.close();
                in.close();
                s.close();
                ss.close();
            }catch(Exception e){}
        }
    }
}
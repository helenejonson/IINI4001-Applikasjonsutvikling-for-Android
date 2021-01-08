package com.example.myapplication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandlerThread extends Thread {
    private final static String TAG = "Handler";
    private Socket s;

    public ClientHandlerThread(Socket s){
        this.s = s;
    }

    public void run(){
        Socket s 			= this.s;
        PrintWriter out		= null;
        BufferedReader in 	= null;
        try {
            Log.i(TAG, "Client connected");
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String res = in.readLine();
            Log.i(TAG,"Message from client: " + res);
            String[] data = res.split(":");
            int result = Integer.parseInt(data[0]) + Integer.parseInt(data[1]);
            out.println(result);

        } catch (IOException e){

        }

    }
}
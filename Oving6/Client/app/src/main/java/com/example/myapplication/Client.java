package com.example.myapplication;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;


public class Client extends Thread {
    private final static String TAG = "Client";
    private final static String IP = "127.0.0.1";
    private final static int PORT = 12345;
    private String data;
    private MainActivity a;

    public Client(Activity a,  String data) {
        this.data = data;
        this.a = (MainActivity) a;
    }

    public void run() {
        Socket s 			= null;
        PrintWriter out		= null;
        BufferedReader in 	= null;

        try {
            s = new Socket(IP, PORT);
            Log.v(TAG, "C: Connected to server" + s.toString());
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println(data);
            String svar = in.readLine();
            Log.i(TAG, svar);
            a.results(svar);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{//close socket!!
            try{
                out.close();
                in.close();
                s.close();
            }catch(IOException e){
                Log.i(TAG,"Kjører ikke");
            }
        }
    }
}
package com.mobisem.engineering.Connection;

import java.io.PrintWriter;
import java.net.Socket;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mobisem.engineering.MeditationApplication;
import com.mobisem.engineering.Models.MeditationModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class TcpClient{
    public static final String TAG = TcpClient.class.getSimpleName();
    public static final String SERVER_IP = "10.2.9.140";
    public static final int SERVER_PORT = 9999;
    private String mServerMessage;
    private OnMessageReceived mMessageListener = null;
    private boolean mRun = false;
    MeditationModel meditationModel=new MeditationModel();
    static int countBase=0;

    private PrintWriter mBufferOut;
    private BufferedReader mBufferIn;
    public TcpClient(OnMessageReceived listener) {
        mMessageListener = listener;
    }
    public void sendMessage(final String message) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mBufferOut != null) {
                    Log.d(TAG, "Sending:" +message);
                    mBufferOut.println(message);
                    mBufferOut.flush();
                }

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }


    public void stopClient() {

        mRun = false;

        if (mBufferOut != null) {
            mBufferOut.flush();
            mBufferOut.close();
        }

        mMessageListener = null;
        mBufferIn = null;
        mBufferOut = null;
        mServerMessage = null;

    }

    public boolean run(int meditationID) {


        mRun = true;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Log.d("TCP Client", "C: Connecting...");
            Socket socket = new Socket(serverAddr, SERVER_PORT);


            
            try {
                mBufferOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                mBufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                int charsRead = 0;
                char[] buffer = new char[7];
                while (mRun)
                {

                    sendMessage("1");

                    charsRead = mBufferIn.read(buffer);
                    mServerMessage = new String(buffer).substring(0, charsRead);
                    if (mServerMessage != null && mMessageListener != null) {
                        Log.i("Message from Server", mServerMessage.toString());
                               mMessageListener.messageReceived(mServerMessage);

                        meditationModel.setID(meditationID);
                        double meanValue = Double.valueOf(mServerMessage);
                        meditationModel.setResult(meanValue);
                            MeditationApplication.getInstance().getMeditationModelArrayList().add(meditationModel);

                    return true;

                    }
                }

            } catch (Exception e) {




                return false;
            } finally {
                //socket.close()

            }

        } catch (Exception e) {
            Log.e("TCP", "C: Error", e);
            return false;
        }
        return false;

    }


    public interface OnMessageReceived {
        public void messageReceived(String message);


    }

}






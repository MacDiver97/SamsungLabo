package com.example.diver.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Handler.Callback{

    protected Handler theHandler;
    protected MyHandlerThread theMyHandlerThread;
    protected TextView theTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Handler(Handler.Callback callback)
        theHandler = new Handler(this);
        theTextView = findViewById(R.id.textView);
    }

    public boolean handleMessage(Message aMessage){
        switch(aMessage.what){
            case MyHandlerThread.MSG_RESPONSE_ID:
                theTextView.setText((String) aMessage.obj);
                break;
            default:
                return false;
        }
        return true;
    }

    protected void onResume(){
        super.onResume();
        theMyHandlerThread = new MyHandlerThread(theHandler);
        theMyHandlerThread.start();
    }

    protected void onPause(){
        super.onPause();
        theMyHandlerThread.quit();
        theMyHandlerThread = null;
    }

    public void onClick_Calculate(View aView){
        theMyHandlerThread.calculate();
    }


}

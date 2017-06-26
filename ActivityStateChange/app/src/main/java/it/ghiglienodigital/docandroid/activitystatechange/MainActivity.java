package it.ghiglienodigital.docandroid.activitystatechange;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    private final String TAG = "StateChange";
    private final String SEC_KEY = "sec";
    private final String START_KEY = "start";
    private final String TIME_TEXT = "Tempo trascorso: ";

    private long startTime = System.currentTimeMillis();

    private TextView lblTime;

    final Runnable updateTime = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG,"updateTime()");
            long current = System.currentTimeMillis();
            long delta = current - startTime;
            float sec = delta/1000f;
            Bundle data = new Bundle();
            data.putFloat(SEC_KEY,sec);
            Message msg = new Message();
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            float sec = data.getFloat(SEC_KEY);
            Log.d(TAG,"handleMessage().... " + sec );
            lblTime.setText(TIME_TEXT + sec);
        }
    };

    ScheduledExecutorService executorService;
    ScheduledFuture excutorServiceHandler;

    //Costruttore
    public MainActivity(){
        Log.d(TAG,"Costruttore....");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            Log.d(TAG,"savedInstance not null...");
            Log.d(TAG,"" + savedInstanceState.getLong(START_KEY));
            startTime = savedInstanceState.getLong(START_KEY);
        }
        Log.d(TAG,"onCreate()....");
        lblTime = (TextView) findViewById(R.id.lblTime);
        lblTime.setText(TIME_TEXT);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"OnStart()....");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"OnResume()....");
        executorService = Executors.newScheduledThreadPool(1);
        excutorServiceHandler = executorService.scheduleAtFixedRate
                (updateTime,1000,1000, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"OnPause()....");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"OnStop()....");
        excutorServiceHandler.cancel(true);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"OnRestart()....");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"OnDestroy()....");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(START_KEY,startTime);
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState()....");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG,"onRestoreInstanceState()....");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.d(TAG,"onRestoreInstanceState altro()....");
    }
}

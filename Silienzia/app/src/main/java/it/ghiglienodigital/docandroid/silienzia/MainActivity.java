package it.ghiglienodigital.docandroid.silienzia;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Chiama metodo onCreate della classe Activity
        super.onCreate(savedInstanceState);
        //Carico il layout dal file activity_main
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        Button btnSilenzia = (Button) findViewById(R.id.btnSilenzia);
        btnSilenzia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManagement.invertAudio(audioManager);
                updateUI();
                Log.d("app_silenzia","onclick...");
            }
        });

    }

    private void updateUI() {
        Button btnSilenzia = (Button) findViewById(R.id.btnSilenzia);
        btnSilenzia.setText(
                AudioManagement.isSilenzioso(audioManager) ?
                        "Suona" :
                        "Muto"
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}

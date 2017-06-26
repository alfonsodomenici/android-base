package it.ghiglienodigital.docandroid.silenzia;

import android.media.AudioManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.content);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuonoHelper.onInvertiModalitaSuono(audioManager);
                updateUI();
                Log.d("Silenziatore", "Test onClick()....");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        int phoneImage = SuonoHelper.isSilenzioso(audioManager)
                ? R.drawable.icon_ringer_off
                : R.drawable.icon_ringer_on;
        imageView.setImageResource(phoneImage);
    }
}

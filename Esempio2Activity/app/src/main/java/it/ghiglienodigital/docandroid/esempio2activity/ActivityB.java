package it.ghiglienodigital.docandroid.esempio2activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityB extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        Intent msg = getIntent();
        CharSequence domanda =
                getIntent().getExtras().getCharSequence("domanda");
        //Bundle extras = getIntent().getExtras();
        //CharSequence domanda = msg.getExtras().getCharSequence("domanda");
        TextView txtDomanda = (TextView) findViewById(R.id.domanda);
        txtDomanda.setText(domanda);

    }

    public void onInviaRisposta(View v){
        Log.i("app","onInviaRisposta");
        EditText txtRisposta = (EditText) findViewById(R.id.risposta);
        Intent msg = new Intent();
        msg.putExtra("risposta",txtRisposta.getText());
        setResult(RESULT_OK,msg);
        super.finish();
    }
}

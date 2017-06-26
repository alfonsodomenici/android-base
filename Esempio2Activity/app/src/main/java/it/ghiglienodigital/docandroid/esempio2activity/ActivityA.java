package it.ghiglienodigital.docandroid.esempio2activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityA extends Activity {

    private final int req_code = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ottengo riferimento all'oggeto EditText
        final EditText txtDomanda = (EditText) findViewById(R.id.domanda);
        //Ottengo il riferimento all'oggetto Button
        Button cmdInvia = (Button) findViewById(R.id.cmdInvia);
        //final ActivityA _this = this;
        cmdInvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("domande","onclick() ok...");
                Intent msg = new Intent(ActivityA.this,ActivityB.class);
                //msg.putExtra("domanda",txtDomanda.getText());
                //startActivity(msg);
                startActivityForResult(msg,req_code);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView lblRisposta =
                (TextView) findViewById(R.id.risposta);
        if(requestCode==req_code && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            if(extras==null
                    || extras.getCharSequence("risposta")==null
                    || extras.getCharSequence("risposta").length()==0){
                Toast.makeText(this,
                        "Nessuna risposta ricevuta",
                        Toast.LENGTH_LONG).show();
                Log.i("app","risposta vuota");
                lblRisposta.setText("In attesa di risposta");
            }else{
                lblRisposta.setText(extras.getCharSequence("risposta"));
            }



        }
    }
}

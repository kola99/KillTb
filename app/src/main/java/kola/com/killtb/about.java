package kola.com.killtb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView txt=(TextView)findViewById(R.id.te) ;
        txt.setText("        I WOULD THANK GOD FOR THIS VENTURE...\n" +
                "        I WOULD LIKE TO THANK MY PARENTS...\n" +
                "        STUDIES B.TECH. CSE H.B.T.U.\n" +
                "        I LOVES INDIA THE MOST..\n" +
                "        I HATES GADDARS AND PAKISTAN....\n" +
                "        HE IS SEXY...\n" +
                "        HE IS GENTLEMAN..\n" +
                "        I AM JHANTU..");
    }
}

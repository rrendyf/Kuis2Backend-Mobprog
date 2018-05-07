package com.example.rendyfunthagrap.kuis2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button cekku, daftaru, saing, turansfar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cekku = findViewById(R.id.btnCek1);
        daftaru = findViewById(R.id.btnDaftar1);
        saing = findViewById(R.id.btnLogin1);
        turansfar = findViewById(R.id.btnTransfer1);

        cekku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cekIntent = new Intent(getBaseContext(), Cek.class);
                startActivity(cekIntent);
            }
        });
        daftaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftIntent = new Intent(getBaseContext(), Daftar.class);
                startActivity(daftIntent);
            }
        });
        saing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LogIntent = new Intent(getBaseContext(), Sign.class);
                startActivity(LogIntent);
            }
        });
        turansfar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trunkIntent = new Intent(getBaseContext(), Transfer.class);
                startActivity(trunkIntent);
            }
        });
    }
}

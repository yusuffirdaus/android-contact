package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AwalActivity extends AppCompatActivity {

    private EditText eNomor;
    private View vContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);
        eNomor = (EditText) findViewById(R.id.etNomor);


        try {
            String nomor = getIntent().getExtras().getString("nomor");
            eNomor.setText(nomor);

        } catch (Exception e) {

        }

        vContact = (View) findViewById(R.id.vContact);
        vContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent kontak = new Intent(AwalActivity.this, MainActivity2.class);
                startActivity(kontak);
                finish();

            }
        });
    }
}

package com.example.contact;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contact.adapter.ListViewAdapter;
import com.example.contact.adapter.RecyclerViewAdapter;
import com.example.contact.models.contact2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {


//    author yusuf firdaus
//    23/02/2020

    private List<contact2> ItemList;
    private TextView etCari;
    private Button bCari;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerviewid);
        etCari = (EditText) findViewById(R.id.etCari);
        bCari = (Button) findViewById(R.id.bCari);
        ItemList = new ArrayList<>();

        hasPermissions(this);
        String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        int PERMISSION_ALL = 1;
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        loadContacts(null);
//        etCari.addTextChangedListener(new TextWatcher() {
//            public void afterTextChanged(Editable s) {
//                loadContacts(etCari.getText().toString());
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {}
//        });

        bCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCari.getText().toString().isEmpty()) {
                    loadContacts(null);
                } else {
                    loadContacts(etCari.getText().toString());
                }
            }
        });
    }

    public void loadContacts(String namanya) {
        ItemList.clear();
        StringBuilder builder = new StringBuilder();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null,
                null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {
                    Cursor cursor2 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{id}, null);

                    while (cursor2.moveToNext()) {
                        String phoneNumber = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        builder.append(name).append("\n").append(phoneNumber).append("\n\n");
                        if (namanya != null) {
                            if (name.matches("(.*)" + namanya + "(.*)")) {
                                contact2 conta = new contact2(id, name, phoneNumber);
                                ItemList.add(conta);
                            }
                        } else {
                            contact2 conta = new contact2(id, name, phoneNumber);
                            ItemList.add(conta);
                        }


                    }
                    cursor2.close();
                }
            }
//            ListViewAdapter adapter = new ListViewAdapter(ItemList, this);
//            listView.setAdapter(adapter);

            setuprecyclerview(ItemList);
        }
        cursor.close();

    }

    private void setuprecyclerview(List<contact2> lstAnime) {


        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, lstAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(getApplicationContext(),"Access is granted...", Toast.LENGTH_SHORT).show();


                } else {
                    ActivityCompat.finishAffinity(this);
                }
                return;
            }
        }
    }


}

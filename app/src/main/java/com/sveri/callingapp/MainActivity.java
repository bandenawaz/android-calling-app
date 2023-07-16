package com.sveri.callingapp;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etPhone;
    Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPhone = findViewById(R.id.editTextPhone);
        btnCall = findViewById(R.id.buttonCall);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                String mob = etPhone.getText().toString();

                //lets check whether the user has given permission
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(MainActivity.this, "You have to grant permission to make a call",
                            Toast.LENGTH_SHORT).show();
                    requestPermissionToCall();

                }else{
                    if(mob.isEmpty()){
                        Toast.makeText(MainActivity.this, "Mobile number cannot be empty",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        callIntent.setData(Uri.parse("tel: "+mob));
                        startActivity(callIntent);
                    }
                }
            }
        });
    }

    private void requestPermissionToCall(){

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CALL_PHONE},1);
    }

}
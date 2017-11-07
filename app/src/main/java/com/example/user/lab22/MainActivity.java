package com.example.user.lab22;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.user.lab22.R.id.textViewEmail;
import static com.example.user.lab22.R.id.textViewName;

public class MainActivity extends AppCompatActivity {
    public static final String PROFILE_NAME = "com.example.user.lab22.name";
    public static final String PROFILE_EMAIL = "com.example.user.lab22.email";
    private static final int REQUEST_PROFILE_UPDATE = 1;
    private TextView textViewName, textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewName = (TextView)findViewById(R.id.textViewName);
        textViewEmail = (TextView)findViewById(R.id.textViewEmail);
    }

    public void updateProfile(View view){
        //Explicit intent
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivityForResult(intent, REQUEST_PROFILE_UPDATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //RequestCode = a unique code to identify an intent
        //ResultCode = result of an intent call; either OK or CANCEL
        //data = the actual data returned by the intent
        if(requestCode == REQUEST_PROFILE_UPDATE && resultCode == RESULT_OK){
            String name, email;
            name = data.getStringExtra(PROFILE_NAME);
            email = data.getStringExtra(PROFILE_EMAIL);
            textViewName.setText(getString(R.string.name) + ": " + name);
            textViewEmail.setText(getString(R.string.email) + ": " + email);
        }
    }

    public void visitBAIT(View v){
        String uri = "http://bait2073.000webhostapp.com/welcome.html";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    public void showDial(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + "0123456789"));
        startActivity(intent);
    }

    public void sendMail(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: " + "seekt@tarc.edu.my"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void showMain(View v){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        startActivity(intent);
    }
}

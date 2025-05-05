package com.example.epermit;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        findViewById(R.id.btnTransfer).setOnClickListener(v ->
                startActivity(new Intent(this, TransferFormActivity.class)));
        // Implement borrow/pending/approved similarly
    }
}

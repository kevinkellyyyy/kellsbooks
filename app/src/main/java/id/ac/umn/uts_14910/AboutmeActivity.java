package id.ac.umn.uts_14910;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

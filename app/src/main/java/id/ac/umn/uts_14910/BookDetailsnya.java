package id.ac.umn.uts_14910;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

class BookDetailsnya extends AppCompatActivity {
    private TextView ASIN, GROUP1, FORMAT, TITLE, AUTHOR, PUBLISHER;
    Button addToFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailbook);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ASIN = findViewById(R.id.textViewASIN);
        GROUP1 = findViewById(R.id.textViewGROUP1);
        FORMAT = findViewById(R.id.textViewFORMAT);
        TITLE = findViewById(R.id.textViewTITLE);
        AUTHOR = findViewById(R.id.textViewAUTHOR);
        PUBLISHER = findViewById(R.id.textViewPUBLISHER);

        Intent intent = getIntent();
        final String ASIND = intent.getExtras().getString("keyASIN");
        final String GROUP1D = intent.getExtras().getString("keyGROUP1");
        final String FORMATD = intent.getExtras().getString("keyFORMAT");
        final String TITLED = intent.getExtras().getString("keyTITLE");
        final String AUTHORD = intent.getExtras().getString("keyAUTHOR");
        final String PUBLISHERD = intent.getExtras().getString("keyPUBLISHER");

        ASIN.setText(ASIND);
        GROUP1.setText(GROUP1D);
        FORMAT.setText(FORMATD);
        TITLE.setText(TITLED);
        AUTHOR.setText(AUTHORD);
        PUBLISHER.setText(PUBLISHERD);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.favobut);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookDbHelper bookDbHelper = BookDbHelper.getsInstance(BookDetailsnya.this);
                bookDbHelper.addFavorites(ASIND, GROUP1D, FORMATD, TITLED, AUTHORD, PUBLISHERD);
                //Toast.makeText(getApplicationContext(),"This book is now added to My Favorite", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "This book is now added to My Favorite", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        addToFav = findViewById(R.id.buttonAddFavo);
//
//        addToFav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BookDbHelper bookDbHelper = BookDbHelper.getsInstance(BookDetailsnya.this);
//                bookDbHelper.addFavorites(ASIND, GROUP1D, FORMATD, TITLED, AUTHORD, PUBLISHERD);
//                Toast.makeText(getApplicationContext(),"This book is now added to My Favorite", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}

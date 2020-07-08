package id.ac.umn.uts_14910;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {
    //Button delFav;
    private ArrayList<BookModel> inputFav;
    private FavAdapter adapterFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addDataFav();
        recViewFav();

//        delFav = findViewById(R.id.buttonhapusFav);

    }

    void addDataFav(){
        inputFav = new ArrayList<>();
        BookDbHelper bookDbHelper = BookDbHelper.getsInstance(this);
        Cursor cursor2 = bookDbHelper.getDataFav();

//        while(cursor2.moveToNext()){
//            inputFav.add(new BookModel(cursor2.getString(cursor2.getColumnIndex("FAVASIN")),
//                    cursor2.getString(cursor2.getColumnIndex("FAVTITLE")),
//                    cursor2.getString(cursor2.getColumnIndex("FAVAUTHOR")),
//                    cursor2.getString(cursor2.getColumnIndex("FAVPUBLISHER"))));
//        }

        if(cursor2.moveToFirst()){
            do{
                inputFav.add(new BookModel(cursor2.getString(cursor2.getColumnIndex("ASIN")),
                        cursor2.getString(cursor2.getColumnIndex("TITLE")),
                        cursor2.getString(cursor2.getColumnIndex("AUTHOR")),
                        cursor2.getString(cursor2.getColumnIndex("PUBLISHER"))));
            }while (cursor2.moveToNext());
        }
    }

    void recViewFav(){

        adapterFav = new FavAdapter(this, inputFav);

        RecyclerView recyclerView = findViewById(R.id.list_favoties);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterFav);
    }
}

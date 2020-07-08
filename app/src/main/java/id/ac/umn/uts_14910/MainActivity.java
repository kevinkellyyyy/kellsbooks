package id.ac.umn.uts_14910;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    EditText textCari;
    Button buttonCari;

    public String sortBy;
    private BookAdapter adapter;

    private ArrayList<BookModel> input;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
//        MenuItem item = menu.findItem(R.id.search_lup);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                adapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.buttonSortBy:
               sortingData();
               break;

            case R.id.myFavo:
                startActivity(new Intent(MainActivity.this, FavoritesActivity.class));
                break;

            case R.id.buttonAboutMe:
                startActivity(new Intent(MainActivity.this, AboutmeActivity.class));
                break;

            case R.id.buttonLogout:
                SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor e=sp.edit();
                e.clear();
                e.commit();

                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void sortingData(){
        String[]  options = {"By Title Ascending", "By Title Descending"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort By");
        //builder.setIcon
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    sortBy = "ascending";
                    recView();
                }
                if(which==1){
                    sortBy = "descending";
                    recView();
                }

            }
        });
        builder.create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addData();
        recView();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        textCari = findViewById(R.id.textCari);
        buttonCari = findViewById(R.id.buttonCari);

        buttonCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cari = textCari.getText().toString();
                adapter.getFilter().filter(cari);
            }
        });


    }

    void recView(){
        if(sortBy == "ascending"){
            Collections.sort(input, BookModel.BY_TITLE_ASCENDING);
        }
        if(sortBy == "descending"){
            Collections.sort(input, BookModel.BY_TITLE_DESCENDING);
        }

        adapter = new BookAdapter(this, input);

        RecyclerView recyclerView = findViewById(R.id.list_buku);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    void addData(){
        input = new ArrayList<>();
        BookDbHelper bookDbHelper = BookDbHelper.getsInstance(this);
        Cursor cursor = bookDbHelper.getData();

        while(cursor.moveToNext()){
            input.add(new BookModel(cursor.getString(cursor.getColumnIndex("ASIN")),
                    cursor.getString(cursor.getColumnIndex("GROUP1")),
                    cursor.getString(cursor.getColumnIndex("FORMAT")),
                    cursor.getString(cursor.getColumnIndex("TITLE")),
                    cursor.getString(cursor.getColumnIndex("AUTHOR")),
                    cursor.getString(cursor.getColumnIndex("PUBLISHER"))));
        }
    }
}

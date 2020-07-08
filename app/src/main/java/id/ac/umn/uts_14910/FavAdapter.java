package id.ac.umn.uts_14910;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.MyHolder>{
    Context context;
    ArrayList<BookModel> itemFav;
    private View view;
    
    public FavAdapter(Context context, ArrayList<BookModel> itemFav){
        this.context = context;
        this.itemFav = itemFav;
    }
    
    @NonNull
    @Override
    public FavAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        view = LayoutInflater.from(context).inflate(R.layout.kotakisifav, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.MyHolder holder, int position) {
        holder.bookJudulFav.setText(itemFav.get(position).getFAVTITLE());
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"This book is now added to My Favorite", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return itemFav.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView bookJudulFav;
        public  MyHolder(@NonNull View itemView){
            super(itemView);
            bookJudulFav = itemView.findViewById(R.id.textJudulFav);
        }
    }
}

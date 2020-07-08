package id.ac.umn.uts_14910;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyHolder> implements Filterable{
    Context context;
    ArrayList<BookModel> items, filterList;
    SearchFileter filter;
    private View view;

    public BookAdapter(Context context, ArrayList<BookModel> items){
        this.context = context;
        this.items = items;
        this.filterList = items;
    }

    @NonNull
    @Override
    public BookAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        view = LayoutInflater.from(context).inflate(R.layout.kotakisi,parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.MyHolder holder, final int position) {
        holder.setIsRecyclable(false);
        final String valueASINtoDet = items.get(position).getASIN();
        final String valueGROUP1toDet = items.get(position).getGROUP1();
        final String valueFORMATtoDet = items.get(position).getFORMAT();
        final String valueTITLEtoDet = items.get(position).getTITLE();
        final String valueAUTHORtoDet = items.get(position).getAUTHOR();
        final String valuePUBLISHERtoDet = items.get(position).getPUBLISHER();
        holder.bookJudul.setText(items.get(position).getTITLE());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in;
                in = new Intent(context,BookDetailsnya.class);
                in.putExtra("keyASIN", valueASINtoDet);
                in.putExtra("keyGROUP1", valueGROUP1toDet);
                in.putExtra("keyFORMAT", valueFORMATtoDet);
                in.putExtra("keyTITLE", valueTITLEtoDet);
                in.putExtra("keyAUTHOR", valueAUTHORtoDet);
                in.putExtra("keyPUBLISHER", valuePUBLISHERtoDet);
                //Toast.makeText(context, items.get(position).getTITLE(), Toast.LENGTH_LONG).show();
                context.startActivity(in);
            }
        });
    }

    public int getItemCount(){
        return items.size();
    }

    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new SearchFileter(filterList, this);
        }
        return filter;
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView bookJudul;
        public MyHolder(@NonNull View itemView){
            super(itemView);
            bookJudul = itemView.findViewById(R.id.textJudul);

        }
    }
}
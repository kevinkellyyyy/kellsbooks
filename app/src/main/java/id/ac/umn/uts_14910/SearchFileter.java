package id.ac.umn.uts_14910;

import android.widget.Filter;

import java.util.ArrayList;

public class SearchFileter extends Filter{
    BookAdapter adapter;
    ArrayList<BookModel> filterList;

    public SearchFileter(ArrayList<BookModel> filterList, BookAdapter adapter){
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if(constraint != null && constraint.length()>0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<BookModel> filteredModels = new ArrayList<>();
            for (int i=0; i<filterList.size();i++){
                if (filterList.get(i).getTITLE().toUpperCase().contains(constraint)){
                    filteredModels.add(filterList.get(i));
                }
            }
            results.count = filteredModels.size();
            results.values = filteredModels;
        }
        else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.items = (ArrayList<BookModel>) results.values;
        adapter.notifyDataSetChanged();
    }
}

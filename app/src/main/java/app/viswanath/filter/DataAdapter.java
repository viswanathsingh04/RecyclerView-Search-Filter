package app.viswanath.filter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {
    private Context mContext;
    private List<DataModel> mDataList;
    private List<DataModel> mDataListFiltered;

    DataAdapter(Context context, List<DataModel> mDataList) {
        this.mContext = context;
        this.mDataList = mDataList;
        this.mDataListFiltered = mDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_adapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final DataModel entity = mDataListFiltered.get(position);
        holder.mText.setText(entity.getName());
    }

    @Override
    public int getItemCount() {
        return mDataListFiltered.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private DataModel mDataListFiltered;
        private TextView mText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mText = itemView.findViewById(R.id.text);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mDataListFiltered = mDataList;
                } else {
                    List<DataModel> filteredList = new ArrayList<>();
                    for (DataModel row : mDataList) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    mDataListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mDataListFiltered = (ArrayList<DataModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}

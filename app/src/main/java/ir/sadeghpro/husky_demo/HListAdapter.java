package ir.sadeghpro.husky_demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class HListAdapter extends RecyclerView.Adapter<HListAdapter.RestaurantViewHolder> {
    private ArrayList<Model> models = new ArrayList<>();
    private Context mContext;

    public HListAdapter(Context context, ArrayList<Model> models) {
        mContext = context;
        this.models = models;
    }

    @Override
    public HListAdapter.RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hlist_item, parent, false);
        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HListAdapter.RestaurantViewHolder holder, int position) {
        holder.bindRestaurant(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        TextView mNameTextView;
        private Context mContext;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mNameTextView = itemView.findViewById(R.id.name);
        }

        public void bindRestaurant(Model model) {
            mNameTextView.setText(model.getName());
        }
    }
}
package ir.sadeghpro.husky.hlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class AdapterHelper<T> extends RecyclerView.Adapter<ViewHolderHelper> {
    private List<T> models;
    private Class<? extends ViewHolderHelper<T>> viewHolder;
    private int layoutResource;


    public AdapterHelper(ArrayList<T> models, Class<? extends ViewHolderHelper<T>> viewHolder, int layoutResource) {
        this.models = models;
        this.viewHolder = viewHolder;
        this.layoutResource = layoutResource;
    }


    @NonNull
    @Override
    public ViewHolderHelper onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        try {
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
            return viewHolder.getDeclaredConstructor(View.class).newInstance(view);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHelper viewHolder, int position) {
        viewHolder.bindModel(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


}

package ir.sadeghpro.husky_demo;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import ir.sadeghpro.husky.vlist.VListAdapterHelper;

public class VListAdapter extends VListAdapterHelper<String> {

    public VListAdapter(Context context, ArrayList<String> data, int layoutResource) {
        super(context, data, layoutResource);
    }

    @Override
    public void bindModel(int position, View convertView, String model) {
        TextView txt = convertView.findViewById(R.id.txt);
        txt.setText(model);
    }
}

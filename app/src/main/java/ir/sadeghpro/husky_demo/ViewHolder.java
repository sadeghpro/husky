package ir.sadeghpro.husky_demo;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import ir.sadeghpro.husky.hlist.ViewHolderHelper;

public class ViewHolder extends ViewHolderHelper<Model> {

    private TextView txt;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        txt = itemView.findViewById(R.id.name);
    }

    @Override
    public void bindModel(Model model) {
        txt.setText(model.getName());
    }
}

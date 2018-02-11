package geronimo.test.com.mylist.views.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import geronimo.test.com.mylist.R;

/**
 * Created by Muthu on 11/02/2018.
 */

public class ContentViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgIcone;
    public TextView txtTitle, txtSubtitle;
    public Button btnShare, btnDetails;
    public ContentViewHolder(View itemView) {
        super(itemView);
        imgIcone = (ImageView) itemView.findViewById(R.id.imgIcon);
        txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        txtSubtitle = (TextView) itemView.findViewById(R.id.txtSubTitle);
        btnDetails = (Button) itemView.findViewById(R.id.btnDetails);
        btnShare = (Button) itemView.findViewById(R.id.btnShare);
    }
}

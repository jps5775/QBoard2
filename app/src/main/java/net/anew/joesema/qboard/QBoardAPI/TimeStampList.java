package net.anew.joesema.qboard.QBoardAPI;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;
import java.util.List;
import net.anew.joesema.qboard.R;



public class TimeStampList extends ArrayAdapter<TimeStamp> {

    private Activity context;
    private List<TimeStamp> timeStampList;

    public TimeStampList(Activity context, List<TimeStamp> timeStampList){
        super(context, R.layout.list_layout, timeStampList);
        this.context = context;
        this.timeStampList = timeStampList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.textViewDate);
        TextView textViewGyroData = (TextView) listViewItem.findViewById(R.id.textViewGyroData);
        TextView textViewAccelData = (TextView) listViewItem.findViewById(R.id.textViewAccelData);

        TimeStamp timeStamp = timeStampList.get(position);

        textViewDate.setText(timeStamp.getDate());
        textViewGyroData.setText(timeStamp.getGyroData());
        textViewAccelData.setText(timeStamp.getAccelData());

        return listViewItem;

    }
}
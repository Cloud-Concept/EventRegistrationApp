package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cloudconcept.R;

import java.util.List;

import model.Event;

/**
 * Created by Bibo on 12/27/15.
 */
public class EventsAdapter extends BaseAdapter {

    private List<Event> events;
    private Context context;

    public EventsAdapter(Context context,List<Event> events){
        this.context = context;
        this.events = events;
    }


    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String inflater = Context.LAYOUT_INFLATER_SERVICE;

        TextView tvOwner,tvEventName;
        LayoutInflater li = (LayoutInflater)context.getSystemService(inflater);
        if(convertView==null){
            convertView = li.inflate(R.layout.event_row, parent, false);
        }

        tvOwner = (TextView) convertView.findViewById(R.id.tvOwner);
        tvEventName = (TextView) convertView.findViewById(R.id.tvEventName);

        tvOwner.setText(events.get(position).getOwnerName());
        tvEventName.setText(events.get(position).getSubject());

        return convertView;
    }
}

package activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.cloudconcept.R;
import com.google.gson.Gson;

import model.Event;

/**
 * Created by Bibo on 12/27/15.
 */
public class EventDetailsActivity extends Activity {

    Event event;
    Gson gson;

    TextView tvSubject,tvIsAllDayEvent,tvOwnerName,tvDurationInMinutes,tvWho,tvWhat,tvStartDateTime,tvActivityDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);

        gson = new Gson();

        event = gson.fromJson(getIntent().getExtras().getString("obj"),Event.class);
        tvSubject = (TextView)findViewById(R.id.tvSubject);
        tvIsAllDayEvent = (TextView)findViewById(R.id.tvIsAllDayEventValue);
        tvOwnerName = (TextView)findViewById(R.id.tvOwnerNameValue);
        tvDurationInMinutes = (TextView)findViewById(R.id.tvDurationInMinutesValue);
        tvWho = (TextView)findViewById(R.id.tvWhoNameValue);
        tvWhat = (TextView)findViewById(R.id.tvWhatValue);
        tvStartDateTime = (TextView)findViewById(R.id.tvStartDateTimeValue);
        tvActivityDateTime = (TextView)findViewById(R.id.tvActivityDateTimeValue);

        tvSubject.setText(event.getSubject());

        if(event.getIsAllDayEvent()==true){
            tvIsAllDayEvent.setText("Yes");
        }else{
            tvIsAllDayEvent.setText("No");
        }

        tvOwnerName.setText(event.getOwnerName());
        tvDurationInMinutes.setText(event.getDurationInMinutes());
        tvWho.setText(event.getWhoName());
        tvWhat.setText(event.getWhatName());
        tvStartDateTime.setText(event.getStartDateTime());
        tvActivityDateTime.setText(event.getActivityDateTime());

    }
}

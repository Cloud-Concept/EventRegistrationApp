package activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.cloudconcept.R;
import com.salesforce.androidsdk.rest.RestClient;
import com.salesforce.androidsdk.rest.RestRequest;
import com.salesforce.androidsdk.rest.RestResponse;
import com.salesforce.androidsdk.ui.sfnative.SalesforceActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import adapter.EventsAdapter;
import model.Event;
import utilities.ActivitiesLauncher;
import utilities.Utilities;

/**
 * Created by Abanoub Wagdy on 12/27/15.
 */
public class HomeActivity extends SalesforceActivity {

    private RestClient client;
    EditText etSearch;

    String soql = "SELECT Id, IsAllDayEvent, Owner.name,Owner.id, DurationInMinutes, Who.name, Who.id, What.id,What.name , StartDateTime, ActivityDateTime,Subject FROM Event";
    //    String soql = "SELECT Id FROM User";
    ListView lstEvents;
    EventsAdapter adapter;
    ArrayList<Event> backupevents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        lstEvents = (ListView) findViewById(R.id.lstEvents);
        etSearch = (EditText) findViewById(R.id.etSearch);
        backupevents = new ArrayList<>();
    }

    @Override
    public void onResume(RestClient client) {

        this.client = client;
        try {
            sendRequest(soql);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    private void sendRequest(String soql) throws UnsupportedEncodingException {

        Utilities.showloadingDialog(this);

        RestRequest restRequest = RestRequest.getRequestForQuery(getString(R.string.api_version), soql);

        client.sendAsync(restRequest, new RestClient.AsyncRequestCallback() {
            @Override
            public void onSuccess(RestRequest request, RestResponse result) {
                Utilities.dismissLoadingDialog();
                Log.d("Success", "Success");

                final ArrayList<Event> events = parseResult(result.toString());

                adapter = new EventsAdapter(getApplicationContext(), events);
                lstEvents.setAdapter(adapter);
                backupevents.addAll(events);

                lstEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ActivitiesLauncher.openEventDetailsActivity(getApplicationContext(), events.get(position));
                    }
                });

                applyEditTextSearchFunctionality();

                etSearch.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        events.clear();
                        if (!s.toString().equals("") && s.toString() != null) {
                            for (int i = 0; i < backupevents.size(); i++) {
                                if (backupevents.get(i).getSubject().contains(s.toString()))
                                    events.add(backupevents.get(i));
                            }

                            adapter.notifyDataSetChanged();

                        } else {
                            events.addAll(backupevents);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }

            @Override
            public void onError(Exception exception) {
                Utilities.dismissLoadingDialog();
                VolleyError volleyError = (VolleyError) exception;
                NetworkResponse response = volleyError.networkResponse;
                String json = new String(response.data);
                Log.d("", json);
            }
        });
    }

    private ArrayList<Event> parseResult(String s) {

        ArrayList<Event> events = new ArrayList<>();

        try {
            JSONObject jsonrecords = new JSONObject(s);
            JSONArray jArrayRecords = jsonrecords.getJSONArray("records");
            for (int i = 0; i < jArrayRecords.length(); i++) {
                Event event = new Event();
                JSONObject record = jArrayRecords.getJSONObject(i);
                event.setId(record.getString("Id"));
                event.setIsAllDayEvent(record.getBoolean("IsAllDayEvent"));
                event.setOwnerId(record.getJSONObject("Owner").getString("Id"));
                event.setOwnerName(record.getJSONObject("Owner").getString("Name"));
                event.setDurationInMinutes(record.getString("DurationInMinutes"));
                event.setWhatId(record.getJSONObject("What").getString("Id"));
                event.setWhatName(record.getJSONObject("What").getString("Name"));
                event.setStartDateTime(record.getString("StartDateTime"));
                event.setActivityDateTime(record.getString("ActivityDateTime"));
                event.setSubject(record.getString("Subject"));
                events.add(event);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return events;
    }


    public void applyEditTextSearchFunctionality() {

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Utilities.showloadingDialog(HomeActivity.this);
                    performSearch(etSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    private void performSearch(String s) {

        String soqlSearch = String.format("SELECT Id, IsAllDayEvent, Owner.name,Owner.id, DurationInMinutes, Who.name, Who.id, What.id,What.name , StartDateTime, ActivityDateTime,Subject FROM Event WHERE Subject='%s'", s);
        try {
            RestRequest restRequest = RestRequest.getRequestForQuery(getString(R.string.api_version), soqlSearch);
            client.sendAsync(restRequest, new RestClient.AsyncRequestCallback() {
                @Override
                public void onSuccess(RestRequest request, RestResponse result) {
                    Utilities.dismissLoadingDialog();
                    final ArrayList<Event> events = parseResult(result.toString());

                    adapter = new EventsAdapter(getApplicationContext(), events);
                    lstEvents.setAdapter(adapter);
                    backupevents.addAll(events);

                    lstEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ActivitiesLauncher.openEventDetailsActivity(getApplicationContext(), events.get(position));
                        }
                    });
                }

                @Override
                public void onError(Exception exception) {
                    Utilities.dismissLoadingDialog();
                    VolleyError volleyError = (VolleyError) exception;
                    NetworkResponse response = volleyError.networkResponse;
                    String json = new String(response.data);
                    Log.d("", json);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

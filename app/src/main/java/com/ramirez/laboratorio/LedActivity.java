package com.ramirez.laboratorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LedActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnEncender;
    Button btnApagar;
    private RequestQueue mQueue;
    String val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led);

        findViewById(R.id.btnPrender).setOnClickListener(this);
        findViewById(R.id.btnApagar).setOnClickListener(this) ;
    }

    @Override
    public void onClick(View view) {
        String url="https://io.adafruit.com/api/v2/eder24m3za/feeds/default-dot-led/data";
        JSONObject led = new JSONObject();

        switch (view.getId()) {
            case R.id.btnPrender:
                try {
                    led.put("value", "1");
                } catch (Exception e) {
                }
                break;
            case R.id.btnApagar:
                try {
                    led.put("value", "0");
                } catch (Exception e) {
                }
                break;
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, led, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("vol", error.toString());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-AIO-Key","aio_DhKI53fEM3CpC9Xh3ixFNIBT4kiu");
                return headers;
            }
        };
        Singleton.getInstance(this).addToRequestQue(jsonObjectRequest);
    }
}
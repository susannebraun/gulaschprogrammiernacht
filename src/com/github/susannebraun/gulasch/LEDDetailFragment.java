package com.github.susannebraun.gulasch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;


// TODO: SB use AsyncTask to do network communication!
public class LEDDetailFragment extends Fragment {

	private static final String TOGGLE_STATE = "toggle_state";
	
	private static final String LED_IP_ADDR = "192.168.0.51";
	
//	private static final String LED_IP_ADDR = "192.168.0.27";
	
	private static final int LED_IP_PORT = 80;
	
//	private static final int LED_IP_PORT = 4321;

	private static final String LOG_TAG = "LED";
   
	private boolean toggle = false;
	
    public LEDDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // TODO: SB query toggle state ?!
        if (getArguments() != null && getArguments().containsKey(TOGGLE_STATE)) {
            toggle = getArguments().getBoolean(TOGGLE_STATE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_led_detail, container, false);
        
//        final TextView errorText = (TextView) rootView.findViewById(R.id.led_error);
        
        ToggleButton toggleButton = (ToggleButton) rootView.findViewById(R.id.ledToggleButton);
        toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				String response = null;
				if(isChecked) {
					// send LED ON
					Random r = new Random(System.currentTimeMillis());
					int number = r.nextInt() % 10;
					String cmd = String.valueOf(number);
					response = sendToLED(cmd);
					toggle = true;
				} else {
					// send LED OFF
					String cmd = "b";
					response = sendToLED(cmd);
					toggle = false;
				}
				
				if(response != null) {
					Log.i(LOG_TAG, "Received: "+response);
				}
//				if(response != null && "OK".equals(response)) {
//					Log.i(LOG_TAG, "Received OK");
//					// OK
//					errorText.setTextColor(getResources().getColor(R.color.white));
//				} else {
//					Log.i(LOG_TAG, "Received ERROR");
//					errorText.setTextColor(getResources().getColor(R.color.red));
//				}
			}

			
		});
        
        
        
        return rootView;
    }
    
    
    private String sendToLED(String cmd) {
    	Socket client = null;
    	PrintWriter out = null;
    	BufferedReader in = null;
    	
    	try {
    		   client = new Socket(LED_IP_ADDR, LED_IP_PORT);
    		   out = new PrintWriter(client.getOutputStream(), true);
    		   out.write(cmd);
    		   out.flush();
    		  
    		   in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    		   return in.readLine();
    	
    	} catch(UnknownHostException e) {
    		   Log.e(LOG_TAG, "Failed to send cmd to LED.", e);
    	} catch(IOException e) {
    			Log.e(LOG_TAG, "Failed to send cmd to LED.", e);
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					Log.w(LOG_TAG, "Failed to close socket input stream.", e);
				}
			}
		}
    	
    	return null;
  
	}
}

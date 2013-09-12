package com.example.vatsana;

import java.util.ArrayList;
import java.util.List; 
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject; 
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
 
public class RegisterNewUserActivity extends Activity {
 
    private ProgressDialog pDialog;
    private static String url_create_product = "http://10.0.2.2/jas/register.php";
 
    JSONParser jsonParser = new JSONParser();
    EditText inputUserName;
    EditText inputPassword;
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
 
        // Edit Text
        inputUserName = (EditText) findViewById(R.id.reg_username);
        inputPassword = (EditText) findViewById(R.id.reg_password);
 
        // Create button
        Button btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);
 
        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // creating new product in background thread
             new RegisterUser().execute();
            	//Intent i = new Intent(getApplicationContext(), JSONParser.class);
              //startActivity(i);
            }
        });
    }
 
    /**
     * Background Async Task to Create new product
     * */
    class RegisterUser extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RegisterNewUserActivity.this);
            pDialog.setMessage("Registering User..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }
 
        /**
         * Creating product
         * */
        int success;
        protected String doInBackground(String... args) {
            //String name = inputUserName.getText().toString();
       // String password = inputPassword.getText().toString();
 
 
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair ("username",inputUserName.getText().toString()));
            params.add(new BasicNameValuePair ("password",inputPassword.getText().toString()));
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,"POST", params);
            Log.d("Create Response", json.toString());
            try {
                success = json.getInt(TAG_SUCCESS);
                
                if(success==1)
                {
                	Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
                  }
             catch (JSONException e) {
                e.printStackTrace();
            } 
            return null;
        }
            protected void onPostExecute(String file_url) {
           	pDialog.dismiss();
        	
        }
     }
}
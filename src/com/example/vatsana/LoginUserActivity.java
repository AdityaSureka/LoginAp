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
import android.widget.TextView;
 
public class LoginUserActivity extends Activity {
 
    // Progress Dialog
    private ProgressDialog pDialog;
    private static String url_user_login = "http://10.0.2.2/jas/login.php";
	//SessionManager session;

    JSONParser jsonParser = new JSONParser();
    EditText inputUserName;
    EditText inputPassword;
    TextView loginErrorMsg;

    // url to create new product
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_UID = "20";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
 
        // Edit Text
        inputUserName = (EditText) findViewById(R.id.log_Username);
        inputPassword = (EditText) findViewById(R.id.log_Password);
        loginErrorMsg = (TextView) findViewById(R.id.login_error);

        // Create button
        Button btnLoginUser = (Button) findViewById(R.id.btnLoginUser);
 
        // button click event
        btnLoginUser.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // creating new product in background thread

             new LoginUser().execute();
            //	Intent i = new Intent(getApplicationContext(), JSONParser.class);
              //  startActivity(i);
            }
        });
    }
 
    /**
     * Background Async Task to Create new product
     * */
    public class LoginUser extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginUserActivity.this);
            pDialog.setMessage("Logging In");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
 
        /**
         * Creating product

         * */        	SessionManager session= new SessionManager(getApplicationContext());


        public String doInBackground(String... args) {
            //String name = inputUserName.getText().toString();
           // String password = inputPassword.getText().toString();
 
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair ("username",inputUserName.getText().toString()));
            params.add(new BasicNameValuePair ("password",inputPassword.getText().toString()));

			// getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_user_login,"POST", params);
            // check log cat fro response
            Log.d("Create Response", json.toString());
// check for success tag
          
            try {

               int success = json.getInt(TAG_SUCCESS);

                    if(success==1)
                {
                	Intent i = new Intent(getApplicationContext(), DashActivity.class);
                    startActivity(i);
                }                
                else
                {
                	Intent j = new Intent(getApplicationContext(), RegisterNewUserActivity.class);
                    startActivity(j);
                }
                    //int uid = json.getInt(TAG_UID);
                    //String u=Integer.toString(uid);
                	session.createLoginSession("TAG_UID","TAG_UID");   

                } catch (JSONException e) {
                e.printStackTrace();
            } 
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
           	pDialog.dismiss();
        
        }
 
    }
}
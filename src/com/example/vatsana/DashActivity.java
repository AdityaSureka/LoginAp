package com.example.vatsana;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
 
public class DashActivity extends Activity {
     
    // Alert Dialog Manager
     
    // Session Manager Class
    SessionManager session;
    
    TextView loginErrorMsg;

     
    // Button Logout
    Button btnLogout;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

         
        // Session class instance
        session = new SessionManager(getApplicationContext());
        loginErrorMsg = (TextView) findViewById(R.id.login_id);
        TextView UserName = (TextView) findViewById(R.id.log_Username);
         
        // Button logout
        btnLogout = (Button) findViewById(R.id.btnLogout);
         
       // Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
         
         
        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
       // session.checkLogin();
         
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
         
        // name
        String name = user.get(SessionManager.KEY_USERNAME);
         
		loginErrorMsg.setText(name);
 
        
        // email
      //  String email = user.get(SessionManager.KEY_EMAIL);
      
        
		// displaying user data
//        reg_UserName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
       // lblEmail.setText(Html.fromHtml("Email: <b>" + email + "</b>"));
         
         
        /**
         * Logout button click event
         * */
        btnLogout.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View arg0) {
                // Clear the session data
                // This will clear all session data and 
                // redirect user to LoginActivity
                session.logoutUser();
            }
        });
    }
         
}
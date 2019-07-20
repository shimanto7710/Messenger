package com.example.messenger3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.db.DatabaseHelper;

public class Signup extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    //    @InjectView(R.id.input_name)
    EditText _nameText;
    //    @InjectView(R.id.input_email)
    EditText _emailText;
    //    @InjectView(R.id.input_password)
    EditText _passwordText;
    //    @InjectView(R.id.btn_signup)
    Button _signupButton;
    //    @InjectView(R.id.link_login)
    TextView _loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        _nameText=(EditText)findViewById(R.id.input_name);
        _emailText=(EditText)findViewById(R.id.input_email);
        _passwordText=(EditText)findViewById(R.id.input_password);
        _signupButton=(Button) findViewById(R.id.btn_signup);
        _loginLink=(TextView) findViewById(R.id.link_login);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Signup.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String name = _nameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        boolean flag=signupCheck(name,email,password);
                        if (flag){
                            Toast.makeText(getBaseContext(), "Successfully created account", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplication(),LoginActivity.class);
                            startActivity(intent);

                            Log.d(TAG, "run: "+"sign up successful");
                        }else {
                            Toast.makeText(getBaseContext(), "Unsuccessful", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "run: "+"unSuccessful");
                        }
//                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }


                }, 3000);
    }

    private boolean signupCheck(String name, String email, String password) {
//        String[] columns = {"id"};

        String tableName="user_info";

        DatabaseHelper myDbHelper;
        myDbHelper=new DatabaseHelper(this);
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("pass",password);
        long aa= myDbHelper.writeData(contentValues,tableName);

        if (aa!=-1){
            return true;
        }

        return false;
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}

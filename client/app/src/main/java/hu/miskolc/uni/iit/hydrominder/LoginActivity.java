package hu.miskolc.uni.iit.hydrominder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_signup) Button _loginButton;
    @Bind(R.id.link_login) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Signup activity indítása
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        //Validációs hiba esetén
        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Bejelentkezés...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: belépési logika

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // Sikeres vagy sikertelen login
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // TODO: Sikeres bejelentkezés
                // Most csak simán beléptetünk
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Vissza gomb letiltása
        moveTaskToBack(true);
    }

    //Sikeres belépés
    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        //MainActivity indítása
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    //Sikertelen belépés
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Sikertelen bejelentkezés", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    //Login adatok validációja
    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Nem megfelelő email cím!");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("A jelszó 4-10 karakter hosszú lehet és betűket vagy számokat tartalmazhat!");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
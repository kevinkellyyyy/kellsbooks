package id.ac.umn.uts_14910;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button buttonLogin;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        buttonLogin = findViewById(R.id.buttonLogin);

        sp = getSharedPreferences("login", MODE_PRIVATE);

        if(sp.contains("username") && sp.contains("password")){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCheck();
            }
        });
    }

    void loginCheck(){
        if(username.getText().toString().equals("user") && password.getText().toString().equals("useruser")){
            SharedPreferences.Editor e=sp.edit();
            e.putString("username", "user");
            e.putString("password", "useruser");
            e.commit();

            Toast.makeText(LoginActivity.this, "Login Succesful", Toast.LENGTH_LONG).show();

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        else {
            Toast.makeText(LoginActivity.this, "Incorrect Login Details", Toast.LENGTH_LONG).show();
        }
    }
}

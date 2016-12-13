package com.example.felix.volleydemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView,nombre,apellido,correo,rol;
    EditText usuario, password;
    ImageView imageView;
    String url = "";
    RequestQueue requestQueue;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.btnsend);
        textView = (TextView)findViewById(R.id.txt);
        nombre = (TextView)findViewById(R.id.nombre);
        apellido = (TextView)findViewById(R.id.apellido);
        correo = (TextView)findViewById(R.id.correo);
        rol = (TextView)findViewById(R.id.rol);
        usuario = (EditText)findViewById(R.id.user);
        password = (EditText)findViewById(R.id.pass);
        builder = new AlertDialog.Builder(MainActivity.this);

    }

    public void send(View v){
        url = "http://192.168.1.112:8000/android/authuser";
       final String correo_send,pass_send, recordar;
        correo_send = usuario.getText().toString();
        pass_send = password.getText().toString();
        recordar = "false";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server response");
                        builder.setMessage("Response: "+response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                usuario.setText("");
                                password.setText("");

                            }
                        });
                        AlertDialog alertDialog= builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("email",correo_send);
                params.put("password",pass_send);
                return params;
            }
        };
        Mysingleton.getmInstance(MainActivity.this).addToRequestQue(stringRequest);

    }
}

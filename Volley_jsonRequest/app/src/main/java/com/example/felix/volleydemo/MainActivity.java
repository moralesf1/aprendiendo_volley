package com.example.felix.volleydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView,nombre,apellido,correo,rol;
    EditText usuario, password;
    ImageView imageView;
    String url = "";
    RequestQueue requestQueue;
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


    }

    public void send(View v){
        url = "http://192.168.1.112:8000/android/authuser?email="+usuario.getText()+"&password="+password.getText()+"&recordar=false";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        textView.setText(response.toString());
                        try {
                            nombre.setText(response.getString("nombre"));
                            apellido.setText(response.getString("apellido"));
                            correo.setText(response.getString("email"));
                            rol.setText(response.getString("id_rol"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Error con la conexion!");
                error.printStackTrace();
            }
        });
        Mysingleton.getmInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
    }
}

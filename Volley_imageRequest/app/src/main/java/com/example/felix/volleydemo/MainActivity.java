package com.example.felix.volleydemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
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
        usuario = (EditText)findViewById(R.id.user);
        password = (EditText)findViewById(R.id.pass);
        imageView = (ImageView)findViewById(R.id.foto);


    }

    public void send(View v){
//        url = "http://192.168.1.112:8000/android/authuser?email="+usuario.getText()+"&password="+password.getText()+"&recordar=false";
        url = "http://192.168.1.112:8000/uploads/im.png";
        ImageRequest imageRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);

                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Error al cargar la foto",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        Mysingleton.getmInstance(MainActivity.this).addToRequestQue(imageRequest);
    }
}

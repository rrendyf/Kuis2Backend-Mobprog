package com.example.rendyfunthagrap.kuis2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Daftar extends AppCompatActivity {

    EditText id,passu;
    Button Dafta;
    String ID,PASSS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        id = findViewById(R.id.etIdDaftar);
        passu = findViewById(R.id.etPassDaft);
        Dafta = findViewById(R.id.btnDaftar);

        Dafta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new AmbilData().execute("http://mobprog.yuliadi.pro:5000/buat_account"); //url jadi parameter
                } else {
                    // tampilkan error
                    Toast t = Toast.makeText( getApplicationContext(), "Tidak ada koneksi!", Toast.LENGTH_LONG);
                    t.show();
                }
            }
        });

    }

    private class AmbilData extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... strUrl) {
            Log.v("daftar", "mulai ambil data");
            String result = null;
            StringBuffer sb = new StringBuffer();
            InputStream is = null;
            try {
                URL url = new URL(strUrl[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //timeout
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                ID = id.getText().toString();
                PASSS = passu.getText().toString();

                conn.setRequestMethod("POST");
                conn.connect();

                try {
                    is = new BufferedInputStream(conn.getInputStream());
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String inputLine = "";
                    while ((inputLine = br.readLine()) != null) {
                        sb.append(inputLine);
                    }
                    result = sb.toString();
                }
                catch (Exception e) {
                    Log.i("rf", "Error reading InputStream");
                    result = null;
                }
                finally {
                    if (is != null) {
                        try {
                            is.close();
                        }
                        catch (IOException e) {
                            Log.i("rf", "Error closing InputStream");
                        }
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        /*protected void onPostExecute(String result) {
            tvHasil.setText(result);
        }*/
    }

}

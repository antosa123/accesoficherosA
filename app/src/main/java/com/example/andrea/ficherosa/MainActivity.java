package com.example.andrea.ficherosa;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

    Button mostrar,anadir;
    TextView  textMostrar;
    EditText textAnadir;
    String file_name = "fichero.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mostrar = (Button) findViewById(R.id.bMostrar);
        anadir = (Button) findViewById(R.id.bAnadir);
        textAnadir = (EditText) findViewById(R.id.tv_intro);
        textMostrar = (TextView) findViewById(R.id.tv_mostrar);

        /**
         * escribir en un fichero
         */
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //abrir un fichero de escritura
                    FileOutputStream fos = openFileOutput(file_name, Context.MODE_APPEND);//añadir contenido
                    //envoltura para el fileoutput
                    DataOutputStream dops = new DataOutputStream(fos);

                    dops.writeBytes(textAnadir.getText().toString());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


        /**
         * lectura del fichero
         * creamos un fichero de entrada
         */
        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput(file_name);
                    //envoltura para su lectura
                    DataInputStream dis = new DataInputStream(fis);
                    byte[] buff = new byte[1000];
                    dis.read(buff);
                    textMostrar.setText(new String(buff));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

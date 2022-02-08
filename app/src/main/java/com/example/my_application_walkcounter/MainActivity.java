package com.example.my_application_walkcounter;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    //public List<Data> accelerometerSample = new ArrayList<>();
    //public RNWalkCounterModule pippo;
    public int nsteps=0;
    public StepDetector dec = new StepDetector();
    //pippo = new RNWalkCounterModule(dec, nsteps);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readAccelerometerData();
    }


    private void readAccelerometerData(){
        InputStream is = getResources().openRawResource(R.raw.user1_armband_1506423438471_pippo);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
            // Split by ','
            String[] tokens = line.split(",");

            Data sample = new Data(Long.parseLong(tokens[0]),Float.parseFloat(tokens[1]),Float.parseFloat(tokens[2]),
                    Float.parseFloat(tokens[3]));
            //sample.setTimestamp(Long.parseLong(tokens[0]));
            //sample.setAccx(Float.parseFloat(tokens[1]));
            //sample.setAccy(Float.parseFloat(tokens[2]));
            //sample.setAccz(Float.parseFloat(tokens[3]));
            //accelerometerSample.add(sample);

            Log.d("MyActivity", "Appena Creata: "+sample);

            //pippo.onSensorChanged(sample);
            /*dec.updateAccel(sample.getTimestamp(),
                    sample.getAccx(), sample.getAccy(),
                    sample.getAccz());

             */
            dec.updateAccel(sample);
            Log.d("MyActivity", "steps: " + ++nsteps);

            }
        } catch (IOException e) {
                Log.wtf("MyActivity", "Error reading data file on line: " + line,e);
                e.printStackTrace();
        }



    }
}
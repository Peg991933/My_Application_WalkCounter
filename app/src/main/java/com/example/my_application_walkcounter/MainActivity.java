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
    public int riga=0;
    private double preMagnitude = 0;
    private int step = 0;
    private int totalStep = 0;
    //public StepDetector dec = new StepDetector();
    //pippo = new RNWalkCounterModule(dec, nsteps);

    private static final int ACCEL_RING_SIZE = 50;
    private static final int VEL_RING_SIZE = 10;

    // change this threshold according to your sensitivity preferences
    private static final float STEP_THRESHOLD = 50f;

    private static final int STEP_DELAY_NS = 250000000;

    private int accelRingCounter = 0;
    private float[] accelRingX = new float[ACCEL_RING_SIZE];
    private float[] accelRingY = new float[ACCEL_RING_SIZE];
    private float[] accelRingZ = new float[ACCEL_RING_SIZE];
    private int velRingCounter = 0;
    private float[] velRing = new float[VEL_RING_SIZE];
    private long lastStepTimeNs = 0;
    private float oldVelocityEstimate = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readAccelerometerData();
    }


    private void readAccelerometerData(){
        InputStream is = getResources().openRawResource(R.raw.filtro_ogni_20_righe);
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
            //dec.updateAccel(sample);
            Log.d("MyActivity", "riga: " + ++riga);

            // prova dell'algoritmo dell'app WalkWithMe

              float x = sample.getAccx();
                float y = sample.getAccy();
                float z = sample.getAccz();


                double lastMagnitude = preMagnitude;
                double magnitude = Math.sqrt(x * x + y * y + z * z);
                double delta = magnitude - preMagnitude;
                preMagnitude = magnitude;

                if (delta > 3 && lastMagnitude != 0) {
                    step++;
                    totalStep++;
                }

                Log.d("MyActivity", "passi: " + step);



                // prova dell'algoritmo della libreria react-native-walk-counter

                /*long timeNs = sample.getTimestamp();
                float[] currentAccel = new float[3];
                currentAccel[0] = sample.getAccx();
                currentAccel[1] = sample.getAccy();
                currentAccel[2] = sample.getAccz();

                // First step is to update our guess of where the global z vector is.
                accelRingCounter++;
                accelRingX[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[0];
                accelRingY[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[1];
                accelRingZ[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[2];

                float[] worldZ = new float[3];
                worldZ[0] = SensorFilter.sum(accelRingX) / Math.min(accelRingCounter, ACCEL_RING_SIZE);
                worldZ[1] = SensorFilter.sum(accelRingY) / Math.min(accelRingCounter, ACCEL_RING_SIZE);
                worldZ[2] = SensorFilter.sum(accelRingZ) / Math.min(accelRingCounter, ACCEL_RING_SIZE);

                float normalization_factor = SensorFilter.norm(worldZ);

                worldZ[0] = worldZ[0] / normalization_factor;
                worldZ[1] = worldZ[1] / normalization_factor;
                worldZ[2] = worldZ[2] / normalization_factor;

                float currentZ = SensorFilter.dot(worldZ, currentAccel) - normalization_factor;
                velRingCounter++;
                velRing[velRingCounter % VEL_RING_SIZE] = currentZ;

                float velocityEstimate = SensorFilter.sum(velRing);

                if (velocityEstimate > STEP_THRESHOLD && oldVelocityEstimate <= STEP_THRESHOLD
                        && (timeNs - lastStepTimeNs > STEP_DELAY_NS)) {
                    step++;
                    lastStepTimeNs = timeNs;
                }
                oldVelocityEstimate = velocityEstimate;

                Log.d("MyActivity", "passi: " + step);

                 */



            }
        } catch (IOException e) {
                Log.wtf("MyActivity", "Error reading data file on line: " + line,e);
                e.printStackTrace();
        }

    }


}
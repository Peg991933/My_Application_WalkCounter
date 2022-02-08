package com.example.my_application_walkcounter;

public class RNWalkCounterModule implements StepListener {
    //private final ReactApplicationContext reactContext;
    private StepDetector simpleStepDetector;
    //private SensorManager sensorManager;
    //private Sensor accel;
    private int numSteps;


    /* public RNWalkCounterModule(StepDetector simpleStepDetector, int numSteps) {
        this.simpleStepDetector = simpleStepDetector;
        this.numSteps = numSteps;
    }
    */

    public String getName() {
        return "RNWalkCounter";
    }

    public void startCounter(){
        //Toast.makeText(getReactApplicationContext(),"Step Started",Toast.LENGTH_LONG).show();
        numSteps = 0;
        initStepCounter();
        //runStepCounter();

    }

    public void initStepCounter(){
        //sensorManager = (SensorManager) reactContext.getSystemService(SENSOR_SERVICE);
        //accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);
    }

    //public void runStepCounter(){
        //sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_FASTEST);
    //}



    /*public void onStepRunning(long newSteps){
        WritableMap params = Arguments.createMap();
        params.putString("steps", ""+newSteps);
        //this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                //.emit("onStepRunning",params);
    } */

    /*@ReactMethod
    public void stopCounter(){
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onAccuracyChanged(Sensor s, int i){}


     */

    /* public void onSensorChanged(Data sample){
            simpleStepDetector.updateAccel(sample.getTimestamp(),
                    sample.getAccx(), sample.getAccy(),
                    sample.getAccz());
                    }
     */

    @Override
    public void step(long timeNs) {
        numSteps++;
        //onStepRunning(numSteps);
    }
}

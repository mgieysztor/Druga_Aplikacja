package com.sdaacademy.zientara.rafal.drugaaplikacja;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.service.wallpaper.WallpaperService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sdacademy.gieysztor.michal.sensorcolor.MyEngine;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mMagneticField;
    private Sensor mGeoMagneticRotationVector;
    private Sensor mLight;

    public TextView logViewText;

    @BindView(R.id.parametres)
    public TextView parametresText;

//    @BindView(R.id.logView)
//    public TextView logViewText;

    @BindView(R.id.activity_main)
    public LinearLayout root;

    private float maxValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //parametresText = (TextView) findViewById(R.id.parametres); // to możemy pominąć dzięki ButterKnife
        logViewText = (TextView) findViewById(R.id.logView);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        mMagneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mGeoMagneticRotationVector = mSensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (mAccelerometer != null) {
//            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
//        }
//        if (mMagneticField != null) {
//            mSensorManager.registerListener(this,mMagneticField,SensorManager.SENSOR_DELAY_NORMAL);
//        }
        if (mGeoMagneticRotationVector != null) {
            mSensorManager.registerListener(this,mGeoMagneticRotationVector,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
//        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            float x = event.values[0];
//            float y = event.values[1];
//            float z = event.values[2];
//
//            String s = String.format("x = %f, y = %f, z = %f", x, y, z);
//            Log.d(getClass().getSimpleName(), s);
//            parametresText.setText(s);
//
//            maxValue = Math.max(maxValue, x);
//            maxValue = Math.max(maxValue, y);
//            maxValue = Math.max(maxValue, z);
//            x += maxValue;
//            y += maxValue;
//            z += maxValue;
//            int r = (int) (255 * x / (maxValue * 2));
//            int g = (int) (255 * y / (maxValue * 2));
//            int b = (int) (255 * z / (maxValue * 2));
//            Log.d(getClass().getSimpleName(), String.format("r = %d, g = %d, b = %d", r, g, b));
//            root.setBackgroundColor(Color.rgb(r, g, b));
//        }
        if (mySensor.getType()== Sensor.TYPE_MAGNETIC_FIELD){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            String sm = String.format("x = %f, y = %f, z = %f", x, y, z);
            Log.d(getClass().getSimpleName(), sm);
            parametresText.setText("Magnetic field:\n" + sm);


            maxValue = Math.max(maxValue, x);
            maxValue = Math.max(maxValue, y);
            maxValue = Math.max(maxValue, z);
            x += maxValue;
            y += maxValue;
            z += maxValue;
            int r = (int) (255 * x / (maxValue * 2));
            int g = (int) (255 * y / (maxValue * 2));
            int b = (int) (255 * z / (maxValue * 2));
            Log.d(getClass().getSimpleName(), String.format("r = %d, g = %d, b = %d", r, g, b));
            root.setBackgroundColor(Color.rgb(r, g, b));
        }

        if (mySensor.getType()== Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            String sgm = String.format("x = %f, y = %f, z = %f", x, y, z);
            Log.d(MyEngine.anyString(), sgm);
            parametresText.setText("\nGeoMagneticRotationVector:\n" + sgm);
            logViewText.setText(MyEngine.anyString());

            maxValue = Math.max(maxValue, x);
            maxValue = Math.max(maxValue, y);
            maxValue = Math.max(maxValue, z);
            x += maxValue;
            y += maxValue;
            z += maxValue;
            int r = (int) (255 * x / (maxValue * 2));
            int g = (int) (255 * y / (maxValue * 2));
            int b = (int) (255 * z / (maxValue * 2));
            Log.d(getClass().getSimpleName(), String.format("r = %d, g = %d, b = %d", r, g, b));
            root.setBackgroundColor(Color.rgb(r, g, b));

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}

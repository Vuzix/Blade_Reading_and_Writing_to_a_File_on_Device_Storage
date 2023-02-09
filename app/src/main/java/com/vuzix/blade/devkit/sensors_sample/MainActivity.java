package com.vuzix.blade.devkit.sensors_sample;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.vuzix.hud.actionmenu.ActionMenuActivity;

import java.util.List;

/**
 * Main Activity that extend ActionMenuActivity.
 * This main class provide the basic information acquiring data from the device sensors.
 * For more information please reference:
 * https://developer.android.com/guide/topics/sensors/sensors_overview
 * Used Android API Classes:
 * https://developer.android.com/reference/android/hardware/SensorManager
 * https://developer.android.com/reference/android/hardware/SensorEvent
 * https://developer.android.com/reference/android/hardware/SensorEventListener
 */
public class MainActivity extends ActionMenuActivity implements SensorEventListener {

    private final String TAG = "VuzixBDK-Sensors_Sample";
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*

        The SensorManager provides information about on device sensors.

        Implementing the MySensorEventListener and overriding the MySensorEventListener.onSensorChanged method, updates to sensors and sensor values can be handled.

        For more information on the SensorManager, MySensorEventListener, Sensor, or SensorEvent classes, please see https://developer.android.com/reference/android/hardware/SensorManager

         */
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        listDevices();

    }


    //Registration of Event Listeners should be done here for performance.
    @Override
    protected void onResume() {
        super.onResume();

        //KNOWN SENSOR Available

        // register this class as a listener for the gyroscope sensor
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_NORMAL);

        // register this class as a listener for the gyroscope sensor
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);


        // register this class as a listener for the gyroscope sensor
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);

        // register this class as a listener for the gyroscope sensor
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);

        // register this class as a listener for the gyroscope sensor
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
                SensorManager.SENSOR_DELAY_NORMAL);
    }


    //Remember to always remove the listeners that you don't need to reduce leakage and battery usage
    @Override
    protected void onPause() {
        super.onPause();

        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    /**
     * Simply get a list of sensor from the SensorManager and loop over the list, logging basic information about each sensor
     */
    private void listDevices() {

        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);


        for (Sensor sensor : sensorList) {
            Log.d(TAG, "====================================================================================================");
            Log.d(TAG, "Name: \t\t\t\t" + sensor.getName());
            Log.d(TAG, "Vendor: \t\t\t" + sensor.getVendor());
            Log.d(TAG, "Type: \t\t\t\t" + sensor.getStringType());
            Log.d(TAG, "Maximum Range: \t\t" + Float.toString(sensor.getMaximumRange()));
            Log.d(TAG, "Resolution: \t\t" + Float.toString(sensor.getResolution()));
            Log.d(TAG, "Power: \t\t\t\t" + Float.toString(sensor.getPower()));
            Log.d(TAG, "Minimum Display: \t" + Integer.toString(sensor.getMinDelay()));
        }

    }

    /*

    When information about the gyroscope changes, log the new values

     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Log.d(TAG, sensorEvent.sensor.toString());

        switch (sensorEvent.sensor.getStringType()){

            case Sensor.STRING_TYPE_ACCELEROMETER:
                ((TextView)findViewById(R.id.sensor_accel_data_textView)).setText(String.valueOf(sensorEvent.values[0]));
                Log.d(TAG, "Acceleration minus Gx on the x-axis: " + sensorEvent.values[0]);

                if (sensorEvent.values.length > 1)
                {
                    Log.d(TAG, "Acceleration minus Gy on the y-axis: " + sensorEvent.values[1]);
                    Log.d(TAG, "Acceleration minus Gz on the z-axis: " + sensorEvent.values[2]);
                    ((TextView)findViewById(R.id.sensor_accel_data_textView2)).setText(String.valueOf(sensorEvent.values[1]));
                    ((TextView)findViewById(R.id.sensor_accel_data_textView3)).setText(String.valueOf(sensorEvent.values[2]));
                }
                break;
            case Sensor.STRING_TYPE_MAGNETIC_FIELD:
                ((TextView)findViewById(R.id.sensor_mag_data_textView)).setText(String.valueOf(sensorEvent.values[0]));
                Log.d(TAG, "Magnetometer Data: " + sensorEvent.values[0]);

                if (sensorEvent.values.length > 1)
                {
                    Log.d(TAG, "Magnetometer Data2: " + sensorEvent.values[1]);
                    Log.d(TAG, "Magnetometer Data:3 " + sensorEvent.values[2]);
                    ((TextView)findViewById(R.id.sensor_mag_data_textView2)).setText(String.valueOf(sensorEvent.values[1]));
                    ((TextView)findViewById(R.id.sensor_mag_data_textView3)).setText(String.valueOf(sensorEvent.values[2]));
                }
                break;
            case Sensor.STRING_TYPE_GYROSCOPE:
                ((TextView)findViewById(R.id.sensor_gyro_data_textView)).setText(String.valueOf(sensorEvent.values[0]));
                Log.d(TAG, "GYROSCOPE minus Gx on the x-axis: " + sensorEvent.values[0]);

                if (sensorEvent.values.length > 1)
                {
                    Log.d(TAG, "GYROSCOPE minus Gy on the y-axis: " + sensorEvent.values[1]);
                    Log.d(TAG, "GYROSCOPE minus Gz on the z-axis: " + sensorEvent.values[2]);
                    ((TextView)findViewById(R.id.sensor_gyro_data_textView2)).setText(String.valueOf(sensorEvent.values[1]));
                    ((TextView)findViewById(R.id.sensor_gyro_data_textView3)).setText(String.valueOf(sensorEvent.values[2]));
                }
                break;
            case Sensor.STRING_TYPE_LIGHT:
                ((TextView)findViewById(R.id.sensor_light_data_textView)).setText(String.valueOf(sensorEvent.values[0]));
                Log.d(TAG, "Ambient Light Data: " + sensorEvent.values[0]);

                break;
            case Sensor.STRING_TYPE_PRESSURE:
                ((TextView)findViewById(R.id.sensor_press_data_textView)).setText(String.valueOf(sensorEvent.values[0]));
                Log.d(TAG, "Pressure/Barometer Data: " + sensorEvent.values[0]);

                break;

        }

    }

    /*

    This is a simple example of logging accuracy changes to a sensor

     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.d(TAG, sensor.toString());
    }
}

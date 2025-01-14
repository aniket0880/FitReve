package com.uilover.project1932.service;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.content.pm.ServiceInfo;

import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.uilover.project1932.callback.stepsCallback;
import com.uilover.project1932.helper.GeneralHelper;
import com.uilover.project1932.helper.PrefsHelper;

public class StepDetectorService extends Service implements SensorEventListener {

    private static stepsCallback callback;
    private static final String CHANNEL_ID = "step_detector_channel"; // Notification channel ID
    private static final int NOTIFICATION_ID = 1; // Notification ID

    @SuppressLint("ForegroundServiceType")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Create the notification channel for Android 8.0+ (API 26+)
        createNotificationChannel();

        // Get the SensorManager system service
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (countSensor != null) {
            Toast.makeText(this, "Step Detecting Start", Toast.LENGTH_SHORT).show();
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_NORMAL);

            // Use startForeground with a specific type for Android 12+ (API 31+)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                startForegroundServiceWithType();
            } else {
                startForeground(NOTIFICATION_ID, createNotification()); // Fallback for older Android versions
            }

            // Update notification with step count and call the callback
            GeneralHelper.updateNotification(this, this, PrefsHelper.getInt("FSteps"));
            if (callback != null) {
                callback.subscribeSteps(PrefsHelper.getInt("FSteps"));
            }

        } else {
            Toast.makeText(this, "Sensor Not Detected", Toast.LENGTH_SHORT).show();
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!PrefsHelper.getString("TodayDate").equals(GeneralHelper.getTodayDate())) {
            PrefsHelper.putInt("Steps", Math.round(event.values[0]));
            PrefsHelper.putString("TodayDate", GeneralHelper.getTodayDate());
        } else {
            int storeSteps = PrefsHelper.getInt("Steps");
            int sensorSteps = Math.round(event.values[0]);
            int finalSteps = sensorSteps - storeSteps;
            if (finalSteps > 0) {
                PrefsHelper.putInt("FSteps", finalSteps);
                GeneralHelper.updateNotification(this, this, finalSteps);
                if (callback != null) {
                    callback.subscribeSteps(finalSteps);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d("SERVICE", sensor.toString());
    }

    // Method to create a notification for foreground service
    private Notification createNotification() {
        // Create a basic notification for the step detector
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Step Detector Service")
                .setContentText("Tracking your steps...")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();
    }

    // Method to start the service in the foreground with a specific type (for Android 12 and above)
    @SuppressLint("ForegroundServiceType")
    private void startForegroundServiceWithType() {
        Notification notification = createNotification();
        startForeground(NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_HEALTH);
    }

    // Create notification channel for Android 8.0+ (API 26+)
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Step Detector Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Channel for Step Detector Service");
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    public static class Subscribe {
        public static void register(Activity activity) {
            callback = (stepsCallback) activity;
        }
    }
}

package leo.test.com.accelerometersensortest;

import android.app.SearchManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager!=null){
            sensorManager.unregisterListener(listener);
        }
    }

    private SensorEventListener listener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //加速度可能是负值，所以要取他们的绝对值
            float xValue=Math.abs(event.values[0]);
            float yValue=Math.abs(event.values[0]);
            float zValue=Math.abs(event.values[2]);

            if (xValue>15||yValue>15||zValue>15){
                Toast.makeText(MainActivity.this,"摇个篮子啊",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


}

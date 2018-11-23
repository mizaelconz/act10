import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text);

        //Creando la instancia
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //Listando los sensores
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        String s = "";
        if(deviceSensors != null){
            for(int i = 0; i<deviceSensors.size(); i++){
                Log.wtf("Listando Senores", "Nombre: " + deviceSensors.get(i).getName());
                s += deviceSensors.get(i).getName()+ " ==";
            }
        }

        if (mSensor == null){
            //Usando el acelerometro
            if(mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
                mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            }
            else{
                //No hay acelerometro en el dispositivo
                textView.setText("No tiene acelerometro");
            }
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        Log.wtf("onSensorCHhange", "" + sensorEvent.values[0]);
        textView.setText("X: " + sensorEvent.values[0] + "  Y:  " + sensorEvent.values[0] + "  Z:  " + sensorEvent.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
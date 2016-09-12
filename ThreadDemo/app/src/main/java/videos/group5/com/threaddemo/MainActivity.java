package videos.group5.com.threaddemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends Activity {

    ExecutorService threadpool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        threadpool = Executors.newFixedThreadPool(4);

        threadpool.execute(new DoWork());

        //Thread thread = new Thread(new DoWork());
        //thread.start();
    }

    class DoWork implements Runnable{

        @Override
        public void run() {
            for(int i =0; i<10000;i++){
                for (int j=0;j<10000;j++){

                }
            }
        }
    }
}

package videos.group5.com.introtohandler;

import android.app.ProgressDialog;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Computing Progress");
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        handler = new Handler(Handler.Callback(){
            public boolean handlerMessage(Message msg){

                switch (msg.what){
                    case DoWork.STATUS_START:
                        progressDialog.show();
                        break;
                    case DoWork.STATUS_STEP:
                        progressDialog.setProgress(msg.getData().getInt("PROGRESS"));
                        //progressDialog.setProgress((Integer)msg.obj);
                        break;
                    case DoWork.STATUS_DONE:
                        progressDialog.dismiss();
                        break;
                }
                return false;
            }
        });

        new Thread(new DoWork()).start();
    }

    class DoWork implements Runnable{

        static final int STATUS_START = 0x00;
        static final int STATUS_STEP = 0x01;
        static final int STATUS_DONE = 0x02;

        @Override
        public void run() {
            Message msg = new Message();
            msg.what = STATUS_START;
            handler.sendMessage(msg);
            for (int i =0;i<10000;i++){
                for ( int j=0;j<10000;j++){

                }
                msg = new Message();
                msg.what = STATUS_STEP;
                //msg.obj = i+1;

                Bundle bundle = new Bundle();
                bundle.putInt("PROGRESS",i+1);
                msg.setData(bundle);

                handler.sendMessage(msg);
            }
            msg = new Message();
            msg.what = STATUS_DONE;
            handler.sendMessage(msg);
        }
    }
}

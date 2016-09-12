package mad.pawan.videos.intentsexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    final static String NAME_KEY = "NAME";
    final static String AGE = "AGE";
    final static String USER_KEY = "USER";
    final static String PERSON_KEY = "PERSON";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_nextpage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(MainActivity.this, Main2Activity.class);
                //Intent inte = new Intent("mad.pawan.videos.intentsexample.intent.action.VIEW");
                inte.putExtra(NAME_KEY,"Pawan Araballi");
                inte.putExtra(AGE,(double)24.5);
                Person per = new Person("Pawan","address",23);
                inte.putExtra(PERSON_KEY,per);
                User usr = new User("Pawan",26.8);
                inte.putExtra(USER_KEY,usr);
                //inte.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(inte);
            }
        });
    }
}

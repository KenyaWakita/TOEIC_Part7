package murota.kenyawakita.toeic_part7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start_button = (Button)findViewById(R.id.start_button);

            start_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // ここに処理を記述する
                Intent intent = new Intent(MainActivity.this, Select_Topic_Activity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}

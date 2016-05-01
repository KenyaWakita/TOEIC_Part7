package murota.kenyawakita.toeic_part7;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class Result_Activity extends AppCompatActivity {

    private TextView countText;					//テキストビュー

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        countText = (TextView) findViewById(R.id.result);

        //インテントでかかった時間の受け取り
        Intent i = getIntent();
        String time = i.getStringExtra("time");
        countText.setText("結果は" +time+ "秒かかりました" );

    }


}

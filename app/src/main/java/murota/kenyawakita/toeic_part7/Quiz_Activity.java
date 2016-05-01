package murota.kenyawakita.toeic_part7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class Quiz_Activity extends AppCompatActivity {

    private Timer mainTimer;					//タイマー用
    private MainTimerTask mainTimerTask;		//タイマタスククラス
    private TextView countText;					//テキストビュー
    private int count = 0;						//カウント
    private Handler mHandler = new Handler();   //UI Threadへのpost用ハンドラ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final Button timestart_button = (Button)findViewById(R.id.timestart_button);
        Button timestop_button = (Button)findViewById(R.id.timestop_button);

        timestart_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // ここに処理を記述する
                //タイマーインスタンス生成
                Quiz_Activity.this.mainTimer = new Timer();
                //タスククラスインスタンス生成
                Quiz_Activity.this.mainTimerTask = new MainTimerTask();
                //タイマースケジュール設定＆開始
                Quiz_Activity.this.mainTimer.schedule(mainTimerTask, 1000,1000);
                //テキストビュー
                Quiz_Activity.this.countText = (TextView)findViewById(R.id.count_text);

                timestart_button.setEnabled(false);

            }
        });

        timestop_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // ここに処理を記述する
                Intent intent = new Intent(Quiz_Activity.this, Result_Activity.class);
                intent.putExtra("time",countText.getText().toString());
                startActivityForResult(intent, 0);
                Quiz_Activity.this.finish();
            }
        });



    }


    /**
     * タイマータスク派生クラス
     * run()に定周期で処理したい内容を記述
     *
     */
    public class MainTimerTask extends TimerTask {
        @Override
        public void run() {
            //ここに定周期で実行したい処理を記述します
            mHandler.post( new Runnable() {
                public void run() {

                    //実行間隔分を加算処理
                    count += 1;
                    //画面にカウントを表示
                    countText.setText(String.valueOf(count));
                }
            });
        }
    }

}

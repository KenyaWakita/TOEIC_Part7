package murota.kenyawakita.toeic_part7;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;


public class Quiz_Activity extends AppCompatActivity {

    private Timer mainTimer;					//タイマー用
    private MainTimerTask mainTimerTask;		//タイマタスククラス
    private TextView countText;					//テキストビュー
    private int count = 0;						//カウント
    private Handler mHandler = new Handler();   //UI Threadへのpost用ハンドラ
    String filename;
    int word_count = 0;                         //文字数


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //intentを生成して，表示したい問題画像のファイル名を受け取る
        Intent i=getIntent();
        filename = i.getStringExtra("filename");

        final Button timestart_button = (Button)findViewById(R.id.timestart_button);
        Button timestop_button = (Button)findViewById(R.id.timestop_button);
//        ImageView quiz_view = (ImageView)findViewById(R.id.quiz_view);
//        //assetsフォルダから問題文画像を取得する
//        Bitmap bitmapDrawable = Filename_To_BitmapDrawable(filename, this);
//        quiz_view.setImageBitmap(bitmapDrawable);

        TextView quiz_text = (TextView)findViewById(R.id.quiz_text);

        InputStream is = null;
        BufferedReader br = null;
        String text = "";

        try {
            try {
                // assetsフォルダ内の sample.txt をオープンする
                is = this.getAssets().open("quiz1.txt");
                br = new BufferedReader(new InputStreamReader(is));

                // １行ずつ読み込み、改行を付加する
                String str;
                while ((str = br.readLine()) != null) {
                    text += str + "\n" +"\n" +"　";
                }
            } finally {
                if (is != null) is.close();
                if (br != null) br.close();
            }
        } catch (Exception e){
            // エラー発生時の処理
        }

        // 読み込んだ文字列を Textview に設定し、画面に表示する
        quiz_text.setText(text);
        //word数を計算する．結果を変数countに代入
        int s = 0;
        while(s < text.length()){
            char one = text.charAt(s);
            if(one == ' '||one=='　') {
                word_count++;
            }
            s++;
        }

        Log.d("count", String.valueOf(word_count));


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
                double w_m = 60*word_count/(Integer.parseInt(countText.getText().toString()));

                Log.d("time", String.valueOf((Integer.parseInt(countText.getText().toString()))));
                Log.d("word_count", String.valueOf(word_count));
                Log.d("wm", String.valueOf(w_m));

                intent.putExtra("words/minute",String.valueOf(w_m));
                startActivityForResult(intent, 0);
                Quiz_Activity.this.finish();
            }
        });
    }

    //ファイルの名前からbitmapdrawableを出力する関数
    public static Bitmap Filename_To_BitmapDrawable(String filename, Activity activity){

        InputStream image = null;
        try {
            image = activity.getAssets().open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = null;
        bitmap = BitmapFactory.decodeStream(image);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(activity.getResources(), bitmap);

        return  bitmap;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_description, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //トップボタンを押した時の動作
        if (id==R.id.top){
            Intent intent = new Intent(Quiz_Activity.this, MainActivity.class);
            startActivityForResult(intent, 0);
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

}

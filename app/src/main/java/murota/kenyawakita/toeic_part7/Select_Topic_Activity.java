package murota.kenyawakita.toeic_part7;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Select_Topic_Activity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Button topic1 = (Button)findViewById(R.id.topic1);
        topic1.setOnClickListener(this);

        Button topic2 = (Button)findViewById(R.id.topic2);
        topic2.setOnClickListener(this);

        Button topic3 = (Button)findViewById(R.id.topic3);
        topic3.setOnClickListener(this);

        Button topic4 = (Button)findViewById(R.id.topic4);
        topic4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(Select_Topic_Activity.this, Quiz_Activity.class);
        switch (v.getId()){
            case R.id.topic1:
                intent.putExtra("filename","mondai1.png");
                break;
            case R.id.topic2:
                intent.putExtra("filename","top.png");
                break;
            case R.id.topic3:
                intent.putExtra("filename","mondai1.png");
                break;
            case R.id.topic4:
                intent.putExtra("filename","top.png");
                break;


            default:
                break;
        }

        startActivityForResult(intent, 0);
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
            Intent intent = new Intent(Select_Topic_Activity.this, MainActivity.class);
            startActivityForResult(intent, 0);
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

}

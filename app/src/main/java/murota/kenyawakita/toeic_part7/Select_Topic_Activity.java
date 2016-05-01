package murota.kenyawakita.toeic_part7;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Select_Topic_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        ImageView topic1 = (ImageView)findViewById(R.id.topic1);
        topic1.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(Select_Topic_Activity.this, Quiz_Activity.class);
                        startActivityForResult(intent, 0);
                    }
                }
        );


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

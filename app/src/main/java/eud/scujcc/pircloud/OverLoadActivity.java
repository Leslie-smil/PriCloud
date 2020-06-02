package eud.scujcc.pircloud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OverLoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_load);

        TextView load=findViewById(R.id.load) ;
        TextView upload=findViewById(R.id.upload);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OverLoadActivity.this, LoadActivity.class);
                startActivity(intent);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OverLoadActivity.this,UpLoadActivity.class);
                startActivity(intent);
            }
        });
    }
}

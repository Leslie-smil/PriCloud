package eud.scujcc.pircloud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class contentAdapter extends RecyclerView.Adapter<contentAdapter.contentRowHolder> {
    private FolderLab lab ;
    private Context context;
    private ContentClickListener listener;

    public contentAdapter(Context context){
        this.context =  context;
    }



    @NonNull
    @Override
    public contentRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_row,parent,false);
        contentRowHolder holder = new contentRowHolder(rowView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull contentRowHolder holder, int position) {
        File f = lab.getFile(position);
        holder.bind(f);
    }

    @Override
    public int getItemCount() {
        return 2;
//        return lab.getSize();
    }


    //自定义新接口
    public interface ContentClickListener {
        public void onContentClick(int position);
    }


    /**
     * 单行布局对应的Java控制类
     */
    public class contentRowHolder extends RecyclerView.ViewHolder{
        private TextView name;


        public contentRowHolder(@NonNull View row) {
            super(row);
            this.name = row.findViewById(R.id.folder_name);
            row.setOnClickListener((v) -> {
                int position = getLayoutPosition();
                Log.d("PirCloud", position + "行被点击啦！");
                listener.onContentClick(position);
            });
        }

        public void bind(File f){
            this.name.setText(f.getBucketName());
        }

    }




}

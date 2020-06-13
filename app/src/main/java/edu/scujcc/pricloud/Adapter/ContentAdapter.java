package edu.scujcc.pricloud.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.scujcc.pricloud.Lab.FolderLab;
import edu.scujcc.pricloud.Model.MyOSSFile;
import edu.scujcc.pricloud.R;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.contentRowHolder> {
    private FolderLab lab = FolderLab.getInstance() ;
    private Context context;
    private ContentClickListener listener;

    public ContentAdapter(ContentClickListener lis, Context context) {
        this.listener= lis;
        this.context =  context;
    }



    @NonNull
    @Override
    public contentRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_row,parent,false);
        return new contentRowHolder(rowView);
    }

    /**
     * 使用新继承的方法
     * @param holder
     * @param position
     */
//    @Override
//    public void whenBindViewHolder(contentRowHolder holder, int position) {
//        MyOSSFile f = lab.getFile(position);
//        holder.bind(f);
//    }
    @Override
    public void onBindViewHolder(@NonNull contentRowHolder holder, int position) {
        MyOSSFile f = lab.getFile(position);
        holder.bind(f);
    }

    @Override
    public int getItemCount() {
//        return 2;
        return lab.getSize();
    }


    //自定义新接口
    public interface ContentClickListener {
         void onContentClick(int position);
    }


    /**
     * 单行布局对应的Java控制类
     */
    public class contentRowHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView cover1;

        public contentRowHolder(@NonNull View row) {
            super(row);
            this.name = row.findViewById(R.id.folder_name);
            this.cover1 = row.findViewById(R.id.image1);
            row.setOnClickListener((v) -> {
                int position = getLayoutPosition();
                Log.d("PirCloud", position + "行被点击啦！");
                listener.onContentClick(position);
            });
        }

        public void bind(MyOSSFile f) {
            this.name.setText(f.getKey());
        }

    }




}

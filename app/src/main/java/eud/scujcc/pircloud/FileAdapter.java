//package eud.scujcc.pircloud;
//
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileRowHolder> {
//   private final static String TAG="PirCloud";
//
//    public FileAdapter(LoadActivity loadActivity, Object o) {
//    }
//
//    @NonNull
//    @Override
//    public FileRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull FileRowHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class FileRowHolder extends RecyclerView.ViewHolder {
//        private TextView fileName;
//        private TextView fileSize;
//        private ImageView cover;
//
//        public FileRowHolder(@NonNull View itemView) {
//            super(itemView);
//            this.fileName=itemView.findViewById(R.id.file_name);
//            this.fileSize=itemView.findViewById(R.id.file_size);
//            this.cover=itemView.findViewById(R.id.image1);
//            itemView.setOnClickListener((v)->{
//                int position =getLayoutPosition();
//                Log.d(TAG, position+"被点击了");
//            });
//
//
//        }
//    }
//
//}

package eud.scujcc.pircloud;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment{
    private RecyclerView contenRv;
    private contentAdapter contentAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container,false);
//        contenRv = (RecyclerView) view.findViewById(R.id.content_rv);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        contenRv.setLayoutManager(layoutManager);
//
        return view;

    }

}

package idv.jason.popupwindow.dismiss;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by jason on 2018/2/2.
 */

public class ActionbarFragment extends Fragment
                               implements View.OnClickListener {

    private Button btnBack;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actionbar, container, false);
        btnBack = view.findViewById(R.id.btn_back);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        btnBack.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        btnBack.setOnClickListener(null);

        super.onPause();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_back) {
            Log.d("TAG", "Back");
        }
    }

}

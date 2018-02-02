package idv.jason.fragment.popbackstack;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jason on 2018/2/1.
 */

public class ContentFragment extends Fragment {

    private ConstraintLayout layout;

    private TextView tvNum;
    private TextView tvName;


    public static ContentFragment getInstance(int num, String name) {
        Bundle data = new Bundle();
        data.putInt("Num", num);
        data.putString("Name", name);

        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(data);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        layout = view.findViewById(R.id.layout);

        tvNum = view.findViewById(R.id.tv_num);
        tvName = view.findViewById(R.id.tv_name);

        int num = getArguments().getInt("Num");
        String name = "Stack Name: " + (getArguments().getString("Name") == null ? "Null" : getArguments().getString("Name"));
        tvNum.setText(String.valueOf(num));
        tvName.setText(name);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ConstraintSet applyConstraintSet = new ConstraintSet();
        applyConstraintSet.clone(layout);
        applyConstraintSet.setMargin(R.id.tv_num, ConstraintSet.BOTTOM, (int) (getArguments().getInt("Num") * 30 * getResources().getDisplayMetrics().density));
        applyConstraintSet.applyTo(layout);
    }

}

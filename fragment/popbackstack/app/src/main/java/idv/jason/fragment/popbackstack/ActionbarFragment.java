package idv.jason.fragment.popbackstack;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jason on 2018/2/1.
 */

public class ActionbarFragment extends Fragment
                               implements View.OnClickListener {

    private Button btnBack;
    private Button btnAdd;

    private EditText etBackStackName;
    private EditText etAddStackName;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actionbar, container, false);
        btnBack = view.findViewById(R.id.btn_back);
        btnAdd = view.findViewById(R.id.btn_add);

        etBackStackName = view.findViewById(R.id.et_back_stack_name);
        etAddStackName = view.findViewById(R.id.et_add_stack_name);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        btnBack.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        btnBack.setOnClickListener(null);
        btnAdd.setOnClickListener(null);

        super.onPause();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_back) {
            String stackName = etBackStackName.getText().length() <= 0 ? null : etBackStackName.getText().toString();
            if (null == stackName) {
                getFragmentManager().popBackStack();
            } else {
                getFragmentManager().popBackStack(stackName, FragmentManager.POP_BACK_STACK_INCLUSIVE); // 名子符合(包含)以上全部移除，null 全部移除
//                getFragmentManager().popBackStack(stackName, 0); // 名子符合以上全部移除，null 等同 getFragmentManager().popBackStack()
            }
        } else if (view.getId() == R.id.btn_add) {
            int num = getFragmentManager().getBackStackEntryCount() + 1;
            String stackName = etAddStackName.getText().length() <= 0 ? null : etAddStackName.getText().toString();
            getFragmentManager().beginTransaction().add(R.id.layout_content, ContentFragment.getInstance(num, stackName)).addToBackStack(stackName).commit();
        }
    }

}

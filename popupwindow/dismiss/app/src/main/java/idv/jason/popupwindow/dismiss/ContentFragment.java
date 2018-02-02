package idv.jason.popupwindow.dismiss;

import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2018/2/2.
 */

public class ContentFragment extends Fragment
                             implements View.OnClickListener {

    private PopupWindow popupWindow;

    private Button btnField1;
    private Button btnVisible;
    private Button btnBg;

    private EditText viewVisible;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        btnField1 = view.findViewById(R.id.btn_field_1);
        btnVisible = view.findViewById(R.id.btn_visible);
        btnBg = view.findViewById(R.id.btn_bg);

        viewVisible = view.findViewById(R.id.view_visible);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        btnField1.setOnClickListener(this);
        btnVisible.setOnClickListener(this);
        btnBg.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        btnField1.setOnClickListener(null);
        btnVisible.setOnClickListener(null);
        btnBg.setOnClickListener(null);

        super.onPause();
    }

    @Override
    public void onDestroyView() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            popupWindow = null;
        }

        super.onDestroyView();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_field_1) {
            if (null != popupWindow) {
                popupWindow.dismiss();
                popupWindow = null;
            } else {
                List<String> items = new ArrayList<>();
                for(int i = 0 ; i < 5 ; i++) {
                    items.add("Item " + i);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View parent = inflater.inflate(R.layout.popupwindow_content,null);
                ViewGroup viewGroup = parent.findViewById(R.id.layout_popupwindow);
                viewGroup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("TAG", "POPUP BG Click");
                        if (null != popupWindow) {
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                    }
                });

                ListView lv = parent.findViewById(R.id.lv);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("TAG", "Item Click " + i);
                        if (null != popupWindow) {
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                    }
                });

                popupWindow = new PopupWindow(getActivity());
                popupWindow.setContentView(parent);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x44000000));
                popupWindow.setFocusable(false);
                popupWindow.setOutsideTouchable(false);
                popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
                popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                popupWindow.showAsDropDown(view);
            }
        } else if (view.getId() == R.id.btn_visible) {
            if (null != popupWindow) {
                popupWindow.dismiss();
                popupWindow = null;
            }
            viewVisible.setVisibility(viewVisible.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        } else if (view.getId() == R.id.btn_bg) {
            Log.d("TAG", "BG Click");
        }
    }

}

package idv.jason.popupwindow.dismiss;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (null == getFragmentManager().findFragmentByTag("ACTIONBAR")) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.layout_actionbar, new ActionbarFragment(), "ACTIONBAR");
            ft.add(R.id.layout_content, new ContentFragment());
            ft.commit();
        }
    }

}

package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public String APP_VER = "2 fast fingers v1.0";
    public String sideBarText;

    public void setSideBarText(String txt) {
        this.sideBarText = txt;
    }
}

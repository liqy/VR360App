package tv.tipsee.vr;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tv.tipsee.vr.models.RootData;
import tv.tipsee.vr.models.VRVideo;
import tv.tipsee.vr.network.RestClient;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}

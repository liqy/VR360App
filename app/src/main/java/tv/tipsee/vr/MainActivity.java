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

        Call<RootData<List<VRVideo>>> list = RestClient.getClient().getVideoList();

        list.enqueue(new Callback<RootData<List<VRVideo>>>() {
            @Override
            public void onResponse(Call<RootData<List<VRVideo>>> call, Response<RootData<List<VRVideo>>> response) {

                RootData<List<VRVideo>> data = response.body();

            }

            @Override
            public void onFailure(Call<RootData<List<VRVideo>>> call, Throwable t) {

            }
        });

    }
}

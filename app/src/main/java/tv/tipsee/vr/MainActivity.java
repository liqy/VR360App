package tv.tipsee.vr;

import android.os.Bundle;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tv.tipsee.vr.models.RootData;
import tv.tipsee.vr.models.VRVideo;
import tv.tipsee.vr.network.TipSeeService;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://video.tipsee.tv/api/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        TipSeeService service = retrofit.create(TipSeeService.class);

        Call<RootData<List<VRVideo>>> list = service.getVideoList();

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

package tv.tipsee.vr;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tv.tipsee.vr.models.RootData;
import tv.tipsee.vr.models.VRVideo;
import tv.tipsee.vr.network.RestClient;
import tv.tipsee.vr.player.MD360PlayerActivity;
import tv.tipsee.vr.utils.RecyclerUtils;
import tv.tipsee.vr.views.adapters.VRVideoAdapter;

public class VRVideoListActivity extends BaseActivity implements XRecyclerView.LoadingListener {

    private XRecyclerView recyclerView;
    private VRVideoAdapter vrVideoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_video_list);

        initView();

        loadVrVideoList();

    }

    private void initView() {

        recyclerView = (XRecyclerView) findViewById(R.id.vr_video_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);

        vrVideoAdapter = new VRVideoAdapter(this);

        recyclerView.setAdapter(vrVideoAdapter);
        recyclerView.setLoadingListener(this);

        recyclerView.setLoadingMoreEnabled(false);

        recyclerView.addOnItemTouchListener(new RecyclerUtils.RecyclerItemClickListener(this,
                new RecyclerUtils.RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        VRVideo vrVideo = vrVideoAdapter.getItem(position);
                        if (TextUtils.isEmpty(vrVideo.file)){

                        }else {
                            MD360PlayerActivity.startVideo(VRVideoListActivity.this, Uri.parse(vrVideo.file));
                        }
                    }
                }));

    }

    private void loadVrVideoList() {

        Call<RootData<List<VRVideo>>> list = RestClient.getClient().getVideoList();

        list.enqueue(new Callback<RootData<List<VRVideo>>>() {
            @Override
            public void onResponse(Call<RootData<List<VRVideo>>> call, Response<RootData<List<VRVideo>>> response) {

                RootData<List<VRVideo>> data = response.body();

                if (data != null && data.isValueOk()) {
                    vrVideoAdapter.addVrVideoList(data.value);
                }
                recyclerView.refreshComplete();
            }

            @Override
            public void onFailure(Call<RootData<List<VRVideo>>> call, Throwable t) {
                recyclerView.refreshComplete();
            }
        });
    }

    @Override
    public void onRefresh() {
//        loadVrVideoList();
        recyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
    }

}

package com.qhsoft.killrecord.module;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.model.local.MenuInfo;
import com.qhsoft.killrecord.model.remote.bean.ApkInfoBean;
import com.qhsoft.killrecord.model.remote.service.PasscheckService;
import com.qhsoft.killrecord.module.baseinfo.buyer.BuyerListActivity;
import com.qhsoft.killrecord.module.baseinfo.good.GoodListActivity;
import com.qhsoft.killrecord.module.baseinfo.killrange.KillRangeListActivity;
import com.qhsoft.killrecord.module.baseinfo.people.PeopleListActivity;
import com.qhsoft.killrecord.module.baseinfo.sharebrute.ShareBruteListActivity;
import com.qhsoft.killrecord.module.baseinfo.sharegood.GoodShareListActivity;
import com.qhsoft.killrecord.module.baseinfo.supply.SupplyListActivity;
import com.qhsoft.killrecord.module.inventory.handlerproduct.HandlerProductListActivity;
import com.qhsoft.killrecord.module.inventory.productstore.ProductStoreListActivity;
import com.qhsoft.killrecord.module.inventory.tobekill.TobeKillListActivity;
import com.qhsoft.killrecord.module.ledger.entryrecord.EntryRecordListActivity;
import com.qhsoft.killrecord.module.ledger.harmlesshandle.HarmlessHandleListActivity;
import com.qhsoft.killrecord.module.ledger.killcheck.KillCheckListActivity;
import com.qhsoft.killrecord.module.ledger.outrecoder.OutrecordListActivity;
import com.qhsoft.killrecord.module.ledger.tobekillcheck.TobekillCheckListActivity;
import com.qhsoft.killrecord.module.trace.livestock.LivestockListActivity;
import com.qhsoft.killrecord.module.trace.product.TraceProductListActivity;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.photo.util.AppUtil;
import com.qhsoft.killrecord.view.FlowLayout;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @Bind(R.id.ll_content)
    LinearLayout ll_content;
    @Bind(R.id.tv_app_name)
    TextView tvAppName;

    private int menuWidth;


    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        menuWidth = getResources().getDisplayMetrics().widthPixels / 4;
        initMenu(getInventoryList(), "即时库存");
        initMenu(getLedgerList(), "台账管理");
        initMenu(getTraceList(), "屠宰溯源");
        initMenu(getInfoList(), "基础信息");
        new RxPermissions(MainActivity.this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    requestData(HttpHandler.create(PasscheckService.class)
                            .checkAppVersion(AppUtil.getVersionName(MainActivity.this), getPackageName())
                    )
                            .subscribe(new TaskObserver<ApkInfoBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {


                                }

                                @Override
                                public void onSuccess(final ApkInfoBean value) {
                                    showToast(value.getMsg());
                                    //下载文件
                                    if (value.getAttributes() != null && value.getAttributes().getStreamPath() != null) {
                                        downloadFile(value.getAttributes().getStreamPath(), "KillRecord.apk");
                                    }


                                }

                                @Override
                                public void onFailure(FailureMessage failureMessage) {

                                }
                            });

                }
            }
        });
        //屠宰场
        try {
            ActivityInfo activityInfo = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            String value = activityInfo.metaData.getString("app_name");
            tvAppName.setText(value);


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }

    @OnClick(R.id.tv_loginout)
    public void clickLoginout() {
        finish();
    }

    private void initMenu(List<MenuInfo> list, String name) {
        View view = inflate(R.layout.layout_menu_item);
        TextView tv_name = view.findViewById(R.id.tv_name);
        final ImageView iv_tool = view.findViewById(R.id.iv_tool);
        final FlowLayout mFlowLayout = view.findViewById(R.id.mFlowLayout);
        tv_name.setText(name);

        iv_tool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFlowLayout.getVisibility() == View.VISIBLE) {
                    iv_tool.setImageResource(R.drawable.up);
                    mFlowLayout.setVisibility(View.GONE);
                } else {
                    mFlowLayout.setVisibility(View.VISIBLE);
                    iv_tool.setImageResource(R.drawable.down);
                }
            }
        });
        for (int i = 0; i < list.size(); i++) {
            final MenuInfo menuInfo = list.get(i);
            View item = inflate(R.layout.layout_menu_item_item);
            ImageView iv_pic = item.findViewById(R.id.iv_pic);
            TextView tv_title = item.findViewById(R.id.tv_title);
            iv_pic.setImageResource(menuInfo.getResId());
            tv_title.setText(menuInfo.getTitle());
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (menuInfo.getClazz() == null) {
                        showToast("未添加菜单");
                        return;
                    }
                    Intent intent = new Intent(MainActivity.this, menuInfo.getClazz());
                    intent.putExtra("title", menuInfo.getTitle());
                    startActivity(intent);
                }
            });
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(menuWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
            mFlowLayout.addView(item, params);


        }
        ll_content.addView(view);


    }

    /**
     * 即时库存
     *
     * @return
     */
    private List<MenuInfo> getInventoryList() {
        List<MenuInfo> list = new ArrayList<>();
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setClazz(TobeKillListActivity.class);
        menuInfo.setResId(R.drawable.daizaichuqin);
        menuInfo.setTitle("待宰畜禽");
        list.add(menuInfo);

        menuInfo = new MenuInfo();

        menuInfo.setResId(R.drawable.dongwuchanp);
        menuInfo.setClazz(ProductStoreListActivity.class);
        menuInfo.setTitle("产品库存");
        list.add(menuInfo);

        menuInfo = new MenuInfo();
        menuInfo.setClazz(HandlerProductListActivity.class);
        menuInfo.setResId(R.drawable.daichulichanpin);
        menuInfo.setTitle("待处理产品");
        list.add(menuInfo);

        return list;

    }

    /**
     * 台账管理
     */
    private List<MenuInfo> getLedgerList() {
        List<MenuInfo> list = new ArrayList<>();
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setClazz(EntryRecordListActivity.class);
        menuInfo.setResId(R.drawable.jinchangjilu);
        menuInfo.setTitle("进场记录");
        list.add(menuInfo);

        menuInfo = new MenuInfo();
        menuInfo.setClazz(TobekillCheckListActivity.class);
        menuInfo.setResId(R.drawable.daijianyanjilu);
        menuInfo.setTitle("待宰检验记录");
        list.add(menuInfo);

        menuInfo = new MenuInfo();
        menuInfo.setClazz(KillCheckListActivity.class);
        menuInfo.setResId(R.drawable.tucaijianyanjilu);
        menuInfo.setTitle("屠宰检验记录");
        list.add(menuInfo);

        menuInfo = new MenuInfo();
        menuInfo.setClazz(OutrecordListActivity.class);
        menuInfo.setResId(R.drawable.chuchangjilu);
        menuInfo.setTitle("出场记录");
        list.add(menuInfo);

        menuInfo = new MenuInfo();
        menuInfo.setClazz(HarmlessHandleListActivity.class);
        menuInfo.setResId(R.drawable.tucaijianyanjilu);
        menuInfo.setTitle("无害化处理记录");
        list.add(menuInfo);

        return list;

    }


    /**
     * 屠宰溯源
     *
     * @return
     */
    private List<MenuInfo> getTraceList() {
        List<MenuInfo> list = new ArrayList<>();
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setClazz(LivestockListActivity.class);
        menuInfo.setResId(R.drawable.yituzaichuqin);
        menuInfo.setTitle("已屠宰畜禽");
        list.add(menuInfo);

        menuInfo = new MenuInfo();
        menuInfo.setClazz(TraceProductListActivity.class);
        menuInfo.setResId(R.drawable.yichuchangchanpin);
        menuInfo.setTitle("已出场产品");
        list.add(menuInfo);


        return list;

    }

    /**
     * 基础信息
     */
    private List<MenuInfo> getInfoList() {
        List<MenuInfo> list = new ArrayList<>();
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setClazz(SupplyListActivity.class);
        menuInfo.setResId(R.drawable.ghs);
        menuInfo.setTitle("供货商");
        list.add(menuInfo);

        menuInfo = new MenuInfo();
        menuInfo.setClazz(BuyerListActivity.class);
        menuInfo.setResId(R.drawable.ghsd);
        menuInfo.setTitle("购货商");
        list.add(menuInfo);

        menuInfo = new MenuInfo();
        menuInfo.setClazz(PeopleListActivity.class);
        menuInfo.setResId(R.drawable.renyuanxinxi);
        menuInfo.setTitle("人员信息");
        list.add(menuInfo);


        menuInfo = new MenuInfo();
        menuInfo.setClazz(KillRangeListActivity.class);
        menuInfo.setResId(R.drawable.tuzaifanwei);
        menuInfo.setTitle("屠宰范围");
        list.add(menuInfo);

        menuInfo = new MenuInfo();
        menuInfo.setClazz(ShareBruteListActivity.class);
        menuInfo.setResId(R.drawable.gognxiangchuqinku);
        menuInfo.setTitle("共享畜禽库");
        list.add(menuInfo);


        menuInfo = new MenuInfo();
        menuInfo.setClazz(GoodListActivity.class);
        menuInfo.setResId(R.drawable.changpinxinxi);
        menuInfo.setTitle("商品信息");
        list.add(menuInfo);

        menuInfo = new MenuInfo();
        menuInfo.setClazz(GoodShareListActivity.class);
        menuInfo.setResId(R.drawable.shangpingongxiangku);
        menuInfo.setTitle("商品共享库");
        list.add(menuInfo);


        return list;

    }


}

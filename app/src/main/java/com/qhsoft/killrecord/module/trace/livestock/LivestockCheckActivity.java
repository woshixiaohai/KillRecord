package com.qhsoft.killrecord.module.trace.livestock;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.LivestockDetailBean;
import com.qhsoft.killrecord.model.remote.service.LivestockService;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.TimeUtil;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.qhsoft.killrecord.view.SimpleTable;

import java.util.List;

import butterknife.Bind;
import io.reactivex.disposables.Disposable;

public class LivestockCheckActivity extends BaseActivity {
    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;

    @Bind(R.id.tv_info)
    TextView tv_info;
    @Bind(R.id.tv_info_1)
    TextView tv_info_1;
    @Bind(R.id.tv_info_2)
    TextView tv_info_2;
    @Bind(R.id.tv_daizai)
    TextView tv_daizai;

    @Bind(R.id.ll_checklist)
    LinearLayout ll_checklist;
    @Bind(R.id.ll_kill_record)
    LinearLayout ll_kill_record;
    @Bind(R.id.ll_outrecord)
    LinearLayout ll_outrecord;
    @Bind(R.id.ll_harmless)
    LinearLayout ll_harmless;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_livestock_check;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle(getIntent().getStringExtra("title"));
        getDetail();

    }

    private void getDetail() {
        requestData(HttpHandler.create(LivestockService.class).getDetail(UserHelper.getSessionId(),
                getIntent().getStringExtra("id"))).subscribe(new TaskObserver<LivestockDetailBean>() {
            @Override
            public void onSuccess(LivestockDetailBean value) {
                if(!value.isSuccess()){
                    showToast(value.getMsg());
                    return;
                }
                if (value.getObj() != null) {
                    LivestockDetailBean.ObjBean objBean = value.getObj();
                    if (objBean.getApproachPage() != null) {
                        LivestockDetailBean.ObjBean.TBruteApprRecPageBean pageBean = objBean.getTBruteApprRecPage();
                        StringBuilder sb = new StringBuilder();
                        sb.append("进场编号：" + pageBean.getBillno() + "\n");
                        sb.append("进场时间:" + TimeUtil.formatSimpleDate(pageBean.getJcdate()) + "\n");
                        sb.append("畜禽名称:" + pageBean.getBruteName() + "\n");
                        sb.append("畜禽货主:" + pageBean.getSupplyname() + "\n");
                        sb.append("屠宰企业:" + objBean.getTBiCompanyName() + "\n");
                        sb.append("待宰圈号:" + pageBean.getCircleNo() + "\n");
                        if (pageBean.getType().equals("0")) {
                            sb.append("转入方式:进场转入\n");
                        } else if (pageBean.getType().equals("1")) {
                            sb.append("转入方式:待宰转入\n");
                        } else if (pageBean.getType().equals("2")) {
                            sb.append("转入方式:送宰转入\n");
                        }
                        sb.append("当前数量:" + pageBean.getInvenQty());

                        tv_info.setText(sb.toString().replaceAll("null",""));


                        if (objBean.getDaizai() != null) {

                            LivestockDetailBean.ObjBean.DaizaiBean daizaiBean = objBean.getDaizai();
                            sb = new StringBuilder();
                            if (pageBean.getType().equals("1")) {
                                sb.append("转入原因：待宰转入\n");
                            } else if (pageBean.getType().equals("2")) {
                                sb.append("转入原因:送宰转入\n");
                            }
                            sb.append("检验时间:" + TimeUtil.formatSimpleDate(daizaiBean.getCheckDate()) + "\n");
                            sb.append("检验单号:" + daizaiBean.getBillno() + "\n");
                            sb.append("检验员:" + daizaiBean.getCheckName() + "\n");
                            sb.append("转入数量:" + daizaiBean.getShiftQty() + "\n");
                            sb.append("缓宰原因:" + daizaiBean.getSlowNote() + "\n");
                            sb.append("转入前圈号:" + daizaiBean.getCircleNo() + "");
                            tv_info_1.setText(sb.toString().replaceAll("null",""));

                        } else {
                            tv_daizai.setVisibility(View.GONE);
                            tv_info_1.setVisibility(View.GONE);
                        }

                    }
                    if (objBean.getApproachPage() != null) {
                        LivestockDetailBean.ObjBean.ApproachPageBean approachPageBean = objBean.getApproachPage();
                        StringBuilder sb = new StringBuilder();

                        sb.append("畜禽产地:" + approachPageBean.getFprovinceName() + approachPageBean.getFcityName() + approachPageBean.getFcountyName() + "\n");
                        sb.append("动检编号:" + approachPageBean.getCertificateNo() + "\n");
                        sb.append("检验员:" + approachPageBean.getCoroner() + "\n");
                        sb.append("进场数量:" + (approachPageBean.getDieQty() + approachPageBean.getApproachQty()) + "\n");
                        sb.append("待宰前死亡数量:" + approachPageBean.getDieQty() + "\n");
                        sb.append("来源方:" + approachPageBean.getSupplyname());


                        tv_info_2.setText(sb.toString().replaceAll("null",""));

                    }

                    if (objBean.getCheckInsList() != null && objBean.getCheckInsList().size() > 0) {
                        List<LivestockDetailBean.ObjBean.CheckInsListBean> list = objBean.getCheckInsList();
                        for (int i = 0; i < list.size(); i++) {
                            View view = inflate(R.layout.layout_livestock_check_item);
                            TextView tv_no = view.findViewById(R.id.tv_no);
                            TextView tv_info_3 = view.findViewById(R.id.tv_info_3);
                            tv_no.setText((i + 1) + ".");
                            LivestockDetailBean.ObjBean.CheckInsListBean checkInsListBean = list.get(i);
                            StringBuilder sb = new StringBuilder();
                            sb.append("检验编号:" + checkInsListBean.getBillno() + "\n");
                            sb.append("检验时间:" + checkInsListBean.getCheckDate() + "\n");
                            sb.append("检验员:" + checkInsListBean.getCheckName() + "\n");
                            sb.append("禁宰数量:" + checkInsListBean.getBearQty() + "\n");
                            sb.append("禁宰原因:" + checkInsListBean.getBearNote() + "\n");
                            sb.append("缓宰数量:" + checkInsListBean.getSlowQty() + "\n");
                            sb.append("缓宰原因:" + checkInsListBean.getSlowNote() + "\n");
                            sb.append("转移圈号:" + checkInsListBean.getShiftNo() + "\n");
                            sb.append("检验结论:" + checkInsListBean.getVerdict() + "\n");
                            sb.append("待宰圈号:" + checkInsListBean.getCircleNo() + "\n");
                            if(i==list.size()-1){
                                sb.append("转移数量:" + checkInsListBean.getShiftQty());
                            }else {
                                sb.append("转移数量:" + checkInsListBean.getShiftQty()+"\n");
                            }


                            tv_info_3.setText(sb.toString().replaceAll("null",""));
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            ll_checklist.addView(view, params);

                        }

                    }
                    if (objBean.getButcherList() != null && objBean.getButcherList().size() > 0) {
                        for (int i = 0; i < objBean.getButcherList().size(); i++) {


                            View view = inflate(R.layout.layout_butcherlist_item);
                            TextView tv_no = view.findViewById(R.id.tv_no);
                            TextView tv_info_4 = view.findViewById(R.id.tv_info_4);
                            TextView tv_info_5 = view.findViewById(R.id.tv_info_5);
                            SimpleTable mSimpleTable = view.findViewById(R.id.mSimpleTable);

                            SimpleTable mSimpleTable_no = view.findViewById(R.id.mSimpleTable_no);
                            mSimpleTable.setVisibility(View.GONE);
                            mSimpleTable_no.setVisibility(View.GONE);
                            LinearLayout ll_kill_finish = view.findViewById(R.id.ll_kill_finish);
                            LivestockDetailBean.ObjBean.ButcherListBean butcherListBean = objBean.getButcherList().get(i);
                            StringBuilder sb = new StringBuilder();
                            sb.append("送检编号:" + butcherListBean.getBillno() + "\n");
                            sb.append("检验时间:" + TimeUtil.formatSimpleDate(butcherListBean.getCheckDate()) + "\n");
                            sb.append("检验员:" + butcherListBean.getCheckName() + "\n");
                            sb.append("准宰数量:" + butcherListBean.getAimQty() + "\n");
                            sb.append("急宰数量:" + butcherListBean.getWorryQty() + "\n");
                            sb.append("急宰原因:" + butcherListBean.getWorryNote() + "\n");
                            sb.append("缓宰数量:" + butcherListBean.getSlowQty() + "\n");
                            sb.append("缓宰原因:" + butcherListBean.getSlowNote() + "\n");
                            sb.append("转移圈号:" + butcherListBean.getShiftNo() + "\n");
                            sb.append("转移数量:" + butcherListBean.getShiftQty() + "\n");
                            sb.append("禁宰数量:" + butcherListBean.getBearQty() + "\n");
                            sb.append("禁宰原因:" + butcherListBean.getBearNote() + "\n");
                            sb.append("检验结论:" + butcherListBean.getVerdict() + "\n");
                            sb.append("待宰圈号:" + butcherListBean.getCircleNo() + "");


                            tv_no.setText((i + 1) + ".送宰检验");
                            tv_info_4.setText(sb.toString());
                            if (objBean.getZaihouList() != null && i < objBean.getZaihouList().size()) {
                                LivestockDetailBean.ObjBean.ZaihouBean zaihouBean = objBean.getZaihouList().get(i);
                                sb = new StringBuilder();
                                sb.append("宰后编号:" + zaihouBean.getBillno() + "\n");
                                sb.append("检验时间:" + zaihouBean.getRuleDate() + "\n");
                                sb.append("检验员:" + zaihouBean.getCheckName() + "");

                                tv_info_5.setText(sb.toString().replaceAll("null",""));

                            }else {
                                tv_info_5.setVisibility(View.GONE);
                            }

                            if (objBean.getEntryList() != null && i < objBean.getEntryList().size()) {
                                List<LivestockDetailBean.ObjBean.EntryListBean> list = objBean.getEntryList().get(i);

                                for (int x = 0; x < list.size(); x++) {
                                    LivestockDetailBean.ObjBean.EntryListBean entryListBean = list.get(x);
                                    if (entryListBean.getIsQualified().equals("1")) {
                                        List<TextView> list_tv = mSimpleTable.addItem();
                                        mSimpleTable.setCellText(list_tv, 0, entryListBean.getProductName());
                                        mSimpleTable.setCellText(list_tv, 1, entryListBean.getUnitName());
                                        mSimpleTable.setCellText(list_tv, 2, entryListBean.getProductQty() + "");
                                        mSimpleTable.setCellText(list_tv, 3, entryListBean.getCertificateNo());
                                        mSimpleTable.setVisibility(View.VISIBLE);

                                    } else {
                                        List<TextView> list_tv = mSimpleTable_no.addItem();
                                        mSimpleTable_no.setCellText(list_tv, 0, entryListBean.getProductName());
                                        mSimpleTable_no.setCellText(list_tv, 1, entryListBean.getUnitName());
                                        mSimpleTable_no.setCellText(list_tv, 2, entryListBean.getProductQty() + "");
                                        mSimpleTable_no.setCellText(list_tv, 3, entryListBean.getCertificateNo());
                                        mSimpleTable_no.setVisibility(View.VISIBLE);
                                    }

                                }


                            }


                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                            ll_kill_record.addView(view, params);

                        }


                    }
                    if (objBean.getEnterRecEntityList() != null && objBean.getEnterRecEntityList().size() > 0) {

                        List<LivestockDetailBean.ObjBean.EnterRecEntityListBean> list = objBean.getEnterRecEntityList();
                        for (int i = 0; i < list.size(); i++) {
                            LivestockDetailBean.ObjBean.EnterRecEntityListBean listBean = list.get(i);
                            View view = inflate(R.layout.layout_enterrec_item);
                            TextView tv_no = view.findViewById(R.id.tv_no);
                            TextView tv_info = view.findViewById(R.id.tv_info);
                            SimpleTable mSimpleTable_product = view.findViewById(R.id.mSimpleTable_product);
                            tv_no.setText((i + 1) + ".");
                            StringBuilder sb = new StringBuilder();
                            sb.append("出场编号:" + listBean.getBillno() + "\n");
                            sb.append("出场时间:" + listBean.getEnterDate() + "\n");
                            sb.append("购货业主:" + listBean.getPurchaser() + "\n");
                            sb.append("联系方式:" + listBean.getPhone() + "\n");
                            sb.append("销售地址:" + listBean.getProvince() + listBean.getCity() + listBean.getTown() + "\n");
                            sb.append("经营场所:" + listBean.getScope() + "");
                            tv_info.setText(sb.toString().replaceAll("null",""));
                            if (objBean.getEnterRecentryList() != null && i < objBean.getEnterRecentryList().size()) {
                                List<LivestockDetailBean.ObjBean.EnterRecentryListBean> list_entry = objBean.getEnterRecentryList().get(i);
                                for (int x = 0; x < list_entry.size(); x++) {
                                    LivestockDetailBean.ObjBean.EnterRecentryListBean entryListBean = list_entry.get(x);

                                    List<TextView> list_tv = mSimpleTable_product.addItem();
                                    mSimpleTable_product.setCellText(list_tv, 0, entryListBean.getProductName());
                                    mSimpleTable_product.setCellText(list_tv, 1, entryListBean.getUnitName());
                                    mSimpleTable_product.setCellText(list_tv, 2, entryListBean.getDeliveryQty() + "");
                                    mSimpleTable_product.setCellText(list_tv, 3, entryListBean.getMeatCert());


                                }


                            }
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                            ll_outrecord.addView(view, params);


                        }


                    }
                    if (objBean.getTBruteHarmHandList() != null && objBean.getTBruteHarmHandList().size() > 0) {
                        List<LivestockDetailBean.ObjBean.TBruteHarmHandListBean> list = objBean.getTBruteHarmHandList();
                        for (int i = 0; i < list.size(); i++) {
                            View view = inflate(R.layout.layout_harmless_item);
                            TextView tv_no = view.findViewById(R.id.tv_no);
                            TextView tv_info = view.findViewById(R.id.tv_info);

                            SimpleTable mSimpleTable_handle_product = view.findViewById(R.id.mSimpleTable_handle_product);
                            LivestockDetailBean.ObjBean.TBruteHarmHandListBean listBean = list.get(i);
                            tv_no.setText((i + 1) + ".");
                            StringBuilder sb = new StringBuilder();
                            sb.append("处理编号:" + listBean.getBillno() + "\n");
                            sb.append("处理时间:" + listBean.getHandleDate() + "\n");
                            sb.append("负责人:" + listBean.getFunctionary() + "\n");
                            sb.append("监督人:" + listBean.getSuperviseName() + "");


                            tv_info.setText(sb.toString().replaceAll("null",""));


                            if (objBean.getTbruteHarmHandentryList() != null && i < objBean.getTbruteHarmHandentryList().size()) {
                                List<LivestockDetailBean.ObjBean.TbruteHarmHandentryListBean> list_entry = objBean.getTbruteHarmHandentryList().get(i);
                                for (int x = 0; x < list_entry.size(); x++) {
                                    LivestockDetailBean.ObjBean.TbruteHarmHandentryListBean handentryListBean = list_entry.get(x);
                                    List<TextView> list_tv = mSimpleTable_handle_product.addItem();
                                    mSimpleTable_handle_product.setCellText(list_tv, 0, handentryListBean.getProductName());
                                    mSimpleTable_handle_product.setCellText(list_tv, 1, handentryListBean.getUnitName());
                                    mSimpleTable_handle_product.setCellText(list_tv, 2, handentryListBean.getHandleQty() + "");
                                    mSimpleTable_handle_product.setCellText(list_tv, 3, handentryListBean.getHandleMode() + "");

                                }


                            }


                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                            ll_harmless.addView(view, params);

                        }


                    }


                }


            }

            @Override
            public void onFailure(FailureMessage failureMessage) {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }
        });

    }
}

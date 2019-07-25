package com.qhsoft.killrecord.module.trace.product;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.TraceProductDetailBean;
import com.qhsoft.killrecord.model.remote.service.TraceProductService;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.TimeUtil;
import com.qhsoft.killrecord.view.FreeToolbar;

import java.util.List;

import butterknife.Bind;
import io.reactivex.disposables.Disposable;

public class TraceProductCheckActivity extends BaseActivity {
    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;
    @Bind(R.id.tv_info)
    TextView tv_info;
    @Bind(R.id.tv_info_1)
    TextView tv_info_1;

    @Bind(R.id.tv_info_2)
    TextView tv_info_2;
    @Bind(R.id.tv_info_3)
    TextView tv_info_3;

    @Bind(R.id.tv_info_4)
    TextView tv_info_4;

    @Bind(R.id.ll_daizai)
    LinearLayout ll_daizai;
    private  float textSize=16;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_product_check;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle(getIntent().getStringExtra("title"));

        getDetail();
    }

    private void getDetail() {
        requestData(HttpHandler.create(TraceProductService.class)
                .getDetail(UserHelper.getSessionId(),
                        getIntent().getStringExtra("id"),getIntent().getStringExtra("zid")))
                .subscribe(new TaskObserver<TraceProductDetailBean>() {
                    @Override
                    public void onSuccess(TraceProductDetailBean value) {
                        if(!value.isSuccess()){
                            showToast(value.getMsg());
                            return;
                        }
                        if(value.getObj()!=null){
                            TraceProductDetailBean.ObjBean objBean=value.getObj();
                            if(objBean.getEntry()!=null){
                                TraceProductDetailBean.ObjBean.EntryBean entryBean=objBean.getEntry();
                                StringBuilder sb=new StringBuilder();
                                sb.append("产品名称："+entryBean.getProductName());
                                if(objBean.getApprRecEntity()!=null){
                                    sb.append("\n畜禽名称："+objBean.getApprRecEntity().getBruteName());
                                    sb.append("\n畜禽种类："+objBean.getApprRecEntity().getBruteType());
                                    sb.append("\n畜禽产地："+objBean.getApprRecEntity().getFprovinceName()+objBean.getApprRecEntity().getFcityName()+objBean.getApprRecEntity().getFcountyName());
                                }
                                sb.append("\n动检编号："+entryBean.getAnimalCert());
                                sb.append("\n肉品编码："+entryBean.getMeatCert());
                                sb.append("\n畜禽来源："+entryBean.getSupplyname());
                                if(objBean.getCompanyEntity()!=null){
                                    sb.append("\n屠宰企业："+objBean.getCompanyEntity().getName());
                                }
                                if(objBean.getRuleAftEntity()!=null){
                                    sb.append("\n生产日期："+ TimeUtil.formatSimpleDate(objBean.getRuleAftEntity().getRuleDate()));
                                }



                                tv_info.setText(sb.toString().replaceAll("null",""));
                                tv_info.setTextSize(textSize);

                            }
                            if(objBean.getTBruteEnterRecPage()!=null){
                                TraceProductDetailBean.ObjBean.TBruteEnterRecPageBean pageBean=objBean.getTBruteEnterRecPage();
                                StringBuilder sb=new StringBuilder();
                                sb.append("出场时间："+pageBean.getEnterDate());
                                sb.append("\n出场单号："+pageBean.getBillno());
                                sb.append("\n购货业主："+pageBean.getPurchaser());
                                sb.append("\n联系方式："+pageBean.getPhone());
                                sb.append("\n销售地址："+pageBean.getProvince()+pageBean.getCity()+pageBean.getTown());
                                sb.append("\n经营场所："+pageBean.getScope());

                                if(objBean.getEntry()!=null){
                                    TraceProductDetailBean.ObjBean.EntryBean entryBean=objBean.getEntry();
                                    sb.append("\n购买数量："+entryBean.getDeliveryQty());
                                    sb.append("\n货主："+entryBean.getSupplyname());
                                }

                                tv_info_1.setText(sb.toString().replaceAll("null",""));
                                tv_info_1.setTextSize(textSize);

                            }
                            if(objBean.getRuleAftEntity()!=null){
                                TraceProductDetailBean.ObjBean.RuleAftEntityBean entityBean=objBean.getRuleAftEntity();

                                StringBuilder sb=new StringBuilder();
                                sb.append("屠宰时间："+entityBean.getRuleDate());
                                sb.append("\n检验单号："+entityBean.getBillno());
                                sb.append("\n检验员："+entityBean.getCheckName());
                                sb.append("\n肉品编号："+entityBean.getCertificateNo());
                                sb.append("\n产出数量："+entityBean.getProductQty());
                                if(entityBean.getIsQualified()!=null){
                                    if(entityBean.getIsQualified().equals("0")){
                                        sb.append("\n是否合格：否");
                                    }else {
                                        sb.append("\n是否合格：是");
                                    }
                                }
                                sb.append("\n检验结果："+entityBean.getResult());
                                tv_info_2.setText(sb.toString().replaceAll("null",""));
                                tv_info_2.setTextSize(textSize);
                            }
                            if(objBean.getCheckInsEntity()!=null){
                                TraceProductDetailBean.ObjBean.CheckInsEntityBean checkInsEntity=objBean.getCheckInsEntity();

                                StringBuilder sb=new StringBuilder();
                                sb.append("检验时间："+checkInsEntity.getCheckDate());
                                sb.append("\n检验单号："+checkInsEntity.getBillno());
                                sb.append("\n检验员："+checkInsEntity.getCheckName());
                                sb.append("\n待宰圈号："+checkInsEntity.getCircleNo());
                                sb.append("\n准宰数量："+checkInsEntity.getAimQty());

                                sb.append("\n急宰数量："+checkInsEntity.getWorryQty());
                                sb.append("\n急宰原因："+checkInsEntity.getWorryNote());
                                sb.append("\n检验结果："+checkInsEntity.getVerdict());

                                tv_info_3.setText(sb.toString().replaceAll("null",""));
                                tv_info_3.setTextSize(textSize);

                            }
                            if(objBean.getDaizaiList()!=null){
                                List<TraceProductDetailBean.ObjBean.DaizaiListBean> list=objBean.getDaizaiList();
                                for(int i=0;i<list.size();i++){
                                    StringBuilder sb=new StringBuilder();
                                    TraceProductDetailBean.ObjBean.DaizaiListBean bean=list.get(i);
                                    sb.append("检验时间："+bean.getCheckDate());
                                    sb.append("\n检验单号："+bean.getBillno());
                                    sb.append("\n检验员："+bean.getCheckName());
                                    sb.append("\n待宰圈号："+bean.getCircleNo());
                                    sb.append("\n缓宰数量："+bean.getSlowQty());

                                    sb.append("\n缓宰原因："+bean.getSlowNote());
                                    sb.append("\n转移圈号："+bean.getShiftNo());
                                    sb.append("\n转移数量："+bean.getShiftQty());
                                    sb.append("\n检验结果："+bean.getVerdict());
                                    TextView tv=inflate(R.layout.layout_textview_productcheck);
                                    tv.setText(sb.toString().replaceAll("null",""));


                                    tv.setTextSize(textSize);

                                    LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                                    ll_daizai.addView(tv,params);


                                }


                            }
                            if(objBean.getApprRecEntity()!=null){
                                TraceProductDetailBean.ObjBean.ApprRecEntityBean checkInsEntity=objBean.getApprRecEntity();

                                StringBuilder sb=new StringBuilder();
                                sb.append("进场时间："+checkInsEntity.getJcdate());
                                sb.append("\n进场单号："+checkInsEntity.getBillno());
                                sb.append("\n检验员："+checkInsEntity.getCoroner());
                                sb.append("\n畜禽产地："+checkInsEntity.getFprovinceName()+checkInsEntity.getFcityName()+checkInsEntity.getFcountyName());
                                sb.append("\n畜禽来源："+checkInsEntity.getSupplyname());

                                sb.append("\n联系方式："+checkInsEntity.getPhone());
                                sb.append("\n动检编号："+checkInsEntity.getCertificateNo());
                                sb.append("\n进场类型："+checkInsEntity.getApproachUse());
                                sb.append("\n进场数量："+checkInsEntity.getApproachQty());
                                sb.append("\n待宰圈号："+checkInsEntity.getCircleNo());
                                sb.append("\n死亡数量："+checkInsEntity.getDieQty());
                                sb.append("\n死亡原因："+checkInsEntity.getDieNote());
                                sb.append("\n检验结果："+checkInsEntity.getResult());


                                tv_info_4.setText(sb.toString().replaceAll("null",""));
                                tv_info_4.setTextSize(textSize);
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

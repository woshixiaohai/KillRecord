package com.qhsoft.killrecord.module.ledger.killcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseFragment;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.KillCheckOneDetailBean;
import com.qhsoft.killrecord.model.remote.bean.PeopleListBean;
import com.qhsoft.killrecord.model.remote.bean.TobeKillListBean;
import com.qhsoft.killrecord.model.remote.service.KillCheckService;
import com.qhsoft.killrecord.module.baseinfo.people.PeopleSelectOneListActivity;
import com.qhsoft.killrecord.module.inventory.tobekill.TobeKillSelectOneListActivity;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.DialogFactory;
import com.qhsoft.killrecord.util.TimeUtil;
import com.qhsoft.killrecord.view.dialog.OnDateSelectedListener;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

import static com.qhsoft.killrecord.util.NetUtil.getRequestBody;

public class KillCheckAddOneFragment extends BaseFragment {

    @Bind(R.id.tv_billno)
    TextView tv_billno;
    @Bind(R.id.tv_checkDate)
    TextView tv_checkDate;
    @Bind(R.id.tv_checkName)
    TextView tv_checkName;
    @Bind(R.id.tv_verdict)
    TextView tv_verdict;
    @Bind(R.id.tv_jcBillno)
    TextView tv_jcBillno;
    @Bind(R.id.tv_bruteName)
    TextView tv_bruteName;
    @Bind(R.id.tv_bruteType)
    TextView tv_bruteType;
    @Bind(R.id.tv_circleNo)
    TextView tv_circleNo;
    @Bind(R.id.tv_aimQty)
    TextView tv_aimQty;

    @Bind(R.id.tv_worryQty)
    TextView tv_worryQty;
    @Bind(R.id.tv_worryNote)
    TextView tv_worryNote;
    @Bind(R.id.tv_slowQty)
    TextView tv_slowQty;
    @Bind(R.id.tv_slowNote)
    TextView tv_slowNote;
    @Bind(R.id.tv_shiftNo)
    TextView tv_shiftNo;
    @Bind(R.id.tv_shiftQty)
    TextView tv_shiftQty;
    @Bind(R.id.tv_bearQty)
    TextView tv_bearQty;
    @Bind(R.id.tv_bearNote)
    TextView tv_bearNote;
    private boolean isFinish=false;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_killcheck_one;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getActivity().getIntent().getStringExtra("title").endsWith("新增")){
            tv_checkDate.setText(TimeUtil.formatSimpleDate(System.currentTimeMillis()));
            String billno="SZ"+System.currentTimeMillis();
            tv_billno.setText(billno);

        }
        if(getActivity().getIntent().getStringExtra("title").endsWith("编辑")){
            tv_billno.setInputType(InputType.TYPE_NULL);
        }
        TobeKillListBean.RowsBean rowsBean= (TobeKillListBean.RowsBean) getActivity().getIntent().getSerializableExtra(TobeKillListBean.RowsBean.class.getSimpleName());
        if(rowsBean!=null){
            getTobeKillInfo(rowsBean);
        }

        getDetail();


    }

    private void getDetail() {
        if (getActivity().getIntent().getStringExtra("id") != null) {
            requestData(HttpHandler.create(KillCheckService.class)
                    .getEntityInfoOne(UserHelper.getSessionId(),
                            getActivity().getIntent().getStringExtra("id")))
                    .subscribe(new TaskObserver<KillCheckOneDetailBean>() {
                        @Override
                        public void onSuccess(KillCheckOneDetailBean value) {
                            EventBus.getDefault().post(value,"toNext");
                            KillCheckOneDetailBean.ObjBean objBean = value.getObj();
                            if (objBean != null) {
                                isFinish=true;
                                tv_billno.setText(objBean.getBillno());
                                tv_checkDate.setText(TimeUtil.formatSimpleDate(objBean.getCheckDate()));
                                tv_jcBillno.setText(objBean.getJcBillno());
                                tv_jcBillno.setTag(objBean.getJcId());

                                tv_bruteName.setText(objBean.getBruteName());
                                tv_bruteName.setTag(objBean.getBruteType());

                                tv_circleNo.setText(objBean.getCircleNo());
                                tv_checkName.setText(objBean.getCheckName());
                                if(objBean.getBearQty()!=null){
                                    tv_bearQty.setText(objBean.getBearQty().toString());
                                }

                                tv_bearNote.setText(objBean.getBearNote());
                                if(objBean.getSlowQty()!=null){
                                    tv_slowQty.setText(objBean.getSlowQty().toString());
                                }

                                tv_slowNote.setText(objBean.getSlowNote());
                                tv_shiftNo.setText(objBean.getShiftNo());
                                if(objBean.getShiftQty()!=null){
                                    tv_shiftQty.setText(objBean.getShiftQty().toString());
                                }

                                tv_verdict.setText(objBean.getVerdict());
                                if(objBean.getWorryQty()!=null){
                                    tv_worryQty.setText(objBean.getWorryQty().toString());
                                }
                                tv_aimQty.setText(objBean.getAimQty()+"");
                                tv_bruteType.setText(objBean.getBruteType());


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

    public void saveData(){
        if(tv_billno.getText().toString().equals("")){
            showToast("单据编号未填写");
            return;
        }
        if(tv_checkName.getText().toString().equals("")){
            showToast("检验员未选择");
            return;
        }
        if(tv_verdict.getText().toString().equals("")){
            showToast("检验结论未填写");
            return;
        }
        if(tv_jcBillno.getText().toString().equals("")){
            showToast("进场编号未选择");
            return;
        }
        if(tv_bearQty.getText().toString().equals("")&&tv_aimQty.getText().toString().equals("")
                &&tv_slowQty.getText().toString().equals("")&&tv_worryQty.getText().toString().equals("")){
            showToast("准宰数量、急宰数量、缓宰数量、禁宰数量四个必须录一个，不能同时为空");
            return;
        }
        int sum=0;
        if(!tv_bearQty.getText().toString().equals("")){
            sum+=Integer.valueOf(tv_bearQty.getText().toString());
        }
        if(!tv_aimQty.getText().toString().equals("")){
            sum+=Integer.valueOf(tv_aimQty.getText().toString());
        }
        if(!tv_slowQty.getText().toString().equals("")){
            sum+=Integer.valueOf(tv_slowQty.getText().toString());
        }
        if(!tv_worryQty.getText().toString().equals("")){
            sum+=Integer.valueOf(tv_worryQty.getText().toString());
        }
        if(!tv_shiftQty.getText().toString().equals("")){
            sum+=Integer.valueOf(tv_shiftQty.getText().toString());
        }
        if(sum>invenQty){
            showToast("输入的准宰数量、急宰数量、缓宰数量、禁宰数量、转移数量其总和不能大于库存数量");
            return;
        }
        if(!tv_shiftQty.getText().toString().equals("")&&tv_shiftNo.getText().toString().equals("")){
            showToast("有填写转移数量时转移圈号必录");
            return;
        }



        JsonObject jsonObject=new JsonObject();



        if(getActivity().getIntent().getStringExtra("id")!=null){
            jsonObject.addProperty("id",getActivity().getIntent().getStringExtra("id"));
        }

        jsonObject.addProperty("type","1");
        jsonObject.addProperty("checkDate",tv_checkDate.getText().toString());
        jsonObject.addProperty("billno",tv_billno.getText().toString());
        jsonObject.addProperty("jcBillno",tv_jcBillno.getText().toString());
        jsonObject.addProperty("jcId",tv_jcBillno.getTag().toString());
        jsonObject.addProperty("bruteType",tv_bruteType.getText().toString());
        jsonObject.addProperty("bruteName",tv_bruteName.getText().toString());


        jsonObject.addProperty("checkName",tv_checkName.getText().toString());
        jsonObject.addProperty("circleNo",tv_circleNo.getText().toString());
        jsonObject.addProperty("bearQty",tv_bearQty.getText().toString());
        jsonObject.addProperty("bearNote",tv_bearNote.getText().toString());
        jsonObject.addProperty("slowQty",tv_slowQty.getText().toString());
        jsonObject.addProperty("slowNote",tv_slowNote.getText().toString());
        jsonObject.addProperty("shiftNo",tv_shiftNo.getText().toString());
        jsonObject.addProperty("shiftQty",tv_shiftQty.getText().toString());
        jsonObject.addProperty("verdict",tv_verdict.getText().toString());

        jsonObject.addProperty("aimQty",tv_aimQty.getText().toString());
        jsonObject.addProperty("worryQty",tv_worryQty.getText().toString());
        jsonObject.addProperty("worryNote",tv_worryNote.getText().toString());

        if(getActivity().getIntent().getStringExtra("id")==null){
            requestData(HttpHandler.create(KillCheckService.class)
                    .doAddAppOne(UserHelper.getSessionId(),
                            getRequestBody(jsonObject.toString())))
                    .subscribe(new TaskObserver<KillCheckOneDetailBean>() {
                        @Override
                        public void onSuccess(KillCheckOneDetailBean value) {
                            showToast(value.getMsg());
                            if(value.isSuccess()){
                                isFinish=true;
                                EventBus.getDefault().post(value,"toNext");
                            }

                        }

                        @Override
                        public void onFailure(FailureMessage failureMessage) {
                            showToast(failureMessage.getMessageText());
                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }
                    });

        }else {
            requestData(HttpHandler.create(KillCheckService.class)
                    .doUpdateAppOne(UserHelper.getSessionId(),
                            getRequestBody(jsonObject.toString())))
                    .subscribe(new TaskObserver<KillCheckOneDetailBean>() {
                        @Override
                        public void onSuccess(KillCheckOneDetailBean value) {
                            showToast(value.getMsg());
                            if(value.isSuccess()){
                                isFinish=true;
                                EventBus.getDefault().post(value,"toNext");
                            }

                        }

                        @Override
                        public void onFailure(FailureMessage failureMessage) {
                            showToast(failureMessage.getMessageText());
                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }
                    });

        }













    }

    public boolean getIsFinish(){
        return  isFinish;
    }

    @Subscriber(tag = "getPeopleInfo")
    private void getPeopleInfo(PeopleListBean.RowsBean bean){
        tv_checkName.setText(bean.getName());

    }
    private double invenQty;
    @Subscriber(tag = "getTobeKillInfo")
    private void getTobeKillInfo(TobeKillListBean.RowsBean bean){
        tv_jcBillno.setText(bean.getBillno());
        tv_jcBillno.setTag(bean.getId());
        tv_bruteName.setText(bean.getBruteName());
        tv_bruteType.setText(bean.getBruteType());
        tv_circleNo.setText(bean.getCircleNo());
        if(!bean.getInvenQty().equals("")){
            invenQty=Double.valueOf(bean.getInvenQty());
            d("invenQty:"+invenQty);
        }




    }

    @OnClick({R.id.tv_checkDate, R.id.tv_checkName,R.id.tv_jcBillno})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_checkDate:
                DialogFactory.createDateBuilder().setOnDateSelectedListener(new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(String date) {
                        tv_checkDate.setText(date);
                    }
                }).show(getActivity());
                break;
            case R.id.tv_checkName:
                Intent intent = new Intent(getActivity(), PeopleSelectOneListActivity.class);
                intent.putExtra("title", "选择检查员");
                intent.putExtra("certificateType", "检验员");
                startActivity(intent);
                break;
            case R.id.tv_jcBillno:
                 intent=new Intent(getActivity(), TobeKillSelectOneListActivity.class);
                intent.putExtra("title","选择进场编号");
                startActivity(intent);
                break;

        }

    }


}

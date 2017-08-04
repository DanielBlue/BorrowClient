package com.zoesap.borrowclient.loan;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.adapter.AdapterContract;
import com.zoesap.borrowclient.adapter.LoanListAdapter;
import com.zoesap.borrowclient.adapter.PopupListAdapter;
import com.zoesap.borrowclient.adapter.SpacesItemDecoration;
import com.zoesap.borrowclient.chooseloan.ChooseLoanFragment;
import com.zoesap.borrowclient.data.bean.ChooseLoanTypeBean;
import com.zoesap.borrowclient.data.bean.LoanListItemBean;
import com.zoesap.borrowclient.loandetail.LoanDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pers.maoqi.core.CoreBaseFragment;
import pers.maoqi.core.util.DensityUtils;
import pers.maoqi.core.util.NullUtils;

/**
 * Created by maoqi on 2017/7/18.
 */

public class LoanFragment extends CoreBaseFragment implements LoanContract.View {
    @BindView(R.id.bt_type_one)
    Button btTypeOne;
    @BindView(R.id.bt_type_two)
    Button btTypeTwo;
    @BindView(R.id.bt_type_three)
    Button btTypeThree;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_no_record)
    ImageView tvNoRecord;
    Unbinder unbinder;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private LoanContract.Presenter mPresenter;
    private PopupWindow mPopupWindow1;
    private PopupWindow mPopupWindow2;
    private LoanListAdapter mAdapter;
    private PopupWindow mPopupWindow3;
    private boolean isLoadMore = false;
    int currentLoantype = 0;
    int currentCareer = 0;
    int currentCredit = 0;
    int currentHouse = 0;
    private List<LoanListItemBean.DataBean.ListBean> data = new ArrayList();
    private boolean isFirstLoad = true;

    public boolean getLoadMoreStatus() {
        return isLoadMore;
    }

    public void setLoadMoreStatus(boolean isLoadMore) {
        this.isLoadMore = isLoadMore;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_loan, null);
        unbinder = ButterKnife.bind(this, view);
        initSwipeRefreshLayout();
        btTypeOne.setText("不限抵押类型");
        btTypeTwo.setText("职业");
        btTypeThree.setText("筛选");
        tvTitle.setText(R.string.all_loan);
        return view;
    }

    private void initSwipeRefreshLayout() {
        srlRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoadMore) {
                    mPresenter.refreshBeanList(currentLoantype, currentCareer, currentCredit, currentHouse);
                } else {
                    srlRefresh.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            mPresenter.start();
            isFirstLoad = false;
        }
    }

    @Override
    public void setPresent(@NonNull LoanContract.Presenter presenter) {
        mPresenter = NullUtils.checkNotNull(presenter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void initPopupWindow(ChooseLoanTypeBean.DataBean bean) {
        initPopupWindowone(bean.getLoantype());
        initPopupWindowtwo(bean.getCareer());
        initPopupWindowthree();
    }

    private BaseQuickAdapter.RequestLoadMoreListener mLoadMoreListener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
            mPresenter.loadMoreBeanList(currentLoantype, currentCareer, currentCredit, currentHouse,
                    new LoanContract.ListLoadMoreListener() {
                        @Override
                        public void loadMoreComplete(LoanListItemBean.DataBean bean) {
                            data.addAll(bean.getList());
                            mAdapter.loadMoreComplete();
                        }

                        @Override
                        public void loadMoreEnd(LoanListItemBean.DataBean bean) {
                            data.addAll(bean.getList());
                            mAdapter.loadMoreEnd();
                        }

                        @Override
                        public void loadMoreFail() {
                            mAdapter.loadMoreFail();
                        }
                    });
        }
    };

    @Override
    public void setNewData(LoanListItemBean.DataBean bean) {
        data.clear();
        data.addAll(bean.getList());
        if (mAdapter != null) {
            mAdapter.setNewData(data);
        } else {
            rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvList.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 1), 0));
            mAdapter = new LoanListAdapter(R.layout.item_loan_list, data);
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    startActivity(LoanDetailActivity.getStartIntent(getActivity(), data.get(position).getId()));
                }
            });
            mAdapter.setOnLoadMoreListener(mLoadMoreListener, rvList);
            rvList.setAdapter(mAdapter);
        }
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        srlRefresh.setRefreshing(refreshing);
    }

    @Override
    public void setEnableLoadMore(boolean enable) {
        if (mAdapter != null) {
            mAdapter.setEnableLoadMore(false);
        }
    }

    @Override
    public void showNoData() {
        rvList.setVisibility(View.GONE);
        tvNoRecord.setVisibility(View.VISIBLE);
    }

    @Override
    public void showList() {
        if (rvList.getVisibility() == View.GONE) {
            rvList.setVisibility(View.VISIBLE);
            tvNoRecord.setVisibility(View.GONE);
        }
    }

    @Override
    public void initLoanList(LoanListItemBean.DataBean bean) {
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 1), 0));
        data.addAll(bean.getList());
        mAdapter = new LoanListAdapter(R.layout.item_loan_list, data);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(LoanDetailActivity.getStartIntent(getActivity(), data.get(position).getId()));
            }
        });
        mAdapter.setOnLoadMoreListener(mLoadMoreListener, rvList);
        rvList.setAdapter(mAdapter);
    }

    private void initPopupWindowone(final List<ChooseLoanTypeBean.DataBean.LoantypeBean> data) {
        View popupView1 = LayoutInflater.from(getActivity()).inflate(R.layout.view_popup_type_one, null);
        RecyclerView rvPopupList = (RecyclerView) popupView1.findViewById(R.id.rv_list);
        rvPopupList.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> list = new ArrayList<>();
        for (ChooseLoanTypeBean.DataBean.LoantypeBean bean : data) {
            list.add(bean.getSval());
        }
        PopupListAdapter popupListAdapter = new PopupListAdapter(getActivity(), list, new AdapterContract.ListItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                if (mPopupWindow1.isShowing()) {
                    currentLoantype = position + 1;
                    mPopupWindow1.dismiss();
                    btTypeOne.setText(data.get(position).getSval());
                    mPresenter.refreshBeanList(currentLoantype, currentCareer, currentCredit, currentHouse);
                }
            }
        });
        rvPopupList.setAdapter(popupListAdapter);
        mPopupWindow1 = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow1.setContentView(popupView1);
        mPopupWindow1.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mPopupWindow1.setOutsideTouchable(false);
        mPopupWindow1.setAnimationStyle(R.style.AnimUpDown);
        mPopupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mPopupWindow1.setFocusable(false);
            }
        });
    }

    private void initPopupWindowtwo(final List<ChooseLoanTypeBean.DataBean.CareerBean> data) {
        View popupView2 = LayoutInflater.from(getActivity()).inflate(R.layout.view_popup_type_one, null);
        RecyclerView rvPopupList2 = (RecyclerView) popupView2.findViewById(R.id.rv_list);
        rvPopupList2.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> list = new ArrayList<>();
        for (ChooseLoanTypeBean.DataBean.CareerBean bean : data) {
            list.add(bean.getSval());
        }
        PopupListAdapter popupListAdapter = new PopupListAdapter(getActivity(), list, new AdapterContract.ListItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                if (mPopupWindow2.isShowing()) {
                    currentCareer = position + 1;
                    mPopupWindow2.dismiss();
                    btTypeTwo.setText(data.get(position).getSval());
                    mPresenter.refreshBeanList(currentLoantype, currentCareer, currentCredit, currentHouse);
                }
            }
        });
        rvPopupList2.setAdapter(popupListAdapter);
        mPopupWindow2 = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow2.setContentView(popupView2);
        mPopupWindow2.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mPopupWindow2.setOutsideTouchable(false);
        mPopupWindow2.setFocusable(true);
        mPopupWindow2.setAnimationStyle(R.style.AnimUpDown);
        mPopupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mPopupWindow2.setFocusable(false);
            }
        });
    }

    private void initPopupWindowthree() {
        View popupView3 = LayoutInflater.from(getActivity()).inflate(R.layout.view_popup_type_three, null);
        final Button btn_credit_on = (Button) popupView3.findViewById(R.id.btn_credit_on);
        final Button btn_credit_off = (Button) popupView3.findViewById(R.id.btn_credit_off);
        btn_credit_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_credit_on.setBackgroundResource(R.color.color_508ae6);
                btn_credit_on.setTextColor(getResources().getColor(R.color.white));
                btn_credit_off.setBackgroundResource(R.color.white);
                btn_credit_off.setTextColor(getResources().getColor(R.color.color_323232));
                currentCredit = 1;
            }
        });
        btn_credit_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_credit_on.setBackgroundResource(R.color.white);
                btn_credit_on.setTextColor(getResources().getColor(R.color.color_323232));
                btn_credit_off.setBackgroundResource(R.color.color_508ae6);
                btn_credit_off.setTextColor(getResources().getColor(R.color.white));
                currentCredit = 2;
            }
        });
        final Button btn_house_on = (Button) popupView3.findViewById(R.id.btn_house_on);
        final Button btn_house_off = (Button) popupView3.findViewById(R.id.btn_house_off);
        btn_house_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_house_on.setBackgroundResource(R.color.color_508ae6);
                btn_house_on.setTextColor(getResources().getColor(R.color.white));
                btn_house_off.setBackgroundResource(R.color.white);
                btn_house_off.setTextColor(getResources().getColor(R.color.color_323232));
                currentHouse = 1;
            }
        });
        btn_house_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_house_on.setBackgroundResource(R.color.white);
                btn_house_on.setTextColor(getResources().getColor(R.color.color_323232));
                btn_house_off.setBackgroundResource(R.color.color_508ae6);
                btn_house_off.setTextColor(getResources().getColor(R.color.white));
                currentHouse = 2;
            }
        });
        Button btn_submit = (Button) popupView3.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow3.isShowing()) {
                    mPopupWindow3.dismiss();
                    if (currentCredit == 0) {
                        currentCredit = 1;
                    }
                    if (currentHouse == 0) {
                        currentHouse = 1;
                    }
                    mPresenter.refreshBeanList(currentLoantype, currentCareer, currentCredit, currentHouse);
                }
            }
        });
        mPopupWindow3 = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow3.setContentView(popupView3);
        mPopupWindow3.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mPopupWindow3.setOutsideTouchable(true);
        mPopupWindow3.setFocusable(true);
        mPopupWindow3.setAnimationStyle(R.style.AnimUpDown);
        mPopupWindow3.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mPopupWindow3.setFocusable(false);
            }
        });
    }


    public static ChooseLoanFragment newInstance() {
        return new ChooseLoanFragment();
    }

    @OnClick({R.id.bt_type_one, R.id.bt_type_two, R.id.bt_type_three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_type_one:
                if (!mPopupWindow1.isShowing()) {
                    mPopupWindow1.setFocusable(true);
                    mPopupWindow1.showAsDropDown(btTypeOne);
                }
                break;
            case R.id.bt_type_two:
                if (!mPopupWindow2.isShowing()) {
                    mPopupWindow2.setFocusable(true);
                    mPopupWindow2.showAsDropDown(btTypeTwo);
                }
                break;
            case R.id.bt_type_three:
                if (!mPopupWindow3.isShowing()) {
                    mPopupWindow3.setFocusable(true);
                    mPopupWindow3.showAsDropDown(btTypeThree);
                }
                break;
        }
    }
}

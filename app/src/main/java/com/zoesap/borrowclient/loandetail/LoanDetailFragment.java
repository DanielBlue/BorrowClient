package com.zoesap.borrowclient.loandetail;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zoesap.borrowclient.BorrowApplication;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.applyloan.ApplyLoanActivity;
import com.zoesap.borrowclient.data.bean.LoanDetailBean;
import com.zoesap.borrowclient.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pers.maoqi.core.CoreBaseFragment;
import pers.maoqi.core.util.NullUtils;

/**
 * Created by maoqi on 2017/7/20.
 */

public class LoanDetailFragment extends CoreBaseFragment implements LoanDetailContract.View {
    @BindView(R.id.tv_loan_title)
    TextView tvLoanTitle;
    @BindView(R.id.tv_loan_type)
    TextView tvLoanType;
    @BindView(R.id.tv_loan_limit_deadline)
    TextView tvLoanLimitDeadline;
    @BindView(R.id.et_loan_num)
    EditText etLoanNum;
    @BindView(R.id.et_loan_deadline)
    EditText etLoanDeadline;
    @BindView(R.id.tv_loan_limit)
    TextView tvLoanLimit;
    @BindView(R.id.tv_loan_deadline)
    TextView tvLoanDeadline;
    @BindView(R.id.tv_loan_deatil_deadline)
    TextView tvLoanDeatilDeadline;
    @BindView(R.id.tv_loan_deatil_interest)
    TextView tvLoanDeatilInterest;
    @BindView(R.id.tv_apply_description)
    TextView tvApplyDescription;
    @BindView(R.id.tv_material_description)
    TextView tvMaterialDescription;
    @BindView(R.id.rl_apply)
    RelativeLayout rlApply;
    Unbinder unbinder;
    private LoanDetailContract.Presenter mPresenter;
    private boolean isFirstLoad = true;
    private AlertDialog mAlertDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loan_detail, null);
        unbinder = ButterKnife.bind(this, view);
        initDialog();
        return view;
    }

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage(R.string.to_login)
                .setCancelable(false)
                .setPositiveButton(R.string.login, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(LoginActivity.getStartIntent(getActivity()));
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        mAlertDialog = builder.create();
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
    public void setPresent(@NonNull LoanDetailContract.Presenter presenter) {
        mPresenter = NullUtils.checkNotNull(presenter);
    }

    public static LoanDetailFragment newInstance() {
        return new LoanDetailFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void updateView(LoanDetailBean.DataBean dataBean) {
        tvLoanTitle.setText(dataBean.getCompany() + "-" + dataBean.getLoan_title());
        tvLoanLimitDeadline.setText(dataBean.getCount() + "人申请/" + dataBean.getLoan_ftime() + "放款");
        tvLoanType.setText(dataBean.getLoan_type());
        tvLoanLimit.setText(dataBean.getLoan_ilimit() + "~" + dataBean.getLoan_alimit() + "万");
        tvLoanDeadline.setText(dataBean.getLoan_ideadline() + "~" +
                dataBean.getLoan_adeadline() + "个月");

        String materialDescription = dataBean.getLoan_data()
                .replaceAll("\t", "")
                .replaceAll("\r\n", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        String applyDescription = dataBean.getLoan_for()
                .replaceAll("\t", "")
                .replaceAll("\r\n", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        tvApplyDescription.setText(Html.fromHtml(applyDescription));
        tvMaterialDescription.setText(Html.fromHtml(materialDescription));
        tvLoanDeatilInterest.setText(dataBean.getLoan_apr() + "%");
        tvLoanDeatilDeadline.setText(dataBean.getLoan_ideadline() + "~" +
                dataBean.getLoan_adeadline() + "个月");
    }

    @OnClick({R.id.rl_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_apply:
                if (BorrowApplication.getInstance().ismSignIn()) {
                    startActivity(ApplyLoanActivity.getStartIntent(getActivity(),
                            ((LoanDetailActivity) getActivity()).loanId,
                            etLoanNum.getText().toString()));
                } else {
                    if (mAlertDialog != null && !mAlertDialog.isShowing()) {
                        mAlertDialog.show();
                    }
                }
                break;
        }
    }
}

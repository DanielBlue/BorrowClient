package com.zoesap.borrowclient.loandetail;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.LoanDetailBean;
import com.zoesap.borrowclient.util.NullUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by maoqi on 2017/7/20.
 */

public class LoanDetailFragment extends Fragment implements LoanDetailContract.View {
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
    private ProgressDialog mProgressDialog;
    private boolean isFirstLoad = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loan_detail, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
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

    @Override
    public void showNetError() {
        Toast.makeText(getActivity(), R.string.net_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadindDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void loadingDialogDismiss() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
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
}

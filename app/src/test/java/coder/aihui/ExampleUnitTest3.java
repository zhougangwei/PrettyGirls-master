package coder.aihui;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import coder.aihui.ui.main.DownLoadBean;
import coder.aihui.util.ListUtils;

import static coder.aihui.ui.main.DownFragment.mBigType;
import static coder.aihui.ui.main.DownPresenter.ASSET_DOWN;
import static coder.aihui.ui.main.DownPresenter.COMPANY_DOWN;
import static coder.aihui.ui.main.DownPresenter.HTTP;
import static coder.aihui.ui.main.DownPresenter.INIT_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_INIT_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_PLAN_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_PM_INIT_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_PM_PLAN_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_PM_TEMPLETITEM_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_TEMPLETITEM_DOWN;
import static coder.aihui.ui.main.DownPresenter.AZYS_DOWN;
import static coder.aihui.ui.main.DownPresenter.PXGL_SB_DOWN;
import static coder.aihui.ui.main.DownPresenter.WEB_SERVICE;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest3 {





    @Test
    public void addition_isCorrect() throws Exception {


            ArrayList<DownLoadBean> mDatas = new ArrayList<DownLoadBean>() {
            };



            //查询台账的已有数目
            mDatas.clear();

            //下载台账初始化
            DownLoadBean downLoadBean = new DownLoadBean();
            downLoadBean.name = "初始化";
            downLoadBean.enties = new String[]{"coder.aihui.data.bean.PUB_DICTIONARY_ITEM", "coder.aihui.data.bean.SYS_DEPT", "coder.aihui.data.bean.IN_STORE_QC", "coder.aihui.data.bean.PUB_DICTIONARY_ITEM"};
            downLoadBean.methods = new String[]{"getHrpPubDictDataJSON2", "getHrpDeptDataJSON", "getHrpInStoreQcDataJSON", "getHrpPubDictDataJSON"};
            downLoadBean.way = WEB_SERVICE;
            downLoadBean.type = INIT_DOWN;
            downLoadBean.bigType = mBigType[0];
            mDatas.add(downLoadBean);


            DownLoadBean assetBean = new DownLoadBean();


            //下载台账
            assetBean.name = "台账下载";
            assetBean.enties = new String[]{"coder.aihui.data.bean.IN_ASSET"};
            assetBean.methods = new String[]{"getHrpInAssetDataJSON3"};
            assetBean.way = WEB_SERVICE;
            assetBean.type = ASSET_DOWN;
            assetBean.bigType = mBigType[0];
            mDatas.add(assetBean);

            //下载公司
            DownLoadBean companyBean = new DownLoadBean();
            companyBean.name = "公司下载";
            companyBean.way = HTTP;
            companyBean.type = COMPANY_DOWN;
            companyBean.bigType = mBigType[0];
            mDatas.add(companyBean);


            //下载巡检计划
            DownLoadBean planBean = new DownLoadBean();


            planBean.enties = new String[]{"coder.aihui.data.bean.INSPECT_PLAN"};
            planBean.methods = new String[]{"getHrpInspectPlanDataJSON4"};
            planBean.way = WEB_SERVICE;
            planBean.type = INSPECT_PLAN_DOWN;
            planBean.name = "巡检下载";
            planBean.bigType = mBigType[1];
            mDatas.add(planBean);


            //下载巡检初始化
            DownLoadBean inspectInitBean = new DownLoadBean();


            inspectInitBean.name = "巡检初始下载";
            inspectInitBean.enties = new String[]{"coder.aihui.data.bean.PUB_DICTIONARY_ITEM", "coder.aihui.data.bean.REPAIR_PLACE", "coder.aihui.data.bean.INSPECT_EXT", "coder.aihui.data.bean.INSPECT_EXT_EXECUTOR", "coder.aihui.data.bean.SYS_PARAM", "coder.aihui.data.bean.INSPECT_GROUP"};
            inspectInitBean.methods = new String[]{"getHrpPubDictDataJSON2", "getHrpRePlaceDataJSON", "getHrpInspectExtDataJSON", "getHrpInspectExtExecDataJSON", "getHrpParamDataJSON", "getHrpInspectGroupDataJSON"};
            inspectInitBean.type = INSPECT_INIT_DOWN;
            inspectInitBean.way = WEB_SERVICE;

            inspectInitBean.bigType = mBigType[1];
            mDatas.add(inspectInitBean);
            //下载巡检模板
            DownLoadBean inspectTempletItemBean = new DownLoadBean();

            inspectTempletItemBean.name = "巡检模板下载";
            inspectTempletItemBean.enties = new String[]{"coder.aihui.data.bean.InspectTempletItem"};
            inspectTempletItemBean.methods = new String[]{"getHrpInspectTempletItemDataJSON"};
            inspectTempletItemBean.type = INSPECT_TEMPLETITEM_DOWN;
            inspectTempletItemBean.way = WEB_SERVICE;
            inspectTempletItemBean.bigType = mBigType[1];
            mDatas.add(inspectTempletItemBean);


            //下载PM计划
            DownLoadBean planPmBean = new DownLoadBean();

            planPmBean.name = "PM下载";


            planPmBean.enties = new String[]{"coder.aihui.data.bean.INSPECT_PLAN"};
            planPmBean.methods = new String[]{"getHrpInspectPlanDataJSON4"};
            planPmBean.way = WEB_SERVICE;
            planPmBean.type = INSPECT_PM_PLAN_DOWN;

            planPmBean.bigType = mBigType[2];
            mDatas.add(planPmBean);


            //下载巡检模板
            DownLoadBean pmTempletItemBean = new DownLoadBean();

            pmTempletItemBean.name = "PM模板下载";
            pmTempletItemBean.enties = new String[]{"coder.aihui.data.bean.InspectTempletItem"};
            pmTempletItemBean.methods = new String[]{"HrpInspectTempletItemDataJSON"};
            pmTempletItemBean.type = INSPECT_PM_TEMPLETITEM_DOWN;
            pmTempletItemBean.way = WEB_SERVICE;

            pmTempletItemBean.bigType = mBigType[2];
            mDatas.add(pmTempletItemBean);

            //下载PM初始化

            DownLoadBean pmInitBean = (DownLoadBean) inspectInitBean.deepClone();
            pmInitBean.type = INSPECT_PM_INIT_DOWN;
            pmInitBean.bigType = mBigType[2];
            mDatas.add(pmInitBean);

            //下载安装验收
            DownLoadBean pubContractPlanBean = new DownLoadBean();
            pubContractPlanBean.name = "安装验收下载";
            pubContractPlanBean.type = AZYS_DOWN;
            pubContractPlanBean.way = HTTP;
            pubContractPlanBean.bigType = mBigType[3];
            mDatas.add(pubContractPlanBean);

            //下载培训管理
            DownLoadBean pxsbBean = new DownLoadBean();
            pxsbBean.name = "培训管理下载";
            pxsbBean.type = PXGL_SB_DOWN;
            pxsbBean.way = HTTP;
            pxsbBean.bigType = mBigType[3];
            mDatas.add(pxsbBean);

        List<String> typeList = ListUtils.ListFiled2list(mDatas, "getType", DownLoadBean.class);
        assert typeList.size()>0;

        }





}
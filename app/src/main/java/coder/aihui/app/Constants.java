package coder.aihui.app;

import coder.aihui.util.FileUtil;

/**
 * Created by oracleen on 2016/7/11.
 * 保存项目中用到的常量
 */
public class Constants {

    //public static final String GANHUO_API = "http://gank.io/";

    public static final String dir        = FileUtil.getDiskCacheDir(MyApplication.getIntstance()) + "/girls";

    //sp临时存储数据  用于数据回显
    public static final int    INSTALL_PRODUCT_NAME = 1; //设备名称
    public static final String LIST_NUM             = "list_num"; //单号
    // public static final String BASE_URL             = AndroidUtils.getWsAddress(BaseApplication.context);
    //public static final String BASE_URL ="http://192.168.1.105:8089";
    // public static final String BASE_URL             = AndroidUtils.getWsAddress(BaseApplication.context);
    //public static final String BASE_URL ="http://192.168.1.21:8080";


    public static final String AZYZ_UP_URL    = "/purContract/sendPurContractDetail.html";
    public static final String AZYZ_UPPIC_URL = "/fileup/upLoadFile_getId_pda.html?folderName=";


    public static final String AZYZ_YSRY_URL    = "/purContract/getPurYsrPda.html";
    public static final String AZYZ_DQ_LIST_URL = "/purEscRecord/getEscTempletsJson.html";

    //安装验收电气的头
    public static final String AZYS_DQ_URL = "/purEscRecord/getEscTempletJson.html";
    //培训管理 品牌
    public static final String PXGL_PP_URL = "/purChecked/getPpmcJson.html";

    //培训管理 物资
    public static final String PXGL_WZ_URL = "/purChecked/getWzmcJson.html";

    //培训管理回传接口
    public static final String PXGL_HC_URL = "/purPxjl/savePxjlJson.html";
    //安装验收详细目录 动态加载数据的地址
    public static final String AZYS_MX_URL = "/pubDictionaryClass/getPurCheckItemJson.html";

    //最近一次安装验收的时间  增量修改
    public static final String AZYS_SJ = "azys_sj";


    public static final String ISREMEMBERPW = "isrememberpw";
    public static final String PASSWORD     = "password";
    public static final String USER_ACCOUNT = "userAccount";

    //公司联系人的表
    public static final String COMPANY_CONTACT_URL = "/inCompany/getCompanyContactJson.html";


    public static final String COMPANY_URL      = "/inCompany/getCompanyJson.html?type=";
    public static final String PMS_MAINTAIN_URL = "/user/getPmsMaintainJson.html";

    //下载已上传的巡检数据
    public static final String REP_CHECKED_URL  = "/inspectrep/getInspectRepJson.html?userId=";
    public static final String REPS_CHECKED_URL = "/inspectrep/getInspectRepsJson.html?userId=";


    //上传修改台账的数据
    public static final String HAND_CORRECT_ASSET = "/inAsset/saveInAssetMsgJson.html";
}

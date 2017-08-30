package coder.aihui.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.utils.TimeUtils;

import org.greenrobot.greendao.database.Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import coder.aihui.data.bean.INSPECT_EXT;
import coder.aihui.data.bean.INSPECT_GROUP;
import coder.aihui.data.bean.INSPECT_PLAN;
import coder.aihui.data.bean.INSPECT_REPS;
import coder.aihui.data.bean.InspectTempletItem;
import coder.aihui.data.bean.PUB_DICTIONARY_ITEM;
import coder.aihui.data.bean.REPAIR_PLACE;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.data.bean.gen.INSPECT_EXTDao;
import coder.aihui.data.bean.gen.INSPECT_GROUPDao;
import coder.aihui.data.bean.gen.INSPECT_PLANDao;
import coder.aihui.data.bean.gen.PUB_DICTIONARY_ITEMDao;
import coder.aihui.data.bean.gen.REPAIR_PLACEDao;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/5/3 11:20
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/5/3$
 * @ 更新描述  ${TODO}
 */

public class AndroidUtils {

    //判断是否联网
    public static boolean isConnect(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.v("error", e.toString());
        }
        return false;
    }

    /**
     * 提示警告信息,点击确定后不关闭程序
     *
     * @param title
     * @param msg
     */
    public static void showErrorMsg(String title, String msg, Context ctx) {
        new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //					android.os.Process.killProcess(android.os.Process.myPid());
                        //					System.exit(0);
                    }
                }).show();

    }

    //获得最大的期次ID
    public static String getMaxPcid(DaoSession daoSession) {
        String qcid = "";// 取最大的盘点期次
        Database db = daoSession.getDatabase();
        String sql_qc = "select max(QCID) as QCID from IN__STORE__QC";
        Cursor cursor = db.rawQuery(sql_qc, new String[]{});
        cursor.moveToFirst();
        while (cursor.getPosition() != cursor.getCount()) {
            qcid = cursor.getString(0);
            cursor.moveToNext();
        }
        return qcid;
    }

    //生成UUID
    public static String createUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String getImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        //TODO 全局activity变量
        String imei = telephonyManager.getDeviceId();//机器的IMEI
        return imei;
    }

    /**
     * @param type       巡检还是PM
     * @param moduleId   库房Id
     * @param daoSession
     * @return
     */
    public static String getPlanPras(String type, String moduleId, DaoSession daoSession) {

        try {
            ArrayList<Map<String, String>> mParDatas = new ArrayList<>();
            String sql;
            if (!TextUtils.isEmpty(moduleId)) {
                sql = "select max(MODIFY__DATE) MODIFY__DATE,KFID,max(CREATE__DATE) CREATE__DATE from INSPECT__PLAN where INSP__TYPE = ? and  KFID in ( " + moduleId + " )  group by KFID";
            } else {
                sql = "select max(MODIFY__DATE) MODIFY__DATE,KFID,max(CREATE__DATE) CREATE__DATE from INSPECT__PLAN where INSP__TYPE = ?  group by KFID";
            }

            //第一个参数对应的是巡检类型
            Cursor cursor = daoSession.getDatabase().rawQuery(sql, new String[]{type});
            String[] split = moduleId.split(",");
            cursor.moveToFirst();
            while (cursor.getCount() > 0 && cursor.getPosition() != cursor.getCount()) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("moduleId", cursor.getString(1));
                String modifyDate = cursor.getString(0);
                String createDate = cursor.getString(2);
                Long time = judgeLong(modifyDate, createDate);
                hashMap.put("lastTime", time == null ? "2000-01-01 00:00:00" : TimeUtils.milliseconds2String(time + 1000));
                mParDatas.add(hashMap);
                cursor.moveToNext();
            }
            cursor.close();
            ArrayList<String> list2 = new ArrayList(); //临时记录

            for (int j = 0; j < mParDatas.size(); j++) {
                list2.add(mParDatas.get(j).get("moduleId"));
            }
            if (list2.size() != split.length) {
                for (int i = 0; i < split.length; i++) {
                    if (!list2.contains(split[i])) {
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("moduleId", split[i]);
                        hashMap.put("lastTime", "2000-01-01 00:00:00");
                        mParDatas.add(hashMap);
                    }
                }
            }
            Log.d("AndroidUtils", mParDatas.toString().toString());
            return GsonUtil.parseListToJson(mParDatas);
        } catch (Exception e) {
            //因为是有可能的  在于webservice doinBacKGround 的返回码
            e.printStackTrace();
            return "";
        }
    }

    public static String getInspectTemPars(String par, String moduleId, DaoSession daoSession) {

        ArrayList<Map<String, String>> mParDatas = new ArrayList<Map<String, String>>();
        try {
            //根据类型库房id来查询
            //String sql = "select max(ITEM__UPDATE__DATE),ITEM__KFID from INSPECT_TEMPLET_ITEM where ITEM__TYPE  = ?  group by ITEM__KFID ";
            String sql = new String();


            if (!TextUtils.isEmpty(moduleId)) {
                sql = "select max(ITEM__UPDATE__DATE)  ,ITEM__KFID,max(ITEM__CREATE__DATE)  from INSPECT_TEMPLET_ITEM where ITEM__TYPE  =  " + (par.equals("PM") ? 2 : 1) + " and  ( ITEM__KFID in ( " + moduleId + " ) or ITEM__KFID = '" + moduleId + "' )  group by ITEM__KFID ";
            } else {
                sql = "select max(ITEM__UPDATE__DATE)  ,ITEM__KFID , max(ITEM__CREATE__DATE)  from INSPECT_TEMPLET_ITEM where ITEM__TYPE  = ?  group by ITEM__KFID ";
            }

            Log.d("AndroidUtils", sql);

            Cursor cursor = daoSession.getDatabase().rawQuery(sql, new String[]{});
            //

            String[] split = moduleId.split(",");


            cursor.moveToFirst();
            while (cursor.getPosition() != cursor.getCount()) {


                TreeMap<String, String> treeMap = new TreeMap<>();
                treeMap.put("moduleId", cursor.getString(1));

                String modify__date = cursor.getString(0);

                String create__date = cursor.getString(2);


                Long time = judgeLong(modify__date, create__date);

                Log.d("AndroidUtils1", "time:" + time + "---" + new Date(time));


                treeMap.put("lastTime", time == null ? "2000-01-01 00:00:00" : TimeUtils.milliseconds2String(time));

                mParDatas.add(treeMap);
                cursor.moveToNext();
            }
            cursor.close();
            ArrayList<String> list2 = new ArrayList(); //临时记录


            for (int j = 0; j < mParDatas.size(); j++) {
                list2.add(mParDatas.get(j).get("moduleId"));
            }


            if (list2.size() != split.length) {
                for (int i = 0; i < split.length; i++) {
                    if (!list2.contains(split[i])) {
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("moduleId", split[i]);
                        hashMap.put("lastTime", "2000-01-01 00:00:00");
                        mParDatas.add(hashMap);
                    }
                }
            }


            for (int i = 0; i < mParDatas.size(); i++) {
                Map<String, String> map = mParDatas.get(i);
                for (int j = 0; j < mParDatas.size(); j++) {
                    Map<String, String> map1 = mParDatas.get(j);
                    String moduleId1 = map.get("moduleId");
                    String moduleId2 = map1.get("moduleId");
                    String lastTime1 = map.get("lastTime");
                    String lastTime2 = map1.get("lastTime");
                    if (moduleId1.indexOf(moduleId2) >= 0 && moduleId1.split(",").length > 1) {
                        if (TimeUtils.string2Date(lastTime1).after(TimeUtils.string2Date(lastTime2))) {
                            mParDatas.remove(map1);         //1包含2删除2
                        }
                    }
                }
            }


            return GsonUtil.parseListToJson(mParDatas);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }


    /**
     * 判断两个时间 哪个大 同时又判空操作
     *
     * @param modifyDate
     * @param createDate
     * @return
     */
    public static Long judgeLong(String modifyDate, String createDate) {
        if (modifyDate == null && createDate != null) {
            return Long.valueOf(createDate);
        } else if (modifyDate != null && createDate == null) {
            return Long.valueOf(modifyDate);
        } else if (modifyDate == null && modifyDate == null) {
            return null;
        } else if (modifyDate != null && createDate != null) {
            return Long.parseLong(modifyDate) > Long.parseLong(createDate) ? Long.parseLong(modifyDate) : Long.parseLong(createDate);
        }
        return null;
    }

    //获得当前日期的所在的时间周期
    public static Date[] getTimeCycle(Integer searchCycle) {

        //searchCycle  1 今天， 2 本周， 3 本月， 其他的话，直接返回2000.1.1-2100.1.1，返回100年
        //4当天向前15天 5当天向前三十天 6当天向前45天 7是两个月
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date today = sdf2.parse(sdf.format(new Date()) + " 00:00:00");
            Date today2 = sdf2.parse(sdf.format(today) + " 23:59:59");
            if (searchCycle == 1) {
                return new Date[]{today, today2};

            } else if (searchCycle == 2) {
                Calendar c1 = Calendar.getInstance(Locale.FRANCE);//法国的周一是第一天
                Calendar c2 = Calendar.getInstance(Locale.FRANCE);//
                c1.setTime(today);
                c1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                c2.setTime(today2);
                c2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

                return new Date[]{c1.getTime(), c2.getTime()};
            } else if (searchCycle == 3) {

                Calendar c1 = Calendar.getInstance();//
                Calendar c2 = Calendar.getInstance();//
                c1.setTime(today);
                c1.set(Calendar.DAY_OF_MONTH, 1);
                c2.setTime(today2);
                c2.add(Calendar.MONTH, 1);
                c2.set(Calendar.DAY_OF_MONTH, 0);
                return new Date[]{c1.getTime(), c2.getTime()};
            } else if (searchCycle == 4) {

                Calendar c1 = Calendar.getInstance();//
                Calendar c2 = Calendar.getInstance();//
                c1.setTime(today);
                c1.set(Calendar.DATE, c1.get(Calendar.DATE) - 15);
                c2.setTime(today2);
                c2.set(Calendar.DATE, c2.get(Calendar.DATE));
                return new Date[]{c1.getTime(), c2.getTime()};
            } else if (searchCycle == 5) {

                Calendar c1 = Calendar.getInstance();//
                Calendar c2 = Calendar.getInstance();//
                c1.setTime(today);
                c1.set(Calendar.DATE, c1.get(Calendar.DATE) - 30);
                c2.setTime(today2);
                c2.set(Calendar.DATE, c2.get(Calendar.DATE));
                return new Date[]{c1.getTime(), c2.getTime()};
            } else if (searchCycle == 6) {

                Calendar c1 = Calendar.getInstance();//
                Calendar c2 = Calendar.getInstance();//
                c1.setTime(today);
                c1.set(Calendar.DATE, c1.get(Calendar.DATE) - 45);
                c2.setTime(today2);
                c2.set(Calendar.DATE, c2.get(Calendar.DATE));
                return new Date[]{c1.getTime(), c2.getTime()};
            } else if (searchCycle == 7) {
                String curTimeString = TimeUtils.getCurTimeString(new SimpleDateFormat("yyyy-MM-dd"));
                String[] split = curTimeString.split("-");
                int year = Integer.parseInt(split[0]);
                int month = Integer.parseInt(split[1]);
                int day = Integer.parseInt(split[2]);

                int month1 = ((month - 1) / 2) * 2;                       // 1,2   3,4   5,6 类推 算一期   头月

                int month2 = month1 + 1;                            //尾月
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();

                c.set(Calendar.MONTH, month1);
                c.set(Calendar.YEAR, year);//先指定年份
                c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
                String first = format.format(c.getTime());


                //获取当前月最后一天
                Calendar ca = Calendar.getInstance();
                ca.set(Calendar.MONTH, month2);
                ca.set(Calendar.YEAR, year);//先指定年份
                ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
                String last = format.format(ca.getTime());

                return new Date[]{sdf2.parse(first + " 00:00:00"), sdf2.parse(last + " 23:59:59")};
            } else {
                return new Date[]{sdf.parse("2000-1-1"), sdf.parse("2000-1-1")};
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    //通过传入的rfid分组标签(地理位置标签)，获取可用的巡检计划列表
    public static List<INSPECT_PLAN> getInspPlanByRfid(Activity activity, DaoSession daoSession, String rfid) {
        List<INSPECT_PLAN> resultList = new ArrayList<>();//最终结果集合

        List<Map<String, Object>> tempList = new ArrayList<>();//临时集合

        if (rfid.indexOf("D") == 0) {
            //要先查地点的id
            PUB_DICTIONARY_ITEM ddItem;
            ddItem = daoSession.getPUB_DICTIONARY_ITEMDao().queryBuilder().where(PUB_DICTIONARY_ITEMDao.Properties.DICT_ID.eq("007114"), PUB_DICTIONARY_ITEMDao.Properties.ITEM_CODE.eq(rfid)).unique();

            if (ddItem == null) {
                return resultList;//不存在ddid
            }
            Set<String> tempSet = new HashSet<>();//用来判断有无重复的临时集合,插入成功，返回true，失败false，重复
            //此处先查分组标签，查到几个算几个。。
            List<INSPECT_GROUP> insgList = daoSession.getINSPECT_GROUPDao().queryBuilder().where(INSPECT_GROUPDao.Properties.INSG_DDID.eq(ddItem.getITEM_ID())).list();
            for (INSPECT_GROUP inspet : insgList) {
                if (tempSet.add(inspet.getRFID_CODE())) {
                    Map<String, Object> tMap = new HashMap<>();
                    tMap.put("rfid", inspet.getRFID_CODE());
                    tMap.put("wzmc", inspet.getWZMC());
                    tMap.put("nid", inspet.getINSGR_PNID());
                    tMap.put("bar", inspet.getBAR_CODE());

                    tempList.add(tMap);
                }
            }
            //再查巡检点表
            List<REPAIR_PLACE> repairList = daoSession.getREPAIR_PLACEDao().queryBuilder().where(REPAIR_PLACEDao.Properties.PLACE_DDID.eq(ddItem.getITEM_ID())).list();
            for (REPAIR_PLACE place : repairList) {
                if (tempSet.add(place.getPLACE_RFID_CODE())) {
                    Map<String, Object> tMap = new HashMap<>();
                    tMap.put("rfid", place.getPLACE_RFID_CODE());
                    tMap.put("wzmc", place.getPLACE_NAME());
                    tMap.put("nid", "RP" + place.getPLACE_ID());
                    tMap.put("bar", place.getPLACE_BAR_CODE());

                    tempList.add(tMap);
                }
            }

        } else if (rfid.indexOf("B") == 0) {

            List<INSPECT_GROUP> insgList;
            insgList = daoSession.getINSPECT_GROUPDao().queryBuilder().where(INSPECT_GROUPDao.Properties.INSG_RFID.eq(rfid)).list();


            for (INSPECT_GROUP inspet : insgList) {
                Map<String, Object> tMap = new HashMap<>();
                tMap.put("rfid", inspet.getRFID_CODE());
                tMap.put("wzmc", inspet.getWZMC());
                tMap.put("nid", inspet.getINSGR_PNID());
                tMap.put("bar", inspet.getBAR_CODE());

                tempList.add(tMap);
            }

        }
        //需要判断找到的所有rfid，有没有巡检计划或者模板
        //这之下是一个循环

        for (Map<String, Object> map : tempList) {
            //查询计划表
            INSPECT_PLANDao planDao = daoSession.getINSPECT_PLANDao();
            //按照计划时间顺序排序
            List<INSPECT_PLAN> list = planDao
                    .queryBuilder()
                    .where(INSPECT_PLANDao.Properties.RFID_CODE.eq(map.get("rfid")))
                    .list();
            if (list != null && list.size() > 0) {
                resultList.addAll(list);
                continue;
            }
            INSPECT_EXTDao extDao = daoSession.getINSPECT_EXTDao();
            List<INSPECT_EXT> extList = extDao.queryBuilder()
                    .where(INSPECT_EXTDao.Properties.INSE_FK_ID.eq(map.get("nid") + ""))
                    .list();
            Long dicId = -1L;
            if (extList == null || extList.size() == 0) {
                continue;
            } else {
                dicId = Long.parseLong(extList.get(0).getINSE_TEMPLATE_ID());
                if (dicId == 0L) {
                    continue;
                }
            }
        }
        return resultList;
    }


    /**
     * 方便渲染 数据转换
     * @param bean 模板表对象
     * @return reps
     */
    public static INSPECT_REPS changIti2Reps(InspectTempletItem bean){

        INSPECT_REPS reps = new INSPECT_REPS();
        reps.setINSPR_REP_ID(-1);                           //设置为-1就是已确认的
        try{
            reps.setINSPR_CYCLE(bean.getITEM_EXT_NUM2()+"");
        }catch (Exception e){
            reps.setINSPR_CYCLE("");
        }
        reps.setINSPR_WX_NEED(0);
        reps.setPLAN_ID(0L);

        reps.setINSPR_PCONTENT(bean.getITEM_NAME());

        reps.setINSPR_RE_VALUE(bean.getITEM_EXT_STRING6());
        try{
            reps.setINSPR_IS_FILL_IN(bean.getITEM_EXT_NUM1()+"");
        }catch (Exception e){
            reps.setINSPR_IS_FILL_IN("");
        }

        //todo  评论没有?  模板不需要  评论
        // reps.setINSPR_COMMENTS("");
        reps.setINSPR_VAL_TYPE(bean.getITEM_EXT_STRING4());
        reps.setINSPR_SEL_VAL(bean.getITEM_EXT_STRING5());
        reps.setINSPR_UNIT(bean.getITEM_EXT_STRING3());
        reps.setINSPR_HG_VAL(bean.getITEM_EXT_STRING2());
        reps.setModelId(bean.getITEM_ID());


        return reps;
    }

}

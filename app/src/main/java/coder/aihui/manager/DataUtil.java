package coder.aihui.manager;

import android.database.Cursor;
import android.text.TextUtils;

import org.greenrobot.greendao.database.Database;

import java.util.List;

import coder.aihui.data.bean.IN_ASSET;
import coder.aihui.data.bean.SYS_DEPT;
import coder.aihui.data.bean.gen.DaoSession;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/7/10 17:34
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/7/10$
 * @ 更新描述  ${TODO}
 */

public class DataUtil {


    public static Observable getDatas(final DaoSession daosession, final String sql, final String[] par) {
        return Observable.create(new Observable.OnSubscribe<IN_ASSET>() {
            @Override
            public void call(Subscriber<? super IN_ASSET> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    Database db = daosession.getDatabase();
                    Cursor cursor = db.rawQuery(sql, par);
                    try {
                        while (cursor.moveToNext() && !subscriber.isUnsubscribed()) {
                            IN_ASSET in_asset = change2Asset(daosession, cursor);
                            subscriber.onNext(in_asset);
                        }
                        subscriber.onCompleted();
                    } catch (Exception e) {
                        e.printStackTrace();
                        subscriber.onError(e);
                    } finally {
                        assert cursor != null;
                        cursor.close();
                    }
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    //将cursor中的内容转为 In_asset
    public static IN_ASSET change2Asset(DaoSession mDaoSession, Cursor cursor) {
        IN_ASSET in_asset = new IN_ASSET();
        List<SYS_DEPT> list = mDaoSession.getSYS_DEPTDao().loadAll();

        /*select a.ID as ID, a.WZMC as WZMC, a.GGXH as GGXH,
        a.PPMC as PPMC, a.SCBH as SCBH, a.KPBH as KPBH,a.KPBH__OLD as KPBH_OLD, a.BAR__CODE as BAR, a.KSMC as KSMC, a.DDMC as DDMC,ic_selected.DQDDMC as DQDDMC,case when ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE__DD != 1 or ic_selected.IS__CHANGE__DD is null) and ic_selected.IS__CHANGE=1 then 4 when ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE != 1 or ic_selected.IS__CHANGE is null) and ic_selected.IS__CHANGE__DD=1 then 3 when ic_selected.ASSET__ID is not null and ic_selected.IS__CHANGE=1 and ic_selected.IS__CHANGE__DD=1 then 5 when ic_selected.ASSET__ID is not null then 1 else 2 end as PDAID from IN__ASSET a  left join PDA__ASSET__CHECK ic_selected
        on a.ID=ic_selected.ASSET__ID and ic_selected.QCPC=20170523
        order by PDAID desc,ID limit ? offset ?*/

        int pdaid = cursor.getInt(cursor.getColumnIndex("PDAID"));
        in_asset.setWhichType(pdaid);
        in_asset.setID(cursor.getLong(cursor.getColumnIndex("ID")));
        in_asset.setWZMC(cursor.getString(cursor.getColumnIndex("WZMC")));

        in_asset.setGGXH(cursor.getString(cursor.getColumnIndex("GGXH")));
        in_asset.setPPMC(cursor.getString(cursor.getColumnIndex("PPMC")));
        in_asset.setSCBH(cursor.getString(cursor.getColumnIndex("SCBH")));
        in_asset.setRFID_CODE(cursor.getString(cursor.getColumnIndex("RFID")));
        in_asset.setBAR_CODE(cursor.getString(cursor.getColumnIndex("BAR")));
        in_asset.setKSMC(cursor.getString(cursor.getColumnIndex("KSMC")));
        in_asset.setDDMC(cursor.getString(cursor.getColumnIndex("DDMC")));
        in_asset.setWZMC(cursor.getString(cursor.getColumnIndex("WZMC")));


        in_asset.setKPBH_OLD(cursor.getString(cursor.getColumnIndex("KPBH_OLD")));
        in_asset.setKPBH(cursor.getString(cursor.getColumnIndex("KPBH")));
        //后面加的 为了显示
        String change__dept = cursor.getString(cursor.getColumnIndex("CHANGE__DEPT"));
        String is__change = cursor.getString(cursor.getColumnIndex("IS__CHANGE"));
        if (!TextUtils.isEmpty(is__change) && is__change.equals("1") && !TextUtils.isEmpty(change__dept)) {
            for (SYS_DEPT ss : list) {
                if ((ss.getDEPT_ID() + "").equals(change__dept)) {            //如果相等 前者是不会为空的
                    in_asset.setChange_dept(ss.getDEPT_NAME());
                }
            }
        }
        String dqddmc = cursor.getString(cursor.getColumnIndex("DQDDMC"));
        if (dqddmc != null && !"".equals(dqddmc)) {
            in_asset.setDqddmc(dqddmc);
        } else {
            in_asset.setDqddmc(cursor.getString(cursor.getColumnIndex("DDMC")));//未盘点的放入自己
        }

        return in_asset;
    }


}

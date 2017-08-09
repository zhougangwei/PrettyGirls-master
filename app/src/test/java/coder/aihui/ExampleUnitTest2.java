package coder.aihui;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import coder.aihui.data.bean.PUR_CONTRACT_PLAN_DETAIL;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.data.bean.gen.PUR_CONTRACT_PLAN_DETAILDao;
import coder.aihui.http.AiHuiLoginServices;
import coder.aihui.http.MyRetrofit;
import coder.aihui.util.GsonUtil;
import rx.functions.Action1;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest2 {


    @Mock
    DaoSession daoSession;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void addition_isCorrect() throws Exception {

        List<PUR_CONTRACT_PLAN_DETAIL> ll = daoSession.getPUR_CONTRACT_PLAN_DETAILDao().queryBuilder()
                .where((PUR_CONTRACT_PLAN_DETAILDao.Properties.CHECK_STATUS.eq(1)))
                .where(PUR_CONTRACT_PLAN_DETAILDao.Properties.IS_UP.eq(2))
                .orderAsc(PUR_CONTRACT_PLAN_DETAILDao.Properties.CONTRACT_ID)
                .list();

        //存取不重复的单号
        // TreeSet ts = new TreeSet();


        TreeMap<String, String> treeMap = new TreeMap<String, String>();

        for (int i = 0; i < ll.size(); i++) {
            //                   ts.add(ll.get(i).getDH_ID());
            //                   ts.add(ll.get(i).getYSR_IDS());
            //一个单号对应几个验收人
            ll.get(i).setYSSJ(new Date());

            daoSession.update(ll.get(i));
            treeMap.put(ll.get(i).getDH_ID(), ll.get(i).getYSR_IDS());


        }

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();


        Iterator ite = treeMap.keySet().iterator();
        while (ite.hasNext()) {
            String string = (String) ite.next();
            sb1.append(string).append(",");
            sb2.append(treeMap.get(string)).append("|");
        }

        String string = sb1.toString();
        //切掉最后一个
        String substring = string.substring(0, string.length() - 1);        //单号

        String string2 = sb2.toString();
        //切掉最后一个
        String substring2 = string2.substring(0, string2.length() - 1);        //验收人


        String json = GsonUtil.parseListToJson(ll);

        String substring1 = json.substring(1);

        StringBuilder sb = new StringBuilder();


        String finaljson = sb.append("[{\"TYPE\":\"")
                .append(substring)                      //单号
                .append("\"},")
                .append("{\"YSR_IDS\":\"")
                .append(substring2)
                .append("\"},")
                .append(substring1).toString();//大的json数据
        Map<String, String> map = new HashMap<>();
        map.put("dataJson", finaljson);

        MyRetrofit.getRetrofit()
                .create(AiHuiLoginServices.class)
                .upLoadPurPlan(map).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("ExampleUnitTest2", s);
            }
        });

    }


}
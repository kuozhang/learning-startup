package misc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Foo {

    private static Logger logger = LoggerFactory.getLogger(Foo.class);

    public static void main(String[] args) {
        //Map<String,Object> map = new HashMap<String, Object>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        System.out.println(sdf.format(new Date()));
        //HashMap<String, String> test = new HashMap<String, String>() {{
        //    put("foo", "bar");
        //    put("hello", "world");
        //}};
        //
        //System.out.println(test.get("foo"));

        //System.out.println(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        //map.put("k1", new HashedMap<String, String>());
        //map.put("k2", "v2");
        //System.out.println(JSONObject.toJSON(map).toString());

        //Long sellerId = 2075581043L;
        //System.out.println(getSellBucket(sellerId));
        //System.out.println(getInsertSqlOfCrowdIncr(1000000L));

        //System.out.println(getDateAfterNowByHour(1, "yyyyMMdd"));
    }

    private static String getSellBucket(Long sellerId){
        Long sellerBucket = sellerId % 2048L;
        return String.valueOf(sellerBucket);
    }

    private static String getInsertSqlOfCrowdIncr(Long sellerId){
        String sellerBucket = getSellBucket(sellerId);
        return "INSERT INTO TABLE crmx_crowd_snapshot_incr PARTITION (ds='${bizdate}', seller_bucket=" + sellerBucket + ")\n";
    }

    /**
     * 获取当前之后或之前的几个小时的时间
     * @param hours
     * @param format
     * @return
     */
    public static String getDateAfterNowByHour(int hours, String format) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return new SimpleDateFormat(format).format(cal.getTime());
    }
}

package kuo.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Misc {

    // the logger
    private static Logger logger = LoggerFactory.getLogger(Misc.class);

    // the instance
    private static Misc instance = new Misc();

    private Misc(){
    }

    public static void main(String[] args) {
//        instance.testSplit();
        instance.testDate();
    }

    private void testSplit(){
        String str = "boo:and:foo";

        printArray(str.split(":", 5));
        printArray(str.split(":", 2));
        printArray(str.split(":", 0));
        printArray(str.split(":", -2));
        printArray(str.split("o", 5));
        printArray(str.split("o", 2));
        printArray(str.split("o", 0));
        printArray(str.split("o", -1));
    }

    private static void printArray(String[] array){
        for(String entry: array){
            System.out.print(entry + " ");
        }

        System.out.println();
    }

    private void testDate(){
        System.out.println(getDateAfterNowByHour(1, "yyyyMMdd"));
    }

    /**
     * 获取当前之后或之前的几个小时的时间
     * @param hours
     * @param format
     * @return
     */
    public String getDateAfterNowByHour(int hours, String format) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return new SimpleDateFormat(format).format(cal.getTime());
    }

    private void testDateTime(){
        String datePath = "year=2017/month=09/date=29";
        String dateStr = datePath.substring(5, 9) + "-" + datePath.substring(16, 18) + "-" + datePath.substring(23);
        System.out.println(dateStr);
    }



}

package com.ming;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * 该包的API提供了大量相关的方法，这些方法一般有一致的方法前缀：
 * <p>
 * of：静态工厂方法。
 * <p>
 * parse：静态工厂方法，关注于解析。
 * <p>
 * get：获取某些东西的值。
 * <p>
 * is：检查某些东西的是否是true。
 * <p>
 * with：不可变的setter等价物。
 * <p>
 * plus：加一些量到某个对象。
 * <p>
 * minus：从某个对象减去一些量。
 * <p>
 * to：转换到另一个类型。
 * <p>
 * at：把这个对象与另一个对象组合起来，例如： date.atTime(time)。
 * <p>
 * format：按照合适的格式 格式化成相应的格式的字符串
 * <p>
 *
 * @author ming
 * @date 2018-06-30 15:33:10
 */
public class TestTime {

    /**
     * 获取当前时间
     *
     * @author ming
     * @date 2018-06-30 15:31:33
     */
    @Test
    public void testNewDate() {
        System.out.println("获取带纳秒的时间:" + Instant.now());
        System.out.println("获取年月日:" + LocalDate.now());
        System.out.println("获取时分秒:" + LocalTime.now());
        System.out.println("获取年月日时分秒:" + LocalDateTime.now());
        System.out.println("获取时分秒带时区:" + OffsetTime.now());
        System.out.println("获取年月日时分秒带时区:" + OffsetDateTime.now());
        System.out.println("获取时分秒带详细时区信息:" + ZonedDateTime.now());
        System.out.println("获取年:" + Year.now());
        System.out.println("获取年月:" + YearMonth.now());
        System.out.println("获取月日:" + MonthDay.now());
    }

    /**
     * 直接根据参数转换成时间类型
     *
     * @author ming
     * @date 2018-06-30 15:32:59
     */
    @Test
    public void testOf() {
        System.out.println("根据秒数获取时间点:" + Instant.ofEpochSecond(1000));
        System.out.println("根据年月日获取年月日时间对象:" + LocalDate.of(2018, 11, 11));
        System.out.println("根据时分秒获取时分秒对象:" + LocalTime.of(11, 11, 11));
        System.out.println("根据年月日时分秒获取年月日时分秒时间对象:" + LocalDateTime.of(2018, 11, 11, 11, 11, 11));
        System.out.println("根据时分秒获取时分秒带时区对象:" + OffsetTime.of(11, 11, 11, 1, ZoneOffset.UTC));
        System.out.println("根据年月日时分秒获取年月日时分秒时间带时区对象:" + OffsetDateTime.of(2018, 11, 11, 11, 11, 11, 11, ZoneOffset.UTC));
        System.out.println("根据年月日时分秒获取年月日时分秒时间带时区对象:" + ZonedDateTime.of(2018, 11, 11, 11, 11, 11, 11, ZoneId.systemDefault()));
        System.out.println("根据年数转会成年对象:" + Year.of(2018));
        System.out.println("根据年月数获取年月对象:" + YearMonth.of(2018, 11));
        System.out.println("根据月日数获取月日对象" + MonthDay.of(11, 11));
    }


    /**
     * 将指定的文本转换成 指定格式的 time对象
     *
     * @author ming
     * @date 2018-07-02 14:41:23
     */
    @Test
    public void testParse() {
        System.out.println(LocalDateTime.parse("2011-12-03T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        //.....略  跟 of差不多 主要是解析字符串成时间相关对象

    }


    /**
     * 根据时间对象获取 属性
     *
     * @author ming
     * @date 2018-07-05 16:49:34
     */
    @Test
    public void testGet() {
        System.out.println("获取当前时间点的时间戳(s)" + Instant.now().getEpochSecond());
        System.out.println(LocalDateTime.now().getDayOfWeek());
    }


    /**
     * 判断某些属性  例如判断时间前后
     *
     * @author ming
     * @date 2018-07-05 16:50:06
     */
    @Test
    public void testIs() {
        System.out.println(Instant.now().isAfter(Instant.now()));
        System.out.println(Instant.now().isBefore(Instant.now()));
    }

    /**
     * 获取一些特殊的时间点
     *
     * @author ming
     * @date 2018-07-05 16:51:36
     */
    @Test
    public void testWith() {
        System.out.println(LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()));
    }

    /**
     * 时间相加
     *
     * @author ming
     * @date 2018-07-05 16:52:49
     */
    @Test
    public void testPlus() {
        System.out.println(Instant.now().plusMillis(999999));
    }

    /**
     * 时间相减
     *
     * @author ming
     * @date 2018-07-05 16:52:59
     */
    @Test
    public void testMinus() {
        System.out.println(Instant.now().minusMillis(99999999));
    }


    /**
     * 将时间对象转换成其他的time对象
     *
     * @author ming
     * @date 2018-07-05 16:53:16
     */
    @Test
    public void testTo() {
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(LocalDateTime.now().toLocalTime());
    }


    /**
     * 设置时区
     *
     * @author ming
     * @date 2018-07-05 16:53:29
     */
    @Test
    public void testAt() {
        System.out.println(Instant.now().atOffset(ZoneOffset.UTC));
        System.out.println(Instant.now().atZone(ZoneId.systemDefault()));
    }

    /**
     * 时间加减
     * ChronoUnit来控制加减的量级
     *
     * @author ming
     * @date 2018-07-05 16:58:19
     * @see ChronoUnit#NANOS 纳秒
     * @see ChronoUnit#MICROS 微秒
     * @see ChronoUnit#MILLIS 毫秒
     * @see ChronoUnit#SECONDS 秒
     * @see ChronoUnit#MINUTES 分
     * @see ChronoUnit#HOURS 小时
     * @see ChronoUnit#HALF_DAYS 半天  12小时
     * @see ChronoUnit#DAYS 一天  24小时
     * @see ChronoUnit#WEEKS 一周
     * @see ChronoUnit#MONTHS 一月
     * @see ChronoUnit#YEARS 一年
     * @see ChronoUnit#DECADES 十年
     * @see ChronoUnit#CENTURIES 百年
     * @see ChronoUnit#MILLENNIA 千年
     * @see ChronoUnit#ERAS 十亿年
     * @see ChronoUnit#FOREVER 永远 Long.MAX_VALUE
     */
    @Test
    public void testPlusAndMinus() {
        System.out.println("加一天" + LocalDateTime.now().plus(1, ChronoUnit.DAYS));
        System.out.println("减一天" + LocalDateTime.now().minus(1, ChronoUnit.DAYS));
    }

    /**
     * 判断是否是闰年
     *
     * @author ming
     * @date 2018-07-05 17:09:34
     */
    @Test
    public void testLeapYear() {
        System.out.println(LocalDateTime.now().toLocalDate().isLeapYear());
    }

    /**
     * 计算两个时间之间的差值
     *
     * @author ming
     * @date 2018-07-05 17:12:37
     */
    @Test
    public void testPeriod() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 11, 11, 11, 11);
        Period period = Period.between(localDateTime.toLocalDate(), LocalDate.now());
        System.out.println("间隔时间:" + period.getYears() + "年" + period.getMonths() + "个月" + period.getDays() + "天");
    }

    /**
     * 根据formatter枚举格式化时间
     *
     * @author ming
     * @date 2018-07-05 17:32:32
     * @see DateTimeFormatter#ofPattern(String) 自定义格式化格式
     * @see DateTimeFormatter#ISO_LOCAL_DATE yyyy-MM-dd
     * @see DateTimeFormatter#ISO_OFFSET_DATE yyyy-MM-dd+offset
     * @see DateTimeFormatter#ISO_DATE 'yyyy-MM-dd' or 'yyyy-MM-dd+offset'.
     * @see DateTimeFormatter#ISO_LOCAL_TIME HH:mm or HH:mm:ss
     * @see DateTimeFormatter#ISO_OFFSET_TIME HH:mm+offset or HH:mm:ss+offset
     * @see DateTimeFormatter#ISO_TIME HH:mm or HH:mm:ss or HH:mm:ss+offset
     * @see DateTimeFormatter#ISO_LOCAL_DATE_TIME yyyy-MM-ddTHH:mm:ss
     * @see DateTimeFormatter#ISO_OFFSET_DATE_TIME yyyy-MM-ddTHH:mm:ss+offset
     * @see DateTimeFormatter#ISO_ZONED_DATE_TIME yyyy-MM-ddTHH:mm:ss+offset[zone]
     * @see DateTimeFormatter#ISO_ORDINAL_DATE yyyy-days
     * @see DateTimeFormatter#ISO_WEEK_DATE yyyy-week-days
     * @see DateTimeFormatter#ISO_INSTANT yyyy-MM-ddTHH:mm:ssZ
     * @see DateTimeFormatter#BASIC_ISO_DATE yyyyMMdd
     * @see DateTimeFormatter#RFC_1123_DATE_TIME 'Tue, 3 Jun 2008 11:05:30 GMT'
     */
    @Test
    public void testFormat() {
        System.out.println(LocalDate.parse("20181111", DateTimeFormatter.BASIC_ISO_DATE));
    }

    /**
     * 将时间 按照格式 格式化成string
     *
     * @author ming
     * @date 2018-07-05 18:23:40
     */
    @Test
    public void testFormatString() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));
    }


    /**
     * jdk8的time 和jdk8之前的date相关的类型转换
     *
     * @author ming
     * @date 2018-07-05 18:29:21
     */
    @Test
    public void testConverter() {
        //jdk8 time 转换成Date
        java.util.Date juDate = java.util.Date.from(Instant.now());
        System.out.println(juDate);
        java.util.Date juDate1 = java.util.Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        System.out.println(juDate1);

        //Date 转换成jdk8 time
        java.util.Date date = new java.util.Date();
        System.out.println(date.toInstant());
        System.out.println(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}

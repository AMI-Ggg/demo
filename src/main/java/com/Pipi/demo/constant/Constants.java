package com.Pipi.demo.constant;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Constants {

	/** 常量int类型1 **/
	public static final int CONSTANT_ONE_INT = 1;
	/** 常量int类型0 **/
	public static final int CONSTANT_ZERO_INT = 0;
	/** 常量int类型2 **/
	public static final int CONSTANT_TWO_INT = 2;
	/** 常量int类型3 **/
	public static final int CONSTANT_THREE_INT = 3;
	/** 常量int类型4 **/
	public static final int CONSTANT_FOUR_INT = 4;
	/** 常量int类型5 **/
	public static final int CONSTANT_FIVE_INT = 5;
	/** 常量int类型6 **/
	public static final int CONSTANT_SIX_INT = 6;
	/** 常量int类型7 **/
	public static final int CONSTANT_SEVEN_INT = 7;
	/** 常量int类型8 **/
	public static final int CONSTANT_EIGHT_INT = 8;
	/** 常量int类型9 **/
	public static final int CONSTANT_NINE_INT = 9;
	/** 常量int类型10 **/
	public static final int CONSTANT_TEN_INT = 10;
	/** 常量int类型11 **/
	public static final int CONSTANT_ELEVEN_INT = 11;
	/** 常量int类型12 **/
	public static final int CONSTANT_TWELVE_INT = 12;
	/** 常量int类型20 **/
	public static final int CONSTANT_TWENTY_INT = 20;
	/** 常量int类型100 **/
	public final static int CONSTANT_HUNDRED_INT = 100;
	/** 常量字符串类型1 **/
	public static final String CONSTANT_ONE_STR = "1";
	/** 常量字符串类型2 **/
	public static final String CONSTANT_TWO_STR = "2";
	/** 常量字符串类型3 **/
	public static final String CONSTANT_THREE_STR = "3";
	/** 常量字符串类型5 **/
	public static final String CONSTANT_FIVE_STR = "5";
	/** 常量Long类型0L **/
	public static final Long CONSTANT_ZERO_LONG = 0L;
	/** 常量Long类型1L **/
	public static final Long CONSTANT_ONE_LONG = 1L;
	/** 常量Long类型5L **/
	public static final Long CONSTANT_FIVE_LONG = 5L;
	/** 常量Long类型1000L **/
	public static final Long CONSTANT_THOUSAND_LONG = 1000L;
	/** 常量Long类型50L **/
	public static final Long CONSTANT_FIFTY_LONG = 50L;
	/** 常量Long类型100L **/
	public final static Long CONSTANT_HUNDRED_LONG = 100L;
	/** 常量Long类型300L **/
	public static final Long CONSTANT_THREEHUNDRED_LONG = 300L;
	/** 常量Long类型3000L **/
	public static final Long CONSTANT_THREE_THOUSAND_LONG = 3000L;
	/** 常量BigDecimal类型0 **/
	public static final BigDecimal CONSTANT_ZERO_DECIMAL = BigDecimal.valueOf(0);
	/** 缓存过期时间一个月 **/
	public final static int REDIS_ONEMONTH = 2592000;// 60*60*24*30
	/** 缓存过期时间一天 **/
	public final static int REDIS_ONEDAY = 86400;// 60*60*24
	/** 缓存过期时间半天 **/
	public final static int REDIS_HALFDAY = 43200;// 60*60*12
	/** 缓存过期时间十天 **/
	public final static int REDIS_TENDAY = 864000;// 60*60*24*10
	/** 缓存过期时间十分钟 **/
	public final static int REDIS_TENMINUTE = 600;// 60*10
	/** 过期时间为一周 **/
	public static final int REDIS_ONEWEEK = 604800;
	/** 缓存过期时间三小时 **/
	public final static int REDIS_THREEHOUR = 10800;// 3*60*60
	/** 缓存过期时间一小时 **/
	public final static int REDIS_ONEHOUR = 3600;// 60*60
	/** 缓存过期时间半小时 **/
	public final static int REDIS_HALFHOUR = 1800;// 60*30
	/** 缓存过期时间半分钟 **/
	public final static int REDIS_HALFMINUTE = 30;
	/** 缓存过期时间90秒 **/
	public final static int REDIS_NINETY = 90;
	/** 空字符串 **/
	public final static String CONSTANT_EMPTY_STR = "";
	/** 时间一天 **/
	public final static int CONSTANT_ONEDAY_SECOND = 86400;// 60*60*24
	/** 半小时毫秒数 **/
	public final static int HALFHOUR_MILLISECOND = 1800000;
	/** 一个月的毫秒数 **/
	public final static long ONEMONTH_MILLISECOND = 2592000000L;// 60*60*24*30*1000
	/** 常量Long类型5000L **/
	public static final Long CONSTANT_FIVE_THOUSAND_LONG = 5000L;
	/** 常量30秒 **/
	public static final int CONSTANT_THIRTY_SECOND = 30000;
	/** 常量BigDecimal类型100 **/
	public static final BigDecimal CONSTANT_HUNDRED_DECIMAL = BigDecimal.valueOf(100);
	/** 缺省ip地址 **/
	public static final String DEFAULT_IP = "127.0.0.1";
	/** 空区域编码 **/
	public static final int ADDR_EMPLY_AREA_CODE = -1;
	/** 系统自身账号 **/
	public static final String SYS_USER_MIP = "system_user";
	/** 常量BigDecimal类型300 **/
	public final static BigDecimal CONSTANT_THREE_HUNDRED_DECIMAL = BigDecimal.valueOf(300);
	/** 无效的int **/
	public static final int CONSTANT_INVALID_INT = -1;
	/** 无效的string **/
	public static final String DEFAULT_INVALID_STR = "-1";
	/** 3秒钟毫秒数 **/
	public final static int THREE_SECOND_MILLISECOND = 3000;
	/** 100毫秒 **/
	public final static long ONE_HUNDRED_LONG = 100L;
	/** 系统自身用户 **/
	public static final String SYS_USER_NAME = "系统";
	/** 常量字符串类型0 **/
	public static final String CONSTANT_ZERO_STR = "0";
	/** 默认分割符号 **/
	public static final String DEFAULT_SPLIT_SIGN = ",";
	/** token分割符号 **/
	public static final String TOKEN_SPLIT_SIGN = "&&";
	/** 模糊匹配符号 **/
	public static final String MATCH_VAGUE_SIGN = "*";
	/** 常量-1 **/
	public static final int CONSTANT_MINUS_ONE_INT = -1;
	/** 操作失败错误码92 **/
	public static final String CONSTANT_ERROR_CODE = "92";
	/** 参数无效错误码96 **/
	public static final String PARAM_CHECK_ILLEGAL = "96";
	
	public static final int DEFAULT_PAGE_INDEX = 1;
	public static final int DEFAULT_PAGE_SIZE = 20;
	
	public static class ExtraConst{
		public static final String errType = "errType";
	}

	public static class RedisKey {
		public static final String EXAMPLE_PRE = "example_pre";
	}

	public static final int REDIS_LOCK_EXPIRED_MILISECONDS = 200;

	public static class ErrCode {
		public static final int PARAM_CHECK_ILLEGAL = 5005;
		public static final int OPERATE_FAIL = 92;
	}

	public static Map<Integer, String> ErrInfo = new HashMap<Integer, String>();
	static {
		ErrInfo.put(ErrCode.PARAM_CHECK_ILLEGAL, "请求参数不合法");
	}
}

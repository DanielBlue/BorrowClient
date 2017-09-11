package pers.maoqi.core.util;

import android.util.Log;

/**
 * Created by maoqi on 2017/5/31.
 * Log管理工具类
 */

public class LogUtils {
    private LogUtils(){
        //构造方法私有
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static final String LOG = "LogUtils";

    public static void log(String message){
        Logger.i(LOG, message);
    }
    public static void i(String tag,String msg){
        Logger.i(tag,msg);
    }

    public static void d(String tag,String msg){
        Logger.d(tag,msg);
    }

    public static void v(String tag,String msg){
        Logger.v(tag,msg);
    }

    public static void w(String tag,String msg){
        Logger.w(tag,msg);
    }

    public static void e(String tag,String msg){
        Logger.e(tag,msg);
    }


    private static class Logger {

        public static int RELEASE_LEVEL = 20;
        public static int DEBUG_LEVEL = 0;

        public static int LEVEL = RELEASE_LEVEL;

        public static int B = 17;
        public static int X = 16;
        public static int VERBOSE = 15;
        public static int DEBUG = 14;
        public static int INFO = 13;
        public static int WARN = 12;
        public static int ERROR = 11;

        /**
         * verbose
         */
        public static void v(String tag, String msg) {
            try {
                if (LEVEL < VERBOSE) {
                    Log.v(tag, msg);
                }
            } catch (Exception e) {
            }
        }

        /**
         * debug
         */
        public static void d(String tag, String msg) {
            try {
                if (LEVEL < DEBUG) {
                    Log.d(tag, msg);
                }
            } catch (Exception e) {
            }
        }

        /**
         * info
         */
        public static void i(String tag, String msg) {
            try {
                if (LEVEL < INFO) {
                    Log.i(tag, msg);
                }
            } catch (Exception e) {
            }
        }

        /**
         * warn
         */
        public static void w(String tag, String msg) {
            try {
                if (LEVEL < WARN) {
                    Log.w(tag, msg);
                }
            } catch (Exception e) {
            }
        }

        /**
         * error
         */
        public static void e(String tag, String msg) {
            try {
                if (LEVEL < ERROR) {
                    Log.e(tag, msg);
                }
            } catch (Exception e1) {
            }
        }

    }
}

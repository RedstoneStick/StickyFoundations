package net.guwy.sticky_foundations.utils;

public class NumberUtils {


    /** Map function from Arduino*/
    public class map {  // Can't live without it
        public static double mapDouble(double x, double in_min, double in_max, double out_min, double out_max)
        {
            return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
        }

        public static int mapInt(int x, int in_min, int in_max, int out_min, int out_max)
        {
            return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
        }

        public static long mapLong(long x, long in_min, long in_max, long out_min, long out_max)
        {
            return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
        }
    }

    /**
     * Quick rounds a number to set decimals
     * @param num number to process
     * @param rounding rounding (ex: 0.1, 0.01, 0.5...)
     * @return rounded number
     */
    public static double quickRound(double num, double rounding){
        double r = Math.pow(rounding, -1);
        return Math.round(num * r) / r;
    }
}

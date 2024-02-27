package net.guwy.sticky_foundations.utils;

import net.minecraft.network.chat.Component;

public class NumberToTextConverter {
    private static final int QUINTILLION = (int) Math.pow(10, 18);
    private static final int QUADRILLION = (int) Math.pow(10, 15);
    private static final int TRILLION = (int) Math.pow(10, 12);
    private static final int BILLION = (int) Math.pow(10, 9);
    private static final int MILLION = (int) Math.pow(10, 6);
    private static final int THOUSAND = (int) Math.pow(10, 3);


    /** The naming didn't made sense,
     *  use BigNumberToText() instead.
     *  Keeping this to not break old code*/
    @Deprecated
    public static String EnergyToText(int energy){
        return BigNumberToText(energy);
    }

    public static String BigNumberToText(int number){
        return BigLongToText(number);
    }

    public static String BigLongToText(long number){
        String text;
        double val;
        long numAbs = Math.abs(number);

        if(numAbs >= QUINTILLION){
            val = (double) number / QUINTILLION;
            val = (Math.floor(val * 10)) / 10;

            text = Double.toString(val);
            text = text + "E";
        }

        else if(numAbs >= QUADRILLION){
            val = (double) number / QUADRILLION;
            val = (Math.floor(val * 10)) / 10;

            text = Double.toString(val);
            text = text + "P";
        }

        else if(numAbs >= TRILLION){
            val = (double) number / TRILLION;
            val = (Math.floor(val * 10)) / 10;

            text = Double.toString(val);
            text = text + "T";
        }

        else if(numAbs >= BILLION){
            val = (double) number / BILLION;
            val = (Math.floor(val * 10)) / 10;

            text = Double.toString(val);
            text = text + "G";
        }

        else if(numAbs >= MILLION){
            val = (double) number / MILLION;
            val = (Math.floor(val * 10)) / 10;

            text = Double.toString(val);
            text = text + "M";
        }

        else if(numAbs >= THOUSAND){
            val = (double) number / THOUSAND;
            val = (Math.floor(val * 10)) / 10;

            text = Double.toString(val);
            text = text + "k";
        }

        else {
            val = number;
            text = Integer.toString((int) val);
        }
        return text;
    }

    public static String TicksToYMD(long ticks){
        int years = (int) (ticks / 8760000);
        ticks = ticks % 8760000;
        int days = (int) (ticks / 24000);
        ticks =  ticks % 24000;
        double hours = ticks / 1200.0;
        hours = Math.round(hours * 100) / 100.0;

        String text = Integer.toString(years) + " " + Component.translatable("tooltip.radiated.generic.year_symbol").getString() + ", "
                + Integer.toString(days) + " " + Component.translatable("tooltip.radiated.generic.day_symbol").getString() + ", "
                + Double.toString(hours) + " " + Component.translatable("tooltip.radiated.generic.hour_symbol").getString();
        return text;
    }
}

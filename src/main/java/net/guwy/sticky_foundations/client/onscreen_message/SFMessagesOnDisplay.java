package net.guwy.sticky_foundations.client.onscreen_message;

import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class SFMessagesOnDisplay {
    /** Messages to display and their timers
     * Hopefully these won't desync
     */
    static List<String> Messages = new ArrayList<String>();
    static List<Integer> MessageTimers = new ArrayList<Integer>();

    static List<Integer> IndexOfMessagesMarkedForRemoval = new ArrayList<Integer>();



    public static void addNewMessage(String message, int timer){

        // If the same message exists it replaces its timer with the new one
        if(Messages.contains(message)){
            int index = Messages.indexOf(message);
            MessageTimers.set(index, timer);
        }

        // If its a unique message it creates a new instance
        else {
            Messages.add(message);
            MessageTimers.add(timer);
        }
    }
    public static void addNewMessage(String message){
        // 40 ticks (2 seconds)
        addNewMessage(message, 40);
    }



    public static void onClientTick(){
        markMessagesForRemoval();
        removeMarkedMessages();
        decreaseTimers();
    }

    private static void decreaseTimers(){
        int size = Messages.size();

        // Checks each index of the lists
        for (int index = 0; index < size; index++){

            // Decreases Timers by 1
            MessageTimers.set(index, MessageTimers.get(index)-1);
        }
    }

    private static void markMessagesForRemoval(){
        int size = Messages.size();

        // Checks each index of the lists
        for (int index = 0; index < size; index++){

            // if its timer <= 0 then mark that index for removal
            if(MessageTimers.get(index) <= 0){
                IndexOfMessagesMarkedForRemoval.add(index);
            }
        }
    }

    private static void removeMarkedMessages(){
        int size = IndexOfMessagesMarkedForRemoval.size();

        // Checks each index of the list from reverse
        // as it may cause a crash when done from first to last due to the shifting of elements when 1 is removed
        for (int index = size - 1; index >= 0; index--){

            // Get the index to remove
            int indexToProcess = IndexOfMessagesMarkedForRemoval.get(index);

            // Remove index
            Messages.remove(indexToProcess);
            MessageTimers.remove(indexToProcess);
        }

        // Purges the Index Markings
        IndexOfMessagesMarkedForRemoval.clear();
    }


    /** Stuff used for rendering the background
     * Width is the exact width of the displayed message in pixels
     *
     * Amount is used for the height of the background
     */
    public static int getLongestMessageWidth(){
        int size = Messages.size();
        int length = 0;

        // Checks each index of the lists
        for (int index = 0; index < size; index++){

            String s = Messages.get(index);

            // Gets display length from minecrafts text display api
            int strlen = Minecraft.getInstance().font.width(s);

            // If the new length is longer make the length to that
            if(strlen > length) length = strlen;
        }

        return length;

    }

    public static int getMessageCount(){
        return Messages.size();
    }


}

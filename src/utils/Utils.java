package utils;
import org.powerbot.script.util.GeItem;
import kitkataddywhack.KitKatAddyWhack;

public class Utils {
	
    	public static int getPrice(final int id) {
    		return GeItem.getProfile(id).getPrice(GeItem.PriceType.CURRENT).getPrice();
    	}

    	public static void log(String string){
    		System.out.println(string);
    	}
    	
        public static int perHour(int value) {
        	return (int) ((value) * 3600000D / (System.currentTimeMillis() - KitKatAddyWhack.startTime));
        }
}
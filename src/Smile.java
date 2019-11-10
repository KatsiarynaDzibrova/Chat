import java.util.HashMap;

public class Smile {

    private Integer index;

    Smile(Integer index ) {
        this.index = index;
        }

        String getAsString() {
            return keyToSmile.get(index);
        }
    private static HashMap<Integer, String> keyToSmile = new HashMap<Integer, String>() {
            {
                put(0, "(:");
                put(1, "¯\\_(ツ)_/¯ ");
                put(2, ":(");
                put(3, ":)");
                put(4, ":0");
                put(5, ":*");
                put(6, ":X");
                put(7, "XD");
            }
        };



}

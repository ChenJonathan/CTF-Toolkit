package format;


/**
 * Created by bird on 5/8/2016.
 */
public abstract class Format {

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * Decode from said format
     * @param s String in format type
     * @return Always hex string
     */
    public abstract String decode(String s);

    /**
     * Encode into said format
     * @param s Always hex string
     * @return Formatted in said format
     */
    public abstract String encode(String s);

    /**
     * Conversion from byte array to a hex string
     * @param b Byte array
     * @return Hex equivalent of the bytes in a string
     */
    protected String bytesToHexString(byte[] b) {
        char[] hexChars = new char[b.length * 2];
        for ( int j = 0; j < b.length; j++ ) {
            int v = b[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];}
        return new String(hexChars);
    }

    /**
     * Conversion from hex string to byte array
     * @param s Hex string
     * @return Byte array
     */
    protected byte[] hexStringToByteArray(String s) {
        if (s.length() % 2 != 0) {
            s = String.format("%" + (s.length() + 1) + "s", s).replace(" ", "0");
        }
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    //force rewrite
    public abstract String toString();
}

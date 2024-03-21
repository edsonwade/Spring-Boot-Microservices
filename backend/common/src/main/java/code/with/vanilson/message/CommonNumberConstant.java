package code.with.vanilson.message;

public class CommonNumberConstant {
    // Integer Constants
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    public static final int NINE = 9;
    public static final int TEN = 10;

    public static final int ELEVEN = 11;
    public static final int TWELVE = 12;
    public static final int THIRTEEN = 13;
    public static final int FOURTEEN = 14;
    public static final int FIFTEEN = 15;
    public static final int SIXTEEN = 16;
    public static final int SEVENTEEN = 17;
    public static final int EIGHTEEN = 18;
    public static final int NINETEEN = 19;
    public static final int TWENTY = 20;

    // Double Constants
    public static final double PI = 3.14159265358979323846;
    public static final double EULER_CONSTANT = 2.71828182845904523536;
    public static final double GOLDEN_RATIO = 1.61803398874989484820;

    public static final double SQUARE_ROOT_OF_TWO = 1.41421356237309504880;
    public static final double SQUARE_ROOT_OF_THREE = 1.73205080756887729352;
    public static final double SQUARE_ROOT_OF_FIVE = 2.23606797749978969641;
    public static final double SQUARE_ROOT_OF_SEVEN = 2.64575131106459059050;
    public static final double SQUARE_ROOT_OF_ELEVEN = 3.31662479035539984911;

    // Long Constants
    public static final long MAX_LONG = Long.MAX_VALUE;
    public static final long MIN_LONG = Long.MIN_VALUE;

    public static final long BILLION = 1_000_000_000L;
    public static final long TRILLION = 1_000_000_000_000L;
    public static final long QUADRILLION = 1_000_000_000_000_000L;

    // Float Constants
    public static final float MAX_FLOAT = Float.MAX_VALUE;
    public static final float MIN_FLOAT = Float.MIN_VALUE;

    public static final float SQUARE_ROOT_OF_TWO_FLOAT = 1.41421356f;
    public static final float SQUARE_ROOT_OF_THREE_FLOAT = 1.73205081f;
    public static final float SQUARE_ROOT_OF_FIVE_FLOAT = 2.23606798f;
    public static final float SQUARE_ROOT_OF_SEVEN_FLOAT = 2.64575131f;
    public static final float SQUARE_ROOT_OF_ELEVEN_FLOAT = 3.31662479f;

    // Byte Constants
    public static final byte MAX_BYTE = Byte.MAX_VALUE;
    public static final byte MIN_BYTE = Byte.MIN_VALUE;
    public static final byte MAX_UNSIGNED_BYTE = (byte) 0xFF;
    public static final byte MIN_UNSIGNED_BYTE = (byte) 0x00;

    // Short Constants
    public static final short MAX_SHORT = Short.MAX_VALUE;
    public static final short MIN_SHORT = Short.MIN_VALUE;

    public static final short MAX_UNSIGNED_SHORT = (short) 0xFFFF;
    public static final short MIN_UNSIGNED_SHORT = (short) 0x0000;

    // Private constructor to prevent instantiation
    private CommonNumberConstant() {
        throw new AssertionError("Cannot instantiate NumberConstants");
    }
}

package gsq.booker.sys.common;

public class ForeSysException extends RuntimeException {

    public ForeSysException() {
    }

    public ForeSysException(String message) {
        super(message);
    }

    /**
     * 丢出一个异常
     *
     * @param message
     */
    public static void fail(String message) {
        throw new ForeSysException(message);
    }

}

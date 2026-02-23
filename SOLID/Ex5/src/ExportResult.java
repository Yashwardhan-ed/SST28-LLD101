public class ExportResult {
    public final String contentType;
    public final byte[] bytes;
    public final boolean success;
    public final String errMsg;

    public ExportResult(String contentType, byte[] bytes) {
        this.contentType = contentType;
        this.bytes = bytes;
        this.success = true;
        this.errMsg = null;
    }
    private ExportResult(String contentType, String errorMsg) {
        this.contentType = contentType;
        this.bytes = new byte[0];
        this.success = false;
        this.errMsg = errorMsg;
    }

    public static ExportResult error(String contentType, String msg) {
        return new ExportResult(contentType, msg);
    }

}

public abstract class Exporter {
    // implied "contract" but not enforced (smell)
    public ExportResult precondition(ExportRequest req) {
        if(req == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        return export(req);
    }
    public abstract ExportResult export(ExportRequest req);
}

public class OrderRepo {
    private final FileStore store = new FileStore();

    void saveToDB(String invId, String printable) {
        store.save(invId, printable);
    }

    int countLines(String invId) {
        return store.countLines(invId);
    }
}

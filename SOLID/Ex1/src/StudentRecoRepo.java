public class StudentRecoRepo {
    private final FakeDb db;

    public StudentRecoRepo(FakeDb db) { this.db = db; }

    public StudentRecord saveToDB(String id, String name, String email, String phone, String program) {
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);
        db.save(rec);
        return rec;
    }

    public int getCount() { return db.count(); }

}

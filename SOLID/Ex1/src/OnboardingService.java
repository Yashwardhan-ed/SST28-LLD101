import java.util.*;


public class OnboardingService {
    private final StudentRecoRepo repo;

    public OnboardingService(StudentRecoRepo repo) {
        this.repo = repo;
    }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        System.out.println("INPUT: " + raw);

        Parser parser = new Parser();
        Map<String,String> kv = parser.parseRawInput(raw);

        // validation inline, printing inline
        InputValidator validator = new InputValidator(kv);
        List<String> errors = validator.validate();

        OutputHandler handler = new OutputHandler();
        if (!errors.isEmpty()) {
            handler.printErrors(errors);
        } else {
            String id = IdUtil.nextStudentId(repo.getCount());
            String name = kv.get("name");
            String email = kv.get("email");
            String phone = kv.get("phone");
            String program = kv.get("program");
            StudentRecord rec = repo.saveToDB(id, name, email, phone, program);
            handler.printSuccess(rec, repo.getCount());
        }
    }
}

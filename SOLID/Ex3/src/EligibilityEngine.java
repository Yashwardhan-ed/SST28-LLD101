import java.util.*;

public class EligibilityEngine {
    private final EligibilityRepository repo;

    public EligibilityEngine(EligibilityRepository repo) { this.repo = repo; }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        repo.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        EligibilityCriteria criteria = new EligibilityFactory().createCriteria();
        EligibilityData data = new EligibilityData(s.disciplinaryFlag, s.cgr, s.earnedCredits, s.attendancePct);
        EligibilityResult res = criteria.checkEligibility(data);
        return new EligibilityEngineResult(res.status, res.reasons);
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}

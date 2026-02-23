import java.util.List;

public class EligibilityResult {
    public final List<String> reasons;
    public final String status;

    public EligibilityResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}

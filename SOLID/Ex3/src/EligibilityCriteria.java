import java.util.ArrayList;
import java.util.List;

public class EligibilityCriteria {
    private final RuleInput ruleInput;
    private final List<EligibilityRules> rules;

    public EligibilityCriteria(RuleInput ruleInput, List<EligibilityRules> rules) {
        this.ruleInput = ruleInput;
        this.rules = rules;
    }

    public EligibilityResult checkEligibility(EligibilityData data) {
        List<String> reasons = new ArrayList<>();
        
        for (EligibilityRules rule : rules) {
            String reason = rule.checkEligibility(data, ruleInput);
            if (reason != null && !reason.isEmpty()) {
                reasons.add(reason);
            }
        }

        String status = reasons.isEmpty() ? "ELIGIBLE" : "NOT_ELIGIBLE";
        return new EligibilityResult(status, List.copyOf(reasons));
    }
}

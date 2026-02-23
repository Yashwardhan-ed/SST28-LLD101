public class CGRCheck implements EligibilityRules {
    @Override
    public String checkEligibility(EligibilityData data, RuleInput rules) {
        if (data.cgr < rules.minCgr) {
            return "CGR below " + rules.minCgr;
        }
        return "";
    } 
}

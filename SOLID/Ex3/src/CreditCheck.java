public class CreditCheck implements EligibilityRules {
    @Override
    public String checkEligibility(EligibilityData data, RuleInput rules) {
        if (data.earnedCredits < rules.minCredits) {
            return "credits below " + rules.minCredits;
        }
        return "";
    }
}

public class DisciplinaryCheck implements EligibilityRules {
    @Override
    public String checkEligibility(EligibilityData data, RuleInput rules) {
        if (data.disciplinaryFlag != 0) {
            return "disciplinary flag present";
        }
        return "";
    }
}

public class AttendanceCheck implements EligibilityRules {
    @Override
    public String checkEligibility(EligibilityData data, RuleInput rules) {
        if (data.attendancePct < rules.minAttendance) {
            return "attendance below " + rules.minAttendance;
        }
        return "";
    } 
}


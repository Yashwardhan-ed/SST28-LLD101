import java.util.List;

public class EligibilityFactory {
	public EligibilityCriteria createCriteria() {
		RuleInput ruleInput = new RuleInput();
		List<EligibilityRules> rules = List.of(
				new DisciplinaryCheck(),
				new AttendanceCheck(),
				new CGRCheck(),
				new CreditCheck()
		);
		return new EligibilityCriteria(ruleInput, rules);
	}
}

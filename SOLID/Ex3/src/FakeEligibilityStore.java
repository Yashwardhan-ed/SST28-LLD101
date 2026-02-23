public class FakeEligibilityStore implements EligibilityRepository {
    @Override
    public void save(String roll, String status) {
        System.out.println("Saved evaluation for roll=" + roll);
    }
}
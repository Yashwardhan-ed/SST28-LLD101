public class EligibilityData {
    int disciplinaryFlag;
    double cgr;
    int earnedCredits;
    int attendancePct;
    public EligibilityData(int d, double cgr, int c, int a) {
        this.disciplinaryFlag = d;
        this.cgr = cgr;
        this.earnedCredits = c;
        this.attendancePct = a;
    }
}

abstract class LoanRemote {
    abstract void applyLoan(int id, String loan);

    abstract void viewLoan(int id);

    abstract void cancelLoan(int id);

    void log(String msg) {
        System.out.println(msg);
    }
}

class LoanService extends LoanRemote {
    String[][] loans = new String[2][2];

    void applyLoan(int id, String loan) {
        for (int i = 0; i < 2; i++) {
            if (loans[id][i] == null) {
                loans[id][i] = loan;
                log("Loan applied: " + loan + " for applicant " + id);
                break;
            }
        }
    }

    void viewLoan(int id) {
        System.out.println("Applicant " + id + " loans: [" +
                loans[id][0] + ", " + loans[id][1] + "]");
    }

    void cancelLoan(int id) {
        loans[id][0] = null;
        loans[id][1] = null;
        log("Loans cancelled for applicant " + id);
    }
}

public class AsLoan {
    public static void main(String[] args) {
        LoanService ls = new LoanService();

        ls.applyLoan(0, "Personal Loan");
        ls.applyLoan(0, "Business Loan");
        ls.applyLoan(1, "Education Loan");

        ls.viewLoan(0);

        ls.cancelLoan(0);

        ls.viewLoan(0);
    }
}


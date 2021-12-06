package AdminServer;

public class Accounting {
    private String Accounting_id;
    private String Acc_workerid;
    private String Acc_hour;
    private String Acc_bonus;
    private String Acc_rebuke;

    @Override
    public String toString() {
        return "Accounting{" +
                "Accounting_id='" + Accounting_id + '\'' +
                ", Acc_workerid='" + Acc_workerid + '\'' +
                ", Acc_hour='" + Acc_hour + '\'' +
                ", Acc_bonus='" + Acc_bonus + '\'' +
                ", Acc_rebuke='" + Acc_rebuke + '\'' +
                '}';
    }

    public String getAccounting_id() {
        return Accounting_id;
    }

    public void setAccounting_id(String accounting_id) {
        Accounting_id = accounting_id;
    }

    public String getAcc_workerid() {
        return Acc_workerid;
    }

    public void setAcc_workerid(String acc_workerid) {
        Acc_workerid = acc_workerid;
    }

    public String getAcc_hour() {
        return Acc_hour;
    }

    public void setAcc_hour(String acc_hour) {
        Acc_hour = acc_hour;
    }

    public String getAcc_bonus() {
        return Acc_bonus;
    }

    public void setAcc_bonus(String acc_bonus) {
        Acc_bonus = acc_bonus;
    }

    public String getAcc_rebuke() {
        return Acc_rebuke;
    }

    public void setAcc_rebuke(String acc_rebuke) {
        Acc_rebuke = acc_rebuke;
    }

    public Accounting(String acc_workerid, String acc_hour, String acc_bonus, String acc_rebuke) {
        Acc_workerid = acc_workerid;
        Acc_hour = acc_hour;
        Acc_bonus = acc_bonus;
        Acc_rebuke = acc_rebuke;
    }
}

package Clasess;

import java.io.Serializable;

public class Salary implements Serializable {
    private String Worker_id;
    private String Worker_name;
    private String Worker_surname;
    private String Worker_departament;
    private String Worker_salary;
    private String Acc_hour;
    private String Acc_bonus;

    public Salary(String worker_id, String worker_name, String worker_surname, String worker_departament, String worker_salary, String acc_hour, String acc_bonus, String acc_rebuke) {
        Worker_id = worker_id;
        Worker_name = worker_name;
        Worker_surname = worker_surname;
        Worker_departament = worker_departament;
        Worker_salary = worker_salary;
        Acc_hour = acc_hour;
        Acc_bonus = acc_bonus;
        Acc_rebuke = acc_rebuke;
    }

    private String Acc_rebuke;

    public Salary() {

    }

    public String getWorker_id() {
        return Worker_id;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "Worker_id='" + Worker_id + '\'' +
                ", Worker_name='" + Worker_name + '\'' +
                ", Worker_surname='" + Worker_surname + '\'' +
                ", Worker_departament='" + Worker_departament + '\'' +
                ", Worker_salary='" + Worker_salary + '\'' +
                ", Acc_hour='" + Acc_hour + '\'' +
                ", Acc_bonus='" + Acc_bonus + '\'' +
                ", Acc_rebuke='" + Acc_rebuke + '\'' +
                '}';
    }

    public void setWorker_id(String worker_id) {
        Worker_id = worker_id;
    }

    public String getWorker_name() {
        return Worker_name;
    }

    public void setWorker_name(String worker_name) {
        Worker_name = worker_name;
    }

    public String getWorker_surname() {
        return Worker_surname;
    }

    public void setWorker_surname(String worker_surname) {
        Worker_surname = worker_surname;
    }

    public String getWorker_departament() {
        return Worker_departament;
    }

    public void setWorker_departament(String worker_departament) {
        Worker_departament = worker_departament;
    }

    public String getWorker_salary() {
        return Worker_salary;
    }

    public void setWorker_salary(String worker_salary) {
        Worker_salary = worker_salary;
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
}

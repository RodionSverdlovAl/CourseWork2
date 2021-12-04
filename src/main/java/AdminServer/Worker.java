package AdminServer;

public class Worker {
    private String Worker_id;
    private String Worker_name;
    private String Worker_surname;
    private String Worker_fathername;
    private String Worker_departament;
    private String Worker_position;
    private String Worker_year;
    private String Worker_salary;

    @Override
    public String toString() {
        return "Worker{" +
                "Worker_id='" + Worker_id + '\'' +
                ", Worker_name='" + Worker_name + '\'' +
                ", Worker_surname='" + Worker_surname + '\'' +
                ", Worker_fathername='" + Worker_fathername + '\'' +
                ", Worker_departament='" + Worker_departament + '\'' +
                ", Worker_position='" + Worker_position + '\'' +
                ", Worker_year='" + Worker_year + '\'' +
                ", Worker_salary='" + Worker_salary + '\'' +
                '}';
    }

    public String getWorker_id() {
        return Worker_id;
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

    public String getWorker_fathername() {
        return Worker_fathername;
    }

    public void setWorker_fathername(String worker_fathername) {
        Worker_fathername = worker_fathername;
    }

    public String getWorker_departament() {
        return Worker_departament;
    }

    public void setWorker_departament(String worker_departament) {
        Worker_departament = worker_departament;
    }

    public String getWorker_position() {
        return Worker_position;
    }

    public void setWorker_position(String worker_position) {
        Worker_position = worker_position;
    }

    public String getWorker_year() {
        return Worker_year;
    }

    public void setWorker_year(String worker_year) {
        Worker_year = worker_year;
    }

    public String getWorker_salary() {
        return Worker_salary;
    }

    public void setWorker_salary(String worker_salary) {
        Worker_salary = worker_salary;
    }

    public Worker(String worker_name, String worker_surname, String worker_fathername, String worker_departament, String worker_position, String worker_year, String worker_salary) {
        Worker_name = worker_name;
        Worker_surname = worker_surname;
        Worker_fathername = worker_fathername;
        Worker_departament = worker_departament;
        Worker_position = worker_position;
        Worker_year = worker_year;
        Worker_salary = worker_salary;
    }
}


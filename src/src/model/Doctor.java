package src.model;

public class Doctor {
    private final String name;
    private final Integer CRM;
    private final Specialization specialization;

    public Doctor(String name, Integer CRM, Specialization specialization) {
        this.name = name;
        this.CRM = CRM;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public Integer getCRM() {
        return CRM;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Doctor { " +
                "name='" + name + '\'' +
                ", CRM=" + CRM +
                ", specialization=" + specialization +
                " }";
    }
}

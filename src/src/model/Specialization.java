package src.model;

public enum Specialization {
    dermatologist, cardiologist, neurologist;

    public String value() {
        switch (this) {
            case dermatologist: return "Dermatologist";
            case cardiologist: return "Cardiologist";
            case neurologist: return "Neurologist";
            default: return "Error finding Specialization";
        }
    }
}

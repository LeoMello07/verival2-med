package src.model;

public enum Specialization {
    dermatologist, cardiologist, neurologist;

    public String value() {
        switch (this) {
            case dermatologist: return "Dermatologista";
            case cardiologist: return "Cardiologista";
            case neurologist: return "Neurologista";
            default: return "Especialização não encontrada.";
        }
    }
}

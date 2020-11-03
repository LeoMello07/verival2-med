package src.model;

public enum SurgeryRoomType {
    small, big, high_risk;

    public int price() {
        switch (this) {
            case small: return 400;
            case big: return 650;
            case high_risk: return 1200;
            default: return Integer.MAX_VALUE;
        }
    }


}

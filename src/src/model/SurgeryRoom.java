package src.model;

public class SurgeryRoom {
    private final String name;
    private final SurgeryRoomType type;

    public SurgeryRoom(String name, SurgeryRoomType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public SurgeryRoomType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "SurgeryRoom { " +
                "name='" + name + '\'' +
                ", type=" + type +
                " }";
    }
}

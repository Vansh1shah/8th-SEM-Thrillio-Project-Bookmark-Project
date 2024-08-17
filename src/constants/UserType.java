package constants;

public enum UserType {

    USER("USER"),
    EDITOR("EDITOR"),
    CHIEF_EDITOR("CHIEFEDITOR");

    private UserType(String name) {
        this.name = name;
    }

    private String name;
    public String getName(){
        return name;
    }
}

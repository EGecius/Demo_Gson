package gson;


import com.google.gson.annotations.SerializedName;

public class User {
    private final String id;
    private final String name;
    @SerializedName ("maiden_name")
    private final String maidenName;

    public User(String id, String name, String maidenName) {
        this.id = id;
        this.name = name;
        this.maidenName = maidenName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMaidenName() {
        return maidenName;
    }
}

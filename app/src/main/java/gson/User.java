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

//        enforceNonNullFields();
    }

    /** Calling this method proves that Gson does not use constructor - it construct successfully
     even with null fields*  */
    private void enforceNonNullFields() {
        if (id == null || name == null || maidenName == null) {
            throw new IllegalArgumentException();
        }
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

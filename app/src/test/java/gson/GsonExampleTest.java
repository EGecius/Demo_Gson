package gson;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;

import org.junit.Test;

public class GsonExampleTest {

    public static final String JOHN_AS_STRING = "{\"id\":\"1\",\"name\":\"john\","
            + "\"maiden_name\":\"brown\"}";
    public static final String ID = "1";
    public static final String NAME = "john";
    private static final String MAIDEN_NAME = "brown";
    public static final User JOHN_AS_USER = new User(ID, NAME, MAIDEN_NAME);
    
    private final Gson mGson = new Gson();

    @Test
    public void convertsToString() {
        String userAsString = mGson.toJson(JOHN_AS_USER);

        assertThat(userAsString).isEqualTo(JOHN_AS_STRING);
    }

    @Test
    public void convertsToUser() {
        User user = mGson.fromJson(JOHN_AS_STRING, User.class);

        assertThat(user.getId()).isEqualTo(ID);
        assertThat(user.getName()).isEqualTo(NAME);
        assertThat(user.getMaidenName()).isEqualTo(MAIDEN_NAME);
    }

    @Test
    public void convertsWithSomeFieldsAsNull() {
        User user = new User("2", null, null);
        String userAsString = mGson.toJson(user);

        assertThat(userAsString).isEqualTo("{\"id\":\"2\"}");
    }

    @Test
    public void convertsFromJsonWithSomeEmptyField() {
        String asString = "{\"id\":\"2\"}";

        User user = mGson.fromJson(asString, User.class);

        assertThat(user.getId()).isEqualTo("2");
        assertThat(user.getName()).isNull();
        assertThat(user.getMaidenName()).isNull();
    }

}
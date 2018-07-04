package gson;


import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import deepcopy.Address;
import deepcopy.User;

public class DeepCopyTest {

    private static final String FIRST_NAME = "Prime";
    private static final String LAST_NAME = "Minister";
    private static final String ZERO = "zero";
    private static final String ONE = "one";

    Address address = new Address("Downing St 10", "London", "England");
    User user = new User(FIRST_NAME, LAST_NAME, address);

    Gson gson = new Gson();

    @Test
    public void whenModifyingOriginalObject_thenGsonCloneShouldNotChange() {

        User userDeepCopy = gson.fromJson(gson.toJson(user), User.class);

        address.setCountry("Great Britain");

        assertThat(userDeepCopy.getAddress().getCountry())
                .isNotEqualTo(user.getAddress().getCountry());
    }

    @Test
    public void concurrentHashMapValuesArePreservedWithDeepCopies() {
        User userDeepCopy = gson.fromJson(gson.toJson(user), User.class);

        assertThat(userDeepCopy.getAddress()).isEqualTo(user.getAddress());
    }

    @Test
    public void demoDeepCopyMapPreservingTypeInfo() {

        HashMap<Integer, String> hashMap = new HashMap<>();

        hashMap.put(0, ZERO);
        hashMap.put(1, ONE);

        String json = gson.toJson(hashMap);
        Type type = new TypeToken<Map<Integer, String>>(){}.getType();
        Map<String, String> mapDeepCopy = gson.fromJson(json, type);

        assertThat(mapDeepCopy.get(0)).isEqualTo(ZERO);
        assertThat(mapDeepCopy.get(1)).isEqualTo(ONE);
    }

//    @Test
//    public void demoHashMapDeepCopy() {
//
//        HashMap<Integer, User> hashMap = new HashMap<>();
//        hashMap.put(1, user);
//
//        String json = gson.toJson(hashMap);
//        Map<Integer, User> hashMapDeepCopy = gson.fromJson(json, Map.class);
//
//        User user = hashMapDeepCopy.get(1);
//        assertThat(user.getFirstName()).isEqualTo(FIRST_NAME);
//    }



    // TODO: 04/07/2018 demo how to deep copy a concurrent map

}

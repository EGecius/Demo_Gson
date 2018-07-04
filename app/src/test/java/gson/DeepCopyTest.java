package gson;


import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;

import org.junit.Test;

import deepcopy.Address;
import deepcopy.User;

public class DeepCopyTest {

    private static final String FIRST_NAME = "Prime";
    private static final String LAST_NAME = "Minister";

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

//    @Test
//    public void demoHashMapDeepCopy() {
//
//        HashMap<Integer, User> hashMap = new HashMap<>();
//        hashMap.put(1, user);
//
//        HashMap<Integer, User> hashMapDeepCopy = gson.fromJson(gson.toJson(hashMap), HashMap.class);
//
//        User user = hashMapDeepCopy.get(1);
//        assertThat(user.getFirstName()).isEqualTo(FIRST_NAME);
//    }

}

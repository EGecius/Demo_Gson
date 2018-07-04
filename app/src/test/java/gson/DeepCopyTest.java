package gson;


import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;

import org.junit.Test;

import deepcopy.Address;
import deepcopy.User;

public class DeepCopyTest {

    Gson gson = new Gson();

    @Test
    public void whenModifyingOriginalObject_thenGsonCloneShouldNotChange() {
        Address address = new Address("Downing St 10", "London", "England");
        User user = new User("Prime", "Minister", address);

        User userDeepCopy = gson.fromJson(gson.toJson(user), User.class);

        address.setCountry("Great Britain");

        assertThat(userDeepCopy.getAddress().getCountry())
                .isNotEqualTo(user.getAddress().getCountry());
    }

}

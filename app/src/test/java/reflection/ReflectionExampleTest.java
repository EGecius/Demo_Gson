package reflection;


import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.annotations.SerializedName;

import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import gson.User;

public class ReflectionExampleTest {

    private ReflectionExample mSut;

    @Before
    public void setUp() {
        mSut = new ReflectionExample();
    }

    @Test
    public void getsFieldNames() {
        List<String> fieldNames = mSut.getFieldNames(User.class);
        assertThat(fieldNames.size()).isEqualTo(3);
        assertThat(fieldNames.get(0)).isEqualTo("id");
        assertThat(fieldNames.get(1)).isEqualTo("name");
        assertThat(fieldNames.get(2)).isEqualTo("maidenName");
    }

    @Test
    public void getAnnotationsOfClass() {
        List<Class<? extends Annotation>> annotations = mSut.getAnnotationsOfClass(User.class);
        assertThat(annotations.size()).isEqualTo(0);
    }

    @Test
    public void getAnnotationsOfFields() {
        List<Annotation> annotations = mSut.getAnnotationsOfFields(User.class);
        assertThat(annotations.size()).isEqualTo(1);
        Annotation annotation = annotations.get(0);
        assertThat(annotation.annotationType()).isEqualTo(SerializedName.class);
    }

    @Test
    public void getFieldValues() throws IllegalAccessException {

        User user = new User("1", "Steve", "Stone");

        List<String> fieldValues = mSut.getFieldValuesWithReflection(user);
        assertThat(fieldValues.size()).isEqualTo(3);
        assertThat(fieldValues.get(0)).isEqualTo("1");
        assertThat(fieldValues.get(1)).isEqualTo("Steve");
        assertThat(fieldValues.get(2)).isEqualTo("Stone");
    }

    @Test (expected = IllegalAccessException.class)
    public void cannotInvokesPrivateMethods() throws InvocationTargetException,
            IllegalAccessException {
        ReflectionTarget reflectionTarget = new ReflectionTarget();
        assertThat(reflectionTarget.isPrivateMethodCalled).isFalse();

        mSut.invokePrivateMethods(reflectionTarget);

        assertThat(reflectionTarget.isPrivateMethodCalled).isTrue();
    }

    @Test
    public void invokesPackagePrivateMethods() throws InvocationTargetException,
            IllegalAccessException {
        ReflectionTarget reflectionTarget = new ReflectionTarget();
        assertThat(reflectionTarget.isPackagePrivateMethodInvoked).isFalse();

        mSut.invokePackagePrivateMethods(reflectionTarget);

        assertThat(reflectionTarget.isPackagePrivateMethodInvoked).isTrue();
    }

}
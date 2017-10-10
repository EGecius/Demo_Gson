package reflection;


import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.annotations.SerializedName;

import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
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

}
package reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import gson.User;


public class ReflectionExample {

    List<String> getFieldNames(Class aClass) {

        Field[] fields = aClass.getDeclaredFields();
        List<String> list = new ArrayList<>();

        for (Field field : fields) {
            String name = field.getName();
            list.add(name);
        }

        return list;
    }

    public List<Class<? extends Annotation>> getAnnotationsOfClass(Class<User> aClass) {

        Annotation[] annotations = aClass.getDeclaredAnnotations();
        List<Class<? extends Annotation>> list = new ArrayList<>();

        for (Annotation annotation : annotations) {
            Class<? extends Annotation> type = annotation.annotationType();
            list.add(type);
        }

        return list;
    }

    public List<Annotation> getAnnotationsOfFields(Class<User> aClass) {

        Field[] fields = aClass.getDeclaredFields();
        List<Annotation> list = new ArrayList<>();

        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                list.add(annotation);
            }
        }

        return list;
    }
}

package reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public List<String> getFieldValuesWithReflection(Object object) throws IllegalAccessException {

        List<String> list = new ArrayList<>();

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String value = (String) field.get(object);
            list.add(value);
        }

        return list;
    }

    public void invokePrivateMethods(ReflectionTarget target)
            throws InvocationTargetException, IllegalAccessException {
        invokeAllMethods(target);
    }

    private void invokeAllMethods(ReflectionTarget target)
            throws InvocationTargetException, IllegalAccessException {
        Method[] methods = target.getClass().getDeclaredMethods();
        for (Method method : methods) {
            method.invoke(target);
        }
    }

    public void invokePackagePrivateMethods(ReflectionTarget target) {
        Method[] methods = target.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                //it will fail on private methods but let's keep trying
                method.invoke(target);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}

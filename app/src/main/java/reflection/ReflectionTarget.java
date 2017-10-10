package reflection;

public class ReflectionTarget {

    public boolean isPrivateMethodCalled;
    public boolean isPackagePrivateMethodInvoked;

    private void privateMethod() {
        isPrivateMethodCalled = true;
    }

    void defaultMethod() {
        isPackagePrivateMethodInvoked = true;
    }
}

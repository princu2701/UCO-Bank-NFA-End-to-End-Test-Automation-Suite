package Utils;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class globalretryanalyzerclass implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor constructor,Method method) {
        annotation.setRetryAnalyzer(Retryanalyzerclasses.class);
    }
}

    package Utils;

    import org.testng.IRetryAnalyzer;
    import org.testng.ITestResult;

    public class Retryanalyzerclasses implements IRetryAnalyzer {

        int count = 0;
        int max = 3;

        @Override
        public boolean retry(ITestResult result) {
            if (!result.isSuccess()) {
                if (count < max) {
                    count++;
                    return true;
                }
            }
            return false; // <--- this line was missing in your code
        }
    }

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class AllureTestWatcher implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        Allure.addAttachment("test-status", "success");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String message = Optional.ofNullable(cause).map(Throwable::toString).orElse("null");
        Allure.addAttachment("test-failure", message);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        Allure.addAttachment("test-disabled", reason.orElse("no reason"));
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        Allure.addAttachment("test-aborted", Optional.ofNullable(cause).map(Throwable::toString).orElse("null"));
    }
}


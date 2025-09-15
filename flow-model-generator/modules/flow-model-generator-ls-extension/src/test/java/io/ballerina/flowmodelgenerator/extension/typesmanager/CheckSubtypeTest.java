package io.ballerina.flowmodelgenerator.extension.typesmanager;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.ballerina.flowmodelgenerator.core.model.TypeData;
import io.ballerina.flowmodelgenerator.extension.request.CheckSubtypeRequest;
import io.ballerina.modelgenerator.commons.AbstractLSTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CheckSubtypeTest extends AbstractLSTest {

    @Override
    @Test(dataProvider = "data-provider")
    public void test(Path config) throws IOException {
        Path configJsonPath = configDir.resolve(config);
        TestConfig testConfig = gson.fromJson(Files.newBufferedReader(configJsonPath), TestConfig.class);
        CheckSubtypeRequest request = new CheckSubtypeRequest(getSourcePath(testConfig.filePath()),
                testConfig.sourceType(), testConfig.targetType());
        JsonObject response = getResponse(request);
        if (!response.equals(testConfig.output())) {
            TestConfig updateConfig = new TestConfig(testConfig.filePath(), testConfig.description(),
                    testConfig.sourceType(), testConfig.targetType(), response);
//            updateConfig(configJsonPath, updateConfig);
            compareJsonElements(response, testConfig.output());
            Assert.fail(String.format("Failed test: '%s' (%s)", testConfig.description(), configJsonPath));
        }
    }

    @Override
    protected String getResourceDir() {
        return "types_manager/check_subtype";
    }

    @Override
    protected Class<? extends AbstractLSTest> clazz() {
        return FindMatchingTypeTest.class;
    }

    @Override
    protected String getApiName() {
        return "isSubtype";
    }

    @Override
    protected String getServiceName() {
        return "typesManager";
    }

    private record TestConfig(String filePath, String description, TypeData sourceType, TypeData targetType,
                              JsonElement output) {
    }
}

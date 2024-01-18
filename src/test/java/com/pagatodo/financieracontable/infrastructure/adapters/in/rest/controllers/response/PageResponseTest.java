package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class PageResponseTest {

    @Test
    @DisplayName("Test for PageResponse model")
    void testModelPageResponse() {
        PageResponse<String> model = new PageResponse<>();
        model.setData("Sample Data");
        model.setTotal(BigInteger.valueOf(10));

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}

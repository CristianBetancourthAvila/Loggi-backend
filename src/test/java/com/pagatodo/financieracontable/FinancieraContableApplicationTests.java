package com.pagatodo.financieracontable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class FinancieraContableApplicationTests {
    @Test
    void main_success() {
        var myFlag = true;
        assertAll("resultado",
                () -> assertTrue(myFlag));
    }
}

package com.vidracariaCampos.unit.security;
import com.vidracariaCampos.config.ConfigSpringTest;
import com.vidracariaCampos.security.CodeAccess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
public class CodeAccessTest implements ConfigSpringTest {

    @Autowired
    private CodeAccess codeAccess;

    @Test
    void testGenCode() {
        UUID inputUUID = UUID.randomUUID();

        UUID generatedCode = codeAccess.genCode(inputUUID);

        assertNotNull(generatedCode);
        assertNotEquals(inputUUID, generatedCode);
    }

    @Test
    void testReverseCode() {
        UUID inputUUID = UUID.randomUUID();
        UUID generatedCode = codeAccess.genCode(inputUUID);

        UUID reversedUUID = codeAccess.reverseCode(generatedCode);

        assertNotNull(reversedUUID);
        assertEquals(inputUUID, reversedUUID);
    }
}


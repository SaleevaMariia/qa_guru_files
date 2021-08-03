import net.lingala.zip4j.ZipFile;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TestZipFile {
    @Test
    public void testZipFile() throws IOException {
        new ZipFile("src/test/resources/test_zip.zip", "4321".toCharArray())
                .extractAll("./src/test/resources/unzip");
        String actual = FileUtils.readFileToString(new File("./src/test/resources/unzip/test_zip.txt"),
                StandardCharsets.UTF_8);
        Assertions.assertTrue(actual.contains("У лукоморья дуб зелёны"));
    }
}

import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestXlsxFile {
    @Test
    public void testXlsxFile() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("test_exel.xlsx");
        XLS xlsFile = new XLS(stream);

        Assertions.assertAll(
                () -> assertEquals("Имя",
                        xlsFile.excel.getSheetAt(0).getRow(0)
                                .getCell(0).getStringCellValue()),
                () -> assertEquals("Фамилия",
                        xlsFile.excel.getSheetAt(0).getRow(0)
                                .getCell(1).getStringCellValue()),
                () -> assertEquals("Иван",
                        xlsFile.excel.getSheetAt(0).getRow(1)
                                .getCell(0).getStringCellValue()),
                () -> assertEquals("Петров",
                        xlsFile.excel.getSheetAt(0).getRow(1)
                                .getCell(1).getStringCellValue()));

    }
}

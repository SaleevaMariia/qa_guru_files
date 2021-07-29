import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import net.lingala.zip4j.ZipFile;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFiles {

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

    @Test
    public void testPDFFile() throws IOException {
        File pdfFile = new File("src/test/resources/test_pdf.pdf");
        PDF parsedPdf = new PDF(pdfFile);
        Assertions.assertAll(
                () -> assertTrue(parsedPdf.text.contains("А я иду шагаю по Москве,")),
                () -> assertTrue(parsedPdf.text.contains("И я ещё пройти смогу,")),
                () -> assertTrue(parsedPdf.text.contains("Солёный Тихий океан")),
                () -> assertTrue(parsedPdf.text.contains("И тундру, и тайгу.")),
                () -> assertTrue(parsedPdf.numberOfPages == 1)
        );
    }

    @Test
    public void testZipFile() throws IOException {
        new ZipFile("src/test/resources/test_zip.zip", "4321".toCharArray())
                .extractAll("./src/test/resources/unzip");
        String actual = FileUtils.readFileToString(new File("./src/test/resources/unzip/test_zip.txt"),
                StandardCharsets.UTF_8);
        Assertions.assertTrue(actual.contains("У лукоморья дуб зелёны"));
    }

    @Test
    public void testDocFile() throws IOException {
        String actual = "";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("test_doc.docx");
        try(FileInputStream fileInputStream = new FileInputStream(resource.getFile())){
            XWPFDocument document = new XWPFDocument(fileInputStream);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph para : paragraphs) {
                actual = actual.concat(para.getText());
            }
        }
        Assertions.assertTrue(actual.contains("Мама мыла раму"));
    }

}

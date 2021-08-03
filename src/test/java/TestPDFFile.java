import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPDFFile {
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
}

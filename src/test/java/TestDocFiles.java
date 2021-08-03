import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class TestDocFiles {

    @Test
    public void testDocFile() throws IOException {
        String actual = "";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("test_doc.docx");
        try (FileInputStream fileInputStream = new FileInputStream(resource.getFile())) {
            XWPFDocument document = new XWPFDocument(fileInputStream);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph para : paragraphs) {
                actual = actual.concat(para.getText());
            }
        }
        Assertions.assertTrue(actual.contains("Мама мыла раму"));
    }

}

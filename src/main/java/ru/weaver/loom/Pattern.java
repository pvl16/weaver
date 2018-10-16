package ru.weaver.loom;

import org.xml.sax.SAXException;
import ru.weaver.shemas.loom.*;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Pattern {
    protected String name;
//  protected M

    Pattern() {
        name = "Pattern 1";
    }

    private void forSaveFile_init(WeavePatternType weavePattern) {
        weavePattern.setSchemaVersion("1.0");
        weavePattern.setName(this.name);
    }

    protected void forSaveFile_getPattern(WeavePatternType weavePattern) {

    }

    public void saveToFile(File path) {
        WeavePatternType weavePattern = new WeavePatternType();
        forSaveFile_init(weavePattern);
        forSaveFile_getPattern(weavePattern);
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            ClassLoader classLoader = this.getClass().getClassLoader();
            Schema schema = schemaFactory.newSchema(classLoader.getResource("jaxb/loom-1.0.xsd"));
            JAXBContext jaxbContext = JAXBContext.newInstance(ru.weaver.shemas.loom.WeavePatternType.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            marshaller.setSchema(schema);
            ru.weaver.shemas.loom.ObjectFactory of = new ru.weaver.shemas.loom.ObjectFactory();
            FileWriter fw = new FileWriter(path, false);
            marshaller.marshal(of.createWeavePattern(weavePattern), fw);
            fw.flush();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

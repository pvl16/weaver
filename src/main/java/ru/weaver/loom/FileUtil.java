package ru.weaver.loom;

import org.xml.sax.SAXException;
import ru.weaver.shemas.loom.LoomPatternType;
import ru.weaver.shemas.loom.WeavePatternType;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class FileUtil {

    public void loadFromFile(File path) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            ClassLoader classLoader = this.getClass().getClassLoader();
            Schema schema = schemaFactory.newSchema(classLoader.getResource("jaxb/loom-1.0.xsd"));
            JAXBContext jaxbContext = JAXBContext.newInstance(ru.weaver.shemas.loom.WeavePatternType.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ru.weaver.shemas.loom.ObjectFactory of = new ru.weaver.shemas.loom.ObjectFactory();
            WeavePatternType weavePattern =(WeavePatternType)unmarshaller.unmarshal(path);

            LoomPatternType loomPatternType = weavePattern.getLoomPattern();
//            if (loomPatternType != null) {
//
//            }

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

}

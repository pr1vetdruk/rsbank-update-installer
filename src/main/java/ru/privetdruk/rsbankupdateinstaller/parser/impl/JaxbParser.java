package ru.privetdruk.rsbankupdateinstaller.parser.impl;

import ru.privetdruk.rsbankupdateinstaller.parser.Parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

public class JaxbParser implements Parser {
    @Override
    public Object getObject(File file, Class c) throws JAXBException {
        return JAXBContext.newInstance(c).createUnmarshaller().unmarshal(file);
    }

    @Override
    public void saveObject(File file, Object o) throws JAXBException {
        JAXBContext.newInstance(o.getClass()).createMarshaller().marshal(o, file);
    }
}

package org.kth.sth.biosignals.edf2json;


import org.junit.Test;

import java.io.*;

public class EdfParserTest {

    @Test
    public void testSimple() throws Exception{
        File sourceFile = new File("/Users/radzieuskaya/master-thesis/prototype/edf2json/src/test/resources/Osas2002.edf");
        File targetFile = new File("/Users/radzieuskaya/master-thesis/prototype/edf2json/src/test/resources/Osas2002.edf.json");

        String json = new Edf2Json().parse(new FileInputStream(sourceFile));

        new PrintWriter(targetFile).write(json);

    }
}

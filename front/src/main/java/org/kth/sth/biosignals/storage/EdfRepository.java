package org.kth.sth.biosignals.storage;

import org.kth.sth.biosignals.edf2json.model.Edf;
import org.kth.sth.biosignals.edf2json.model.EdfProperties;

import java.util.List;

/**
 * Created by radzieuskaya on 2015-05-03.
 */
public interface EdfRepository {
    void save(Edf edf);

    Edf get(String uuid);

    List<EdfProperties> list();

    boolean remove(String uuid);
}

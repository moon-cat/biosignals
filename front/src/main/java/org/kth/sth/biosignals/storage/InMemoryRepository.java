package org.kth.sth.biosignals.storage;


import org.kth.sth.biosignals.edf2json.model.Edf;
import org.kth.sth.biosignals.edf2json.model.EdfProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository implements EdfRepository {

    private Map<String, EdfProperties> edfProperties = new HashMap<String, EdfProperties>();

    private Map<String, Edf> edfs = new HashMap<String, Edf>();

    @Override
    public void save(Edf edf){
        String uuid = edf.getEdfProperties().getUuid();
        edfs.put(uuid, edf);
        edfProperties.put(uuid, edf.getEdfProperties());
    }

    @Override
    public Edf get(String uuid) {
        return edfs.get(uuid);
    }

    @Override
    public List<EdfProperties> list() {
        return new ArrayList<EdfProperties>(edfProperties.values());
    }

    @Override
    public boolean remove(String uuid){

        boolean removed = false;

        if (edfs.containsKey(uuid)){
            edfs.remove(uuid);
            edfProperties.remove(uuid);
            removed = true;
        }

        return removed;
    }

}

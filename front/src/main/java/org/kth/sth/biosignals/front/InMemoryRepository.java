package org.kth.sth.biosignals.front;


import org.kth.sth.biosignals.edf2json.model.Edf;
import org.kth.sth.biosignals.edf2json.model.EdfProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryRepository {

    private Map<String, EdfProperties> edfProperties = new HashMap<String, EdfProperties>();

    private Map<String, Edf> edfs = new HashMap<String, Edf>();

    public void save(Edf edf){
        String uuid = edf.getEdfProperties().getUuid();
        edfs.put(uuid, edf);
        edfProperties.put(uuid, edf.getEdfProperties());
    }

    public Edf get(String uuid) {
        return edfs.get(uuid);
    }

    public List<EdfProperties> list() {
        return new ArrayList<EdfProperties>(edfProperties.values());
    }

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

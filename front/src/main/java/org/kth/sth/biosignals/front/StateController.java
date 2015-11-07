package org.kth.sth.biosignals.front;

import org.apache.log4j.Logger;
import org.kth.sth.biosignals.edf2json.model.Edf;
import org.kth.sth.biosignals.edf2json.model.EdfData;
import org.kth.sth.biosignals.edf2json.model.EdfMetadata;
import org.kth.sth.biosignals.storage.DataAccessHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("session")
public class StateController {

    private final static Logger log = Logger.getLogger(StateController.class);

    @Autowired
    private  SessionData sessionData;

    @Autowired
    private DataAccessHelper dataAccessHelper;

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public void visualize(@RequestParam(value = "uuid", required = false) String uuid) throws Exception{

        if (uuid != null) {
            EdfMetadata edfMetadata = dataAccessHelper.getMetadata(uuid);
            EdfData edfData = dataAccessHelper.loadRecord(uuid, 0, edfMetadata);

            Edf edf = new Edf();
            edf.setEdfMetadata(edfMetadata);
            edf.setEdfData(edfData);
            sessionData.setEdf(edf);
        }

        log.info(String.format("Selected edf with uuid=%s", uuid));

    }


}

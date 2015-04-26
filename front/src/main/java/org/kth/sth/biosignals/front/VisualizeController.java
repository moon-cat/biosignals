package org.kth.sth.biosignals.front;


import org.kth.sth.biosignals.edf2json.model.EdfMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VisualizeController {

    @Autowired
    private  SessionData sessionData;

    @RequestMapping("/visualize/metadata")
    public EdfMetadata getMetadata() {
        return sessionData.getEdf() != null ? sessionData.getEdf().getEdfMetadata() : null;
    }

    @RequestMapping("/visualize/data")
    public List<Object> getData(@RequestParam("recordNo") Integer recordNo, @RequestParam("signalNo") Integer signalNo) {
        return sessionData.getEdf().getEdfData().getEdfDataRecords().get(recordNo).getEdfSignalDataRecords().get(signalNo).getData();
    }

}

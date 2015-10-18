package org.kth.sth.biosignals.front;


import org.apache.log4j.Logger;
import org.kth.sth.biosignals.edf2json.model.EdfMetadata;
import org.kth.sth.biosignals.storage.EdfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Scope("session")
public class VisualizeController {

    private final static Logger log = Logger.getLogger(VisualizeController.class);

    @Autowired
    private  SessionData sessionData;

    @Autowired
    private EdfRepository repository;



    @RequestMapping("/visualize/metadata")
    public EdfMetadata getMetadata() {
        if (sessionData.getEdf() == null){
            log.warn("No edf found in session");
        }
        return sessionData.getEdf() != null ? sessionData.getEdf().getEdfMetadata() : null;
    }

    @RequestMapping("/visualize/data")
    public List<Object> getData(@RequestParam("recordNo") Integer recordNo, @RequestParam("signalNo") Integer signalNo,
                                @RequestParam("zoom") Double zoom, @RequestParam("positionInRecord") Integer positionInRecord) {
        if (zoom == 1){
            return sessionData.getEdf().getEdfData().getEdfDataRecords().get(recordNo).getEdfSignalDataRecords().get(signalNo).getData();
        } else if (zoom < 1) {
            List<Object> data = sessionData.getEdf().getEdfData().getEdfDataRecords().get(recordNo).getEdfSignalDataRecords().get(signalNo).getData();
            int length = (int) (data.size() * zoom);
            return data.subList(positionInRecord * length, (positionInRecord + 1) * length);
        } else {
            List<Object> allData = new ArrayList<Object>();
            for (int i = 0; i < zoom; i++){
                List<Object> data = sessionData.getEdf().getEdfData().getEdfDataRecords().get(recordNo + i).getEdfSignalDataRecords().get(signalNo).getData();
                allData.addAll(data);
            }
            return allData;
        }
    }
}

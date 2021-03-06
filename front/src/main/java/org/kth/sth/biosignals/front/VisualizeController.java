package org.kth.sth.biosignals.front;


import org.apache.log4j.Logger;
import org.kth.sth.biosignals.edf2json.model.EdfData;
import org.kth.sth.biosignals.edf2json.model.EdfDataRecord;
import org.kth.sth.biosignals.edf2json.model.EdfMetadata;
import org.kth.sth.biosignals.storage.DataAccessHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@Scope("session")
public class VisualizeController {

    private final static Logger log = Logger.getLogger(VisualizeController.class);

    @Autowired
    private  SessionData sessionData;

    @Autowired
    private DataAccessHelper dataAccessHelper;

    @RequestMapping("/visualize/metadata")
    public EdfMetadata getMetadata() {
        if (sessionData.getEdf() == null){
            log.warn("No edf found in session");
        }
        return sessionData.getEdf() != null ? sessionData.getEdf().getEdfMetadata() : null;
    }

    @RequestMapping("/visualize/data")
    public List<Object> getData(@RequestParam("recordNo") Integer recordNo, @RequestParam("signalNo") Integer signalNo,
                                @RequestParam("zoom") Double zoom, @RequestParam("positionInRecord") Integer positionInRecord)
    throws Exception {
        if (zoom == 1){
            return getDataRecord(recordNo).getEdfSignalDataRecords().get(signalNo).getData();
        } else if (zoom < 1) {
            List<Object> data = getDataRecord(recordNo).getEdfSignalDataRecords().get(signalNo).getData();
            int length = (int) (data.size() * zoom);
            return data.subList(positionInRecord * length, (positionInRecord + 1) * length);
        } else {
            List<Object> allData = new LinkedList<Object>();
            for (int i = 0; i < zoom; i++){
                List<Object> data = getDataRecord(recordNo + i).getEdfSignalDataRecords().get(signalNo).getData();
                allData.addAll(data);
            }
            return allData;
        }
    }

    private EdfDataRecord getDataRecord(Integer recordNo) throws Exception{
        EdfDataRecord result = getDataRecordFromSession(recordNo);

        if (result == null){
            EdfData edfData = dataAccessHelper.loadRecord(sessionData.getUuid(), recordNo,
                    sessionData.getEdf().getEdfMetadata());

            if (edfData != null) {
                sessionData.getEdf().setEdfData(edfData);
                result = getDataRecordFromSession(recordNo);
                log.info("Found data");
            } else {
                log.warn("Not found data");
            }
        }

        return result;
    }

    private EdfDataRecord getDataRecordFromSession(Integer recordNo){
        List<EdfDataRecord> sessionRecords = sessionData.getEdf().getEdfData().getEdfDataRecords();
        Integer firstRecord = Integer.valueOf(sessionRecords.get(0).getDataRecordId());
        Integer lastRecord = Integer.valueOf(sessionRecords.get(sessionRecords.size() - 1).getDataRecordId());

        log.info("First record: " + firstRecord);
        log.info("Last record: " + lastRecord);

        if (recordNo >= firstRecord && recordNo <= lastRecord){
            return sessionRecords.get(recordNo - firstRecord);
        } else {
            return null;
        }
    }
}

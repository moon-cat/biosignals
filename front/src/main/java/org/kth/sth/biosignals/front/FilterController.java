package org.kth.sth.biosignals.front;

import org.apache.log4j.Logger;
import org.kth.sth.biosignals.edf2json.model.EdfData;
import org.kth.sth.biosignals.edf2json.model.EdfDataRecord;
import org.kth.sth.biosignals.storage.DataAccessHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@Scope("session")
public class FilterController {

    private final static Logger log = Logger.getLogger(VisualizeController.class);

    @Autowired
    private  SessionData sessionData;

    @Autowired
    private DataAccessHelper dataAccessHelper;

    @RequestMapping("/filter/data")
    public List<Object> getData(@RequestParam("recordNo") Integer recordNo, @RequestParam("signalNo") Integer signalNo,
                                @RequestParam("zoom") Double zoom, @RequestParam("positionInRecord") Integer positionInRecord,
                                @RequestParam("filterType") FilterType filterType) throws Exception{
        List<Object> result = null;

        if (zoom == 1){
            result = getDataRecord(recordNo).getEdfSignalDataRecords().get(signalNo).getData();
        } else if (zoom < 1) {
            List<Object> data = getDataRecord(recordNo).getEdfSignalDataRecords().get(signalNo).getData();
            int length = (int) (data.size() * zoom);
            result = data.subList(positionInRecord * length, (positionInRecord + 1) * length);
        } else {
            List<Object> allData = new LinkedList<Object>();
            for (int i = 0; i < zoom; i++){
                List<Object> data = getDataRecord(recordNo + i).getEdfSignalDataRecords().get(signalNo).getData();
                allData.addAll(data);
            }
            result = allData;
        }
        return filter(result, filterType);
    }

    private List<Object> filter(List<Object> input, FilterType filterType) {
        switch (filterType){
            case lowpass: return filterLowPass(input);
            case highpass: return filterHighPass(input);
            default: return input;
        }
    }

    private List<Object> filterHighPass(List<Object> input) {
        List<Object> result = new ArrayList<Object>(input.size());
        result.add(input.get(0));
        for (int i = 1; i < input.size(); i++){
            result.add(i, ((Integer) (result.get(i - 1)) +  (Integer) (input.get(i)) - (Integer) (input.get(i - 1))) / 2);
        }
        return result;
    }

    private List<Object> filterLowPass(List<Object> input) {
        List<Object> result = new ArrayList<Object>(input.size());
        result.add(input.get(0));
        for (int i = 1; i < input.size(); i++){
            result.add(i, ((Integer) (result.get(i - 1)) +  (Integer) (input.get(i))) / 2);
        }
       return result;
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

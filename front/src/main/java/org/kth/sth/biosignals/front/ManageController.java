package org.kth.sth.biosignals.front;

import org.kth.sth.biosignals.edf2json.model.EdfProperties;

import org.kth.sth.biosignals.storage.DataAccessHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Scope("session")
public class ManageController {

    @Autowired
    private SessionData sessionData;

    @Autowired
    private DataAccessHelper dataAccessHelper;


    @RequestMapping("/manage/list")
    public List<EdfProperties> list() throws Exception{
        return dataAccessHelper.list();
    }

    @RequestMapping("/manage/remove")
    public void remove(@RequestParam("uuid") String uuid) throws Exception {
        dataAccessHelper.remove(uuid);

        if (sessionData.getEdf() != null && uuid.equals(sessionData.getEdf().getEdfMetadata().getEdfProperties().getUuid())){
            sessionData.setEdf(null);
        }

    }
}

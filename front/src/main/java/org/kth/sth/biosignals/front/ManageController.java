package org.kth.sth.biosignals.front;

import org.kth.sth.biosignals.edf2json.model.EdfProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ManageController {

    @Autowired
    private SessionData sessionData;

    @Autowired
    private InMemoryRepository repository;

    @RequestMapping("/manage/list")
    public List<EdfProperties> list(){
        return repository.list();
    }

    @RequestMapping("/manage/remove")
    public void remove(@RequestParam("uuid") String uuid){
        repository.remove(uuid);

        if (sessionData.getEdf() != null && uuid.equals(sessionData.getEdf().getEdfProperties().getUuid())){
            sessionData.setEdf(null);
        }

    }
}

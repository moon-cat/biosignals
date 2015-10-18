package org.kth.sth.biosignals.front;

import org.kth.sth.biosignals.edf2json.model.EdfProperties;

import org.kth.sth.biosignals.storage.EdfRepository;
import org.kth.sth.biosignals.storage.InMemoryRepository;
import org.kth.sth.biosignals.storage.RedisRepository;
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
    private EdfRepository repository;


    @RequestMapping("/manage/list")
    public List<EdfProperties> list() throws Exception{
        return repository.list();
    }

    @RequestMapping("/manage/remove")
    public void remove(@RequestParam("uuid") String uuid) throws Exception {
        repository.remove(uuid);

        if (sessionData.getEdf() != null && uuid.equals(sessionData.getEdf().getEdfProperties().getUuid())){
            sessionData.setEdf(null);
        }

    }
}

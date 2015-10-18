package org.kth.sth.biosignals.front;

import org.apache.log4j.Logger;
import org.kth.sth.biosignals.storage.EdfRepository;
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
    private EdfRepository repository;


    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public void visualize(@RequestParam(value = "uuid", required = false) String uuid) throws Exception{


        if (uuid != null) {
            sessionData.setEdf(repository.get(uuid));
        }

        if (sessionData != null) {
            log.info(String.format("Selected edf with uuid=%s", sessionData.getEdf().getEdfProperties().getUuid()));

        }
    }


}

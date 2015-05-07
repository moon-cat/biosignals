package org.kth.sth.biosignals.front;


import org.kth.sth.biosignals.storage.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
public class FrontController {

    @Autowired
    private  SessionData sessionData;

    @Autowired
    private RedisRepository repository;

    @RequestMapping("/home")
    String home() throws Exception{
        return "home";
    }

    @RequestMapping("/home/upload")
    String upload() throws Exception{
        return "upload";
    }


    @RequestMapping("/home/manage")
    String manage() throws Exception{
        return "manage";
    }

    @RequestMapping("/home/visualize")
    String visualize(@RequestParam(value = "uuid", required = false) String uuid) throws Exception{

        if (StringUtils.isNotEmpty(uuid)) {
            sessionData.setEdf(repository.get(uuid));
        }

        return "visualize";
    }

    @RequestMapping("/home/analyze")
    String analyze(@RequestParam(value = "uuid", required = false) String uuid) throws Exception{

        if (StringUtils.isNotEmpty(uuid)) {
            sessionData.setEdf(repository.get(uuid));
        }

        return "analyze";
    }


}

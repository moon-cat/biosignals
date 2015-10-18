package org.kth.sth.biosignals.storage;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kth.sth.biosignals.edf2json.model.Edf;
import org.kth.sth.biosignals.edf2json.model.EdfProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@Component
public class RedisRepository {

    @Value("${storage.host}")
    private String redisHost;

    @PostConstruct
    public void init() throws Exception{
    }


    @PreDestroy
    public void destroy() throws Exception{
    }

    public void save(Edf edf) throws Exception{
        Jedis jedis = new Jedis(redisHost);
        ObjectMapper object2JsonMapper = new ObjectMapper(new JsonFactory());

        String edfData = object2JsonMapper.writeValueAsString(edf);
        String edfPropertiesData = object2JsonMapper.writeValueAsString(edf.getEdfProperties());
        jedis.set("edfProperties-" + edf.getEdfProperties().getUuid(), edfPropertiesData);
        jedis.set("edf-" + edf.getEdfProperties().getUuid(), edfData);
        jedis.close();

    }

    public Edf get(String uuid) throws Exception{
        Jedis jedis = new Jedis(redisHost);
        ObjectMapper object2JsonMapper = new ObjectMapper(new JsonFactory());

        String edfData = jedis.get("edf-" + uuid);
        jedis.close();

        return object2JsonMapper.readValue(edfData, Edf.class);
    }

    public List<EdfProperties> list() throws Exception{
        Jedis jedis = new Jedis(redisHost);
        ObjectMapper object2JsonMapper = new ObjectMapper(new JsonFactory());

        Set<String> keys = jedis.keys("edfProperties-*");
        List<EdfProperties> edfProperties = new ArrayList<EdfProperties>();

        for (String key : keys) {
            System.out.println("Key:" + key);
            String edfPropertiesData = jedis.get(key);
            if (edfPropertiesData != null) {
                edfProperties.add(object2JsonMapper.readValue(edfPropertiesData, EdfProperties.class));
            }
        }
        jedis.close();

        return edfProperties;
    }

    public boolean remove(String uuid) throws Exception{
        Jedis jedis = new Jedis(redisHost);

        Long countEdf = jedis.del("edf-" + uuid);
        Long countEdfProperties = jedis.del("edfProperties-" + uuid);
        jedis.close();

        return countEdf > 0 && countEdfProperties > 0;
    }
}

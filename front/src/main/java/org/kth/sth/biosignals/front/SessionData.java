package org.kth.sth.biosignals.front;

import org.kth.sth.biosignals.edf2json.model.Edf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session")
public class SessionData {

    private Edf edf;

    public Edf getEdf() {
        return edf;
    }

    public void setEdf(Edf edf) {
        this.edf = edf;
    }
}

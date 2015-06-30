package org.renci.gene2accession;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.renci.gene2accession.model.Record;

public class G2AParser {

    private static G2AParser instance;

    public static G2AParser getInstance() {
        if (instance == null) {
            instance = new G2AParser();
        }
        return instance;
    }

    private G2AParser() {
        super();
    }

    public List<Record> parse(final File gene2accessionFile) {
        return parse(null, gene2accessionFile);
    }

    public List<Record> parse(final G2AFilter filter, final File gene2accessionFile) {
        List<Record> ret = new ArrayList<Record>();
        ExecutorService es = Executors.newSingleThreadExecutor();
        try {
            Future<List<Record>> future = es.submit(new G2ADeserializer(filter, gene2accessionFile));
            es.shutdown();
            es.awaitTermination(5L, TimeUnit.MINUTES);
            ret.addAll(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ret;
    }

}

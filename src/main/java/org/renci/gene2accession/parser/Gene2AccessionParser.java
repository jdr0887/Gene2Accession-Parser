package org.renci.gene2accession.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.renci.gene2accession.model.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gene2AccessionParser {

    private final Logger logger = LoggerFactory.getLogger(Gene2AccessionParser.class);

    private static Gene2AccessionParser instance;

    public static Gene2AccessionParser getInstance() {
        if (instance == null) {
            instance = new Gene2AccessionParser();
        }
        return instance;
    }

    private Gene2AccessionParser() {
        super();
    }

    public List<Record> parse(final File gene2accessionFile) {
        return parse(null, gene2accessionFile);
    }

    public List<Record> parse(final Filter filter, final File gene2accessionFile) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        List<Record> ret = new ArrayList<Record>();
        try {
            Future<List<Record>> future = es.submit(new Gene2AccessionDeserializer(filter, gene2accessionFile));
            es.shutdown();
            es.awaitTermination(5L, TimeUnit.MINUTES);
            ret.addAll(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ret;
    }

}

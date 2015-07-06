package org.renci.gene2accession;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import org.renci.gene2accession.model.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class G2AParser {

    private final Logger logger = LoggerFactory.getLogger(G2AParser.class);

    private int threads;

    private static G2AParser instance;

    public static G2AParser getInstance() {
        if (instance == null) {
            instance = new G2AParser(4);
        }
        return instance;
    }

    public static G2AParser getInstance(int threads) {
        if (instance == null) {
            instance = new G2AParser(threads);
        }
        return instance;
    }

    private G2AParser(int threads) {
        super();
        this.threads = threads;
    }

    public List<Record> parse(final File gene2accessionFile) {
        return parse(null, gene2accessionFile);
    }

    public List<Record> parse(final G2AFilter filter, final File gene2accessionFile) {
        long start = System.currentTimeMillis();

        List<Record> ret = new ArrayList<Record>();
        ExecutorService es = Executors.newFixedThreadPool(threads);
        List<Future<Record>> futures = new ArrayList<Future<Record>>();

        try (FileInputStream fis = new FileInputStream(gene2accessionFile);
                GZIPInputStream gis = new GZIPInputStream(fis);
                InputStreamReader isr = new InputStreamReader(gis);
                BufferedReader br = new BufferedReader(isr)) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }
                futures.add(es.submit(new G2ADeserializer(filter, line)));
            }
            es.shutdown();
            es.awaitTermination(5L, TimeUnit.MINUTES);

            for (Future<Record> future : futures) {
                Record record = future.get();
                if (record != null) {
                    ret.add(record);
                }
            }

        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        logger.info("duration: {} seconds", (end - start) / 1000);

        return ret;
    }

}

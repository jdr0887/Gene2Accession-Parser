package org.renci.gene2accession.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang3.StringUtils;
import org.renci.gene2accession.model.OrientationType;
import org.renci.gene2accession.model.Record;
import org.renci.gene2accession.model.StatusType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gene2AccessionDeserializer implements Callable<List<Record>> {

    private final Logger logger = LoggerFactory.getLogger(Gene2AccessionDeserializer.class);

    private Filter filter;

    private File gene2accessionFile;

    public Gene2AccessionDeserializer(final Filter filter, final File gene2accessionFile) {
        super();
        this.filter = filter;
        this.gene2accessionFile = gene2accessionFile;
    }

    @Override
    public List<Record> call() throws Exception {
        List<Record> ret = new ArrayList<Record>();
        try (FileInputStream fis = new FileInputStream(gene2accessionFile);
                GZIPInputStream gis = new GZIPInputStream(fis);
                InputStreamReader isr = new InputStreamReader(gis);
                BufferedReader br = new BufferedReader(isr)) {
            Record record = null;
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }
                record = new Record();
                Scanner scanner = new Scanner(line).useDelimiter("\t");
                record.setTaxonId(Integer.valueOf(scanner.next()));
                record.setGeneId(Long.valueOf(scanner.next()));
                record.setStatus(StatusType.valueOf(scanner.next()));

                String rnaNucleotideAccessionVersion = scanner.next();
                if (!rnaNucleotideAccessionVersion.equals("-")) {
                    record.setRNANucleotideAccessionVersion(rnaNucleotideAccessionVersion);
                }
                String rnaNucleotideGenInfoId = scanner.next();
                if (!rnaNucleotideGenInfoId.equals("-")) {
                    record.setRNANucleotideGenInfoId(Long.valueOf(rnaNucleotideGenInfoId));
                }

                String proteinAccessionVersion = scanner.next();
                if (!proteinAccessionVersion.equals("-")) {
                    record.setProteinAccessionVersion(proteinAccessionVersion);
                }
                String proteinGenInfoId = scanner.next();
                if (!proteinGenInfoId.equals("-")) {
                    record.setProteinGenInfoId(Long.valueOf(proteinGenInfoId));
                }

                String genomicNucleotideAccessionVersion = scanner.next();
                if (!genomicNucleotideAccessionVersion.equals("-")) {
                    record.setGenomicNucleotideAccessionVersion(genomicNucleotideAccessionVersion);
                }
                String genomicNucleotidGenInfoId = scanner.next();
                if (!genomicNucleotidGenInfoId.equals("-")) {
                    record.setGenomicNucleotideGenInfoId(Long.valueOf(genomicNucleotidGenInfoId));
                }

                String genomicStartPosition = scanner.next();
                if (!genomicStartPosition.equals("-")) {
                    record.setGenomicStartPosition(Integer.valueOf(genomicStartPosition));
                }

                String genomicEndPosition = scanner.next();
                if (!genomicEndPosition.equals("-")) {
                    record.setGenomicEndPosition(Integer.valueOf(genomicEndPosition));
                }

                String orientation = scanner.next();
                record.setOrientation(StringUtils.isNotEmpty(orientation) && orientation.equals("-") ? OrientationType.MINUS
                        : OrientationType.PLUS);
                record.setAssembly(scanner.next());

                String maturePeptideAccessionVersion = scanner.next();
                if (!maturePeptideAccessionVersion.equals("-")) {
                    record.setMaturePeptideAccessionVersion(maturePeptideAccessionVersion);
                }
                String maturePeptideGenInfoId = scanner.next();
                if (!maturePeptideGenInfoId.equals("-")) {
                    record.setMaturePeptideGenInfoId(Long.valueOf(maturePeptideGenInfoId));
                }

                record.setSymbol(scanner.next());
                scanner.close();

                if (filter != null && !filter.accept(record)) {
                    continue;
                }

                logger.debug(record.toString());
                ret.add(record);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

}

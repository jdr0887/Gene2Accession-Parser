package org.renci.gene2accession;

import java.util.Scanner;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.renci.gene2accession.model.OrientationType;
import org.renci.gene2accession.model.Record;
import org.renci.gene2accession.model.StatusType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class G2ADeserializer implements Callable<Record> {

    private final Logger logger = LoggerFactory.getLogger(G2ADeserializer.class);

    private G2AFilter filter;

    private String line;

    public G2ADeserializer(G2AFilter filter, String line) {
        super();
        this.filter = filter;
        this.line = line;
    }

    @Override
    public Record call() throws Exception {
        Record record = new Record();
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
            return null;
        }

        logger.debug(record.toString());

        return record;
    }

}

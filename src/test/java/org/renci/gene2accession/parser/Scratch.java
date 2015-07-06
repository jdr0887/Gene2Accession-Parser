package org.renci.gene2accession.parser;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.renci.gene2accession.G2AFilter;
import org.renci.gene2accession.G2AParser;
import org.renci.gene2accession.filter.G2AAndFilter;
import org.renci.gene2accession.filter.G2AAssemblyFilter;
import org.renci.gene2accession.filter.G2ARNANucleotideAccessionVersionPrefixFilter;
import org.renci.gene2accession.filter.G2ATaxonIdFilter;
import org.renci.gene2accession.model.Record;

public class Scratch {

    @Test
    public void testFilterAssembly() {
        G2AParser parser = G2AParser.getInstance(16);
        G2AAndFilter andFilter = new G2AAndFilter(Arrays.asList(new G2AFilter[] { new G2ATaxonIdFilter(9606),
                new G2AAssemblyFilter("Reference GRCh38.p2 Primary Assembly"),
                new G2ARNANucleotideAccessionVersionPrefixFilter(Arrays.asList(new String[] { "NM_", "NR_" })) }));
        List<Record> recordList = parser.parse(andFilter, new File("/tmp", "gene2refseq.gz"));
        assertTrue(recordList != null && !recordList.isEmpty());
        for (Record record : recordList) {
            assertTrue(record.getTaxonId().equals(9606));
            assertTrue(record.getAssembly().contains("GRCh38.p2"));
        }

    }
}

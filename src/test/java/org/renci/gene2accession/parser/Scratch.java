package org.renci.gene2accession.parser;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.renci.gene2accession.G2AFilter;
import org.renci.gene2accession.G2AParser;
import org.renci.gene2accession.filter.G2AAndFilter;
import org.renci.gene2accession.filter.G2AAssemblyFilter;
import org.renci.gene2accession.filter.G2AGenomicNucleotideAccessionVersionPrefixFilter;
import org.renci.gene2accession.filter.G2AProteinAccessionVersionPrefixFilter;
import org.renci.gene2accession.filter.G2ARNANucleotideAccessionVersionPrefixFilter;
import org.renci.gene2accession.filter.G2ATaxonIdFilter;
import org.renci.gene2accession.model.Record;

public class Scratch {

    @Test
    public void testRegex() {
        String primary = "Reference GRCh38.p2 Primary Assembly";
        String alt = "Reference GRCh38.p2 ALT_REF_LOCI_1";
        Pattern p = Pattern.compile("Reference.*(Primary Assembly|ALT_REF_LOCI.*)");
        Matcher m = p.matcher(primary);
        assertTrue(m.matches());
    }

    @Test
    public void testFilterAssembly() {
        G2AParser parser = G2AParser.getInstance(16);
        G2AAndFilter andFilter = new G2AAndFilter(Arrays.asList(new G2AFilter[] { new G2ATaxonIdFilter(9606),
                new G2AAssemblyFilter("Reference.*(Primary Assembly|ALT_REF_LOCI.*)"),
                new G2AProteinAccessionVersionPrefixFilter(Arrays.asList(new String[] { "NP_" })),
                new G2ARNANucleotideAccessionVersionPrefixFilter(Arrays.asList(new String[] { "NM_", "NR_" })) }));
        List<Record> recordList = parser.parse(andFilter, new File("/tmp", "gene2refseq.gz"));
        assertTrue(recordList != null && !recordList.isEmpty());
        for (Record record : recordList) {
            assertTrue(record.getTaxonId().equals(9606));
            assertTrue(record.getAssembly().contains("Reference"));
            assertTrue(record.getAssembly().contains("Primary Assembly")
                    || record.getAssembly().contains("ALT_REF_LOCI"));
            if (!record.getAssembly().contains("Primary Assembly")) {
                System.out.println(record.toString());
            }
        }
        System.out.println(recordList.size());
    }

    @Test
    public void testFindOddities() {
        G2AParser parser = G2AParser.getInstance(16);
        G2AAndFilter andFilter = new G2AAndFilter(Arrays.asList(new G2AFilter[] { new G2ATaxonIdFilter(9606),
                new G2AAssemblyFilter("Reference GRCh38.p2 Primary Assembly"),
                new G2AGenomicNucleotideAccessionVersionPrefixFilter(Arrays.asList(new String[] { "NC_" })),
                new G2ARNANucleotideAccessionVersionPrefixFilter(Arrays.asList(new String[] { "NM_", "NR_" })) }));
        List<Record> recordList = parser.parse(andFilter, new File("/tmp", "gene2refseq.gz"));
        assertTrue(recordList != null && !recordList.isEmpty());
        int count = 0;
        for (Record record : recordList) {
            if (StringUtils.isNotEmpty(record.getRNANucleotideAccessionVersion())
                    && StringUtils.isNotEmpty(record.getProteinAccessionVersion())
                    && StringUtils.isEmpty(record.getGenomicNucleotideAccessionVersion())) {
                count++;
            }
        }
        System.out.println(count);
    }

}

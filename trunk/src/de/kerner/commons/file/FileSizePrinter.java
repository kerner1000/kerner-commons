package de.kerner.commons.file;

import java.io.File;

public class FileSizePrinter {
    private static final String POST_FIX_B = "B";
    private static final String POST_FIX_K = "K";
    private static final String POST_FIX_M = "M";
    private static final String POST_FIX_G = "G";
    private final long oneK;
    private final long oneM;
    private final long oneG;
    private final long size;

    FileSizePrinter(long size, boolean usePowerOf1000) {
        if (usePowerOf1000)
            oneK = 1000L;
        else
            oneK = 1024L;

        oneM = oneK * oneK;
        oneG = oneK * oneM;
        this.size = size;
    }

    FileSizePrinter(File file) {
        oneK = 1024L;
        oneM = oneK * oneK;
        oneG = oneK * oneM;
        this.size = file.length();
    }

    public String toString() {
        String postFix = POST_FIX_B;
        long sizeCp = size;
        if (sizeCp / oneG > 0) {
            postFix = POST_FIX_G;
            sizeCp = sizeCp / oneG;
        } else if (sizeCp / oneM > 0) {
            postFix = POST_FIX_M;
            sizeCp = sizeCp / oneM;
        } else if (sizeCp / oneK > 0) {
            postFix = POST_FIX_K;
            sizeCp = sizeCp / oneK;
        } else {
            // nothing
        }
        return sizeCp + postFix;
    }
}

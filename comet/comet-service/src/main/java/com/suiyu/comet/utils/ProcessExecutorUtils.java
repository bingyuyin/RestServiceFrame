package com.suiyu.comet.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by BingyuYin on 2016/5/25.
 */
public class ProcessExecutorUtils {
    private ProcessExecutorUtils() {

    }
    public static class ProcessOutput {
        public int exitVal;
        public String errorMessage;
        public String outputMessage;
    }

    private static class StreamGobbler extends Thread {
        private String type;
        private InputStream is;
        private StringBuffer stringBuffer;

        public String getBufferAsString() {
            return stringBuffer.toString();
        }

        public StreamGobbler(InputStream is, String type) {
            this.type = type;
            this.is = is;
            this.stringBuffer = new StringBuffer();
            this.stringBuffer.append("Type: " + this.type + System.lineSeparator());
        }

        @Override
        public void run(){
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            try {
                while ((line = br.readLine()) != null) {
                    stringBuffer.append(line + System.lineSeparator());
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }
    }

    private static ProcessOutput collectExecutorResult(Process process, long waitTime) throws InterruptedException{
        StreamGobbler errorStreamGobbler = new StreamGobbler(process.getErrorStream(), "Error");
        StreamGobbler outputStreamGobbler = new StreamGobbler(process.getInputStream(), "Output");
        errorStreamGobbler.start();
        outputStreamGobbler.start();

        if (waitTime != -1) {
            process.wait(waitTime);
        } else {
            process.waitFor();
        }

        ProcessOutput res = new ProcessOutput();
        res.exitVal = process.exitValue();
        res.errorMessage = errorStreamGobbler.getBufferAsString();
        res.outputMessage = outputStreamGobbler.getBufferAsString();
        return res;
    }

    public static ProcessOutput execute(String command, long waitTime) {
        try {
            return collectExecutorResult(Runtime.getRuntime().exec(command), waitTime);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        return null;
    }

    public static ProcessOutput execute(ProcessBuilder pb, long waitTime){
        try {
            return collectExecutorResult(pb.start(), waitTime);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        return null;
    }
}

package com.suiyu.comet.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * Created by BingyuYin on 2016/5/25.
 */
public class ProcessExecutorUtils {
    private ProcessExecutorUtils() {

    }
    public static class ProcessOutput {
        public int exitVal = 0;
        public String errorMessage = null;
        public String outputMessage = null;
        public Process process = null;
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

        if (waitTime != 0) {
            process.waitFor();
        }

        ProcessOutput res = new ProcessOutput();
        res.exitVal = process.exitValue();
        res.errorMessage = errorStreamGobbler.getBufferAsString();
        res.outputMessage = outputStreamGobbler.getBufferAsString();
        res.process = process;
        return res;
    }

    private static ProcessOutput retrieveProcessOutput(Future<Object> future, long waitTime) throws InterruptedException,
            ExecutionException,
            TimeoutException{
        ProcessOutput res = null;
        if (waitTime < 0) {
            res = (ProcessOutput)future.get();
        } else {
            res = (ProcessOutput)future.get(waitTime, TimeUnit.MILLISECONDS);
        }
        return res;
    }

    public static ProcessOutput execute(final String command, final long waitTime) {

        Future<Object> future = Executors.newSingleThreadExecutor().submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return collectExecutorResult(Runtime.getRuntime().exec(command), waitTime);
            }
        });

        ProcessOutput res = null;
        try {
            res = retrieveProcessOutput(future, waitTime);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            if (res != null && res.process != null) {
                res.process.destroy();
            }
        } finally {
            future.cancel(true);
        }

        return null;

    }

    public static ProcessOutput execute(final ProcessBuilder pb, final long waitTime) {
       Future<Object> future = Executors.newSingleThreadExecutor().submit(new Callable<Object> () {
           @Override
           public Object call() throws Exception {
               return collectExecutorResult(pb.start(), waitTime);
           }
       });
        ProcessOutput res = null;
        try {
            res = retrieveProcessOutput(future, waitTime);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            if (res != null && res.process != null) {
                res.process.destroy();
            }
        } finally {
            future.cancel(true);
        }
        return null;
    }




}

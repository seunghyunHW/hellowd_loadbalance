package com.hellowd.monitor;

import com.google.gson.JsonObject;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2015-12-24
 * Time : 오전 11:42
 * To change this template use File | Settings | File and Code Templates.
 */
public class ServerMonitor {

    private Runtime runtime = Runtime.getRuntime();

    private long startTime;

    private AtomicLong requests;
    private AtomicLong errors;
    private AtomicLong connections;
    private AtomicLong receivedBytes;
    private AtomicLong sendBytes;
    private AtomicLong currentProcessTime;
    private AtomicLong totalProcessTime;

    public ServerMonitor(){
        this(new Date().getTime());
    }

    public ServerMonitor(long startTime){
        this.startTime = startTime;
        this.requests = new AtomicLong(0);
        this.requests 	 		= new AtomicLong(0);
        this.errors 	 		= new AtomicLong(0);
        this.connections 		= new AtomicLong(0);
        this.receivedBytes 		= new AtomicLong(0);
        this.sendBytes 	 		= new AtomicLong(0);
        this.currentProcessTime = new AtomicLong(0);
        this.totalProcessTime  	= new AtomicLong(0);
    }

    public String OSname(){
        return System.getProperty("os.name");
    }


    public String OSversion() {
        return System.getProperty("os.version");
    }

    public String OsArch() {
        return System.getProperty("os.arch");
    }



    public JsonObject getOsInfo(){
        JsonObject obj = new JsonObject();
        obj.addProperty("name",this.OSname());
        obj.addProperty("version",this.OSversion());
        obj.addProperty("arch",this.OsArch());
        obj.addProperty("processors",runtime.availableProcessors());
        return obj;
    }



    static int mb = 1024*1024;

    public JsonObject getMemoryInfo() {
        JsonObject obj = new JsonObject();

        long maxmem   = runtime.maxMemory()/mb;
        long totalmem = runtime.totalMemory()/mb;
        long freemem  = runtime.freeMemory()/mb;
        long usagemem = totalmem-freemem;

        obj.addProperty("maxmem",maxmem);
        obj.addProperty("totalmem",totalmem);
        obj.addProperty("freemem",freemem);
        obj.addProperty("usagemem",usagemem);
        return obj;
    }

    public long getUptime() {
        return getUptime(Calendar.SECOND);
    }

    public long getUptime(int unit) {
        if( unit == Calendar.SECOND ) 	 return (new Date().getTime() - startTime)/1000;
        if( unit == Calendar.MILLISECOND ) return (new Date().getTime() - startTime);

        return (new Date().getTime() - startTime)/1000;
    }



    public long getRequest() {
        return requests.get();
    }


    public long getReceivedBytes() {
        return receivedBytes.get();
    }


    public long getErrorCount() {
        return errors.get();
    }

    public long getConnectionCount() {
        return connections.get();
    }

    public long getCurrentProcessTime() {
        return ((long) currentProcessTime.get()/1000);
    }

    public long getAverageProcessTime() {
        return ((long) averageProcessTime()/1000);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("{'os':");
        sb.append(getOsInfo());
        sb.append(",'memory':");
        sb.append(getMemoryInfo());
        sb.append(",'uptime':");
        sb.append(getUptime());
        sb.append(",'requests':");
        sb.append(getRequest());
        sb.append(",'recvedBytes':");
        sb.append(receivedBytes.get());
        sb.append(",'sendBytes':");
        sb.append(sendBytes.get());
        sb.append(",'errors':");
        sb.append(errors.get());
        sb.append(",'connections':");
        sb.append(connections.get());
        sb.append(",'currentProcessTime':");
        sb.append(((float) currentProcessTime.get()/1000));
        sb.append(",'averageProcessTime':");
        sb.append(((float) averageProcessTime()/1000));
        sb.append("}");

        return sb.toString();
    }



    public long averageRequests() {
        return requests.get()/getUptime();
    }

    public long averageErrors() {
        return errors.get()/getUptime();
    }

    public long averageConnections() {
        return connections.get()/getUptime();
    }

    public long averageRecvedBytes() {
        return receivedBytes.get()/getUptime();
    }

    public long averageSendBytes() {
        return sendBytes.get()/getUptime();
    }

    public long averageProcessTime() {
        return (requests.get()!=0)?totalProcessTime.get()/requests.get():0;
    }

    public long incrementAndGetRequests() {
        return requests.incrementAndGet();
    }

    public long getRequests() {
        return requests.get();
    }

    public long incrementAndGetErrors() {
        return errors.incrementAndGet();
    }

    public long getErrors() {
        return errors.get();
    }

    public long incrementAndGetConnections() {
        return connections.incrementAndGet();
    }

    public long decrementAndGetConnections() {
        return connections.decrementAndGet();
    }

    public long getConnections() {
        return connections.get();
    }

    public long addAndGetRecvedBytes(long bytes) {
        return receivedBytes.addAndGet(bytes);
    }

    public long getRecvedBytes() {
        return receivedBytes.get();
    }

    public long addAndGetSendBytes(long bytes) {
        return sendBytes.addAndGet(bytes);
    }

    public long getSendBytes() {
        return sendBytes.get();
    }

    public void setCurrentProcessTime(long processTime) {
        currentProcessTime.set(processTime);
    }

    public long addAndGetTotalProcessTime(long processTime) {
        return totalProcessTime.addAndGet(processTime);
    }

}

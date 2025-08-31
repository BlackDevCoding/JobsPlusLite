package dev.blackdev.jobsplus.lite.core;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class JobManager {
    private final Map<String, Job> jobs = new LinkedHashMap<>();
    public void register(Job job){ jobs.put(job.getId(), job); }
    public Collection<Job> getJobs(){ return jobs.values(); }
    public Job getJob(String id){ return jobs.get(id); }
    public boolean exists(String id){ return jobs.containsKey(id); }
}

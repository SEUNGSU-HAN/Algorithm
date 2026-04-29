import java.util.*;

class Solution {
    class Job {
        int id, start, exec;
        
        public Job(int id, int start, int exec) {
            this.id = id;
            this.start = start;
            this.exec = exec;
        }
    }
    public int solution(int[][] jobs) {
        int answer = 0;
        int N = jobs.length;
        PriorityQueue<Job> waitQ = new PriorityQueue<>((j1, j2) -> {
           if(j1.exec != j2.exec) return Integer.compare(j1.exec, j2.exec);
            if(j1.start != j2.start) return Integer.compare(j1.start, j2.start);
            return Integer.compare(j1.id, j2.id);
        });
        PriorityQueue<Job> jobsQ = new PriorityQueue<>((j1, j2) -> Integer.compare(j1.start, j2.start));
        int[] jobsReturnTime = new int[N];
        
        for(int i=0; i<N; i++) 
            jobsQ.offer(new Job(i, jobs[i][0], jobs[i][1]));
        
        int time = 0;
        int runningTime = 0;
        while(runningTime != 0 || !jobsQ.isEmpty() || !waitQ.isEmpty()) {
            while(!jobsQ.isEmpty() && jobsQ.peek().start == time)
                waitQ.offer(jobsQ.poll());
            
            if(runningTime == 0 && !waitQ.isEmpty()) {
                Job job = waitQ.poll();
                runningTime = job.exec;
                jobsReturnTime[job.id] = (time+job.exec) - job.start;
            }
            
            time++;
            if(runningTime != 0) runningTime--;
        }
        
        int sum = 0;
        for(int jrt : jobsReturnTime) {
            sum += jrt;
        }
        answer = sum/N;
                
        return answer;
    }
}
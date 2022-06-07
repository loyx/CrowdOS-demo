package cn.crowdos.demo.entity;

import cn.crowdos.kernel.constraint.Constraint;
import cn.crowdos.kernel.resource.SimpleTask;

import java.util.List;


public class Task extends SimpleTask {
    private int taskId;

    public Task(List<Constraint> constraints, TaskDistributionType taskDistributionType) {
        super(constraints, taskDistributionType);
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}

package cn.crowdos.demo.controller;

import cn.crowdos.demo.entity.Task;
import cn.crowdos.demo.entity.User;
import cn.crowdos.demo.service.DemoService;
import cn.crowdos.kernel.constraint.Constraint;
import cn.crowdos.kernel.constraint.InvalidConstraintException;
import cn.crowdos.kernel.constraint.SimpleTimeConstraint;
import cn.crowdos.kernel.constraint.wrapper.DateCondition;
import cn.crowdos.kernel.constraint.wrapper.IntegerCondition;
import cn.crowdos.kernel.resource.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.DatagramChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/demo")
public class demo {
    final DemoService demoService;

    public demo(DemoService demoService) {
        this.demoService = demoService;
    }

    @PostMapping("submitTask")
    public void submitTask(@RequestBody Map<String, String> taskInfo){
        Task task = makeTask(taskInfo);
        demoService.submitTask(task);
    }

    @PostMapping("registerParticipant")
    public void registerParticipant(@RequestBody Map<String, String> userInfo){
        DateCondition activeTime = null;
        try {
            activeTime = new DateCondition(new SimpleDateFormat("yyyy.MM.dd").parse(userInfo.get("activeTime")).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        IntegerCondition userId = new IntegerCondition(Integer.parseInt(userInfo.get("userId")));
        User user = new User(userId, activeTime);
        demoService.registerParticipant(user);
    }

    @PostMapping("getTaskRecommendation")
    public List<User> getTaskRecommendation(@RequestBody Map<String, String> taskInfo){
        Task task = makeTask(taskInfo);
        return demoService.getTaskRecommendation(task);
    }


    private Task makeTask(Map<String, String> taskInfo) {
        int taskId = Integer.parseInt(taskInfo.get("taskId"));
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        SimpleTimeConstraint simpleTimeConstraint;
        try {

            Date startTime = df.parse(taskInfo.get("startTime"));
            Date endTime = df.parse(taskInfo.get("endTime"));
            simpleTimeConstraint = new SimpleTimeConstraint(startTime, endTime);
        } catch (ParseException | InvalidConstraintException e) {
            throw new RuntimeException(e);
        }
        Task task = new Task(Collections.singletonList(simpleTimeConstraint), cn.crowdos.kernel.resource.Task.TaskDistributionType.RECOMMENDATION);
        task.setTaskId(taskId);
        return task;
    }
}

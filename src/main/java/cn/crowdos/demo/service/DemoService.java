package cn.crowdos.demo.service;

import cn.crowdos.demo.entity.Task;
import cn.crowdos.demo.entity.User;

import java.util.List;

public interface DemoService {
    void submitTask(Task task);
    void registerParticipant(User user);
    List<User> getTaskRecommendation(Task task);
}

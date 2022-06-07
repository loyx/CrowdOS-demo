package cn.crowdos.demo.service;

import cn.crowdos.demo.CrowdKernelComponent;
import cn.crowdos.demo.entity.Task;
import cn.crowdos.demo.entity.User;
import cn.crowdos.kernel.resource.Participant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {
    final CrowdKernelComponent crowdKernelComponent;

    public DemoServiceImpl(CrowdKernelComponent crowdKernelComponent) {
        this.crowdKernelComponent = crowdKernelComponent;
    }

    @Override
    public void submitTask(Task task) {
        crowdKernelComponent.getKernel().submitTask(task);
    }

    @Override
    public void registerParticipant(User user) {
        crowdKernelComponent.getKernel().registerParticipant(user);
    }

    @Override
    public List<User> getTaskRecommendation(Task task) {
        ArrayList<User> users = new ArrayList<>();
        List<Participant> taskRecommendationScheme = crowdKernelComponent.getKernel().getTaskRecommendationScheme(task);
        for (Participant participant : taskRecommendationScheme) {
            users.add((User) participant);
        }
        return users;
    }
}

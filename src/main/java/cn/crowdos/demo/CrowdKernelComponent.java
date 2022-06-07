package cn.crowdos.demo;

import cn.crowdos.kernel.CrowdKernel;
import cn.crowdos.kernel.Kernel;
import org.springframework.stereotype.Component;

@Component
public class CrowdKernelComponent {
    public CrowdKernel getKernel(){
        CrowdKernel kernel = Kernel.getKernel();
        if (!kernel.isInitialed())kernel.initial();
        return kernel;
    }
}

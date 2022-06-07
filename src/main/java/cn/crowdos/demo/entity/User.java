package cn.crowdos.demo.entity;

import cn.crowdos.kernel.constraint.Condition;
import cn.crowdos.kernel.constraint.wrapper.DateCondition;
import cn.crowdos.kernel.constraint.wrapper.IntegerCondition;
import cn.crowdos.kernel.resource.AbstractParticipant;
import cn.crowdos.kernel.resource.ability;
import com.fasterxml.jackson.annotation.JsonFormat;


public class User extends AbstractParticipant {

    @ability
    private IntegerCondition userId;

    @ability
    @JsonFormat(pattern = "yyyy.MM.dd")
    private DateCondition activeTime;

    public User(IntegerCondition userId, DateCondition activeTime) {
        this.userId = userId;
        this.activeTime = activeTime;
        this.status = ParticipantStatus.AVAILABLE;
    }

    public IntegerCondition getUserId() {
        return userId;
    }

    public void setUserId(IntegerCondition userId) {
        this.userId = userId;
    }

    public DateCondition getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(DateCondition activeTime) {
        this.activeTime = activeTime;
    }

    @Override
    public boolean hasAbility(Class<? extends Condition> aClass) {
        return aClass == DateCondition.class;
    }

    @Override
    public Condition getAbility(Class<? extends Condition> aClass) {
        if (!hasAbility(aClass)) return null;
        return activeTime;
    }
}

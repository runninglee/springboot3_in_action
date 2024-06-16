package com.sp3.chapter16.common.schedulers.goodLuck;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GoodLuckJob implements Job {
    private static final List<String> GREETINGS = Arrays.asList(
            "早上好！愿今天的阳光带给你温暖和活力，愿你的一天充满美好与成功！ 🌞",
            "新的一天开始了，愿你带着愉快的心情迎接每一个时刻。早安！ 🌼",
            "早上好！希望今天的你充满干劲，迎接新的挑战。加油！ 🌟",
            "早安！每一天都是新的起点，愿你带着希望和微笑出发。 🌺",
            "早上好！愿你今天充满动力和正能量，成就一个非凡的自己。 💪",
            "早安！新的一天，新的机会，愿你把握每一个美好的瞬间。 🌸",
            "早上好！阳光明媚的一天，愿你心情如阳光般灿烂。 ☀️",
            "早安！愿你今天充满快乐和惊喜，开启美好的一天。 😊",
            "早上好！愿你在今天的每一个时刻，都能感受到温暖和关怀。 💖",
            "早安！新的一天，新希望，愿你带着好心情，迎接每一个精彩瞬间。 🌷"
    );

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println(GREETINGS.get((new Random()).nextInt(GREETINGS.size())));
    }
}

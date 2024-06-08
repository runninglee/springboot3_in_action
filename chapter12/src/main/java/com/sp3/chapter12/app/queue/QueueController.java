package com.sp3.chapter12.app.queue;

import com.sp3.chapter12.app.queue.request.CreateQueueRequest;
import com.sp3.chapter12.job.DelaySendMessageJob;
import com.sp3.chapter12.job.DelayXSendMessageJob;
import com.sp3.chapter12.job.SendMessageJob;
import com.sp3.chapter12.util.api.ResultJson;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/queue")
public class QueueController {

    @Resource
    private SendMessageJob sendMessageJob;

    @Resource
    private DelaySendMessageJob delaySendMessageJob;

    @Resource
    private DelayXSendMessageJob delayXSendMessageJob;

    @GetMapping
    public ResultJson<Object> index() {
        sendMessageJob.dispatch(new CreateQueueRequest(1, "eea8ec75a90d4c6c96cbd676174366e0"));
        return ResultJson.success("发送队列消息了");
    }

    @GetMapping("delay")
    public ResultJson<Object> delay() {
        delaySendMessageJob.dispatch(new CreateQueueRequest(2, "eea8ec75a90d4c6c96cbd676174366e0"),15);
        return ResultJson.success("发送延迟死信队列消息了");
    }

    @GetMapping("x-delay")
    public ResultJson<Object> xDelay() {
        delayXSendMessageJob.dispatch(new CreateQueueRequest(3, "eea8ec75a90d4c6c96cbd676174366e0"), 10);
        return ResultJson.success("发送插件延迟死信队列消息了");
    }
}

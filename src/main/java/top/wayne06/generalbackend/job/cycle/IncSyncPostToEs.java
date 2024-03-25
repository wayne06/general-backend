package top.wayne06.generalbackend.job.cycle;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import top.wayne06.generalbackend.job.SyncPostToEs;
import top.wayne06.generalbackend.mapper.PostMapper;
import top.wayne06.generalbackend.model.entity.Post;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Incremental synchronization of post from DB to ES.
 * TODO add @Component to the class to enable the task
 *
 * @author https://github.com/wayne06
 */
@Slf4j
public class IncSyncPostToEs extends SyncPostToEs {

    @Resource
    private PostMapper postMapper;

    /**
     * execute every minute
     */
    @Scheduled(fixedRate = 60 * 1000)
    public void run() {
        // query data within the past 5 minutes
        Date fiveMinutesAgoDate = new Date(System.currentTimeMillis() - 5 * 60 * 1000L);
        List<Post> postList = postMapper.listPostWithDelete(fiveMinutesAgoDate);
        if (CollUtil.isEmpty(postList)) {
            log.info("no inc post");
            return;
        }
        saveToEs(postList, "IncSyncPostToEs");
    }
}

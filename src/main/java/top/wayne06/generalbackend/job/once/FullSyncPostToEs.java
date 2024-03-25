package top.wayne06.generalbackend.job.once;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import top.wayne06.generalbackend.job.SyncPostToEs;
import top.wayne06.generalbackend.model.entity.Post;
import top.wayne06.generalbackend.service.PostService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Full synchronization of post from DB to ES.
 * TODO add @Component to the class to enable the task
 *
 * @author https://github.com/wayne06
 */
@Slf4j
public class FullSyncPostToEs extends SyncPostToEs implements CommandLineRunner {

    @Resource
    private PostService postService;

    @Override
    public void run(String... args) {
        List<Post> postList = postService.list();
        if (CollUtil.isEmpty(postList)) {
            return;
        }
        saveToEs(postList, "FullSyncPostToEs");
    }
}

package top.wayne06.generalbackend.job;

import lombok.extern.slf4j.Slf4j;
import top.wayne06.generalbackend.esdao.PostEsDao;
import top.wayne06.generalbackend.model.dto.post.PostEsDTO;
import top.wayne06.generalbackend.model.entity.Post;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * save post to elasticsearch
 *
 * @author wayne
 */
@Slf4j
public class SyncPostToEs {

    @Resource
    private PostEsDao postEsDao;

    public void saveToEs(List<Post> postList, String logHeader) {
        List<PostEsDTO> postEsDTOList = postList.stream().map(PostEsDTO::objToDto).collect(Collectors.toList());
        final int pageSize = 500;
        int total = postEsDTOList.size();
        log.info(logHeader + " start, total {}", total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            log.info("sync from {} to {}", i, end);
            postEsDao.saveAll(postEsDTOList.subList(i, end));
        }
        log.info(logHeader + " end, total {}", total);
    }

}

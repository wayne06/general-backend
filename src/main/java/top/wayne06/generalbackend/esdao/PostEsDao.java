package top.wayne06.generalbackend.esdao;

import top.wayne06.generalbackend.model.dto.post.PostEsDTO;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * operate posts in elasticsearch
 *
 * @author wayne06
 */
public interface PostEsDao extends ElasticsearchRepository<PostEsDTO, Long> {

    /**
     * find posts by user id
     *
     * @param userId
     * @return
     */
    List<PostEsDTO> findByUserId(Long userId);
}

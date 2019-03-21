package com.jannchie.biliob.utils.credit;

import com.jannchie.biliob.utils.RedisOps;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/** @author jannchie */
@Component
public class RefreshVideoCreditCalculator extends AbstractCreditCalculator {

  private final RedisOps redisOps;

  @Autowired
  public RefreshVideoCreditCalculator(MongoTemplate mongoTemplate, RedisOps redisOps) {
    super(mongoTemplate);
    this.redisOps = redisOps;
  }

  @Override
  void execute(Long id, ObjectId objectId) {
    redisOps.postVideoCrawlTask(id, objectId);
  }
}

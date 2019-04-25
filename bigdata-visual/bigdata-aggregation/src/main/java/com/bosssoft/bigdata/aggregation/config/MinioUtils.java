package com.bosssoft.bigdata.aggregation.config;

import com.bosssoft.bigdata.aggregation.constant.MinioBucketEnum;
import com.bosssoft.bigdata.common.minio.service.MinioTemplate;
import io.minio.messages.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(value = 1)
public class MinioUtils implements CommandLineRunner {

    @Autowired
    private MinioTemplate template;

    @Override
    public void run(String... args) throws Exception {
        // 获取现存所有bucketname
        List<Bucket> bucketList = template.getAllBuckets();
        List<String> bucketNameList = new ArrayList<>();
        for (Bucket bucket : bucketList) {
            bucketNameList.add(bucket.name());
        }

        MinioBucketEnum[] values = MinioBucketEnum.values();
        for (MinioBucketEnum field : values) {
            String bucketName = field.getName();
            if (!bucketNameList.contains(bucketName)) {
                template.createBucket(bucketName);
            }
        }
    }
}

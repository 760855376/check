package com.odin.TenIndex.service;

import com.odin.base.domain.enumeration.IndexType;
import com.odin.base.service.api.IKnnService;
import com.odin.base.service.dto.KnnDTO;
import com.odin.base.service.dto.result.KnnResultDTO;
import com.odin.TenIndex.domain.TenIndexKnn;
import com.odin.TenIndex.domain.TenIndexVariable;
import com.odin.TenIndex.domain.TenIndexVertex;
import com.odin.TenIndex.service.graph.TenIndexVertexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 * 2022/10/2 zhoutao
 */
@Service
public class QueryIPService implements IKnnService {
    @Autowired
    TenIndexVertexService vertexService;

    TenIndexKnn tenStarKnn;

    public QueryIPService() {
        register();
    }

    @Override
    public void initKnn(int queryName) {
        TenIndexVertex vertex = TenIndexVariable.INSTANCE.getVertex(queryName);
        if (!vertex.isBuiltAncestor()) {
            vertexService.buildAncestor(vertex);
        }
        tenStarKnn = new TenIndexKnn(queryName);

    }

    @Override
    public void knnSearch(int queryName) {
        initKnn(queryName);
        tenStarKnn.knn();
    }

    @Override
    public KnnResultDTO buildResult(KnnDTO knnDTO) {
        return buildResult(tenStarKnn, knnDTO);
    }

    @Override
    public IndexType supportType() {
        return IndexType.TENINDEX;
    }
}

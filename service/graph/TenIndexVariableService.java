package com.odin.TenIndex.service.graph;

import com.odin.base.domain.Node;
import com.odin.base.domain.enumeration.IndexType;
import com.odin.base.service.api.IVariableService;
import com.odin.TenIndex.domain.TenIndexVariable;
import com.odin.TenIndex.domain.TenIndexVertex;
import org.springframework.stereotype.Service;

/**
 * TODO
 * 2022/9/30 zhoutao
 */
@Service
public class TenIndexVariableService implements IVariableService {

    public TenIndexVariableService() {
        register();
    }

    @Override
    public void buildVertex(int vertexName, String clusterName) {
        TenIndexVertex vertex = new TenIndexVertex();
        vertex.setName(vertexName);
        TenIndexVariable.INSTANCE.addVertex(vertex);
    }

    @Override
    public void buildEdge(int vertexName, String[] edgeInfo) {
        TenIndexVertex vertex = TenIndexVariable.INSTANCE.getVertex(vertexName);
        for (int i = 0; i < edgeInfo.length; i += 2) {
            int neighbor = Integer.parseInt(edgeInfo[i]), dis = Integer.parseInt(edgeInfo[i + 1]);
            if (neighbor < vertexName) continue;

            TenIndexVertex neighborVertex = TenIndexVariable.INSTANCE.getVertex(neighbor);
            // add Origion Edge
            vertex.addOrigionEdge(new Node(neighbor, dis));
            neighborVertex.addOrigionEdge(new Node(vertexName, dis));
        }
    }

    @Override
    public IndexType supportType() {
        return IndexType.TENINDEX;
    }
}

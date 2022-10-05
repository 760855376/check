package com.odin.TenIndex.service.graph;

import com.odin.base.domain.Car;
import com.odin.base.domain.GlobalVariable;
import com.odin.TenIndex.domain.TenIndexVariable;
import com.odin.TenIndex.domain.TenIndexVertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 * 2022/10/1 zhoutao
 */
@Service
public class TenIndexActiveService {
    @Autowired
    TenIndexVertexService vertexService;

    public void buildActive() {
        for (Car car : GlobalVariable.CARS) {
            int active = car.getActive();
            TenIndexVertex vertex = TenIndexVariable.INSTANCE.getVertex(active);

            if (!vertex.isActive()) {
                // build active info
                vertex.setActive(true);
                vertexService.buildAncestor(vertex);
            }
            vertex.addCar(car);
        }
    }
}

package com.odin.TenIndex.service.graph;

import com.odin.base.domain.Car;
import com.odin.base.domain.enumeration.IndexType;
import com.odin.base.service.dto.UpdateDTO;
import com.odin.base.service.graph.CarService;
import com.odin.TenIndex.domain.TenIndexVariable;
import com.odin.TenIndex.domain.TenIndexVertex;
import com.odin.TenIndex.service.dto.TenIndexUpdateProcessDTO;
import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * TODO
 * 2022/10/2 zhoutao
 */
@Service
@Getter
public class TenIndexCarService extends CarService {
    private TenIndexUpdateProcessDTO updateProcessDTO;

    public TenIndexCarService() {
        register();
    }

    @Override
    protected void initUpdateDTO(UpdateDTO updateDTO) {
        this.updateProcessDTO = new TenIndexUpdateProcessDTO();
    }

    @Override
    protected void updateActive(int originalActiveName, int activeName, Car car) {
        TenIndexVertex originalVertex = TenIndexVariable.INSTANCE.getVertex(originalActiveName),
                vertex = TenIndexVariable.INSTANCE.getVertex(activeName);

        originalVertex.removeCar(car);
        vertex.addCar(car);
        if (!vertex.isActive()) {
            updateProcessDTO.addToChange2InActiveSet(activeName);
            vertex.setActive(true);
        }
    }

    @Override
    public IndexType supportType() {
        return IndexType.TENINDEX;
    }
}

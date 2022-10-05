package com.odin.TenIndex.service;

import com.odin.base.domain.GlobalVariable;
import com.odin.base.domain.api.Vertex;
import com.odin.base.domain.enumeration.IndexType;
import com.odin.base.service.api.IndexService;
import com.odin.TenIndex.domain.TenIndexVariable;
import com.odin.TenIndex.domain.TenIndexVertex;
import com.odin.TenIndex.service.dto.TenIndexUpdateProcessDTO;
import com.odin.TenIndex.service.graph.TenIndexActiveService;
import com.odin.TenIndex.service.graph.TenIndexCarService;
import com.odin.TenIndex.service.graph.TenIndexVertexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * TODO
 * 2022/9/30 zhoutao
 */
@Service
public class TenIndexIndexService extends IndexService {
    @Autowired
    private TenIndexVertexService vertexService;

    @Autowired
    private TenIndexActiveService activeService;

    private Stack<Integer> treeStack;

    private TenIndexVertex root;

    public TenIndexIndexService() {
        register();
    }

    @Override
    protected void build() {
        buildStack();
        buildTree();
        activeService.buildActive();
        buildCKNN();
    }

    @Override
    protected void update() {
        TenIndexUpdateProcessDTO processDTO = ((TenIndexCarService) carService).getUpdateProcessDTO();
        for (Integer name : processDTO.getChange2InActiveSet()) {
            TenIndexVariable.INSTANCE.getVertex(name).buildCKNN();
        }
    }

    @Override
    public IndexType supportType() {
        return IndexType.TENINDEX;
    }

    //构造索引
    private void buildStack() {
        treeStack = new Stack<>();
        int minDegree = 1;
        HashSet<Integer> allKeys = TenIndexVariable.INSTANCE.getVertices()
                .stream().map(Vertex::getName).collect(Collectors.toCollection(HashSet::new));

        while (!allKeys.isEmpty()) {
            Iterator<Integer> allKeysIter = allKeys.iterator();

            while (allKeysIter.hasNext()) {
                Integer vertexName = allKeysIter.next();
                TenIndexVertex vertex = TenIndexVariable.INSTANCE.getVertex(vertexName);
                if (vertex.getDegree() <= minDegree) {
                    vertexService.buildClique(vertex);                    //构建团

                    treeStack.add(vertexName);                //进栈
                    allKeysIter.remove();
                }
            }
            minDegree++;
        }
    }

    private void buildTree() {
        //栈顶作为根节点
        root = TenIndexVariable.INSTANCE.getVertex(treeStack.pop());
        root.setRoot();
        //构建树
        while (!treeStack.empty()) {
            //如果栈不为空
            Integer vertexName = treeStack.pop();                       //出栈
            TenIndexVertex vertex = TenIndexVariable.INSTANCE.getVertex(vertexName);
            vertex.setParent();                                        //寻找父亲
        }
    }

    private void buildCKNN() {
        HashSet<Integer> allKeys = TenIndexVariable.INSTANCE.getVertices()
                .stream().map(Vertex::getName).collect(Collectors.toCollection(HashSet::new));
        int maxTreeLevel = 0;
        //找到最大的层高
        for (int i = 0; i < GlobalVariable.VERTEX_NUM; i++) {
            if (TenIndexVariable.INSTANCE.getVertex(i).getTreeLevel() > maxTreeLevel) {
                maxTreeLevel = TenIndexVariable.INSTANCE.getVertex(i).getTreeLevel();
            }
        }
        System.out.println("MaxTreeLevel=" + maxTreeLevel);
        while (!allKeys.isEmpty()) {
            Iterator<Integer> allKeysIter = allKeys.iterator();

            while (allKeysIter.hasNext()) {
                Integer Name = allKeysIter.next();
                TenIndexVertex vertex = TenIndexVariable.INSTANCE.getVertex(Name);
                //当层高等于最大层高时
                if (vertex.getTreeLevel() == maxTreeLevel) {

                    vertex.buildCKNN();
                    allKeysIter.remove();
                }
            }
            maxTreeLevel--;
        }
    }


}

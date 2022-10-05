package com.odin.TenIndex.domain;

import com.odin.base.domain.Node;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 * 2022/10/1 zhoutao
 */
@Getter
@Setter
public class TenIndexNode extends Node {
    private int index;

    public TenIndexNode(Node node) {
        this.setNode(node);
    }

    public TenIndexNode(int name) {
        this.setName(name);
        this.setDis(0);
    }
}

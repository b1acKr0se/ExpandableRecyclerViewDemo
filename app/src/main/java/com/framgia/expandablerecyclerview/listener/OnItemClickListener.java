package com.framgia.expandablerecyclerview.listener;

import com.framgia.expandablerecyclerview.model.ChildItem;
import com.framgia.expandablerecyclerview.model.ParentItem;

public interface OnItemClickListener {
    void onParentItemClick(ParentItem parentItem);
    void onChildItemClick(ChildItem childItem);
}

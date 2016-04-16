package com.framgia.expandablerecyclerview.model;

import java.util.ArrayList;
import java.util.List;

public class ParentItem {
    private String mTitle;
    private boolean mExpanded;
    private List<ChildItem> mChildItemList;

    public ParentItem(String title, boolean expanded) {
        this.mTitle = title;
        this.mExpanded = expanded;
        this.mChildItemList = new ArrayList<>();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public boolean isExpanded() {
        return mExpanded;
    }

    public void setExpanded(boolean expanded) {
        this.mExpanded = expanded;
    }

    public List<ChildItem> getChildItemList() {
        return mChildItemList;
    }

    public void setChildItemList(List<ChildItem> childItemList) {
        this.mChildItemList.clear();
        this.mChildItemList.addAll(childItemList);
    }
}

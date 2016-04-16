package com.framgia.expandablerecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.expandablerecyclerview.R;
import com.framgia.expandablerecyclerview.listener.OnItemClickListener;
import com.framgia.expandablerecyclerview.model.ChildItem;
import com.framgia.expandablerecyclerview.model.ParentItem;

import java.util.List;

public class ExpandableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_PARENT = 0;
    public static final int VIEW_CHILD = 1;
    private List<Object> mItemList;
    private OnItemClickListener mOnItemClickListener;

    public ExpandableAdapter(List<Object> list) {
        this.mItemList = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mItemList.get(position) instanceof ParentItem)
            return VIEW_PARENT;
        return VIEW_CHILD;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_PARENT)
            return new ParentViewHolder(parent);
        return new ChildViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mItemList.get(position) instanceof ParentItem) {
            ParentItem parentItem = (ParentItem) mItemList.get(position);
            ParentViewHolder viewHolder = (ParentViewHolder) holder;
            viewHolder.parentItem = parentItem;
            viewHolder.titleTextView.setText(parentItem.getTitle());
            if (parentItem.isExpanded())
                viewHolder.expandButtonImage.setImageResource(R.drawable.ic_chevron_up_grey600_24dp);
            else
                viewHolder.expandButtonImage.setImageResource(R.drawable.ic_chevron_down_grey600_24dp);
            viewHolder.expandButton.setOnClickListener(viewHolder);
        } else {
            ChildItem childItem = (ChildItem) mItemList.get(position);
            ChildViewHolder viewHolder = (ChildViewHolder) holder;
            viewHolder.childItem = childItem;
            viewHolder.mTitleTextView.setText(childItem.getName());
            viewHolder.itemView.setOnClickListener(viewHolder);
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ParentItem parentItem;
        public TextView titleTextView;
        public View expandButton;
        public ImageView expandButtonImage;

        public ParentViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.text_title);
            expandButton = itemView.findViewById(R.id.button_expand);
            expandButtonImage = (ImageView) itemView.findViewById(R.id.image_expand);
            expandButton.setOnClickListener(this);
        }

        public void animateExpandButton(boolean isExpanded) {
            RotateAnimation rotateAnimation = new RotateAnimation(30, 90,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            expandButtonImage.startAnimation(rotateAnimation);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener == null || parentItem == null)
                return;
            animateExpandButton(parentItem.isExpanded());
            mOnItemClickListener.onParentItemClick(parentItem);
        }
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ChildItem childItem;
        private TextView mTitleTextView;

        public ChildViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.text_title);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener == null | childItem == null)
                return;
            mOnItemClickListener.onChildItemClick(childItem);
        }
    }

}

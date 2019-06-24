package com.virudhairaj.feedcore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;


public abstract class AbsFeedAdapter<Holder extends AbsFeedHolder> extends RecyclerView.Adapter<Holder> {
    private ArrayList<? extends AbsFeed> feeds;

    protected Context context;
    /**
     * arguments that pass when call onBindViewHolder.  this variable used for communication in view holder to external classes
     */
    protected Args args;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setFeeds(ArrayList<? extends AbsFeed> feeds) {
        this.feeds = feeds != null ? feeds : new ArrayList();
        notifyDataSetChanged();
    }

    public void addAllAndNotify(int index, ArrayList data) {
        try {
            if (getItemCount() > 0 && index > 0 && index < getItemCount()) {
                int count = (getItemCount() - (index + 1)) + data.size();
                feeds.addAll(index, data);
                notifyItemRangeInserted(index, count);
            } else {
                int start = feeds.size();
                feeds.addAll(data);
                notifyItemRangeInserted(start, data.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAllAndNotify(ArrayList<? extends AbsFeed> data) {
        addAllAndNotify(-1, data);
    }

    public void clearAndNotify() {
        int totalCount = feeds.size();
        feeds.clear();
        notifyItemRangeRemoved(0, totalCount);
    }

    public void removeAndNotify(int position) {
        try {
            Object removedObject = feeds.remove(position);
            if (removedObject != null) notifyItemRemoved(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * this function used to add list of bind arguments. we can pass any type(object, enum, interface implementation) of arguments. these arguments will delivered to onItemBind method in viewholder class.
     *
     * @param args
     */
    public void addBindArgs(Object... args) {
        if (args == null) return;
        if (this.args == null) this.args = new Args();
        this.args.addAll(args);
    }

    /***
     * Returns current feed list
     * @return
     */
    public ArrayList<? extends AbsFeed> getFeeds() {
        return feeds != null ? feeds : new ArrayList<AbsFeed>();
    }

    /***
     * cannot override this function in derived class
     * please use onGetItemCount(int count)
     * @return
     */
    @Override
    final public int getItemCount() {
        int count = feeds != null ? feeds.size() : 0;
        onGetItemCount(count);
        return count;
    }


    /***
     * this callback invoked when onGetItemCount. if you want to listen this, then override this function
     * @param itemCount
     */
    protected void onGetItemCount(int itemCount) {

    }

    /**
     * You already defined ItemView type in class extended from AbsFeed class. so lets reuse it. And this function won't available in its sub-classes
     *
     * @param position
     * @return
     */
    @Override
    final public int getItemViewType(int position) {
        return feeds.get(position).getFeedType().getValue();
    }

    @NonNull
    @Override
    final public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        final AbsFeed feed = feeds.get(position);
        return HolderBuilder.with(feed.getFeedType().getHolderClass())
                .setContext(context)
                .setParent(viewGroup).build();
    }

    @Override
    final public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (args != null)
            holder.onItemBind(feeds.get(position), position, args.toArray());
        else holder.onItemBind(feeds.get(position), position);
    }
}

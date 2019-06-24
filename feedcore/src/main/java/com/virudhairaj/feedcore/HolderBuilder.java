package com.virudhairaj.feedcore;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;

/**
 * this class will invoked internally
 */
public class HolderBuilder {
    private final @NonNull
    Class<? extends AbsFeedHolder> holderClass;
    private Context context;
    private ViewGroup parent;


    private HolderBuilder(@NonNull Class<? extends AbsFeedHolder> holderClass) {
        this.holderClass = holderClass;
    }

    public static HolderBuilder with(@NonNull Class<? extends AbsFeedHolder> holderClass) {
        return new HolderBuilder(holderClass);
    }

    public HolderBuilder setContext(Context context) {
        this.context = context;
        return this;
    }

    public HolderBuilder setParent(@NonNull ViewGroup parent) {
        this.parent = parent;
        return this;
    }

    public <T extends AbsFeedHolder> T build() {
        final LayoutResource layoutResource = holderClass.getAnnotation(LayoutResource.class);
        if (layoutResource == null)
            throw new RuntimeException("'@LayoutResource(resourceId = R.layout.layout_res_id, attachToRoot = false)' annotation is not defined in class " + holderClass.getCanonicalName());
        final @LayoutRes int layoutResId = layoutResource.resourceId();
        if (layoutResId == 0)
            throw new RuntimeException("must specify layout resource id like  '@LayoutResource(resourceId = R.layout.layout_feed_video)' in " + holderClass.getCanonicalName());
        if (parent == null) throw new RuntimeException("parent viewGroup must not be null");

        Constructor constructor = null;
        try {
            constructor = holderClass.getConstructor(
                    Context.class,
                    int.class,
                    ViewGroup.class,
                    boolean.class
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (constructor == null)
            throw new RuntimeException("constructor is not defined\n " + holderClass.getSimpleName() + "(@NonNull Context context, @LayoutRes int layoutResId, @NonNull ViewGroup parent, boolean attachToRoot)");
        if (context == null) context = parent.getContext();
        final boolean attachToRoot = layoutResource.attachToRoot();

        T out = null;
        try {
            out = (T) holderClass.cast(constructor.newInstance(context, layoutResId, parent, attachToRoot));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return out;
    }
}

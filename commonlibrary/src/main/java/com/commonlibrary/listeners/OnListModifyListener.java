package com.commonlibrary.listeners;

import java.util.Collection;

/**
 * Created by Asif on 2/25/2016.
 */
public abstract class OnListModifyListener
{
    public void onAdd(Object value){}
    public void onRemove(Object value){}
    public void onAddAll(Collection collection){}
    public void onClearAll(boolean isClearAll){}
    public void onDirty(boolean isDirty){}
    public void onEmpty(boolean isEmpty){}
    public void TotalCount(int count){}
}

package com.commonlibrary.collections;

import android.support.annotation.NonNull;

import com.commonlibrary.listeners.OnListModifyListener;
import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by Asif on 2/25/2016.
 */
public class ExCollection<T> extends ArrayList<T> implements Serializable
{
    private static final String FIELD_ID = "id";
    private static final String FIELD_SELECTED = "selected";
    private OnListModifyListener onListModifyListener;
    public void setOnListModifyListener(OnListModifyListener listModifyListener)
    {
        onListModifyListener = listModifyListener;
    }

    ArrayList<T> arrayList = new ArrayList<T>();

    //Class<T> type;

    public ExCollection()
    {
        //this.type = type;
    }

    /*public Class<T> getMyType()
    {
        return this.type;
    }*/

    @Override
    public T get(int index) {
        return arrayList.get(index);
    }

    @Override
    public T set(int index, T object) {
        return arrayList.set(index,object);
    }

    @Override
    public boolean add(T object)
    {
        boolean status = arrayList.add(object);
        if(onListModifyListener != null)
        {
            onListModifyListener.onAdd(object);
            onListModifyListener.onDirty(true);
            onListModifyListener.TotalCount(arrayList.size());
        }
        return status;
    }

    @Override
    public void add(int index, T object) {
        arrayList.add(index,object);
        if(onListModifyListener != null)
        {
            onListModifyListener.onAdd(object);
            onListModifyListener.onDirty(true);
            onListModifyListener.onEmpty(arrayList.isEmpty());
            onListModifyListener.TotalCount(arrayList.size());
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> collection)
    {
        boolean status = arrayList.addAll(collection);
        if(onListModifyListener != null)
        {
            onListModifyListener.onAddAll(collection);
            onListModifyListener.onDirty(true);
            onListModifyListener.onEmpty(arrayList.isEmpty());
            onListModifyListener.TotalCount(arrayList.size());
        }
        return status;
    }

    @Override
    public void clear()
    {
        arrayList.clear();
        if(onListModifyListener != null)
        {
            onListModifyListener.onClearAll(true);
            onListModifyListener.onDirty(true);
            onListModifyListener.TotalCount(arrayList.size());
        }

    }

    @Override
    public boolean contains(Object object)
    {
        return arrayList.contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> collection)
    {
        return arrayList.containsAll(collection);
    }

    @Override
    public boolean isEmpty()
    {
        return arrayList.isEmpty();
    }

    @NonNull
    @Override
    public Iterator<T> iterator()
    {
        return arrayList.iterator();
    }

    @Override
    public boolean remove(Object object)
    {
        boolean status = arrayList.remove(object);
        if(onListModifyListener != null)
        {
            onListModifyListener.onRemove(object);
            onListModifyListener.onDirty(true);
            onListModifyListener.onEmpty(arrayList.isEmpty());
            onListModifyListener.TotalCount(arrayList.size());
        }
        return status;
    }

    @Override
    public T remove(int index) {
        T obj = arrayList.remove(index);
        if(onListModifyListener != null)
        {
            onListModifyListener.onRemove(obj);
            onListModifyListener.onDirty(true);
            onListModifyListener.onEmpty(arrayList.isEmpty());
        }

        return obj;
    }

    @Override
    public boolean removeAll(Collection<?> collection)
    {
        boolean status = arrayList.removeAll(collection);
        if(onListModifyListener != null)
        {
            onListModifyListener.onDirty(true);
            onListModifyListener.onEmpty(arrayList.isEmpty());
            onListModifyListener.TotalCount(arrayList.size());
        }
        return status;
    }



    @Override
    public boolean retainAll(Collection<?> collection)
    {
        return arrayList.retainAll(collection);
    }

    @Override
    public int size()
    {
        return arrayList.size();
    }

    @NonNull
    @Override
    public Object[] toArray()
    {
        return arrayList.toArray();
    }

    @NonNull
    @Override
    public <T1> T1[] toArray(T1[] array)
    {
        return arrayList.toArray(array);
    }

    public ExCollection<T> getAllFilteredItems(String fieldToSearch, String value, @SEARCH_OPERATOR String operator)
    {
        ExCollection<T> selectedArrayList = null;

        for (int i = 0; i < arrayList.size(); i++)
        {
            Field[] fields = arrayList.get(i).getClass().getDeclaredFields();

            for (Field field : fields)
            {
                try
                {
                    field.setAccessible(true);

                    if (field.getName().equals(fieldToSearch)) {
                        field.setAccessible(true);
                        field.get(arrayList.get(i));
                        if (operator.compareTo(SEARCH_OPERATOR.EQUALS) == 0) {
                            if (field.get(arrayList.get(i)).toString().compareTo(value) == 0) {
                                if (selectedArrayList != null) {
                                    selectedArrayList.add(arrayList.get(i));
                                } else {
                                    selectedArrayList = new ExCollection<T>();
                                    selectedArrayList.add(arrayList.get(i));
                                }
                                break;
                            }
                        } else if (operator.compareTo(SEARCH_OPERATOR.NOT) == 0) {
                            if (field.get(arrayList.get(i)).toString().compareTo(value) != 0) {
                                if (selectedArrayList != null) {
                                    selectedArrayList.add(arrayList.get(i));
                                } else {
                                    selectedArrayList = new ExCollection<T>();
                                    selectedArrayList.add(arrayList.get(i));
                                }
                                break;
                            }
                        }

                    }
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }

        }

        /*if (selectedArrayList != null)
        {
            Log.e(getMyType().getName() + " Size", String.valueOf(selectedArrayList.size()));
        }*/

        return selectedArrayList;
    }

    public ExCollection<T> getAllSelectedItems()
    {
        ExCollection<T> selectedArrayList = null;

        for (int i = 0; i < arrayList.size(); i++)
        {
            Field[] fields = arrayList.get(i).getClass().getDeclaredFields();

            for (Field field : fields)
            {
                try
                {
                    field.setAccessible(true);

                    if (field.getName().equals(FIELD_SELECTED))
                    {
                        field.setAccessible(true);
                        field.get(arrayList.get(i));

                        if ((boolean) (field.get(arrayList.get(i))))
                        {
                            if (selectedArrayList != null)
                            {
                                selectedArrayList.add(arrayList.get(i));
                            }
                            else
                            {
                                selectedArrayList = new ExCollection<T>();
                                selectedArrayList.add(arrayList.get(i));
                            }
                            break;
                        }
                    }
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }

        }

        /*if (selectedArrayList != null)
        {
            Log.e(getMyType().getName() + " Size", String.valueOf(selectedArrayList.size()));
        }*/

        return selectedArrayList;
    }

    public T getItemByID(int id)
    {
        T found = null;
        for (int i = 0; i < arrayList.size(); i++)
        {
            Field[] fields = arrayList.get(i).getClass().getDeclaredFields();

            for (Field field : fields)
            {
                try
                {
                    field.setAccessible(true);

                    if (field.getName().equals(FIELD_ID))
                    {
                        field.setAccessible(true);
                        if(Integer.parseInt(field.get(arrayList.get(i)).toString()) == id)
                        {
                            found = arrayList.get(i);
                            break;
                        }

                    }
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }

            if(found != null)
                break;

        }


        return found;
    }

    public ArrayList<T> getFilterItemsByName(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());

        ArrayList<T> allItemsList = arrayList;
        clear();

        if (charText.length() == 0)
        {
            addAll(allItemsList);
        }
        else
        {
            for (int i = 0; i < allItemsList.size(); i++)
            {
                // if (allItemsList.get(i).get.toLowerCase(Locale.getDefault()).startsWith(charText)) {
                // arrayList.add(allItemsList.get());
                // }
            }
        }
        return null;
    }

    public ExCollection<Integer> getIds()
    {
        ExCollection<Integer> integers = new ExCollection<>();
        for(int index = 0; index<arrayList.size();index++)
        {
            Field[] fields = arrayList.get(index).getClass().getDeclaredFields();

            for (Field field : fields)
            {
                try
                {
                    field.setAccessible(true);

                    if (field.getName().equals(FIELD_ID))
                    {
                        field.setAccessible(true);
                        integers.add(Integer.parseInt(field.get(arrayList.get(index)).toString()));
                        break;
                    }
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return integers;
    }

    public String toJson()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}

package org.example;

import java.util.Objects;

public class StringListImpl implements StringList {

    private static final int Initial_Size = 15;

    private final String[] data;

    private int capacity;

    public StringListImpl() {
        this (Initial_Size);
    }

    public StringListImpl(int n) {
        if (n <= 0){
            throw new IllegalArgumentException("Размер списка не может быть отрицательным !");
        }
        data = new String[n];
        capacity = 0;
    }



    @Override
    public String add(String item) {
        return add(capacity,item);
    }

    @Override
    public String add(int index, String item) {
        if (capacity>= data.length){
            throw new IllegalArgumentException("Список полный!");
        }
        checkNotNull(item);
        checkNonNegativeIndex(index);
        checkIndex(index,false);
        System.arraycopy(data,index,data,index+1,capacity-index);
        data[index]=item;
        capacity++;
        return item;
    }




    @Override
    public String set(int index, String item) {
        checkNotNull(item);
        checkNonNegativeIndex(index);
        checkIndex(index,true);
        return data[index] = item;
    }

    @Override
    public String remove(String item) {
        int indexForRemoving = indexOf(item);
        if (indexForRemoving == -1){
            throw new IllegalArgumentException("Элемеент не найден");
        }
        return remove(indexForRemoving);
    }

    @Override
    public String remove(int index) {
        checkNonNegativeIndex(index);
        checkIndex(index,true);
        String removed = data[index];
        System.arraycopy(data,index+1,data,index,capacity-1-index);
        data[--capacity] = null;
        return removed;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        checkNotNull(item);
        int index = -1;
        for (int i = 0; i < capacity; i++) {
            if (data[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(String item) {
        checkNotNull(item);
        int index = -1;
        for (int i = capacity-1; i >=0; i--) {
            if (data[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;

    }

    @Override
    public String get(int index) {
        checkNonNegativeIndex(index);
        checkIndex(index,true);
        return data[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (size() != otherList.size()){
            return false;
        }
        for (int i = 0; i < capacity; i++) {
            if (!data[i].equals(otherList.get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            data[i] = null;
        }
        capacity = 0;
    }

    @Override
    public String[] toArray() {
        String[] result = new String[capacity];
        System.arraycopy(data,0,result,0,capacity);
        return result;
    }


    private void checkNotNull(String item) {
        if(Objects.isNull(item)) {
            throw new IllegalArgumentException("В списке не должно быть null.");
        }
    }

    private void checkNonNegativeIndex(int index) {
        if (index < 0){
            throw new IllegalArgumentException("Индекс не должен быть отрицательным.");
        }
    }

    private void checkIndex(int index, boolean includeEquality) {
        boolean expersion = includeEquality ? index >= capacity: index > capacity;
        if (expersion) throw new IllegalArgumentException("Индекс:" + index + ", Размер:" + capacity);
    }
}

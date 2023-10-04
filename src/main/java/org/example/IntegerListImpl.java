package org.example;

import java.util.Objects;

public class IntegerListImpl implements IntegerList {

    private static final int Initial_Size = 15;

    private  Integer[] data;

    private int capacity;

    public IntegerListImpl() {
        this (Initial_Size);
    }

    public IntegerListImpl(int n) {
        if (n <= 0){
            throw new IllegalArgumentException("Размер списка не может быть отрицательным !");
        }
        data = new Integer[n];
        capacity = 0;
    }



    @Override
    public Integer add(Integer item) {
        return add(capacity,item);
    }

    private void grow(){
        Integer[] newData = new Integer[(int)(1.5*data.length)];
        System.arraycopy(data,0,newData,0,capacity);
        data = newData;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (capacity>= data.length){
            grow();
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
    public Integer set(int index, Integer item) {
        checkNotNull(item);
        checkNonNegativeIndex(index);
        checkIndex(index,true);
        return data[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        int indexForRemoving = indexOf(item);
        if (indexForRemoving == -1){
            throw new IllegalArgumentException("Элемеент не найден");
        }
        return remove(indexForRemoving);
    }

    @Override
    public Integer remove(int index) {
        checkNonNegativeIndex(index);
        checkIndex(index,true);
        Integer removed = data[index];
        System.arraycopy(data,index+1,data,index,capacity-1-index);
        data[--capacity] = null;
        return removed;
    }

    @Override
    public boolean contains(Integer item) {
        checkNotNull(item);

        Integer[] arrayForSearch = toArray();
        quickSort(arrayForSearch,0,arrayForSearch.length - 1);

        int min = 0;
        int max = arrayForSearch.length - 1;
        while (min <= max){
            int mid = (min + max) / 2;
            if (item.equals(arrayForSearch[mid])){
                return true;
            }
            if (item < arrayForSearch[mid]){
                max = mid -1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
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
    public int lastIndexOf(Integer item) {
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
    public Integer get(int index) {
        checkNonNegativeIndex(index);
        checkIndex(index,true);
        return data[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
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
    public Integer[] toArray() {
        Integer[] result = new Integer[capacity];
        System.arraycopy(data,0,result,0,capacity);
        return result;
    }


    public  void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private  int partition(Integer[] arr, int begin, int end) {
        Integer pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, end);
        return i + 1;
    }


    private  void swap(Integer[] arr, int left, int right) {
        Integer temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private void checkNotNull(Integer item) {
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

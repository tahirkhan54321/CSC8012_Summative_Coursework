import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<E>> {

    ArrayList<E> arrayList;

    public SortedArrayList(ArrayList<E> arrayList) {
        this.arrayList = arrayList;
    }


    public ArrayList sortedArrayList(ArrayList<E> arrayList, E addToList) {
        ArrayList<E> newSortedArrayList = new ArrayList<>();
        if (arrayList.size() == 0) {
            newSortedArrayList.add(addToList);
        } else {
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).compareTo(addToList) < 0) { //TODO: is this the right way around?
                    newSortedArrayList.add(arrayList.get(i));
                }
                if (arrayList.get(i).compareTo(addToList) == 0) {
                    //TODO: this either does nothing or maybe prints letter?
                }
                if (arrayList.get(i).compareTo(addToList) > 0) { //TODO: is this the right way around?
                    newSortedArrayList.add(addToList);
                }
            }
        }
// list.add could work as an alternative, would this be more memory efficient?
        return newSortedArrayList;
    }

}

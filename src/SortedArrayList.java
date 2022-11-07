import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<E>> extends ArrayList {

    public SortedArrayList<E> addElement(SortedArrayList<E> list, E addToList) {
        if (list.size() == 0) {
            list.add(addToList);
            //System.out.println("First element has been added");
            //System.out.println(list);
        } else {
            //System.out.println("size is: " + list.size()); this would suggest that I'm running the below on the wrong thing (i.e. not this.aL)
            for (int i = 0; i < list.size(); i++) {
                if (addToList.compareTo((E) list.get(i)) < 0) { //TODO: is this the right way around?
                    //System.out.println(addToList + " is less than " + list.get(i));
                    list.add(i, addToList);
                    //System.out.println(list);
                    break;
                }
                //if (this.aL.get(i).compareTo(addToList) == 0) {
                //TODO: this either does nothing or maybe prints letter?
                //}
                else if (addToList.compareTo((E) list.get(i)) > 0 && i == list.size()-1) { //TODO: is this the right way around?
                    /* the conditions above can only be met if we've looped through the entire existing arraylist
                        if we've not found anything that addToList is less than,
                        then we can assume it can be added to the end of the list
                     */
                    //System.out.println(addToList + " is greater than " + list.get(i));
                    list.add(i+1, addToList);
                    //System.out.println(list);
                    break;
                }
            }
            //}
// list.add could work as an alternative, would this be more memory efficient?
        }
        return list;
    }
}

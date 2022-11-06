import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<E>> extends ArrayList {

    private ArrayList<E> aL; //the structure of this object only has an arraylist of generic objects

    public SortedArrayList(ArrayList<E> aL) {
        super();
        this.aL = aL; //the sortedarraylist object takes in an arraylist
    }

    public ArrayList<E> addElement(ArrayList<E> list, E addToList) {
        //SortedArrayList<E> newSortedArrayList = new SortedArrayList<E>(new ArrayList<E>());
        /* if (this.aL.size() == 0) {
            //newSortedArrayList.add(addToList);
            this.aL.add(addToList);
        } else { */
        System.out.println("size is: " + list.size()); // this would suggest that I'm running the below on the wrong thing (i.e. not this.aL)
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).compareTo(addToList) < 0) { //TODO: is this the right way around?
                    //newSortedArrayList.add(this.aL.get(i));
                    //this.aL.add(this.aL.get(i));
                    System.out.println("<");
                    list.add(i, addToList);
                    break;
                }
                //if (this.aL.get(i).compareTo(addToList) == 0) {
                    //TODO: this either does nothing or maybe prints letter?
                //}
                else if (list.get(i).compareTo(addToList) > 0) { //TODO: is this the right way around?
                    //newSortedArrayList.add(addToList);
                    //this.aL.add(addToList);
                    System.out.println(">");
                    list.add(i, addToList);
                    break;
                }
            }
        //}
// list.add could work as an alternative, would this be more memory efficient?
        return list;
    }

}

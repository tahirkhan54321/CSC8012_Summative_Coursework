import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<E>> extends ArrayList {

    public SortedArrayList<E> addElement(SortedArrayList<E> list, E addToList) {
        if (list.size() == 0) {
            list.add(addToList);
            /*  print statements for debugging and testing
                System.out.println("First element has been added");
                System.out.println(list);
             */

        } else {
            /* print statement for debugging
               System.out.println("size is: " + list.size());
             */
            for (int i = 0; i < list.size(); i++) {
                if (addToList.compareTo((E) list.get(i)) < 0) {
                    list.add(i, addToList);
                    /* print statements for debugging and testing
                       System.out.println(addToList + " is less than " + list.get(i));
                       System.out.println(list);
                     */
                    break;
                }
                /* the conditions below can only be met if we've looped through the entire existing arraylist
                   if we've not found anything that addToList is less than,
                   then we can assume it can be added to the end of the list
                */
                else if (addToList.compareTo((E) list.get(i)) > 0 && i == list.size() - 1) {
                    list.add(i+1, addToList);
                    /* print statements for debugging and testing
                       System.out.println(addToList + " is greater than " + list.get(i));
                       System.out.println(list);
                     */
                    break;
                }
                //(this.aL.get(i).compareTo(addToList) == 0) this case can never happen due to the assumptions in the problem
            }
        }
        return list;
    }
}

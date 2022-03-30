package com.hepsiburada.marsrover.util;

import com.hepsiburada.marsrover.base.DoublyLinkedList;
import com.hepsiburada.marsrover.model.Heading;

public class HeadingUtil {
    public static DoublyLinkedList<Heading> headings = new DoublyLinkedList<>();
    static {
        headings.add(Heading.NORTH);
        headings.add(Heading.EAST);
        headings.add(Heading.SOUTH);
        headings.add(Heading.WEST);
    }
}

package com.hepsiburada.marsrover.base;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Node<T> {
    private Node<T> previous;
    private T data;
    private Node<T> next;
}

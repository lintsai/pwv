package com.innova.pwv.util;

public record ValidPair<L, R>(L left, R right) {

    @Override
    public int hashCode() {
        return left.hashCode() ^ right.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ValidPair pairo)) return false;
        return this.left.equals(pairo.left()) && this.right.equals(pairo.right());
    }

}

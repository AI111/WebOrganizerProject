package com.example.weborganizer.Containers;

/**
 * Created by Sasha on 10.11.13.
 */
public class Filter {
    public int filterId;
    public String filterName;
    public  Filter(int filterId,String filterName){
        this.filterId=filterId;
        this.filterName=filterName;
    }



    @Override
    public String toString() {
        return filterId+"  "+filterName;
    }
}

